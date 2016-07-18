package com.newrelic.airline.reservations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.newrelic.airline.reservations.forms.CitiesServedResponse;
import com.newrelic.airline.reservations.forms.CreateReservationResponse;
import com.newrelic.airline.reservations.forms.FindReservationRequest;
import com.newrelic.airline.reservations.forms.FindReservationsResponse;
import com.newrelic.airline.reservations.forms.FlightDate;
import com.newrelic.airline.reservations.forms.FlightSearchRequest;
import com.newrelic.airline.reservations.forms.FlightSearchResults;
import com.newrelic.airline.reservations.forms.GetCitiesRequest;
import com.newrelic.airline.reservations.forms.ReservationDetails;
import com.newrelic.airline.reservations.forms.ReservationRequest;

public class TestSocket {

	String confirmationNum;
	
	public static void main(String[] args) {
		TestSocket test = new TestSocket();
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2014, Calendar.DECEMBER, 4);
		Date out = cal.getTime();
		cal.set(2014, Calendar.DECEMBER, 8);
		Date ret = cal.getTime();
		test.findFlights("San Francisco", "Portland", out , ret);
		test.makeReservation("San Francisco", "Portland", out , ret);
		test.listReservation();
//		test.findFlights("San Francisco", "Portland", out , ret);
//		test.listReservations();
		test.listReservations("Doug","Hilpipre");
		test.getCities();
		
		
	}
	
	public void getCities() {
		GetCitiesRequest request = new GetCitiesRequest();
		Socket socket = null;
		try {
			socket = new Socket("localhost", 6500);
			ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
			outStream.writeObject(request);
			
			ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
			Object obj = inStream.readObject();
			
			if(obj.getClass().isArray()) {
				String[] cities = (String[])obj;
				StringBuffer sb = new StringBuffer("New Relic Airlines Serves: ");
				for(int i=0;i<cities.length;i++) {
					sb.append(cities[i]+", ");
				}
				System.out.println(sb.toString());
			} else if(CitiesServedResponse.class.isInstance(obj)) {
				CitiesServedResponse resp = (CitiesServedResponse)obj;
				String[] cities = resp.getCitiesServed();
				StringBuffer sb = new StringBuffer("New Relic Airlines Serves: ");
				for(int i=0;i<cities.length;i++) {
					sb.append(cities[i]+", ");
				}
				System.out.println(sb.toString());
			}
		} catch(Exception e) {
			
		}
	}
	
	public void listReservations(String first, String last) {
		FindReservationRequest request = new FindReservationRequest(first, last, null);
		Socket socket = null;
		try {
			socket = new Socket("localhost", 6500);
			ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
			
			outStream.writeObject(request);
			
			ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
			Object obj = inStream.readObject();
			if(List.class.isInstance(obj)) {
				List<ReservationDetails> list = (List<ReservationDetails>)obj;
				for(int i=0;i<list.size();i++) {
					ReservationDetails details = list.get(i);
					System.out.println(details);
				}
			} else if(ReservationDetails.class.isInstance(obj)) {
				System.out.println((ReservationDetails)obj);
			} else if(FindReservationsResponse.class.isInstance(obj)) {
				FindReservationsResponse resp = (FindReservationsResponse)obj;
				List<ReservationDetails> list = resp.getReservationList();
				for(int i=0;i<list.size();i++) {
					ReservationDetails details = list.get(i);
					System.out.println(details);
				}
			} else {
				System.out.println("Unknown class type: "+obj.getClass().getName());
			}
			socket.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		
	}
	public void listReservations() {
		ReservationController controller = new ReservationControllerImpl();
		List<ReservationDetails> allReservations = controller.findAllReservations().getReservationList();
		
		for(int i=0;i<allReservations.size();i++) {
			ReservationDetails details = allReservations.get(i);
			System.out.println(details);
		}
		
	}
	 
	public void listReservation() {
		FindReservationRequest request = new FindReservationRequest(null, null, confirmationNum);
		Socket socket = null;
		try {
			socket = new Socket("localhost", 6500);
			ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
			
			outStream.writeObject(request);
			
			ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
			Object obj = inStream.readObject();
			if(ReservationDetails.class.isInstance(obj)) {
				System.out.println((ReservationDetails)obj);
			}
			socket.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	public void makeReservation(String from, String to, Date outbound, Date returnDate) {
		FlightSearchResults results = findAvailFlights(from, to, outbound, returnDate);
		
		List<FlightDate> outflights = results.getOutFlights();
		List<FlightDate> retflights = results.getReturnFlights();
		
		FlightDate outFlightDate = outflights.get(0);
		FlightDate retFlightDate = retflights.get(1);

		ReservationRequest request = new ReservationRequest();
		request.setDepartDate(outbound);
		request.setFirstName("Doug");
		request.setFromAirport(from);
		request.setLastName("Hilpipre");
		request.setOutboundFltNum(outFlightDate.getFlightNumber());
		request.setOutSeat(outFlightDate.getAvailableSeats().get(0));
		request.setRetSeat(retFlightDate.getAvailableSeats().get(0));
		request.setReturnDate(returnDate);
		request.setReturnFltNum(retFlightDate.getFlightNumber());
		request.setToAirport(to);
		
		Socket socket = null;
		try {
			socket = new Socket("localhost", 6500);
			ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
			
			outStream.writeObject(request);
			
			ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
			Object obj = inStream.readObject();
			CreateReservationResponse resp = (CreateReservationResponse)obj;
			confirmationNum = resp.getConfirmationNumber();
			socket.close();
			System.out.println("Created Reservation #"+confirmationNum);
			
		} catch(Exception e) {
			e.printStackTrace();
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	
//		ReservationController controller = new ReservationControllerImpl();
//		confirmationNum = controller.createReservation(request);
		
	}

	private FlightSearchResults findAvailFlights(String from, String to, Date outbound, Date returnDate) {
		FlightSearchRequest request = new FlightSearchRequest();
		request.setDepartDate(outbound);
		request.setReturnDate(returnDate);
		request.setFromAirport(from);
		request.setToAirport(to);
		FlightSearchResults results = null;		
		Socket socket = null;
		try {
			socket = new Socket("localhost", 6500);
			ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
			
			outStream.writeObject(request);
			
			ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
			Object obj = inStream.readObject();
			if(FlightSearchResults.class.isInstance(obj))
				results = (FlightSearchResults)obj;
			socket.close();
		} catch(Exception e) {
			e.printStackTrace();
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		
		return results;
	}
	
	public void findFlights(String from, String to, Date outbound, Date returnDate) {

		FlightSearchResults results = findAvailFlights(from, to, outbound, returnDate);
		List<FlightDate> outflights = results.getOutFlights();
		List<FlightDate> retflights = results.getReturnFlights();
		
		System.out.println("Outbound:");
		for(int i=0;i<outflights.size();i++) {
			FlightDate flightDate = outflights.get(i);
			String seatAvail = flightDate.seatsAvailable() ? "has "+flightDate.getAvailableSeats().size()+" seats" : "has no seats";
			System.out.println(flightDate.getFlightNumber()+" - "+flightDate.getFlightDate()+" "+seatAvail);
		}

		System.out.println("\nReturn:");
		for(int i=0;i<retflights.size();i++) {
			FlightDate flightDate = retflights.get(i);
			String seatAvail = flightDate.seatsAvailable() ? "has "+flightDate.getAvailableSeats().size()+" seats" : "has no seats";
			System.out.println(flightDate.getFlightNumber()+" - "+flightDate.getFlightDate()+" "+seatAvail);
		}
	}
}

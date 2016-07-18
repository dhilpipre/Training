package com.newrelic.airline.reservations;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.newrelic.airline.reservations.forms.FlightDate;
import com.newrelic.airline.reservations.forms.FlightSearchRequest;
import com.newrelic.airline.reservations.forms.FlightSearchResults;
import com.newrelic.airline.reservations.forms.ReservationDetails;
import com.newrelic.airline.reservations.forms.ReservationRequest;
import com.newrelic.airline.reservations.forms.Revenue;
import com.newrelic.airline.reservations.forms.RevenueSinceRequest;
import com.newrelic.airline.reservations.forms.RevenueResponse;

public class Test {

	String confirmationNum;
	
	public static void main(String[] args) {
		Test test = new Test();
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2015, Calendar.NOVEMBER, 14);
		Date out = cal.getTime();
		cal.set(2015, Calendar.NOVEMBER, 18);
		Date ret = cal.getTime();
		test.findFlights("San Francisco", "Portland", out , ret);
		test.makeReservation("San Francisco", "Portland", out , ret);
		test.findFlights("San Francisco", "Portland", out , ret);
		test.listReservations();
		test.listReservations("Doug","Hilpipre");
		test.listReservation("lhs809");
		
		test.getRevenue();
//		test.addCapacity();
	}
	
	public void addCapacity() {
		ReservationControllerImpl controller = new ReservationControllerImpl();
		controller.addNewCapacity();
	}
	public void getRevenue() {
		Calendar cal = Calendar.getInstance();
		long time = cal.getTimeInMillis();
		time -= 1000 * 60 * 60 * 2;
		long last = 1000 * 60 * 60 * 3;
		ReservationController controller = new ReservationControllerImpl();
		RevenueSinceRequest req = new RevenueSinceRequest();
		req.setSinceByLastMs(last);
		long t = req.getSince();
		cal.setTimeInMillis(t);
		System.out.println("Since: "+cal.getTime().toString());
		RevenueResponse response = controller.getRevenue(req);
		System.out.println("Total Revenue: "+response.getRevenueSum());
		System.out.println("Reservations: "+response.getReservations());
		System.out.println("Cancelations: "+response.getCancelations());
		
		List<Revenue> list = response.getRevenues();
		for(Revenue rev : list) {
			System.out.println(rev.getTimestamp().toString()+": "+rev.getRevenue());
		}
	}
	public void listReservations(String first, String last) {
		ReservationController controller = new ReservationControllerImpl();
		List<ReservationDetails> allReservations = controller.findReservationsByName(first, last).getReservationList();
		
		for(int i=0;i<allReservations.size();i++) {
			ReservationDetails details = allReservations.get(i);
			System.out.println(details);
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
	 
	public void listReservation(String conf) {
		ReservationController controller = new ReservationControllerImpl();
		ReservationDetails details = controller.findReservationByConf(conf).getReservationList().get(0);
		System.out.println(details);
	}
	public void listReservation() {
		ReservationController controller = new ReservationControllerImpl();
		ReservationDetails details = controller.findReservationByConf(confirmationNum).getReservationList().get(0);
		System.out.println(details);
		
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
		request.setOutPrice(200F);
		request.setReturnPrice(200F);
		ReservationController controller = new ReservationControllerImpl();
		confirmationNum = controller.createReservation(request).getConfirmationNumber();
		
	}

	private FlightSearchResults findAvailFlights(String from, String to, Date outbound, Date returnDate) {
		FlightSearchRequest request = new FlightSearchRequest();
		request.setDepartDate(outbound);
		request.setReturnDate(returnDate);
		request.setFromAirport(from);
		request.setToAirport(to);
		
		ReservationController controller = new ReservationControllerImpl();
		FlightSearchResults results = controller.findFlights(request);		
		
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

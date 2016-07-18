package com.newrelic.airline.reservations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.BaseRequest;
import com.newrelic.airline.reservations.forms.CancelResponse;
import com.newrelic.airline.reservations.forms.CitiesServedResponse;
import com.newrelic.airline.reservations.forms.CreateFlightRequest;
import com.newrelic.airline.reservations.forms.CreateFlightResponse;
import com.newrelic.airline.reservations.forms.CreateReservationResponse;
import com.newrelic.airline.reservations.forms.DeleteFlightRequest;
import com.newrelic.airline.reservations.forms.DeleteFlightResponse;
import com.newrelic.airline.reservations.forms.FindReservationRequest;
import com.newrelic.airline.reservations.forms.FindReservationsResponse;
import com.newrelic.airline.reservations.forms.Flight;
import com.newrelic.airline.reservations.forms.FlightSearchRequest;
import com.newrelic.airline.reservations.forms.FlightSearchResults;
import com.newrelic.airline.reservations.forms.GetCitiesRequest;
import com.newrelic.airline.reservations.forms.GetWaitPeriodRequest;
import com.newrelic.airline.reservations.forms.GetWaitPeriodResponse;
import com.newrelic.airline.reservations.forms.ReservationCancelRequest;
import com.newrelic.airline.reservations.forms.ReservationDetails;
import com.newrelic.airline.reservations.forms.ReservationRequest;
import com.newrelic.airline.reservations.forms.ScheduleListRequest;
import com.newrelic.airline.reservations.forms.ScheduleListResponse;
import com.newrelic.airline.reservations.forms.SearchIDRequest;
import com.newrelic.airline.reservations.forms.SearchIDResponse;
import com.newrelic.airline.reservations.forms.SetWaitPeriodRequest;
import com.newrelic.airline.reservations.forms.SetWaitPeriodResponse;
import com.newrelic.airline.reservations.forms.UpdateFlightRequest;
import com.newrelic.airline.reservations.forms.UpdateFlightResponse;

public class RezSystemGateway {
	
	static private final Logger LOG = Logger.getLogger(RezSystemGateway.class);
	static private RezSystemGateway instance = null;
	static private String rezSystemHost = "localhost";
	static private int rezSystemPort = 6500;
	private static final String SEARCHID = "Search-ID";
	private static final String REZIP = "OPENSHIFT_REZSYSTEM_IP";
	private static final String REZPORT = "OPENSHIFT_REZSYSTEM_REZ_PORT";
	
	protected String getRezSystemHost() {
		return rezSystemHost;
	}

	protected int getRezSystemPort() {
		return rezSystemPort;
	}

	static public RezSystemGateway getInstance() {
		if(instance == null) {
			instance = new RezSystemGateway();
		}
		return instance;
	}

	private RezSystemGateway() {
		String rezIP = System.getenv(REZIP);
		
		String rezPort = System.getenv(REZPORT);
		
		if(rezIP != null && !rezIP.equals("") && rezPort != null && !rezPort.equals("")) {
			rezSystemHost = rezIP;
			rezSystemPort = Integer.parseInt(rezPort);
		} else {
		
			File propFile = new File("RezSystem.properties");
			try {
				if(propFile.exists()) {
					Properties props = new Properties();
					FileReader reader = new FileReader(propFile);
					props.load(reader);
					if(props.containsKey("RezHost")) {
						String host = props.getProperty("RezHost");
						if(host != null && !host.isEmpty()) {
							rezSystemHost = host;
						}
					}

					if(props.containsKey("RezPort")) {
						String portStr = props.getProperty("RezPort");
						if(portStr != null && !portStr.isEmpty()) {
							rezSystemPort = Integer.parseInt(portStr);
						}
					}
				}
			} catch (NumberFormatException e) {
				LOG.error("NumberFormatException", e);
			} catch (FileNotFoundException e) {
				LOG.error("FileNotFoundException", e);
			} catch (IOException e) {
				LOG.error("IOException", e);
			}
		}
		LOG.info("RezSystemGateway initialized with host: "+rezSystemHost+" and port: "+rezSystemPort);
	}
	
	private Socket getRezSystemSocket() {
		Socket socket = null;
		try {
			socket = new Socket(rezSystemHost, rezSystemPort);
			
		} catch(IOException e) {
			LOG.error("IOException", e);
		}
		return socket;
	}
	
	public long getWaitPeriod() throws RezGatewayException {
		GetWaitPeriodRequest request = new GetWaitPeriodRequest();
		request.setProperty("Request-Type", "GetWaitPeriodRequest");
		Object obj = send(request);
		if(GetWaitPeriodResponse.class.isInstance(obj)) {
			GetWaitPeriodResponse response = (GetWaitPeriodResponse)obj;
			return response.getWait_period();
		}
		return -1L;
	}
	
	public SetWaitPeriodResponse setWaitPeriod(long new_wait) throws RezGatewayException {
		SetWaitPeriodRequest request = new SetWaitPeriodRequest(new_wait);
		request.setProperty("Request-Type", "SetWaitPeriod");
		Object obj = send(request);
		SetWaitPeriodResponse response = null;
		if(SetWaitPeriodResponse.class.isInstance(obj)) {
			response = (SetWaitPeriodResponse)obj;
		}
		return response;
		
	}
	
	public UpdateFlightResponse modifyFlight(Flight original, Flight modified) throws RezGatewayException {
		UpdateFlightRequest req = new UpdateFlightRequest(original, modified);
		req.setProperty("Request-Type", "UpdateFlightRequest");
		Object obj = send(req);
		if(UpdateFlightResponse.class.isInstance(obj)) {
			return (UpdateFlightResponse)obj;
		}
		return null;
	}
	
	public DeleteFlightResponse deleteFlight(String flightNumber) throws RezGatewayException {
		DeleteFlightRequest request = new DeleteFlightRequest(flightNumber);
		request.setProperty("Request-Type", "DeleteFlightRequest");
		Object obj = send(request);
		if(DeleteFlightResponse.class.isInstance(obj)) {
			return (DeleteFlightResponse)obj;
		}
		return null;
	}
	
	public CreateFlightResponse createNewFlight(Flight flight) throws RezGatewayException {
		CreateFlightRequest request = new CreateFlightRequest(flight);
		request.setProperty("Request-Type", "CreateFlightRequest");
		Object obj = send(request);
		if(CreateFlightResponse.class.isInstance(obj)) {
			return (CreateFlightResponse)obj;
		}
		return null;
	}
	
	public HashMap<String, List<Flight>> getSchedule() throws RezGatewayException {
		ScheduleListRequest request = new ScheduleListRequest();
		request.setProperty("Request-Type", "ScheduleListRequest");
		Object obj = send(request);
		HashMap<String, List<Flight>> map = new HashMap<String, List<Flight>>();
		if(ScheduleListResponse.class.isInstance(obj)) {
			ScheduleListResponse response = (ScheduleListResponse)obj;
			map = response.getFlights();
		} else if(Exception.class.isInstance(obj)) {
			Exception e = (Exception)obj;
			LOG.error("Exception getting schedule", e);
		} else if(obj == null) {
			RezGatewayException e = new RezGatewayException("Received null object from RezSystem");
			throw e;
		}

		return map;
	}
	
	protected Object send(BaseRequest request) throws RezGatewayException {
		dumpProperties(request);
		Socket socket = getRezSystemSocket();
		Object obj = null;
		
		if (socket != null) {
			try {
				ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
				outStream.writeObject(request);
				
				ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
				obj = inStream.readObject();
				outStream.close();
				inStream.close();
			} catch (Exception e) {
				LOG.error(e.getClass().getSimpleName(), e);
				throw new RezGatewayException(e);
			}
			try {
				socket.close();
			} catch (IOException e) {
				LOG.error(e.getClass().getSimpleName(), e);
				throw new RezGatewayException(e);
			}
		}
		return obj;
	}
	
	public String[] getCitiesServed() throws RezGatewayException {
		String[] citiesServed = {};
		GetCitiesRequest request = new GetCitiesRequest();
		request.setProperty("Request-Type", "GetCitiesRequest");
		Object obj = send(request);
		if(obj == null) {
			RezGatewayException e = new RezGatewayException("Received null object from RezSystem");
			throw e;
		}
		if (obj.getClass().isArray()) {
					citiesServed = (String[]) obj;
		} else if(CitiesServedResponse.class.isInstance(obj)) {
					CitiesServedResponse response = (CitiesServedResponse)obj;
					citiesServed = response.getCitiesServed();
		} else if(Exception.class.isInstance(obj)) {
			Exception e = (Exception)obj;
			LOG.error("Exception getting cities", e);
		} 
		return citiesServed;
	}
	
	public ReservationDetails listReservation(String confirmationNum) throws RezGatewayException {
		FindReservationRequest request = new FindReservationRequest(null, null, confirmationNum);
		request.setProperty("Request-Type", "FindReservationRequest");
		ReservationDetails details = null;
		Object obj = send(request);
		if (ReservationDetails.class.isInstance(obj)) {
			details = (ReservationDetails)obj;
		} else if (FindReservationsResponse.class.isInstance(obj)) { 
			FindReservationsResponse response = (FindReservationsResponse)obj;
			List<ReservationDetails> list = response.getReservationList();
			if(list != null && list.size() == 1) {
				details = list.get(0);
			}
		} else if(List.class.isInstance(obj)) {
			List<ReservationDetails> list = (List<ReservationDetails>)obj;
			if(list != null && list.size() == 1) {
				details = list.get(0);
			}
		} else if(Exception.class.isInstance(obj)) {
			Exception e = (Exception)obj;
			LOG.error("Exception getting reservation", e);
		} else if(obj == null) {
			RezGatewayException e = new RezGatewayException("Received null object from RezSystem");
			throw e;
		}
		return details;
	}

	@SuppressWarnings("unchecked")
	public List<ReservationDetails> listReservations(String first, String last) throws RezGatewayException {
		List<ReservationDetails> list = new ArrayList<ReservationDetails>();
		LOG.debug("Searching for reservations for "+first+" "+last);
		
		FindReservationRequest request = new FindReservationRequest(first, last, null);
		request.setProperty("Request-Type", "FindReservationRequest");
		Object obj = send(request);
		if (List.class.isInstance(obj)) {
			list = (List<ReservationDetails>) obj;
		} else if (FindReservationsResponse.class.isInstance(obj)) { 
			FindReservationsResponse response = (FindReservationsResponse)obj;
			list = response.getReservationList();
		} else if (ReservationDetails.class.isInstance(obj)) {
			list.add((ReservationDetails) obj);
		} else if(Exception.class.isInstance(obj)) {
			Exception e = (Exception)obj;
			LOG.error("Exception getting reservation list", e);
		} else if(obj == null) {
			RezGatewayException e = new RezGatewayException("Received null object from RezSystem");
			throw e;		
		} else {
			System.out.println("Unknown class type: " + obj.getClass().getName());
		}
		return list;
	}

	public FlightSearchResults findAvailFlights(String from, String to, Date outbound, Date returnDate) throws RezGatewayException {
		FlightSearchRequest request = new FlightSearchRequest();
		request.setProperty("Request-Type", "FlightSearchRequest");
		String searchID = getSearchID();
		request.setProperty(SEARCHID, searchID);
		request.setProperty("Search-State", "Searching");
		
		request.setDepartDate(outbound);
		request.setReturnDate(returnDate);
		request.setFromAirport(from);
		request.setToAirport(to);
		FlightSearchResults results = null;		
		Object obj = send(request);
		if (FlightSearchResults.class.isInstance(obj)) {
			results = (FlightSearchResults) obj;
		} else if(Exception.class.isInstance(obj)) {
			Exception e = (Exception)obj;
			LOG.error("Exception getting cities", e);
		} else if(obj == null) {
			RezGatewayException e = new RezGatewayException("Received null object from RezSystem");
			throw e;
		}

		results.setProperty(SEARCHID, searchID);
		return results;
	}

	private String getSearchID() {
		SearchIDRequest request = new SearchIDRequest();
		request.setProperty("Request-Type", "SearchIDRequest");
		
		Object obj;
		try {
			obj = send(request);
			if(SearchIDResponse.class.isInstance(obj)) {
				SearchIDResponse response = (SearchIDResponse)obj;
				return response.getSearchID();
			}
		} catch (RezGatewayException e) {
		}
		return null;
	}
	

	public String makeReservation(ReservationRequest request) throws RezGatewayException {
		request.setProperty("Request-Type", "ReservationRequest");
		String confirmationNum = null;
		
		Object obj = send(request);

		if(CreateReservationResponse.class.isInstance(obj)) {
			CreateReservationResponse response = (CreateReservationResponse)obj;
			confirmationNum = response.getConfirmationNumber();
		} else if(Exception.class.isInstance(obj)) {
			Exception e = (Exception)obj;
			LOG.error("Exception getting cities", e);
			confirmationNum = "";
		} else if(obj == null) {
			RezGatewayException e = new RezGatewayException("Received null object from RezSystem");
			throw e;
		} else {
			confirmationNum = obj.toString();
		}

		return confirmationNum;
	}

	public String cancelReservation(ReservationCancelRequest request) throws RezGatewayException {
		request.setProperty("Request-Type", "ReservationCancelRequest");
		String result = null;
		Object obj = send(request);
		if(CancelResponse.class.isInstance(obj)) {
			CancelResponse response = (CancelResponse)obj;
			result = response.getCancellationResponse();
		} else if(Exception.class.isInstance(obj)) {
			Exception e = (Exception)obj;
			LOG.error("Exception getting cities", e);
		} else if(obj == null) {
			RezGatewayException e = new RezGatewayException("Received null object from RezSystem");
			throw e;
		} else {
			result = obj.toString();
		}
		return result;
	}
	
	protected void dumpProperties(BaseRequest request) {
		System.out.println("Dumping Properties");
		Properties props = request.getProperties();
		Set<Object> keys = props.keySet();
		if(keys.size() == 0) {
			System.out.println("\tNo properties found");
		}
		for(Object key : keys) {
			Object value = props.get(key);
			System.out.println("\tProperty "+key.toString()+": "+value.toString());
		}
	}

 }

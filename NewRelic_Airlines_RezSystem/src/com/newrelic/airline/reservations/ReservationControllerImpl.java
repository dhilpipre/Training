package com.newrelic.airline.reservations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.BaseResponse;
import com.newrelic.airline.reservations.forms.CancelResponse;
import com.newrelic.airline.reservations.forms.CancelSearchRequest;
import com.newrelic.airline.reservations.forms.CancelSearchResponse;
import com.newrelic.airline.reservations.forms.CapacityByDateRequest;
import com.newrelic.airline.reservations.forms.CapacityByDateResponse;
import com.newrelic.airline.reservations.forms.CapacityByFlightsRequest;
import com.newrelic.airline.reservations.forms.CapacityByFlightsResponse;
import com.newrelic.airline.reservations.forms.CapacityRecord;
import com.newrelic.airline.reservations.forms.CitiesServedResponse;
import com.newrelic.airline.reservations.forms.CreateFlightRequest;
import com.newrelic.airline.reservations.forms.CreateFlightResponse;
import com.newrelic.airline.reservations.forms.CreateReservationResponse;
import com.newrelic.airline.reservations.forms.DeleteFlightRequest;
import com.newrelic.airline.reservations.forms.DeleteFlightResponse;
import com.newrelic.airline.reservations.forms.FindReservationsResponse;
import com.newrelic.airline.reservations.forms.Flight;
import com.newrelic.airline.reservations.forms.FlightDate;
import com.newrelic.airline.reservations.forms.FlightRosterRequest;
import com.newrelic.airline.reservations.forms.FlightRosterResponse;
import com.newrelic.airline.reservations.forms.FlightSearchRequest;
import com.newrelic.airline.reservations.forms.FlightSearchResults;
import com.newrelic.airline.reservations.forms.GetWaitPeriodRequest;
import com.newrelic.airline.reservations.forms.GetWaitPeriodResponse;
import com.newrelic.airline.reservations.forms.ReservationDetails;
import com.newrelic.airline.reservations.forms.ReservationRequest;
import com.newrelic.airline.reservations.forms.Revenue;
import com.newrelic.airline.reservations.forms.RevenueDateRequest;
import com.newrelic.airline.reservations.forms.RevenueDateResponse;
import com.newrelic.airline.reservations.forms.RevenueSinceRequest;
import com.newrelic.airline.reservations.forms.RevenueResponse;
import com.newrelic.airline.reservations.forms.ScheduleListResponse;
import com.newrelic.airline.reservations.forms.SearchIDRequest;
import com.newrelic.airline.reservations.forms.SearchIDResponse;
import com.newrelic.airline.reservations.forms.SeatInfo;
import com.newrelic.airline.reservations.forms.SetWaitPeriodRequest;
import com.newrelic.airline.reservations.forms.SetWaitPeriodResponse;
import com.newrelic.airline.reservations.forms.UpdateFlightRequest;
import com.newrelic.airline.reservations.forms.UpdateFlightResponse;
import com.newrelic.airline.reservations.pools.DatabaseConnectionPool;

/**
 * Implementation of the ReservationController
 * 
 * @author dhilpipre
 *
 */
public class ReservationControllerImpl implements ReservationController {

	DatabaseConnectionPool databaseConnectionPool;
	
	public DatabaseConnectionPool getDatabaseConnectionPool() {
		return databaseConnectionPool;
	}

	int poolSize;
	private static final List<String> newSeatList;
	private static final List<String> newSeatList2;
	private static boolean citiesInitialized = false;

	static {
		newSeatList = new ArrayList<String>();
		for(int i=1;i<21;i++) {
			for(int j=1;j<5;j++) {
				switch (j) {
				case 1: 
					newSeatList.add(i + "A");
					break;
				case 2:
					newSeatList.add(i + "B");
					break;
				case 3:
					newSeatList.add(i + "C");
					break;
				case 4:
					newSeatList.add(i + "D");
					break;
				default:
					break;
				}
			}
		}

		newSeatList2 = new ArrayList<String>();
		for(int i=1;i<26;i++) {
			for(int j=1;j<5;j++) {
				switch (j) {
				case 1: 
					newSeatList2.add(i + "A");
					break;
				case 2:
					newSeatList2.add(i + "B");
					break;
				case 3:
					newSeatList2.add(i + "C");
					break;
				case 4:
					newSeatList2.add(i + "D");
					break;
				default:
					break;
				}
			}
		}
	}

	static private String nextSearchID;
	
	private static Vector<String> citiesServed;
	private static final String dbConfigFilename = "DBConfiguration.properties";
	private static final Logger LOG = Logger.getLogger(ReservationControllerImpl.class);
	private static final String searchIDFilename = "SearchID.dat";
	private static final String SEARCHID = "Search-ID";
	private static final String SEARCHSTATE = "Search-State";
	
	static {
		citiesServed = new Vector<String>();
		citiesServed.add("San Francisco");
		citiesServed.add("Portland");
		citiesServed.add("Seattle");
		citiesServed.add("Dublin");
	}
	
	public static String[] getCities() {
		int size = citiesServed.size();
		String[] cities = new String[size];
		citiesServed.toArray(cities);
		return cities;
	}
	
	protected long wait_period = 0L;

	public long getWait_period() {
		return wait_period;
	}

	public void setWait_period(long wait_period) {
		this.wait_period = wait_period;
	}

	
	
	static {
		
		
		Object obj = null;
		
		String temp = null;
		try {
			FileInputStream in = null;
			obj = null;
			
			try {
				in = new FileInputStream(searchIDFilename);
				ObjectInputStream inStream = new ObjectInputStream(in);
				
				obj = inStream.readObject();
				inStream.close();
			} catch (FileNotFoundException e) {
			} 
			
			temp = (String)obj;
			
		} catch (ClassNotFoundException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		} catch (IOException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
		if(temp != null) {
			nextSearchID = temp;
		} else {
			nextSearchID = "NR1000";
		}
	
		
	}
	public ReservationControllerImpl() {
		databaseConnectionPool = new DatabaseConnectionPool(dbConfigFilename, "Reservation ConnectionPool");
	}
	
	public void databaseConfigChanged() {
		databaseConnectionPool.resetDataSource();
		
	}
	
	private List<ReservationDetails> getReservations(String first, String last) {
		List<ReservationDetails> custReservations = new ArrayList<ReservationDetails>();
		
		List<String> cNumbers = new ArrayList<String>();

		Connection conn = databaseConnectionPool.getConnection();
		if (conn != null) {
			try {
				String query = "select ConfirmationNumber from reservations where FirstName=? AND LastName=?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, first);
				pstmt.setString(2, last);

				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					String cNumber = rs.getString("ConfirmationNumber");
					cNumbers.add(cNumber);
				}
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				LOG.error("SQLException:", e);
			}
		}
		for(String cNumber : cNumbers) {
			ReservationDetails details = getReservation(cNumber, conn);
			custReservations.add(details);
		}
		databaseConnectionPool.returnConnection(conn);
		return custReservations;
	}
	
	private ReservationDetails getReservation(String confirmNum, Connection conn) {
		ReservationDetails details = null;
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm aa");
		
		try {
			String query = "select * from reservations where ConfirmationNumber=?";
			if (conn != null) {
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, confirmNum);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					details = new ReservationDetails();
					details.setConfirmationNumber(confirmNum);
					details.setFirstName(rs.getString("FirstName"));
					details.setLastName(rs.getString("LastName"));
					details.setFromAirport(rs.getString("FromAirport"));
					details.setToAirport(rs.getString("ToAirport"));

					Timestamp outDeparture = rs.getTimestamp("OutDeparture");
					details.setDepartDate(outDeparture);
					details.setDepartTime(sdf2.format(outDeparture));
					Timestamp outArrival = rs.getTimestamp("OutArrival");
					details.setOutArriveTime(sdf2.format(outArrival));
					details.setOutFlightNumber(rs.getString("OutFlightNumber"));
					details.setOutSeat(rs.getString("OutSeat"));

					Timestamp retDeparture = rs.getTimestamp("ReturnDeparture");
					details.setReturnDate(retDeparture);
					details.setReturnTime(sdf2.format(retDeparture));
					Timestamp retArrival = rs.getTimestamp("ReturnArrival");
					details.setRetArriveTime(sdf2.format(retArrival));
					details.setRetFlightNumber(rs.getString("ReturnFlightNumber"));
					details.setRetSeat(rs.getString("ReturnSeat"));

					float price = rs.getFloat("Price");
					details.setPrice(price);
					float outPrice = rs.getFloat("OutPrice");
					details.setOutPrice(outPrice);
					float retPrice = rs.getFloat("RetPrice");
					details.setRetPrice(retPrice);
					
					Date created = rs.getDate("Created");
					if(created == null) {
						Calendar cal = Calendar.getInstance();
						cal.set(2014, Calendar.SEPTEMBER, 1, 0, 0, 0);
						created = new Date(cal.getTimeInMillis());
					}
					details.setCreated(created);
				}
				rs.close();
				pstmt.close();
			}
		} catch (SQLException e) {
			LOG.error("SQLException:", e);
		}
		return details;
	}
	
	@Override
	public FindReservationsResponse findReservationByConf(String confirmation) {
		LOG.debug("Received Search Request for confirmation #"+confirmation);
		List<ReservationDetails> reservationList = new ArrayList<ReservationDetails>();
		Connection conn = databaseConnectionPool.getConnection();
		reservationList.add(getReservation(confirmation,conn));
		databaseConnectionPool.returnConnection(conn);
		FindReservationsResponse response = new FindReservationsResponse(reservationList );
		return response;
	}
	
	private List<String> getAllConfirmationNumbers(Connection conn) {
		List<String> numbers = new ArrayList<String>();
		if (conn != null) {
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT ConfirmationNumber from reservations");
				while (rs.next()) {
					String confNum = rs.getString("ConfirmationNumber");
					numbers.add(confNum);
				}
			} catch (SQLException e) {
				LOG.error("SQLException:", e);
			}
		}
		return numbers;
	}
	
	private String getNewConfirmationNumber(Connection conn) {
		List<String> confCodes = getAllConfirmationNumbers(conn);
		
		RandomString rs = new RandomString(6);
		String confCode = rs.nextString();
		while(confCodes.contains(confCode)) {
			confCode = rs.nextString();
		}
		
		
		return confCode;
	}
	
	private void addRevenue(Float price, String outflightNo, Date outflightDate, String retflightNo, Date retflightDate, Connection conn) {
		try {
			String insert = "insert into revenue(timestamp,revenue,outflightNumber,outflightDate,retFlightnumber,retflightdate) values(?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(insert);
			Timestamp x = new Timestamp(System.currentTimeMillis());
			
			pstmt.setTimestamp(1, x );
			pstmt.setFloat(2, price);
			pstmt.setString(3, outflightNo);
			pstmt.setDate(4, new java.sql.Date(outflightDate.getTime()));
			pstmt.setString(5, retflightNo);
			pstmt.setDate(6, new java.sql.Date(retflightDate.getTime()));
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			LOG.error("SQLException:", e);
		}
	}
	
	private void reportDateRevenue(Date revenueDate, Float price, Connection conn ) {
		String query = "select * from revenuebyday where RevenueDate=?";
		String insert = "INSERT INTO revenuebyday(revenuedate,revenue,refunds,reservations) VALUES(?,?,?,?)";
		String update = "update revenuebyday set revenue=?,refunds=?,reservations=? where revenueDate=?";

		Calendar cal = Calendar.getInstance();
		cal.setTime(revenueDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		java.sql.Date d = new java.sql.Date(cal.getTimeInMillis());
		Float revenue = 0F;
		Float refunds = 0F;
		int reservations = 0;
		boolean isNew = true;
		
		try {
			PreparedStatement pstmt1 = conn.prepareStatement(query );
			pstmt1.setDate(1, d);
			
			ResultSet rs = pstmt1.executeQuery();
			if(rs.next()) {
				revenue = rs.getFloat("Revenue");
				reservations = rs.getInt("Reservations");
				refunds = rs.getFloat("Refunds");
				isNew = false;
			}
			
			rs.close();
			pstmt1.close();
			
			revenue += price;
			reservations++;
			if(price < 0F) {
				refunds += -price;
			}
			PreparedStatement pstmt2;
			if(isNew) {
				pstmt2 = conn.prepareStatement(insert);
				pstmt2.setDate(1, d);
				pstmt2.setFloat(2, revenue);
				pstmt2.setFloat(3, refunds);
				pstmt2.setInt(4, reservations);
			} else {
				pstmt2 = conn.prepareStatement(update);
				pstmt2.setDate(4, d);
				pstmt2.setFloat(1, revenue);
				pstmt2.setFloat(2, refunds);
				pstmt2.setInt(3, reservations);
				
			}
			
			pstmt2.executeUpdate();
			pstmt2.close();
		} catch (SQLException e) {
			LOG.error("SQLException:", e);
		}
		
	}
	private void addReservation(ReservationDetails details, Connection conn) {
		LOG.debug("Adding Reservation "+details.getConfirmationNumber() + " to database ");
		if (conn != null) {
			String insert = "Insert into reservations(ConfirmationNumber,FirstName,LastName,FromAirport,ToAirport,OutDeparture,OutArrival,ReturnDeparture,ReturnArrival,OutSeat,ReturnSeat,OutFlightNumber,ReturnFlightNumber,Price,OutPrice,RetPrice,Created) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(insert);
				pstmt.setString(1, details.getConfirmationNumber());
				pstmt.setString(2, details.getFirstName());
				pstmt.setString(3, details.getLastName());
				pstmt.setString(4, details.getFromAirport());
				pstmt.setString(5, details.getToAirport());
				pstmt.setTimestamp(6, new Timestamp(details.getOutDeparture().getTime()));
				pstmt.setTimestamp(7, new Timestamp(details.getOutArrival().getTime()));
				pstmt.setTimestamp(8, new Timestamp(details.getReturnDeparture().getTime()));
				pstmt.setTimestamp(9, new Timestamp(details.getReturnArrival().getTime()));
				pstmt.setString(10, details.getOutSeat());
				pstmt.setString(11, details.getRetSeat());
				pstmt.setString(12, details.getOutFlightNumber());
				pstmt.setString(13, details.getRetFlightNumber());
				pstmt.setFloat(14, details.getPrice());
				pstmt.setFloat(15, details.getOutPrice());
				pstmt.setFloat(16, details.getRetPrice());
				pstmt.setDate(17, new java.sql.Date(System.currentTimeMillis()));
				Float p = details.getPrice();
				if(p == null) {
					p = new Float(0);
				}
				pstmt.setFloat(14, p);
				int c = pstmt.executeUpdate();
				if (c != 1) {
					LOG.error("Adding reservation update returned " + c);
				}
				pstmt.close();
				addRevenue(details.getPrice(),details.getOutFlightNumber(),details.getOutDeparture(),details.getRetFlightNumber(),details.getReturnDate(),conn);
				reportDateRevenue(details.getDepartDate(), details.getOutPrice(), conn);
				reportDateRevenue(details.getReturnDate(), details.getRetPrice(), conn);
			} catch (SQLException e) {
				LOG.error("SQLException:", e);
			}
			LOG.debug("Reservation " + details.getConfirmationNumber() + " added to database ");
		}
		
	}
	
	private FlightInfo getFlightInfo(String flightNumber, Connection conn) {
		FlightInfo info = new FlightInfo();
		
		if (conn != null) {
			try {
				String query = "Select * from flights where FlightNumber=?";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, flightNumber);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					info.arrival = rs.getString("Arrive");
					info.departure = rs.getString("Depart");
					info.flightNumber = rs.getString("FlightNumber");

					String s;
					String am_pm;
					StringTokenizer st;
					int i;
					if (info.flightNumber.equalsIgnoreCase(flightNumber)) {
						s = info.departure;
						am_pm = s.substring(s.length() - 2, s.length());
						info.dAM_PM = am_pm;
						s = s.substring(0, s.length() - 2);
						st = new StringTokenizer(s, ":");
						i = 0;
						while (st.hasMoreTokens()) {
							String token = st.nextToken().trim();
							switch (i) {
							case 0:
								info.dhours = Integer.parseInt(token);
								break;
							case 1:
								info.dminutes = Integer.parseInt(token);
								break;
							}
							i++;
						}
					}
					s = info.arrival;
					boolean isNextDay = false;
					if(s.endsWith("+1")) {
						isNextDay = true;
					}
					info.nextDayArrival = isNextDay;
					if(isNextDay) {
						s = s.substring(0, s.length()-2);
					}
					am_pm = s.substring(s.length()-2, s.length());
					info.aAM_PM = am_pm;
					s = s.substring(0, s.length()-2);
					st = new StringTokenizer(s, ":");
					i = 0;
					while(st.hasMoreTokens()) {
						String token = st.nextToken().trim();
						switch(i) {
						case 0:
							info.ahours = Integer.parseInt(token);
							break;
						case 1:
							info.aminutes = Integer.parseInt(token);
							break;
						}
						i++;
					}
				}
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				LOG.error("SQLException:", e);
			}
//			databaseConnectionPool.returnConnection(conn);
		}
		return info;
	}

	@Override
	public DeleteFlightResponse deleteFlight(DeleteFlightRequest request) {
		Connection conn = databaseConnectionPool.getConnection();
		boolean check = false;
		
		try {
			String delete = "delete from flights where FlightNumber=?";
			PreparedStatement pstmt = conn.prepareStatement(delete);
			String flightNumber = request.getFlightNumber();
			pstmt.setString(1, flightNumber );
			int c = pstmt.executeUpdate();
			pstmt.close();
			check = c == 1;
		} catch (SQLException e) {
			LOG.error("SQLException:", e);
		}
		
		databaseConnectionPool.returnConnection(conn);
		return new DeleteFlightResponse(check);
	}


	@Override
	public CreateReservationResponse createReservation(ReservationRequest request) {
		//dumpRequestProperties(request);
		
		Connection conn = databaseConnectionPool.getConnection();

		String confCode = getNewConfirmationNumber(conn);
		LOG.debug("Creating Reservation "+confCode + " for " + request.getFirstName() + " " + request.getLastName());
		FlightInfo outFlightInfo = getFlightInfo(request.getOutboundFltNum(), conn);
		FlightInfo retFlightInfo = getFlightInfo(request.getReturnFltNum(), conn);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(request.getDepartDate());
		cal.set(Calendar.HOUR, outFlightInfo.dhours);
		cal.set(Calendar.MINUTE, outFlightInfo.dminutes);
		if(outFlightInfo.dAM_PM.equalsIgnoreCase("AM"))
			cal.set(Calendar.AM_PM, Calendar.AM);
		else
			cal.set(Calendar.AM_PM, Calendar.PM);
		
		Date ddDate = cal.getTime();
		
		cal.set(Calendar.HOUR, outFlightInfo.ahours);
		cal.set(Calendar.MINUTE, outFlightInfo.aminutes);
		if(outFlightInfo.aAM_PM.equalsIgnoreCase("AM"))
			cal.set(Calendar.AM_PM, Calendar.AM);
		else
			cal.set(Calendar.AM_PM, Calendar.PM);
		if(outFlightInfo.nextDayArrival) {
			cal.roll(Calendar.DAY_OF_YEAR, 1);
		}
		Date daDate = cal.getTime();
		
		cal.setTime(request.getReturnDate());
		cal.set(Calendar.HOUR, retFlightInfo.dhours);
		cal.set(Calendar.MINUTE, retFlightInfo.dminutes);
		if(retFlightInfo.dAM_PM.equalsIgnoreCase("AM"))
			cal.set(Calendar.AM_PM, Calendar.AM);
		else
			cal.set(Calendar.AM_PM, Calendar.PM);
		
		Date rdDate = cal.getTime();
		
		cal.set(Calendar.HOUR, retFlightInfo.ahours);
		cal.set(Calendar.MINUTE, retFlightInfo.aminutes);
		if(retFlightInfo.aAM_PM.equalsIgnoreCase("AM"))
			cal.set(Calendar.AM_PM, Calendar.AM);
		else
			cal.set(Calendar.AM_PM, Calendar.PM);
		if(retFlightInfo.nextDayArrival) {
			cal.roll(Calendar.DAY_OF_YEAR, 1);
		}
		Date raDate = cal.getTime();
		
		ReservationDetails details = new ReservationDetails(request);
		details.setOutDeparture(ddDate);
		details.setOutArrival(daDate);
		details.setReturnDeparture(rdDate);
		details.setReturnArrival(raDate);
		details.setDepartTime(outFlightInfo.departure);
		details.setOutArriveTime(outFlightInfo.arrival);
		details.setReturnTime(retFlightInfo.departure);
		details.setRetArriveTime(retFlightInfo.arrival);
		details.setConfirmationNumber(confCode);
		float oprice = 0F;
		Float o = request.getOutPrice();
		if(o != null) {
			oprice = o;
		}
		details.setOutPrice(oprice);
		Float r = request.getReturnPrice();
		float rprice = 0F;
		if(r != null) {
			rprice = r;
		}
		details.setRetPrice(rprice);
		if(details.isValid()) {
			addReservation(details, conn);
			LOG.debug("Reserving outbound seat "+details.getOutSeat()+ " for "+details.getConfirmationNumber());
			occupySeat(request.getOutSeat(), request.getOutboundFltNum(), request.getDepartDate(), conn);
			LOG.debug("Reserving return seat "+details.getOutSeat()+ " for "+details.getConfirmationNumber());
			occupySeat(request.getRetSeat(), request.getReturnFltNum(), request.getReturnDate(), conn);
			
			LOG.debug("Creating response for "+details.getConfirmationNumber());
			CreateReservationResponse response = new CreateReservationResponse(confCode);
			String searchID = request.getProperty(SEARCHID);
			if(searchID != null) {
				response.setProperty(SEARCHID, searchID);
				response.setProperty(SEARCHSTATE, "Confirmed");
			}
			LOG.debug("Response sent for "+details.getConfirmationNumber());
			dumpResponseProperties(response);
			databaseConnectionPool.returnConnection(conn);
			return response;
		}
		databaseConnectionPool.returnConnection(conn);
		return null;
	}

	@Override
	public FindReservationsResponse findReservationsByName(String firstName, String lastName) {
		
		List<ReservationDetails> custReservations = getReservations(firstName, lastName);
		return new FindReservationsResponse(custReservations);
	}

	@Override
	public CitiesServedResponse getCitiesServed() {
		if(!citiesInitialized) {
			Connection conn = databaseConnectionPool.getConnection();
			String query = "SELECT DISTINCT fromAirport from flights";
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				citiesServed = new Vector<String>();
				while(rs.next()) {
					String airport = rs.getString("fromAirport");
					citiesServed.add(airport);
				}
			} catch (SQLException e) {
				LOG.error("SQLException:", e);
			}
			citiesInitialized = true;
			databaseConnectionPool.returnConnection(conn);
		}
		return new CitiesServedResponse(getCities());
	}

	@Override
	public FlightSearchResults findFlights(FlightSearchRequest request) {
		//dumpRequestProperties(request);
		FlightSearchResults results = new FlightSearchResults();
		Date outboundDate = request.getDepartDate();
		Date returnDate = request.getReturnDate();
		String departingFrom = request.getFromAirport();
		String arrivingTo = request.getToAirport();
		
		LOG.debug("Search for flight from "+departingFrom + " to "+arrivingTo);
		Connection conn = databaseConnectionPool.getConnection();
		
		if (conn != null) {
			String query1 = "SELECT * from flights WHERE FromAirport=? AND ToAirport=?";
			List<FlightDate> returnFlightDates;
			try {
				PreparedStatement pstmt1 = conn.prepareStatement(query1);
				pstmt1.setString(1, departingFrom);
				pstmt1.setString(2, arrivingTo);

				ResultSet rs = pstmt1.executeQuery();
				List<Flight> outFlights = new ArrayList<Flight>();
				List<FlightDate> outFlightsDates = new ArrayList<FlightDate>();
				while (rs.next()) {
					Flight flight = new Flight();
					String flightNumber = rs.getString("FlightNumber");
					String Depart = rs.getString("Depart");
					String Arrive = rs.getString("Arrive");
					String aircraft = rs.getString("Aircraft");
					String arrivalAirport = rs.getString("ToAirport");
					String departAirport = rs.getString("FromAirport");
					Float price = rs.getFloat("Price");

					flight.setAircraft(aircraft);
					flight.setArrival(Arrive);
					flight.setArriveAirport(arrivalAirport);
					flight.setDepartAirport(departAirport);
					flight.setDeparture(Depart);
					flight.setFlightNumber(flightNumber);
					flight.setPrice(price);

					SeatsInfo info = getAvailableSeats(flightNumber, outboundDate,conn);
					if (info.numberAvailable > 0) {
						outFlights.add(flight);
					}
				}

				for (Flight flight : outFlights) {
					SeatsInfo info = getAvailableSeats(flight.getFlightNumber(), outboundDate,conn);
					FlightDate outFlightDate = new FlightDate(outboundDate, flight.getFlightNumber(), info.seatsAvailable);

					if (outFlightDate.seatsAvailable()) {
						outFlightDate.setFlight(flight);
						outFlightsDates.add(outFlightDate);
					}
				}
				results.setOutFlights(outFlightsDates);

				pstmt1.clearParameters();
				pstmt1.setString(1, arrivingTo);
				pstmt1.setString(2, departingFrom);

				rs = pstmt1.executeQuery();
				List<Flight> returnFlights = new ArrayList<Flight>();
				returnFlightDates = new ArrayList<FlightDate>();
				while (rs.next()) {
					Flight flight = new Flight();
					String flightNumber = rs.getString("FlightNumber");
					String Depart = rs.getString("Depart");
					String Arrive = rs.getString("Arrive");
					String aircraft = rs.getString("Aircraft");
					String arrivalAirport = rs.getString("ToAirport");
					String departAirport = rs.getString("FromAirport");

					flight.setAircraft(aircraft);
					flight.setArrival(Arrive);
					flight.setArriveAirport(arrivalAirport);
					flight.setDepartAirport(departAirport);
					flight.setDeparture(Depart);
					flight.setFlightNumber(flightNumber);
					Float price = rs.getFloat("Price");
					flight.setPrice(price);

					SeatsInfo info = getAvailableSeats(flightNumber, outboundDate,conn);
					if (info.numberAvailable > 0) {
						returnFlights.add(flight);
					}
				}

				for (Flight flight : returnFlights) {
					SeatsInfo info = getAvailableSeats(flight.getFlightNumber(), returnDate,conn);

					FlightDate retFlightDate = new FlightDate(returnDate, flight.getFlightNumber(), info.seatsAvailable);
					if (retFlightDate.seatsAvailable()) {
						retFlightDate.setFlight(flight);

						returnFlightDates.add(retFlightDate);
					}
				}
				results.setReturnFlights(returnFlightDates);
			} catch (SQLException e1) {
				LOG.error("SQLException:", e1);
			}
		}
		String searchId = request.getProperty(SEARCHID);
		if (searchId == null) {
			searchId = getSearchID(new SearchIDRequest()).getSearchID();
		}
		results.setProperty(SEARCHID, searchId);
		results.setProperty(SEARCHSTATE, "Searching");
		dumpResponseProperties(results);
		databaseConnectionPool.returnConnection(conn);
		if(wait_period > 0L) {
			try {
				synchronized (this) {
					wait(wait_period);
				}
			} catch (InterruptedException e) {
				LOG.error(e.getClass().getSimpleName(), e);
			}
		}
		
		return results;
	}
	
	@Override
	public FindReservationsResponse findAllReservations() {
		List<ReservationDetails> list = new ArrayList<ReservationDetails>();
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm aa");
		Connection conn = databaseConnectionPool.getConnection();
		if (conn != null) {
			try {
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM reservations";
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					ReservationDetails details = new ReservationDetails();
					details.setConfirmationNumber(rs.getString("ConfirmationNumber"));
					details.setFirstName(rs.getString("FirstName"));
					details.setLastName(rs.getString("LastName"));
					details.setFromAirport(rs.getString("FromAirport"));
					details.setToAirport(rs.getString("ToAirport"));
					java.sql.Date outDeparture = rs.getDate("OutDeparture");
					details.setDepartDate(outDeparture);
					details.setDepartTime(sdf2.format(outDeparture));
					java.sql.Date outArrival = rs.getDate("OutArrival");
					details.setOutArriveTime(sdf2.format(outArrival));
					details.setOutFlightNumber(rs.getString("OutFlightNumber"));
					details.setOutSeat(rs.getString("OutSeat"));

					java.sql.Date retDeparture = rs.getDate("ReturnDeparture");
					details.setReturnDate(retDeparture);
					details.setReturnTime(sdf2.format(retDeparture));
					java.sql.Date retArrival = rs.getDate("ReturnArrival");
					details.setRetArriveTime(sdf2.format(retArrival));
					details.setRetFlightNumber(rs.getString("ReturnFlightNumber"));
					details.setRetSeat(rs.getString("ReturnSeat"));
					list.add(details);
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				LOG.error("SQLException:", e);
			}
		}
		databaseConnectionPool.returnConnection(conn);
		return new FindReservationsResponse(list);
	}

	private SeatsInfo getAvailableSeats(String flightNumber, Date flightDate, Connection conn) {
		List<String> seats = new ArrayList<String>();
		String query = "select * from capacity where FlightNumber=? AND FlightDate=?";
		int numSeatsAvailable = -1;
		
		if (conn != null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, flightNumber);
				pstmt.setDate(2, new java.sql.Date(flightDate.getTime()));
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					numSeatsAvailable = rs.getInt("SeatsAvailable");
					if (numSeatsAvailable > 0) {
						String seatsAvailable = rs.getString("AvailableSeats");
						StringTokenizer st = new StringTokenizer(seatsAvailable, ",");
						while (st.hasMoreTokens()) {
							seats.add(st.nextToken());
						}
					}
				}
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				LOG.error("SQLException:", e);
			}
			if (numSeatsAvailable == -1) {
				String query2 = "select aircraft from flights where FlightNumber=?";
				String aircraft = "737";
				
				try {
					PreparedStatement pstmt2 = conn.prepareStatement(query2);
					pstmt2.setString(1, flightNumber);
					ResultSet rs2 = pstmt2.executeQuery();
					if(rs2.next()) {
						aircraft = rs2.getString("aircraft");
					}
				} catch (SQLException e) {
					LOG.warn("Failed to find aircraft for "+flightNumber, e);
				}

				if(aircraft.equals("A330") || aircraft.equals("777")) {
					seats = new ArrayList<String>(newSeatList2);
				} else {
					seats = new ArrayList<String>(newSeatList);
				}
				numSeatsAvailable = seats.size();
			}
			Collections.sort(seats);
		}
		return new SeatsInfo(seats, numSeatsAvailable);
	}
	
	private void returnSeat(String seat, String flightNumber, Date flightDate, Connection conn) {
		SeatsInfo info = getAvailableSeats(flightNumber, flightDate, conn);
		List<String> seats = info.getSeatsAvailable();
		int numSeats = info.numberAvailable;
		
		seats.add(seat);
		Collections.sort(seats);
		numSeats++;
		
		if (conn != null) {
			try {
				String update = "update capacity set SeatsAvailable=?,AvailableSeats=? WHERE FlightNumber=? AND FlightDate=?";
				PreparedStatement pstmt = conn.prepareStatement(update);
				pstmt.setInt(1, numSeats);
				pstmt.setString(2, StringUtils.join(seats, ","));
				pstmt.setString(3, flightNumber);
				pstmt.setDate(4, new java.sql.Date(flightDate.getTime()));
				int c = pstmt.executeUpdate();
				if (c != 1) {
					LOG.error("updating row in capacity returned " + c);
				}

				pstmt.close();

			} catch (SQLException e) {
				LOG.error("SQLException:", e);
			}
		}
	}
	
	private boolean occupySeat(String seat, String flightNumber, Date flightDate, Connection conn) {
		boolean succussful = false;
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		LOG.debug("Attempting to reserve seat: "+seat+" on "+flightNumber+" for "+sdf.format(flightDate));
		SeatsInfo info = getAvailableSeats(flightNumber, flightDate, conn);
		List<String> seats = info.getSeatsAvailable();
		succussful = seats.remove(seat);
		int numSeats = info.getNumberAvailable();
		
		Collections.sort(seats);
		
		numSeats--;
		
		if (conn != null) {
			try {
				LOG.debug("Checking if capacity record exists");

				String check = "select count(*) as count from capacity where FlightNumber=? AND FlightDate=?";
				PreparedStatement pstmt1 = conn.prepareStatement(check);
				pstmt1.setString(1, flightNumber);
				pstmt1.setDate(2, new java.sql.Date(flightDate.getTime()));
				ResultSet rs = pstmt1.executeQuery();
				int count = 0;
				while (rs.next()) {
					count = rs.getInt("count");
				}
				rs.close();
				pstmt1.close();

				if (count > 0) {
					LOG.debug("Record did exists, updating record");
					String update = "update capacity set SeatsAvailable=?,AvailableSeats=? WHERE FlightNumber=? AND FlightDate=?";
					PreparedStatement pstmt = conn.prepareStatement(update);
					pstmt.setInt(1, numSeats);
					pstmt.setString(2, StringUtils.join(seats, ","));
					pstmt.setString(3, flightNumber);
					pstmt.setDate(4, new java.sql.Date(flightDate.getTime()));
					int c = pstmt.executeUpdate();
					if (c != 1) {
						LOG.warn("update of capacity record returned " + c);
					}
					pstmt.close();
				} else {
					LOG.debug("Record did not exist, create new record");
					String insert = "insert into capacity(SeatsAvailable,AvailableSeats,FlightNumber,FlightDate,TotalSeats) VALUES(?,?,?,?,?)";
					PreparedStatement pstmt2 = conn.prepareStatement(insert);
					pstmt2.setInt(1, numSeats);
					pstmt2.setString(2, StringUtils.join(seats, ","));
					pstmt2.setString(3, flightNumber);
					pstmt2.setDate(4, new java.sql.Date(flightDate.getTime()));
					pstmt2.setInt(5, numSeats+1);
					int c = pstmt2.executeUpdate();
					if (c != 1) {
						LOG.error("Inserting new row into capacity returned " + c);
					}
					pstmt2.close();
					LOG.debug("Created new record");
				}
			} catch (SQLException e) {
				LOG.error(e.getClass().getSimpleName(), e);
			}
			LOG.debug("Seat: " + seat + " on " + flightNumber + " for " + sdf.format(flightDate) + "has been reserved");
		}
		return succussful;
	}
	@Override
	public CancelResponse cancelReservation(String confirmation) {
		String result = "";
		String update = "delete from reservations where ConfirmationNumber=?";
		Connection conn = databaseConnectionPool.getConnection();
		float price = 0F;
		
		if (conn != null) {
			ReservationDetails request = getReservation(confirmation,conn);
			if (request == null) {
				result = "Reservation does not exist";
			} else {
				int c = -1;
				try {
					PreparedStatement pstmt = conn.prepareStatement(update);
					pstmt.setString(1, confirmation);
					c = pstmt.executeUpdate();
				} catch (SQLException e1) {
					LOG.error("SQLException:", e1);
				}
				if (c != 1) {
					LOG.error("Delete of confirmation " + confirmation + " returned " + c);
				} else {
					result = "Reservation Cancelled";
					price = request.getPrice();
					reportDateRevenue(request.getDepartDate(), -request.getOutPrice(), conn);
					reportDateRevenue(request.getReturnDate(), -request.getRetPrice(), conn);
					addRevenue(-price,request.getOutFlightNumber(),request.getDepartDate(),request.getRetFlightNumber(),request.getReturnDate(), conn);
					returnSeat(request.getOutSeat(), request.getOutFlightNumber(), request.getDepartDate(), conn);
					returnSeat(request.getRetSeat(), request.getRetFlightNumber(), request.getReturnDate(), conn);

				}
			}
		} else {
			result = "Database Unavailable, try again later";
		}
		databaseConnectionPool.returnConnection(conn);
		
		return new CancelResponse(result,price);
	}
	
	protected void purgeReservations() {
		Connection conn = databaseConnectionPool.getConnection();
		if (conn != null) {
			try {
				SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DAY_OF_MONTH);
				cal.set(year, month, day, 0, 0);
				java.sql.Date midnight = new java.sql.Date(cal.getTimeInMillis());

				String delete = "DELETE FROM reservations WHERE ReturnArrival < ?";
				PreparedStatement pstmt = conn.prepareStatement(delete);
				pstmt.setDate(1, midnight);
				int c = pstmt.executeUpdate();
				pstmt.close();
				LOG.info("Purged " + c + " reservations that were older than " + sdf2.format(midnight));
			} catch (SQLException e) {
				LOG.error("SQLException:", e);
			}
		}
		databaseConnectionPool.returnConnection(conn);
	}

	private int getCapacity(String aircraft, Connection conn) {
		int c = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement("select aircraftcapacity from aircraft where aircraftname=?");
			pstmt.setString(1, aircraft);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				c = rs.getInt("aircraftcapacity");
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			LOG.error("SQLException:", e);
		}
		return c;
	}
	protected void addNewCapacity() {
		Connection conn = databaseConnectionPool.getConnection();

		try {
			ScheduleListResponse scheduleResponse = getSchedule();
			
			String insert = "insert into capacity(SeatsAvailable,AvailableSeats,FlightNumber,FlightDate,TotalSeats) VALUES(?,?,?,?,?)";
			PreparedStatement pstmt2 = conn.prepareStatement(insert);
			Calendar cal = Calendar.getInstance();
			HashMap<String, List<Flight>> flights = scheduleResponse.getFlights();
			for (int i = 0; i < 90; i++) {
				cal.add(Calendar.DAY_OF_YEAR, 1);
				java.sql.Date d = new java.sql.Date(cal.getTimeInMillis());
				Set<String> keys = flights.keySet();
				for (String key : keys) {
					List<Flight> flightList = flights.get(key);
					for (Flight flight : flightList) {
						String flightNumber = flight.getFlightNumber();

						String query = "select * from capacity where FlightNumber=? AND flightdate=?";
						PreparedStatement pstmt1 = conn.prepareStatement(query);
						pstmt1.setString(1, flightNumber);
						pstmt1.setDate(2, d);
						ResultSet rs = pstmt1.executeQuery();
						if (!rs.next()) {
							String aircraft = flight.getAircraft();
							int capacity = getCapacity(aircraft, conn);
							pstmt2.clearParameters();
							pstmt2.setInt(1, capacity);

							List<String> seats;
							if (capacity == 80) {
								seats = newSeatList;
							} else {
								seats = newSeatList2;
							}

							pstmt2.setString(2, StringUtils.join(seats, ","));
							pstmt2.setString(3, flightNumber);
							pstmt2.setDate(4, new java.sql.Date(d.getTime()));
							pstmt2.setInt(5, capacity);
							int c = pstmt2.executeUpdate();
							if (c != 1) {
								LOG.error("Inserting new row into capacity returned " + c);
							}

						}
						rs.close();
						pstmt1.close();

					}
				}
			}
			pstmt2.close();
		} catch (SQLException e) {
			LOG.error("SQLException:", e);
		}
		databaseConnectionPool.returnConnection(conn);
	}
	
	
	
	protected void purgeCapacity() {
		Connection conn = databaseConnectionPool.getConnection();
		if (conn != null) {
			try {
				SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DAY_OF_MONTH);
				cal.set(year, month, day, 0, 0);
				java.sql.Date midnight = new java.sql.Date(cal.getTimeInMillis());
				
				String getRecords = "SELECT * from capacity WHERE FlightDate < ?";
				PreparedStatement pstmt2 = conn.prepareStatement(getRecords);
				pstmt2.setDate(1, midnight);
				ResultSet rs = pstmt2.executeQuery();
				String insert = "Insert INTO capacityarchive(FlightNumber,FlightDate,Capacity) VALUES(?,?,?)";
				PreparedStatement pstmt3 = conn.prepareStatement(insert);
				while(rs.next()) {
					String flightNumber = rs.getString("FlightNumber");
					java.sql.Date flightDate = rs.getDate("FlightDate");
					int available = rs.getInt("SeatsAvailable");
					int total = rs.getInt("TotalSeats");
					float pc = (float)available/(float)total;
					pstmt3.clearParameters();
					pstmt3.setString(1, flightNumber);
					pstmt3.setDate(2, flightDate);
					pstmt3.setFloat(3, pc);
					pstmt3.executeUpdate();
				}
				rs.close();
				pstmt3.close();
				pstmt2.close();
				
				String delete = "DELETE FROM capacity WHERE FlightDate < ?";
				PreparedStatement pstmt = conn.prepareStatement(delete);
				pstmt.setDate(1, midnight);
				int c = pstmt.executeUpdate();
				pstmt.close();
				LOG.info("Purged " + c + " capacity records that were older than " + sdf2.format(midnight));
			} catch (SQLException e) {
				LOG.error("SQLException:", e);
			}
		}
		databaseConnectionPool.returnConnection(conn);
	}

	@Override
	public ScheduleListResponse getSchedule() {
		ScheduleListResponse response = new ScheduleListResponse();
		HashMap<String, List<Flight>> flights = new HashMap<String, List<Flight>>();
		
		Connection conn = databaseConnectionPool.getConnection();
		if (conn != null) {
			String query = "Select * from flights";
			try {
				Statement stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					String flightNumber = rs.getString("FlightNumber");
					String depart = rs.getString("Depart");
					String arrive = rs.getString("Arrive");
					String aircraft = rs.getString("Aircraft");
					String fromAirport = rs.getString("FromAirport");
					String toAirport = rs.getString("ToAirport");
					float price = rs.getFloat("Price");

					Flight flight = new Flight();
					flight.setAircraft(aircraft);
					flight.setArrival(arrive);
					flight.setArriveAirport(toAirport);
					flight.setDepartAirport(fromAirport);
					flight.setDeparture(depart);
					flight.setFlightNumber(flightNumber);
					flight.setPrice(price);

					String s = fromAirport + " - " + toAirport;
					List<Flight> flightList = flights.get(s);
					if (flightList == null) {
						flightList = new ArrayList<Flight>();
					}
					flightList.add(flight);
					flights.put(s, flightList);
				}
			} catch (SQLException e) {
				LOG.error("SQLException:", e);
			}
		}
		databaseConnectionPool.returnConnection(conn);
		response.setFlights(flights );
		return response;
	}

	@Override
	public SearchIDResponse getSearchID(SearchIDRequest request) {
		//dumpRequestProperties(request);
		int id = Integer.parseInt(nextSearchID.substring(2));
		String searchID = nextSearchID;
		
		id++;
		nextSearchID = "NR" + id;
		try {
			FileOutputStream resOut = new FileOutputStream(searchIDFilename);
			ObjectOutputStream resOutStream = new ObjectOutputStream(resOut);
			resOutStream.writeObject(nextSearchID);
			resOutStream.close();
		} catch (FileNotFoundException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		} catch (IOException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
	
		return new SearchIDResponse(searchID);
	}

	@Override
	public CreateFlightResponse addNewFlightToSchedule(CreateFlightRequest request) {
		Connection conn = databaseConnectionPool.getConnection();
		String insert = "insert into flights(FlightNumber, Depart, Arrive, Aircraft, FromAirport, ToAirport, Price) VALUES(?,?,?,?,?,?,?)";
		Flight flight = request.getFlight();
		boolean check = false;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, flight.getFlightNumber());
			pstmt.setString(2, flight.getDeparture());
			pstmt.setString(3, flight.getArrival());
			pstmt.setString(4, flight.getAircraft());
			pstmt.setString(5, flight.getDepartAirport());
			pstmt.setString(6, flight.getArriveAirport());
			pstmt.setFloat(7, flight.getPrice());
			
			int c = pstmt.executeUpdate();
			pstmt.close();
			check = c == 1;
		} catch (SQLException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
		databaseConnectionPool.returnConnection(conn);
		
		return new CreateFlightResponse(check);
	}
	
	@Override
	public UpdateFlightResponse updateFlight(UpdateFlightRequest request) {
		Connection conn = databaseConnectionPool.getConnection();
		String update = "update flights set FlightNumber=?, Depart=?, Arrive=?, Aircraft=?, FromAirport=?, ToAirport=?, Price=?) where FlightNumber=?";
		Flight origFlight = request.getOriginalFlight();
		Flight modFlight = request.getModifiedFlight();
		boolean check = false;

		if(!origFlight.equals(modFlight)) { 
			StringBuffer sb = new StringBuffer("update flights set ");
			
			boolean sameflightNumber = origFlight.getFlightNumber().equals(modFlight.getFlightNumber()) || modFlight.getFlightNumber() == null || modFlight.getFlightNumber().equals("");
			boolean sameDeparture = origFlight.getDeparture().equals(modFlight.getDeparture()) || modFlight.getDeparture() == null || modFlight.getDeparture().equals("");
			boolean sameArrival = origFlight.getArrival().equals(modFlight.getArrival()) || modFlight.getArrival() == null || modFlight.getArrival().equals("");
			boolean sameFromAirport = origFlight.getDepartAirport().equals(modFlight.getDepartAirport()) || modFlight.getDepartAirport() == null || modFlight.getDepartAirport().equals("");
			boolean sameToAirport = origFlight.getArriveAirport().equals(modFlight.getArriveAirport()) || modFlight.getArriveAirport() == null || modFlight.getArriveAirport().equals("");
			boolean sameAircraft = origFlight.getAircraft().equals(modFlight.getAircraft()) || modFlight.getAircraft() == null || modFlight.getAircraft().equals("");
			boolean samePrice = origFlight.getPrice() == modFlight.getPrice() || modFlight.getPrice() == null || (modFlight.getPrice() != null && modFlight.getPrice() <= 0.01);

			boolean added = false;
			if(!sameflightNumber) {
				sb.append("FlightNumber=?");
				added = true;
			}
			if(!sameDeparture) {
				if(added) {
					sb.append(", ");
				}
				sb.append("Depart=?");
				added = true;
			}
			if(!sameArrival) {
				if(added) {
					sb.append(", ");
				}
				sb.append("Arrive=?");
				added = true;
			}
			if(!sameFromAirport) {
				if(added) {
					sb.append(", ");
				}
				sb.append("FromAirport=?");
				added = true;
			}
			if(!sameToAirport) {
				if(added) {
					sb.append(", ");
				}
				sb.append("ToAirport=?");
				added = true;
			}
			if(!sameAircraft) {
				if(added) {
					sb.append(", ");
				}
				sb.append("Aircraft=?");
				added = true;
			}
			if(!samePrice) {
				if(added) {
					sb.append(", ");
				}
				sb.append("Price=?");
				added = true;
			}
			sb.append(" where FlightNumber=?");
			
			update = sb.toString();
			
			try {
				PreparedStatement pstmt = conn.prepareStatement(update);
				int col = 1;
				if(!sameflightNumber) {
					pstmt.setString(col++, modFlight.getFlightNumber());
				}
				if(!sameDeparture) {
					pstmt.setString(col++, modFlight.getDeparture());
				}
				if(!sameArrival) {
					pstmt.setString(col++, modFlight.getArrival());
				}
				if(!sameFromAirport) {
					pstmt.setString(col++, modFlight.getDepartAirport());
				}
				if(!sameToAirport) {
					pstmt.setString(col++, modFlight.getArriveAirport());
				}
				if(!sameAircraft) {
					pstmt.setString(col++, modFlight.getAircraft());
				}
				if(!samePrice) {
					pstmt.setFloat(col++, modFlight.getPrice());
				}
				pstmt.setString(col, origFlight.getFlightNumber());
				int c = pstmt.executeUpdate();
				pstmt.close();
				check = c == 1;
			} catch (SQLException e) {
				LOG.error(e.getClass().getSimpleName(), e);
			}
		}
		databaseConnectionPool.returnConnection(conn);
		
		return new UpdateFlightResponse(check);
	}



	@Override
	public CancelSearchResponse cancelSearch(CancelSearchRequest request) {
		//dumpRequestProperties(request);
		String searchID = request.getProperty(SEARCHID);
		CancelSearchResponse response = new CancelSearchResponse();
		if(searchID != null) {
			response.setProperty(SEARCHID, searchID);
			response.setProperty(SEARCHSTATE, "SearchCancelled");
		}
		dumpResponseProperties(response);
		return response;
	}
	
/*	protected void dumpRequestProperties(BaseRequest request) {
		System.out.println("Dumping Request Properties");
		Properties props = request.getProperties();
		Set<Object> keys = props.keySet();
		if(keys.size() == 0) {
			System.out.println("\tNo properties found");
		}
		for(Object key : keys) {
			Object value = props.get(key);
			System.out.println("\tProperty "+key.toString()+": "+value.toString());
		}
	}*/

	protected void dumpResponseProperties(BaseResponse response) {
		LOG.debug("Dumping Response Properties");
		Properties props = response.getProperties();
		Set<Object> keys = props.keySet();
		if(keys.size() == 0) {
			LOG.debug("\tNo properties found");
		}
		for(Object key : keys) {
			Object value = props.get(key);
			LOG.debug("\tProperty "+key.toString()+": "+value.toString());
		}
	}
	
	private class SeatsInfo {
		List<String> seatsAvailable;
		int numberAvailable;
		
		
		private List<String> getSeatsAvailable() {
			return seatsAvailable;
		}


		private int getNumberAvailable() {
			return numberAvailable;
		}


		private SeatsInfo(List<String> seatsAvailable, int numberAvailable) {
			super();
			this.seatsAvailable = seatsAvailable;
			this.numberAvailable = numberAvailable;
		}
		
		
	}
	
	private class FlightInfo {
		private String flightNumber;
		private String departure;
		private String arrival;
		private int dhours;
		private int dminutes;
		private String dAM_PM;
		private int ahours;
		private int aminutes;
		private String aAM_PM;
		private boolean nextDayArrival;
	}

	@Override
	public GetWaitPeriodResponse getWaitPeriod(GetWaitPeriodRequest request) {
		return new GetWaitPeriodResponse(wait_period);
	}

	@Override
	public SetWaitPeriodResponse setWaitPeriod(SetWaitPeriodRequest request) {
		long old_wait = wait_period;
		long new_wait = request.getWait_period();
		wait_period = request.getWait_period();
		
		return new SetWaitPeriodResponse(new_wait, old_wait);
	}

	@Override
	public FlightRosterResponse getFlightRoster(FlightRosterRequest request) {
		String query = "select * from reservations where OutFlightNumber=? AND DATE_FORMAT(OutDeparture,'%Y-%m-%d')=?";
		String query2 = "select * from reservations where ReturnFlightNumber=? AND DATE_FORMAT(ReturnDeparture,'%Y-%m-%d')=?";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Connection conn = databaseConnectionPool.getConnection();
		FlightRosterResponse response = new FlightRosterResponse();
		response.setFlightNumber(request.getFlightNumber());
		response.setFlightDate(request.getFlightDate());
		
		List<SeatInfo> seats = new ArrayList<SeatInfo>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, request.getFlightNumber());
			pstmt.setString(2, sdf.format(request.getFlightDate()));
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String confirmation = rs.getString("ConfirmationNumber");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				String seat = rs.getString("OutSeat");
				SeatInfo seatinfo = new SeatInfo(firstName, lastName, confirmation, seat);
				seats.add(seatinfo);
			}
			pstmt.close();
			rs.close();
			
			PreparedStatement pstmt3 = conn.prepareStatement(query2);
			pstmt3.setString(1, request.getFlightNumber());
			pstmt3.setString(2, sdf.format(request.getFlightDate()));
			
			ResultSet rs2 = pstmt3.executeQuery();
			
			while(rs2.next()) {
				String confirmation = rs2.getString("ConfirmationNumber");
				String firstName = rs2.getString("FirstName");
				String lastName = rs2.getString("LastName");
				String seat = rs2.getString("ReturnSeat");
				SeatInfo seatinfo = new SeatInfo(firstName, lastName, confirmation, seat);
				seats.add(seatinfo);
			}
			pstmt3.close();
			rs2.close();
			
			response.setSeats(seats);
			response.setSeatsOccupied(seats.size());
			
			String findFlight = "select * from flights where FlightNumber=?";
			PreparedStatement pstmt2 = conn.prepareStatement(findFlight);
			pstmt2.setString(1, request.getFlightNumber());
			
			ResultSet rs3 = pstmt2.executeQuery();
			if(rs3.next()) {
				String fromAirport = rs3.getString("FromAirport");
				String toAirport = rs3.getString("ToAirport");
				String depart = rs3.getString("Depart");
				String arrival = rs3.getString("Arrive");
				String aircraft = rs3.getString("Aircraft");
				int totalSeats;
				if(aircraft.equals("A330") || aircraft.equals("777")) {
					totalSeats = newSeatList2.size();
				} else {
					totalSeats = newSeatList.size();
				}
				
				response.setFromAirport(fromAirport);
				response.setToAirport(toAirport);
				response.setDeparture(depart);
				response.setArrival(arrival);
				response.setAircraft(aircraft);
				response.setTotalSeats(totalSeats);
			}
			
		} catch (SQLException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
		
		databaseConnectionPool.returnConnection(conn);
		return response;
	}

	@Override
	public RevenueResponse getRevenue(RevenueSinceRequest request) {
		RevenueResponse response = new RevenueResponse();
		Connection conn = databaseConnectionPool.getConnection();
		float sum = 0F;
		try {
			String query = "select * from revenue where timestamp >= ?";
//			java.sql.Date since = new java.sql.Date(request.getSince());
			Timestamp since = new Timestamp(request.getSince());
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setTimestamp(1, since);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				float f = rs.getFloat("revenue");
				Timestamp ts = rs.getTimestamp("timestamp");
				String outflight = rs.getString("outFlightNumber");
				Date outDate = rs.getDate("OutFlightDate");
				String retflight = rs.getString("retFlightNumber");
				Date retdate = rs.getDate("retFlightDate");
				
				Revenue rev = new Revenue();
				List<String> flights = request.getFlights();
				if(flights == null || flights.isEmpty()) {
					rev.setRevenue(f);
					rev.setTimestamp(ts);
					rev.setOutFlightNumber(outflight);
					rev.setOutFlightDate(outDate);
					rev.setRetFlightNumber(retflight);
					rev.setRetFlightDate(retdate);
					response.addRevenue(rev);
					sum += f;
				} else if(flights.contains(outflight)) {
					rev.setRevenue(f/2.0F);
					rev.setTimestamp(ts);
					rev.setOutFlightNumber(outflight);
					rev.setOutFlightDate(outDate);
					response.addRevenue(rev);
					sum += f/2.0F;
				} else if(flights.contains(retflight)) {
					rev.setRevenue(f/2.0F);
					rev.setTimestamp(ts);
					rev.setRetFlightNumber(retflight);
					rev.setRetFlightDate(retdate);
					response.addRevenue(rev);
					sum += f/2.0F;
				}
			}
		} catch (SQLException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
		response.setRevenueSum(sum);
		databaseConnectionPool.returnConnection(conn);
		
		return response;
	}

	@Override
	public CapacityByDateResponse getCapacityByDate(CapacityByDateRequest request) {
		CapacityByDateResponse response = new CapacityByDateResponse();
		java.sql.Date start = request.getStart();
		response.setStart(start);
		java.sql.Date end = request.getEnd();
		
		response.setEnd(end);
		Connection conn = databaseConnectionPool.getConnection();
		PreparedStatement pstmt;
		
		try {
			if(end != null) {
				String query = "select * from capacity where flightdate >=?  AND flightdate <= ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setDate(1, start);
				pstmt.setDate(2, end);
			} else {
				String query = "select * from capacity where flightdate =?";
				pstmt = conn.prepareStatement(query);
				pstmt.setDate(1, start);
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				CapacityRecord record = new CapacityRecord();
				record.setAvailable(rs.getInt("SeatsAvailable"));
				record.setFlightDate(rs.getDate("flightdate"));
				record.setFlightNumber(rs.getString("flightnumber"));
				record.setTotalSeats(rs.getInt("TotalSeats"));
				response.addCapacity(record);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		databaseConnectionPool.returnConnection(conn);
		
		return response;
	}

	@Override
	public CapacityByFlightsResponse getCapacityByFlights(CapacityByFlightsRequest request) {
		CapacityByFlightsResponse response = new CapacityByFlightsResponse();
		java.sql.Date start = request.getStart();
		response.setStart(start);
		java.sql.Date end = request.getEnd();
		response.setEnd(end);
		Connection conn = databaseConnectionPool.getConnection();
		PreparedStatement pstmt;
		
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		List<String> flights = request.getFlights();
		List<String> capFlights = new ArrayList<String>();
		int size = flights.size();
		for(int i=0;i<size;i++) {
			sb.append("\"");
			sb.append(flights.get(i));
			sb.append("\"");
			if(i < size -1) {
				sb.append(",");
			}
		}
		sb.append(")");
		
		try {
			if(end != null) {
				String query = "select * from capacity where flightdate >=?  AND flightdate <= ? AND FlightNumber IN "+sb.toString();
				pstmt = conn.prepareStatement(query);
				pstmt.setDate(1, start);
				pstmt.setDate(2, end);
			} else {
				String query = "select * from capacity where flightdate =? AND FlightNumber IN "+sb.toString();
				pstmt = conn.prepareStatement(query);
				pstmt.setDate(1, start);
			}
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				CapacityRecord record = new CapacityRecord();
				record.setAvailable(rs.getInt("SeatsAvailable"));
				record.setFlightDate(rs.getDate("flightdate"));
				String flightNumber = rs.getString("flightnumber");
				if(flightNumber != null && !flightNumber.equals("")) {
					if(!capFlights.contains(flightNumber)) {
						capFlights.add(flightNumber);
					}
				}
				record.setFlightNumber(flightNumber);
				record.setTotalSeats(rs.getInt("TotalSeats"));
				response.addCapacity(record);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		databaseConnectionPool.returnConnection(conn);
		response.setFlights(capFlights);
		return response;
	}

	@Override
	public RevenueDateResponse getRevenue(RevenueDateRequest request) {
		RevenueDateResponse response = new RevenueDateResponse();
		Connection conn = databaseConnectionPool.getConnection();
		float sum = 0F;
		
		try {
			String query = "select * from revenue where timestamp >= ? AND timestamp >= ?";
			Timestamp from = request.getFromTimestamp();
			Timestamp to = request.getToTimestamp();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setTimestamp(1, from);
			pstmt.setTimestamp(2, to);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				float f = rs.getFloat("revenue");
				Timestamp ts = rs.getTimestamp("timestamp");
				String outflight = rs.getString("outFlightNumber");
				Date outDate = rs.getDate("OutFlightDate");
				String retflight = rs.getString("retFlightNumber");
				Date retdate = rs.getDate("retFlightDate");
				
				Revenue rev = new Revenue();
				List<String> flights = request.getFlights();
				if(flights == null || flights.isEmpty()) {
					rev.setRevenue(f);
					rev.setTimestamp(ts);
					rev.setOutFlightNumber(outflight);
					rev.setOutFlightDate(outDate);
					rev.setRetFlightNumber(retflight);
					rev.setRetFlightDate(retdate);
					response.addRevenue(rev);
					sum += f;
				} else if(flights.contains(outflight)) {
					rev.setRevenue(f/2.0F);
					rev.setTimestamp(ts);
					rev.setOutFlightNumber(outflight);
					rev.setOutFlightDate(outDate);
					response.addRevenue(rev);
					sum += f/2.0F;
				} else if(flights.contains(retflight)) {
					rev.setRevenue(f/2.0F);
					rev.setTimestamp(ts);
					rev.setRetFlightNumber(retflight);
					rev.setRetFlightDate(retdate);
					response.addRevenue(rev);
					sum += f/2.0F;
				}
			}
		} catch (SQLException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
		response.setRevenueSum(sum);
		databaseConnectionPool.returnConnection(conn);
		
		return response;
	}

}

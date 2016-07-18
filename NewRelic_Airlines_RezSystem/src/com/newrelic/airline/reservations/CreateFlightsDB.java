package com.newrelic.airline.reservations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.newrelic.airline.reservations.forms.Flight;

public class CreateFlightsDB {

	private static Vector<String> citiesServed;
	private static final String scheduleFilename = "FlightSchedule.dat";
	
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
		return cities;
	}
	
	private static HashMap<String, List<Flight>> flights;
	
	static void create() {
		flights = new HashMap<String, List<Flight>>();
		
/*		String[] seats = new String[20];
		int n = 0;
		for(int i=1;i<6;i++) {
			for(int j=1;j<5;j++) {
				switch (j) {
				case 1: 
					seats[n++] = i + "A";
					break;
				case 2:
					seats[n++] = i + "B";
					break;
				case 3:
					seats[n++] = i + "C";
					break;
				case 4:
					seats[n++] = i + "D";
					break;
				default:
					break;
				}
			}
		}
*/		
		ArrayList<Flight> destflights = new ArrayList<Flight>();
		
		Flight flight1 = new Flight();
		flight1.setAircraft("737");
		flight1.setArrival("9:30 AM");
		flight1.setArriveAirport("Portland");
		flight1.setDepartAirport("San Francisco");
		flight1.setDeparture("8:00 AM");
		flight1.setFlightNumber("NR 100");
		destflights.add(flight1);
		
		Flight flight2 = new Flight();
		flight2.setAircraft("737");
		flight2.setArrival("1:30 PM");
		flight2.setArriveAirport("Portland");
		flight2.setDepartAirport("San Francisco");
		flight2.setDeparture("12:00 PM");
		flight2.setFlightNumber("NR 200");
		destflights.add(flight2);
		
		Flight flight3 = new Flight();
		flight3.setAircraft("A320");
		flight3.setArrival("8:30 PM");
		flight3.setArriveAirport("Portland");
		flight3.setDepartAirport("San Francisco");
		flight3.setDeparture("7:00 PM");
		flight3.setFlightNumber("NR 300");
		destflights.add(flight3);
		
		flights.put("San Francisco-Portland", destflights);
		
		destflights = new ArrayList<Flight>();
		flight1 = new Flight();
		flight1.setAircraft("737");
		flight1.setArrival("11:45 AM");
		flight1.setArriveAirport("San Francisco");
		flight1.setDepartAirport("Portland");
		flight1.setDeparture("10:30 AM");
		flight1.setFlightNumber("NR 101");
		destflights.add(flight1);
		
		flight2 = new Flight();
		flight2.setAircraft("737");
		flight2.setArrival("4:00 PM");
		flight2.setArriveAirport("San Francisco");
		flight2.setDepartAirport("Portland");
		flight2.setDeparture("2:15 PM");
		flight2.setFlightNumber("NR 201");
		destflights.add(flight2);
		
		flight3 = new Flight();
		flight3.setAircraft("A320");
		flight3.setArrival("11:00 PM");
		flight3.setArriveAirport("San Francisco");
		flight3.setDepartAirport("Portland");
		flight3.setDeparture("9:30 PM");
		flight3.setFlightNumber("NR 301");
		destflights.add(flight3);
		flights.put("Portland-San Francisco", destflights);
		
		destflights = new ArrayList<Flight>();
		flight1 = new Flight();
		flight1.setAircraft("737");
		flight1.setArrival("10:30 AM");
		flight1.setArriveAirport("Seattle");
		flight1.setDepartAirport("San Francisco");
		flight1.setDeparture("8:30 AM");
		flight1.setFlightNumber("NR 400");
		destflights.add(flight1);
		
		flight2 = new Flight();
		flight2.setAircraft("737");
		flight2.setArrival("2:30 PM");
		flight2.setArriveAirport("Seattle");
		flight2.setDepartAirport("San Francisco");
		flight2.setDeparture("12:30 PM");
		flight2.setFlightNumber("NR 500");
		destflights.add(flight2);
		
		flight3 = new Flight();
		flight3.setAircraft("A320");
		flight3.setArrival("7:00 PM");
		flight3.setArriveAirport("Seattle");
		flight3.setDepartAirport("San Francisco");
		flight3.setDeparture("5:00 PM");
		flight3.setFlightNumber("NR 600");
		destflights.add(flight3);
		
		flights.put("San Francisco-Seattle", destflights);
		
		destflights = new ArrayList<Flight>();
		flight1 = new Flight();
		flight1.setAircraft("737");
		flight1.setDeparture("11:45 AM");
		flight1.setArriveAirport("San Francisco");
		flight1.setDepartAirport("Seattle");
		flight1.setArrival("1:00 PM");
		flight1.setFlightNumber("NR 401");
		destflights.add(flight1);
		
		flight2 = new Flight();
		flight2.setAircraft("737");
		flight2.setArrival("6:00 PM");
		flight2.setArriveAirport("Seattle");
		flight2.setDepartAirport("San Francisco");
		flight2.setDeparture("4:00 PM");
		flight2.setFlightNumber("NR 501");
		destflights.add(flight2);
		
		flight3 = new Flight();
		flight3.setAircraft("A320");
		flight3.setArrival("10:00 PM");
		flight3.setArriveAirport("San Francisco");
		flight3.setDepartAirport("Seattle");
		flight3.setDeparture("8:00 PM");
		flight3.setFlightNumber("NR 601");
		destflights.add(flight3);
		flights.put("Seattle-SanFrancisco", destflights);
		
		destflights = new ArrayList<Flight>();
		flight1 = new Flight();
		flight1.setAircraft("A330");
		flight1.setArrival("11:00 AM+1");
		flight1.setArriveAirport("Dublin");
		flight1.setDepartAirport("San Francisco");
		flight1.setDeparture("5:00 PM");
		flight1.setFlightNumber("NR 700");
		destflights.add(flight1);
		flights.put("San Francisco-Dublin", destflights);
		
		destflights = new ArrayList<Flight>();
		flight1 = new Flight();
		flight1.setAircraft("A330");
		flight1.setArrival("4:00 PM");
		flight1.setArriveAirport("San Francisco");
		flight1.setDepartAirport("Dublin");
		flight1.setDeparture("1:00 PM");
		flight1.setFlightNumber("NR 701");
		destflights.add(flight1);
		flights.put("Dublin-San Francisco", destflights);
		
		destflights = new ArrayList<Flight>();
		flight1 = new Flight();
		flight1.setAircraft("737");
		flight1.setArrival("7:30 AM");
		flight1.setArriveAirport("Seattle");
		flight1.setDepartAirport("Portland");
		flight1.setDeparture("8:30 AM");
		flight1.setFlightNumber("NR 800");
		destflights.add(flight1);
		
		flight2 = new Flight();
		flight2.setAircraft("737");
		flight2.setArrival("2:00 PM");
		flight2.setArriveAirport("Seattle");
		flight2.setDepartAirport("Portland");
		flight2.setDeparture("1:00 PM");
		flight2.setFlightNumber("NR 900");
		destflights.add(flight2);
		
		flight3 = new Flight();
		flight3.setAircraft("A320");
		flight3.setArrival("9:00 PM");
		flight3.setArriveAirport("Seattle");
		flight3.setDepartAirport("Portland");
		flight3.setDeparture("8:00 PM");
		flight3.setFlightNumber("NR 1000");
		destflights.add(flight3);
		flights.put("Portland-Seattle", destflights);
		
		destflights = new ArrayList<Flight>();
		flight1 = new Flight();
		flight1.setAircraft("737");
		flight1.setArrival("10:30 AM");
		flight1.setArriveAirport("Portland");
		flight1.setDepartAirport("Seattle");
		flight1.setDeparture("9:30 AM");
		flight1.setFlightNumber("NR 801");
		destflights.add(flight1);
		
		flight2 = new Flight();
		flight2.setAircraft("737");
		flight2.setArrival("4:00 PM");
		flight2.setDepartAirport("Seattle");
		flight2.setArriveAirport("Portland");
		flight2.setDeparture("3:00 PM");
		flight2.setFlightNumber("NR 901");
		destflights.add(flight2);
		
		flight3 = new Flight();
		flight3.setAircraft("A320");
		flight3.setArrival("11:00 PM");
		flight3.setDepartAirport("Seattle");
		flight3.setArriveAirport("Portland");
		flight3.setDeparture("10:00 PM");
		flight3.setFlightNumber("NR 1001");
		destflights.add(flight3);
		flights.put("Seattle-Portland", destflights);

		
		destflights = new ArrayList<Flight>();
		flight1 = new Flight();
		flight1.setAircraft("A330");
		flight1.setArrival("11:30 AM+1");
		flight1.setArriveAirport("Dublin");
		flight1.setDepartAirport("Portland");
		flight1.setDeparture("6:00 PM");
		flight1.setFlightNumber("NR 1100");
		destflights.add(flight1);
		flights.put("Portland-Dublin", destflights);
		
		destflights = new ArrayList<Flight>();
		flight1 = new Flight();
		flight1.setAircraft("A330");
		flight1.setArrival("3:30 PM");
		flight1.setArriveAirport("Portland");
		flight1.setDepartAirport("Dublin");
		flight1.setDeparture("1:00 PM");
		flight1.setFlightNumber("NR 1101");
		destflights.add(flight1);
		flights.put("Dublin-Portland", destflights);
		
		destflights = new ArrayList<Flight>();
		flight1 = new Flight();
		flight1.setAircraft("A330");
		flight1.setArrival("11:00 AM+1");
		flight1.setArriveAirport("Dublin");
		flight1.setDepartAirport("Seattle");
		flight1.setDeparture("6:00 PM");
		flight1.setFlightNumber("NR 1200");
		destflights.add(flight1);
		flights.put("Seattle-Dublin", destflights);
		
		destflights = new ArrayList<Flight>();
		flight1 = new Flight();
		flight1.setAircraft("A330");
		flight1.setArrival("3:00 PM");
		flight1.setArriveAirport("Seattle");
		flight1.setDepartAirport("Dublin");
		flight1.setDeparture("1:00 PM");
		flight1.setFlightNumber("NR 1201");
		destflights.add(flight1);
		flights.put("Dublin-Seattle", destflights);
		
	}
	
	public static void main(String[] args) {
		try {
			CreateFlightsDB create = new CreateFlightsDB();
			create.createFlights();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createFlights() throws IOException, SQLException, ClassNotFoundException {
		create();
		Class.forName ("com.mysql.jdbc.Driver");
		MysqlDataSource ods = new MysqlDataSource();
		
		ods.setServerName("localhost");
		ods.setDatabaseName("nrairlines");
		ods.setPort(3307);
		ods.setUser("doug");
		ods.setPassword("9shGuxwfQcMY9G89");
		//conn = ods.getConnection();
		
		Connection connection = ods.getConnection(); //DriverManager.getConnection("jdbc:mysql://localhost/mydb");
		
		String insert = "insert into flights(flightnumber,FromAirport,ToAirport,aircraft,Depart,Arrive) values(?,?,?,?,?,?)";
		
		PreparedStatement pstmt = connection.prepareStatement(insert);
		Set<String> keys = flights.keySet();
		for(String key : keys) {
			List<Flight> list = flights.get(key);
			for(int i=0;i<list.size();i++) {
				Flight f = list.get(i);
				int col = 1;
				pstmt.setString(col++, f.getFlightNumber());
				pstmt.setString(col++, f.getDepartAirport());
				pstmt.setString(col++, f.getArriveAirport());
				pstmt.setString(col++, f.getAircraft());
				pstmt.setString(col++, f.getDeparture());
				pstmt.setString(col++, f.getArrival());
				int count = pstmt.executeUpdate();
				if(count != 1) {
					System.out.println("insert failed for "+f);
				}
			}
		}
		
	}
	
	
}

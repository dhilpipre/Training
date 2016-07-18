package com.newrelic.airline.reservations.forms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightDate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6346572639428293364L;
	private Date flightDate;
	private String flightNumber;
	
	public boolean occupySeat(String s) {
		return availableSeats.remove(s);
	}
	public boolean vacateSeat(String s) {
		if(!availableSeats.contains(s))
			return availableSeats.add(s);
		return true;
	}
	public Date getFlightDate() {
		return flightDate;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public List<String> getAvailableSeats() {
		return availableSeats;
	}

	private List<String> availableSeats;
	private static final List<String> seats;
	private Flight flight;

	static {
		seats = new ArrayList<String>();
		for(int i=1;i<6;i++) {
			for(int j=1;j<5;j++) {
				switch (j) {
				case 1: 
					seats.add(i + "A");
					break;
				case 2:
					seats.add(i + "B");
					break;
				case 3:
					seats.add(i + "C");
					break;
				case 4:
					seats.add(i + "D");
					break;
				default:
					break;
				}
			}
		}
	}
	
	public FlightDate(Date date, String fn) {
		this(date,fn,seats);
	}
	
	public FlightDate(Date date, String fn, List<String> aSeats) {
		availableSeats = aSeats;
		flightDate = date;
		flightNumber = fn;
	}
	
	public boolean seatsAvailable() {
		return availableSeats.size() > 0;
	}

	@Override
	public boolean equals(Object obj) {
		if(FlightDate.class.isInstance(obj)) {
			FlightDate fd = (FlightDate)obj;
			return fd.flightDate.equals(flightDate) && fd.flightNumber.equalsIgnoreCase(flightNumber);
		}
		return super.equals(obj);
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	
}

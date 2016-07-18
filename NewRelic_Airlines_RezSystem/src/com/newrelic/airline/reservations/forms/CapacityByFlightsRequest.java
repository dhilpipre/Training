package com.newrelic.airline.reservations.forms;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CapacityByFlightsRequest extends CapacityByDateRequest {
	
	private List<String> flights;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5597356178439372441L;

	public CapacityByFlightsRequest() {
		super();
		flights = new ArrayList<String>();
	}

	public CapacityByFlightsRequest(Date s, Date e) {
		super(s, e);
		flights = new ArrayList<String>();
	}

	public CapacityByFlightsRequest(Date s) {
		super(s);
		flights = new ArrayList<String>();
	}

	public List<String> getFlights() {
		return flights;
	}

	public void setFlights(List<String> flights) {
		this.flights = flights;
	}

	public void addFlight(String flight) {
		flights.add(flight);
	}
	
	public boolean removeFlight(String flight) {
		return flights.remove(flight);
	}
}

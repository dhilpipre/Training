package com.newrelic.airline.reservations.forms;

import java.util.List;

public class CapacityByFlightsResponse extends CapacityByDateResponse {
	
	private List<String> flights;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4857158640913889319L;

	public CapacityByFlightsResponse() {
		super();
	}

	public List<String> getFlights() {
		return flights;
	}

	public void setFlights(List<String> flights) {
		this.flights = flights;
	}

}

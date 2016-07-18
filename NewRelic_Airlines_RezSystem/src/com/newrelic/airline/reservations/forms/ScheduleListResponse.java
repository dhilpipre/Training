package com.newrelic.airline.reservations.forms;

import java.util.HashMap;
import java.util.List;

public class ScheduleListResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9141610914457976713L;

	private HashMap<String, List<Flight>> flights;

	public HashMap<String, List<Flight>> getFlights() {
		return flights;
	}

	public void setFlights(HashMap<String, List<Flight>> flights) {
		this.flights = flights;
	}
	
}

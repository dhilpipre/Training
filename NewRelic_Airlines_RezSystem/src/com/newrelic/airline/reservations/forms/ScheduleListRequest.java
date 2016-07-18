package com.newrelic.airline.reservations.forms;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class ScheduleListRequest extends BaseRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6098870280872912872L;

	private HashMap<String, List<Flight>> flights;

	public HashMap<String, List<Flight>> getFlights() {
		return flights;
	}

	public void setFlights(HashMap<String, List<Flight>> flights) {
		this.flights = flights;
	}
	
}

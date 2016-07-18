package com.newrelic.airline.reservations.forms;

import java.util.ArrayList;
import java.util.List;



public abstract class RevenueRequest extends BaseRequest {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8478024339821782632L;
		
	private List<String> flights = new ArrayList<String>();

	public List<String> getFlights() {
		return flights;
	}

	public void setFlights(List<String> flights) {
		this.flights = flights;
	}


}

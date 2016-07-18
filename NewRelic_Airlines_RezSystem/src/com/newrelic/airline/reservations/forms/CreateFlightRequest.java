package com.newrelic.airline.reservations.forms;

public class CreateFlightRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2366118296777706839L;
	
	private Flight flight;

	public CreateFlightRequest(Flight flight) {
		this.flight = flight;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	
}

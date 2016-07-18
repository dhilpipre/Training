package com.newrelic.airline.reservations.forms;

public class DeleteFlightRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4295339967272534171L;
	
	private String flightNumber;
	
	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public DeleteFlightRequest(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	

}

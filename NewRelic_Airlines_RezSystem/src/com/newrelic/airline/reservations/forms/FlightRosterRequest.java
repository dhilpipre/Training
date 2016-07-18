package com.newrelic.airline.reservations.forms;

import java.util.Date;

public class FlightRosterRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 248819343092068403L;

	private String flightNumber;
	private Date flightDate;
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public Date getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}
	
	
}

package com.newrelic.airline.reservations.forms;

public class UpdateFlightRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5011993902991472463L;
	
	private Flight orig_flight;
	private Flight mod_flight;

	public UpdateFlightRequest(Flight original, Flight modified) {
		this.setOriginalFlight(original);
		this.setModifiedFlight(modified);
	}

	public Flight getOriginalFlight() {
		return orig_flight;
	}

	public void setOriginalFlight(Flight flight) {
		this.orig_flight = flight;
	}

	public Flight getModifiedFlight() {
		return mod_flight;
	}

	public void setModifiedFlight(Flight mod_flight) {
		this.mod_flight = mod_flight;
	}
	
	
}

package com.newrelic.airline.reservations;

import com.newrelic.airline.reservations.forms.BaseResponse;
import com.newrelic.airline.reservations.forms.FlightDate;

public class FlightSearchResults extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7938006421696118325L;
	private FlightDate[] outFlights;
	private FlightDate[] returnFlights;

	public FlightDate[] getOutFlights() {
		return outFlights;
	}
	public void setOutFlights(FlightDate[] outFlights) {
		this.outFlights = outFlights;
	}
	public FlightDate[] getReturnFlights() {
		return returnFlights;
	}
	public void setReturnFlights(FlightDate[] returnFlights) {
		this.returnFlights = returnFlights;
	}


}

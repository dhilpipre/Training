package com.newrelic.airline.reservations.forms;

import java.io.Serializable;
import java.util.List;

public class FlightSearchResults extends BaseResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1371667071285215729L;
	private List<FlightDate> outFlights;
	private List<FlightDate> returnFlights;
	public List<FlightDate> getOutFlights() {
		return outFlights;
	}
	public void setOutFlights(List<FlightDate> outFlights) {
		this.outFlights = outFlights;
	}
	public List<FlightDate> getReturnFlights() {
		return returnFlights;
	}
	public void setReturnFlights(List<FlightDate> returnFlights) {
		this.returnFlights = returnFlights;
	}
	
	
}

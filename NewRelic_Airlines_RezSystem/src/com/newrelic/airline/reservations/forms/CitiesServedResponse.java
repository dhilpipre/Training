package com.newrelic.airline.reservations.forms;

public class CitiesServedResponse extends BaseResponse {

	public CitiesServedResponse(String[] citiesServed) {
		super();
		this.citiesServed = citiesServed;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5814328121344786331L;
	
	private String[] citiesServed;

	public String[] getCitiesServed() {
		return citiesServed;
	}

	public void setCitiesServed(String[] citiesServed) {
		this.citiesServed = citiesServed;
	}
	
}

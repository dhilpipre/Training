package com.newrelic.airline.reservations.pools;

public class GetCitiesRequestPool extends BaseRequestPool {

	public static BaseRequestPool instance;
	
	public static GetCitiesRequestPool getInstance() {
		if(instance == null) {
			instance = new GetCitiesRequestPool();
		}
		return (GetCitiesRequestPool) instance;
	}
	
	private GetCitiesRequestPool() {
		super();
	}
}

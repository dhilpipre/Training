package com.newrelic.airline.reservations.pools;


public class FlightSearchRequestPool extends BaseRequestPool {
	
	public static BaseRequestPool instance;
	
	public static FlightSearchRequestPool getInstance() {
		if(instance == null) {
			instance = new FlightSearchRequestPool();
		}
		return (FlightSearchRequestPool) instance;
	}
	static {
		instance = new FlightSearchRequestPool();
	}
	
	private FlightSearchRequestPool() {
		super();
	}
	
	
}

package com.newrelic.airline.reservations.pools;


public class FlightRosterRequestPool extends BaseRequestPool {
	
	public static BaseRequestPool instance;
	
	public static FlightRosterRequestPool getInstance() {
		if(instance == null) {
			instance = new FlightRosterRequestPool();
		}
		return (FlightRosterRequestPool) instance;
	}
	static {
		instance = new FlightRosterRequestPool();
	}
	
	private FlightRosterRequestPool() {
		super();
	}
	
	
}

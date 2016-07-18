package com.newrelic.airline.reservations.pools;

public class CreateFlightRequestPool extends BaseRequestPool {
	
	public static BaseRequestPool instance;
	
	public static CreateFlightRequestPool getInstance() {
		if(instance == null) {
			instance = new CreateFlightRequestPool();
		}
		return (CreateFlightRequestPool)instance;
	}
	
	private CreateFlightRequestPool() {
		super();
	}
 }

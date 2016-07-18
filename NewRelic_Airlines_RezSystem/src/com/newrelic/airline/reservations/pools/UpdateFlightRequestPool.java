package com.newrelic.airline.reservations.pools;

public class UpdateFlightRequestPool extends BaseRequestPool {
	
	public static BaseRequestPool instance;
	
	public static UpdateFlightRequestPool getInstance() {
		if(instance == null) {
			instance = new UpdateFlightRequestPool();
		}
		return (UpdateFlightRequestPool)instance;
	}
	
	private UpdateFlightRequestPool() {
		super();
	}
 }

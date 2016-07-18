package com.newrelic.airline.reservations.pools;


public class RetrieveRequestPool extends BaseRequestPool {

	public static BaseRequestPool instance;
	
	public static RetrieveRequestPool getInstance() {
		if(instance == null) {
			instance = new RetrieveRequestPool();
		}
		return (RetrieveRequestPool) instance;
	}

	private RetrieveRequestPool() {
		super();
		
	}
	
}

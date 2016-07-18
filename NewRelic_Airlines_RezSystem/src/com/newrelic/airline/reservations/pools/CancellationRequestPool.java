package com.newrelic.airline.reservations.pools;


public class CancellationRequestPool extends BaseRequestPool {
	
	public static BaseRequestPool instance;
	
	public static CancellationRequestPool getInstance() {
		if(instance == null) {
			instance = new CancellationRequestPool();
		}
		return (CancellationRequestPool) instance;
	}
		
	private CancellationRequestPool() {
		super();
		
	}
	
}

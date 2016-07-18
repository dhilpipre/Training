package com.newrelic.airline.reservations.pools;

public class CapacityRequestPool extends BaseRequestPool {
	public static BaseRequestPool instance;
	
	public static CapacityRequestPool getInstance() {
		if(instance == null) {
			instance = new CapacityRequestPool();
		}
		return (CapacityRequestPool) instance;
	}
	
	private CapacityRequestPool() {
		super();
	}

}

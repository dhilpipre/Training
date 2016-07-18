package com.newrelic.airline.reservations.pools;

public class RevenueRequestPool extends BaseRequestPool {
	public static BaseRequestPool instance;
	
	public static RevenueRequestPool getInstance() {
		if(instance == null) {
			instance = new RevenueRequestPool();
		}
		return (RevenueRequestPool) instance;
	}
	
	private RevenueRequestPool() {
		super();
	}

}

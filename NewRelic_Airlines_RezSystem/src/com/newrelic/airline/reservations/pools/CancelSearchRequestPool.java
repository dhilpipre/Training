package com.newrelic.airline.reservations.pools;

public class CancelSearchRequestPool extends BaseRequestPool {
	
	public static BaseRequestPool instance;
	
	public static CancelSearchRequestPool getInstance() {
		if(instance == null) {
			instance = new CancelSearchRequestPool();
		}
		return (CancelSearchRequestPool)instance;
	}
	
	private CancelSearchRequestPool() {
		super();
	}
 }

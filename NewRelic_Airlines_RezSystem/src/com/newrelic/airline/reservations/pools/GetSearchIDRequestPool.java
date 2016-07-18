package com.newrelic.airline.reservations.pools;

public class GetSearchIDRequestPool extends BaseRequestPool {

	public static BaseRequestPool instance;
	
	public static GetSearchIDRequestPool getInstance() {
		if(instance == null) {
			instance = new GetSearchIDRequestPool();
		}
		return (GetSearchIDRequestPool)instance;
	}
	
	private GetSearchIDRequestPool() {
		super();
	}
 }

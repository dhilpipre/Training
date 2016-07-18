package com.newrelic.airline.reservations.pools;

public class GetWaitPeriodRequestPool extends BaseRequestPool {
	
	public static BaseRequestPool instance;
	
	public static GetWaitPeriodRequestPool getInstance() {
		if(instance == null) {
			instance = new GetWaitPeriodRequestPool();
		}
		return (GetWaitPeriodRequestPool)instance;
	}
	
	private GetWaitPeriodRequestPool() {
		super();
	}
 }

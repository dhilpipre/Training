package com.newrelic.airline.reservations.pools;

public class SetWaitPeriodRequestPool extends BaseRequestPool {
	
	public static BaseRequestPool instance;
	
	public static SetWaitPeriodRequestPool getInstance() {
		if(instance == null) {
			instance = new SetWaitPeriodRequestPool();
		}
		return (SetWaitPeriodRequestPool)instance;
	}
	
	private SetWaitPeriodRequestPool() {
		super();
	}
 }

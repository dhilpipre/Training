package com.newrelic.airline.reservations.pools;

public class GetScheduleRequestPool extends BaseRequestPool {
	
	public static BaseRequestPool instance;
	
	public static GetScheduleRequestPool getInstance() {
		if(instance == null) {
			instance = new GetScheduleRequestPool();
		}
		return (GetScheduleRequestPool)instance;
	}
	
	private GetScheduleRequestPool() {
		super();
	}
 }

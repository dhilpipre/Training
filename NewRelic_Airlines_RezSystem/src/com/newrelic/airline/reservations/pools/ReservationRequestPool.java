package com.newrelic.airline.reservations.pools;


public class ReservationRequestPool extends BaseRequestPool {

	public static BaseRequestPool instance;
	
	public static ReservationRequestPool getInstance() {
		if(instance == null) {
			instance = new ReservationRequestPool();
		}
		return (ReservationRequestPool) instance;
	}
	
	private ReservationRequestPool() {
		super();
		
	}

}

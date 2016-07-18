package com.newrelic.airline.reservations.pools;

public class DeleteFlightRequestPool extends BaseRequestPool {
	
	public static BaseRequestPool instance;
	
	public static DeleteFlightRequestPool getInstance() {
		if(instance == null) {
			instance = new DeleteFlightRequestPool();
		}
		return (DeleteFlightRequestPool)instance;
	}
	
	private DeleteFlightRequestPool() {
		super();
	}
 }

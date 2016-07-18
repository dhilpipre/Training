package com.newrelic.airline.reservations.pools;

public class DBRefreshRequestPool extends BaseRequestPool {
	
	public static BaseRequestPool instance;
	
	public static DBRefreshRequestPool getInstance() {
		if(instance == null) {
			instance = new DBRefreshRequestPool();
		}
		return (DBRefreshRequestPool)instance;
	}
	
	private DBRefreshRequestPool() {
		super();
	}
 }

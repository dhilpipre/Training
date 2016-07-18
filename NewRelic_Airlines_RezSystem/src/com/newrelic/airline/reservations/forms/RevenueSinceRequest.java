package com.newrelic.airline.reservations.forms;

import java.util.Date;


public class RevenueSinceRequest extends RevenueRequest {
	
	private long since;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8478024339821782632L;
	
	public void setSinceByLastMs(long l) {
		since = System.currentTimeMillis() - l;
	}
	
	public void setSince(Date d) {
		since = d.getTime();
	}

	public long getSince() {
		return since;
	}

	public void setSince(long since) {
		this.since = since;
	}

	
}

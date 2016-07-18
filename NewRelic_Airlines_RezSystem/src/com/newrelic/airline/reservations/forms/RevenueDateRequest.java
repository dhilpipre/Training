package com.newrelic.airline.reservations.forms;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RevenueDateRequest extends RevenueRequest {
	
	private Timestamp fromDate;
	private Timestamp toDate;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8478024339821782632L;


	public RevenueDateRequest(Timestamp fromDate, Timestamp toDate) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		
	}

	public RevenueDateRequest(Timestamp fromDate) {
		super();
		this.fromDate = fromDate;
		this.toDate = null;
	}

	public Timestamp getFromTimestamp() {
		return fromDate;
	}


	public void setFromTimestamp(Timestamp fromDate) {
		this.fromDate = fromDate;
	}


	public Timestamp getToTimestamp() {
		return toDate;
	}


	public void setToTimestamp(Timestamp toDate) {
		this.toDate = toDate;
	}
	
}

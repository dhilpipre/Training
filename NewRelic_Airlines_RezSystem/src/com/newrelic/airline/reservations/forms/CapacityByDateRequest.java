package com.newrelic.airline.reservations.forms;

import java.sql.Date;

public class CapacityByDateRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1600961239486032069L;

	private Date start;
	private Date end;
	
	public CapacityByDateRequest() {
		super();
		start = new Date(System.currentTimeMillis());
		end = null;
	}
	
	public CapacityByDateRequest(Date s) {
		super();
		start = s;
		end = null;
	}
	
	public CapacityByDateRequest(Date s, Date e) {
		super();
		start = s;
		end = e;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
}

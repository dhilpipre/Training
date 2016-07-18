package com.newrelic.airline.reservations.forms;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Revenue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -307441537887235961L;
	private Timestamp timestamp;
	private float revenue;
	private String outFlightNumber;
	private String retFlightNumber;
	public String getOutFlightNumber() {
		return outFlightNumber;
	}
	public void setOutFlightNumber(String outFlightNumber) {
		this.outFlightNumber = outFlightNumber;
	}
	public String getRetFlightNumber() {
		return retFlightNumber;
	}
	public void setRetFlightNumber(String retFlightNumber) {
		this.retFlightNumber = retFlightNumber;
	}
	public Date getOutFlightDate() {
		return outFlightDate;
	}
	public void setOutFlightDate(Date outFlightDate) {
		this.outFlightDate = outFlightDate;
	}
	public Date getRetFlightDate() {
		return retFlightDate;
	}
	public void setRetFlightDate(Date retFlightDate) {
		this.retFlightDate = retFlightDate;
	}
	private Date outFlightDate;
	private Date retFlightDate;
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public float getRevenue() {
		return revenue;
	}
	public void setRevenue(float revenue) {
		this.revenue = revenue;
	}
	
	
}

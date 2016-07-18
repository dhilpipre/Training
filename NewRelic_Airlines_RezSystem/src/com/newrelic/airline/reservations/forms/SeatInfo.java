package com.newrelic.airline.reservations.forms;

import java.io.Serializable;

public class SeatInfo implements Serializable, Comparable<SeatInfo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4107823264936166496L;
	private String firstName;
	private String lastName;
	private String confirmation;
	private String seat;
	
	public SeatInfo(String firstName, String lastName, String confirmation, String seat) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.confirmation = confirmation;
		this.seat = seat;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	@Override
	public int compareTo(SeatInfo o) {
		return seat.compareTo(o.getSeat());
	}
	
	
}

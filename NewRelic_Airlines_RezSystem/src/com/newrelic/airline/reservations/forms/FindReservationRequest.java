package com.newrelic.airline.reservations.forms;

import java.io.Serializable;

public class FindReservationRequest extends BaseRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6740900349769429563L;
	
	private String firstName;
	private String lastName;
	private String confirmation;
	
	public FindReservationRequest(String firstName, String lastName,String confirmation) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.confirmation = confirmation;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getConfirmation() {
		return confirmation;
	}
	
	
}

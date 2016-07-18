package com.newrelic.airline.reservations.forms;

import java.io.Serializable;

public class ReservationCancelRequest extends BaseRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3901693974025186566L;
	private String confirmation;

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
	
	
}

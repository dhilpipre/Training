package com.newrelic.airline.reservations.forms;

public class CreateReservationResponse extends BaseResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7183353694268379913L;
	private String confirmationNumber;

	
	public CreateReservationResponse(String confirmationNumber) {
		super();
		this.confirmationNumber = confirmationNumber;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}
	
}

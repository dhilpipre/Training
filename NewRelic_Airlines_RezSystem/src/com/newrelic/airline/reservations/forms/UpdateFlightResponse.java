package com.newrelic.airline.reservations.forms;

public class UpdateFlightResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2315854612659725030L;

	private boolean success;

	public UpdateFlightResponse(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}

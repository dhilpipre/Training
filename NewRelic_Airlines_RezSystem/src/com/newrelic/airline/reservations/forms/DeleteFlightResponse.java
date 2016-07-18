package com.newrelic.airline.reservations.forms;

public class DeleteFlightResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2475808058113300654L;

	boolean success;

	public DeleteFlightResponse(boolean success) {
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

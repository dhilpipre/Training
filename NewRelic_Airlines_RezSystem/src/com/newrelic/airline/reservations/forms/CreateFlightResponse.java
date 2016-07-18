package com.newrelic.airline.reservations.forms;

public class CreateFlightResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3752835296945976433L;

	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public CreateFlightResponse(boolean success) {
		super();
		this.success = success;
	}
	
	
}

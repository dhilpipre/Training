package com.newrelic.airline.reservations.forms;

public class DBRefreshResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5015726975418804160L;

	private boolean successful;

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccess(boolean success) {
		this.successful = success;
	}
	
	
}

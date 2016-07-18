package com.newrelic.airline.reservations.forms;

public class CancelResponse extends BaseResponse {
	public CancelResponse(String cancellationResult, float f) {
		super();
		this.cancellationResult = cancellationResult;
		refund = f;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7208581583231558947L;
	private String cancellationResult;
	private float refund = 0;
	
	public String getCancellationResponse() {
		return cancellationResult;
	}

	public float getRefund() {
		return refund;
	}

	public void setRefund(float refund) {
		this.refund = refund;
	}

}

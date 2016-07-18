package com.newrelic.airline.reservations.forms;

public class SetWaitPeriodRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6051891839069181577L;

	private long wait_period;

	public SetWaitPeriodRequest(long wait_period) {
		this.wait_period = wait_period;
	}

	public long getWait_period() {
		return wait_period;
	}

	public void setWait_period(long wait_period) {
		this.wait_period = wait_period;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

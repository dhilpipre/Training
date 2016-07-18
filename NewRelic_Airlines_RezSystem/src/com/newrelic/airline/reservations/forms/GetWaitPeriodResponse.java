package com.newrelic.airline.reservations.forms;

public class GetWaitPeriodResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3975316027808728875L;

	private long wait_period;

	public long getWait_period() {
		return wait_period;
	}

	public void setWait_period(long wait_period) {
		this.wait_period = wait_period;
	}

	public GetWaitPeriodResponse(long wait_period) {
		super();
		this.wait_period = wait_period;
	}
	
}

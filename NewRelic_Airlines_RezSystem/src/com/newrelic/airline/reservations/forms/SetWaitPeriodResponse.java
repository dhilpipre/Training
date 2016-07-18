package com.newrelic.airline.reservations.forms;

public class SetWaitPeriodResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3975316027808728875L;

	private long new_wait_period;
	private long old_wait_period;

	public long getNew_Wait_period() {
		return new_wait_period;
	}

	public void setWait_period(long wait_period) {
		this.new_wait_period = wait_period;
	}

	public long getOld_wait_period() {
		return old_wait_period;
	}

	public void setOld_wait_period(long old_wait_period) {
		this.old_wait_period = old_wait_period;
	}

	public SetWaitPeriodResponse(long new_wait_period, long old_wait_period) {
		super();
		this.new_wait_period = new_wait_period;
		this.old_wait_period = old_wait_period;
	}
	
}

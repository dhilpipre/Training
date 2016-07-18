package com.newrelic.airline.reservations;

public interface WaitPeriodMXBean {

	public long getWaitPeriod();
	public void setWaitPeriod(long wp);
}

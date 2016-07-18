package com.newrelic.airline.reservations.pools;

public interface RequestPoolMXBean {
	public int getMaxThreads();
	public int getInUseThreads();
	public int getFreeThreads();
	public int getPoolSize();
	public long getTaskCount();
	public long getTotalTaskCount();
	public int getWaitingCount();
	
	public void setMaxThreads(int m);
	
	public void purge();
	
}

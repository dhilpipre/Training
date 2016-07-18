package com.newrelic.airline.reservations.pools;

public interface DBConnectionPoolMXBean {
	public int getMaxPoolSize();
	public int getInUseConnections();
	public int getFreeConnections();
	public int getPoolSize();
	public int getTotalWaits();
	public int getWaiting();
	public long getAverageWait();
	public int getTimedOut();
	public long getTimeout();
	public void setTimeout(long ts);
	public void setPoolSize(int m);
	public void setMaxPoolSize(int m);
	
	public void reset();
	
	public String getName();
	
}

package com.newrelic.airline.reservations.pools;

import org.apache.log4j.Logger;

public class DBConnectionPool implements DBConnectionPoolMXBean {
	
	private static final Logger LOG = Logger.getLogger(DBConnectionPool.class);
	
	DatabaseConnectionPool pool;
	String name;
	
	public String getName() {
		return name;
	}

	public DBConnectionPool(DatabaseConnectionPool p) {
		pool = p;
		name = pool.getName();
	}

	@Override
	public int getMaxPoolSize() {
		return pool.getMaxSize();
	}

	@Override
	public int getInUseConnections() {
		return pool.getInUseSize();
	}

	@Override
	public int getFreeConnections() {
		return pool.getFreeSize();
	}

	@Override
	public void setPoolSize(int m) {
		pool.setPoolSize(m);
	}

	@Override
	public void setMaxPoolSize(int m) {
		pool.setMaxPoolSize(m);
	}

	@Override
	public void reset() {
		try {
			pool.reset();
		} catch (Exception e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
	}

	@Override
	public int getPoolSize() {
		return pool.getPoolSize();
	}

	@Override
	public int getTotalWaits() {
		return pool.getTotalWaits();
	}

	@Override
	public int getWaiting() {
		return pool.getWaitCount();
	}

	@Override
	public long getAverageWait() {
		return pool.getAverageWait();
	}

	@Override
	public int getTimedOut() {
		return pool.getTimedout();
	}

	@Override
	public void setTimeout(long ts) {
		pool.setTimeout(ts);
	}

	@Override
	public long getTimeout() {
		return pool.getTimeout();
	}

}

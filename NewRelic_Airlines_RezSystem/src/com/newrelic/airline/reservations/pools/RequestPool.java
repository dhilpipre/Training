package com.newrelic.airline.reservations.pools;

import java.util.concurrent.ThreadPoolExecutor;

public class RequestPool implements RequestPoolMXBean {
	
	private BaseRequestPool pool;
	
	public RequestPool(BaseRequestPool p) {
		pool = p;
	}

	public int getMaxThreads() {
		return pool.threadPoolExecutor.getMaximumPoolSize();
	}

	public int getInUseThreads() {
		return pool.threadPoolExecutor.getActiveCount();
	}

	public int getFreeThreads() {
		ThreadPoolExecutor tpe = pool.threadPoolExecutor;
		return tpe.getPoolSize() - tpe.getActiveCount();
	}

	public void setMaxThreads(int m) {
		pool.setMaxThreads(m);
	}

	@Override
	public int getPoolSize() {
		return pool.threadPoolExecutor.getPoolSize();
	}

	@Override
	public long getTaskCount() {
		return pool.threadPoolExecutor.getTaskCount();
	}

	@Override
	public long getTotalTaskCount() {
		return pool.threadPoolExecutor.getCompletedTaskCount();
	}

	@Override
	public void purge() {
		pool.threadPoolExecutor.purge();
	}

	@Override
	public int getWaitingCount() {
		return pool.threadPoolExecutor.getQueue().size();
	}

	
}

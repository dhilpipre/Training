package com.newrelic.airline.reservations.pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.newrelic.airline.reservations.processing.BaseAgent;

public abstract class BaseRequestPool {
	protected int maxThreads = 10;
	protected int poolSize = 10;
	
	ExecutorService executor; 
	protected ThreadPoolExecutor threadPoolExecutor;
	
	protected BaseRequestPool() {
		executor = Executors.newFixedThreadPool(maxThreads);
		if(ThreadPoolExecutor.class.isInstance(executor)) {
			threadPoolExecutor = (ThreadPoolExecutor)executor;
		} else {
			
		}
	}
	
	protected void setPoolSize(int m) {
		threadPoolExecutor.setCorePoolSize(m);
	}
	protected int getMaxThreads() {
		if(threadPoolExecutor != null) {
			int max = threadPoolExecutor.getMaximumPoolSize();
			return max;
		}
		return maxThreads;
	}
	
	public int numberOfThreadsInUse() {
		if(threadPoolExecutor != null) {
			int active = threadPoolExecutor.getActiveCount();
			return active;
		}
		int inUse =  maxThreads;
		return inUse;
	}
	
	public int numberOfFreeThreads() {
		if(threadPoolExecutor != null) {
			int freeThreads = threadPoolExecutor.getPoolSize() - threadPoolExecutor.getActiveCount();
			return freeThreads;
		}
		return maxThreads;
	}
	
	public void setMaxThreads(int m) {
		if(threadPoolExecutor != null) {
			if(threadPoolExecutor.getCorePoolSize() > m) {
				threadPoolExecutor.setCorePoolSize(m);
			}
			threadPoolExecutor.setMaximumPoolSize(m);
		} 
		maxThreads = m;
	}
	
	public final void submit(BaseAgent agent) {
		if(threadPoolExecutor != null) {
			threadPoolExecutor.submit(agent);
		}
	}

	public int getPoolSize() {
		return threadPoolExecutor.getPoolSize();
	}
}

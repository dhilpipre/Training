package com.newrelic.airline.reservations;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class WatcherThread extends Thread {

	private long wait_period = 60000L;
	private static final Logger LOG = Logger.getLogger(WatcherThread.class);
	private int warnings = 0;
	private String loggingConfigFile = "log4j.lcf";
	private String dbConfigFile = "DBConfiguration.properties";
	
	public WatcherThread(String cf, String dbcf) {
		loggingConfigFile = cf;
		dbConfigFile = dbcf;
	}

	@Override
	public void run() {
		while(true) {
			LOG.debug("Performing Watcher Tasks");
			File config = new File(loggingConfigFile);
			if(config.exists()) {
				long diff = System.currentTimeMillis() - config.lastModified();
				
				if(diff < 60000L) {
					LOG.info("Log4j configuration has changed, file is being reloaded");
					PropertyConfigurator.configure(loggingConfigFile);
				}
			} else {
				if (warnings < 5) {
					LOG.warn("Could not find "+loggingConfigFile);
					warnings++;
				} else if(warnings == 5) {
					LOG.warn("Could not find "+loggingConfigFile+", no further warnings will be issued");
					warnings++;
				}
			}
			
			config = new File(dbConfigFile);
			if(config.exists()) {
				long diff = System.currentTimeMillis() - config.lastModified();
				
				if(diff < 60000L) {
					ReservationControllerImpl controller = (ReservationControllerImpl)ReservationControllerFactory.getReservationController();
					LOG.info("Database configuration has changed, file is being reloaded and pool will be refreshed");
					controller.databaseConfigChanged();
				}
			} else {
				if (warnings < 5) {
					LOG.warn("Could not find "+dbConfigFile);
					warnings++;
				} else if(warnings == 5) {
					LOG.warn("Could not find "+dbConfigFile+", no further warnings will be issued");
					warnings++;
				}
			}
			
			synchronized (this) {
				try {
					wait(wait_period);
				} catch (InterruptedException e) {
					LOG.error(e.getClass().getSimpleName(), e);
				}
			}
		}
	}
	
	
}

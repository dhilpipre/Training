package com.newrelic.airline.reservations.pools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.apache.log4j.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DatabaseConnectionPool {
	private File configurationFile;
	private int poolSize;

	private List<Connection> databaseConnectionPool = new ArrayList<Connection>();
	private String poolName;
	private int maxSize;
	private static final Logger LOG = Logger.getLogger(DatabaseConnectionPool.class);
	private MysqlDataSource ods;
	private int waitCount = 0;
	private int totalWaits = 0;
	private long totalWaitTime = 0L;
	private long timeout = 60000L;
	private int timedout = 0;
	private int inUse = 0;
	public int getTimedout() {
		return timedout;
	}

	private boolean initialized = false;
	
	public int getWaitCount() {
		return waitCount;
	}

	public int getTotalWaits() {
		return totalWaits;
	}

	public long getAverageWait() {
		if(totalWaits == 0) return 0L;
		return (totalWaitTime/totalWaits);
	}
	
	public DatabaseConnectionPool(String cf, String name) {
		poolName = name;
		configurationFile = new File(cf);
		
		setDataSource();
		
		maxSize = poolSize;
		initialized = intialize();
		try {
			DBConnectionPool bean = new DBConnectionPool(this);
			String prefix = "com.newrelic.airlines.pools:type=ConnectionPool,name=";
			MBeanServer server = ManagementFactory.getPlatformMBeanServer();
			ObjectName oname1 = new ObjectName(prefix+poolName);
			server.registerMBean(bean, oname1);
		} catch (MalformedObjectNameException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		} catch (InstanceAlreadyExistsException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		} catch (MBeanRegistrationException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		} catch (NotCompliantMBeanException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
	}
	
	public void resetDataSource() {
		setDataSource();
		try {
			reset();
		} catch (Exception e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
	}
	
	private void setDataSource() {
		Properties props = new Properties();
		try {
			FileInputStream in = new FileInputStream(configurationFile);
			props.load(in);
		} catch (FileNotFoundException e) {
			LOG.error("Error: "+e.getClass().getSimpleName(), e);
		} catch (IOException e) {
			LOG.error("Error: "+e.getClass().getSimpleName(), e);
		}
		
		String dbhost = props.getProperty("db-host", "localhost");
		String dbport = props.getProperty("db-port","3306");
		String dbuser = props.getProperty("db-user","doug");
		String dbpw = props.getProperty("db-password", "doug");
		String dbname = props.getProperty("db-name", "airlines");
		
		ods = new MysqlDataSource();
		
		ods.setServerName(dbhost);
		ods.setDatabaseName(dbname);
		ods.setPort(Integer.parseInt(dbport));
		ods.setUser(dbuser);
		ods.setPassword(dbpw);
	
		String poolSizeStr = props.getProperty("pool-size", "10");
		poolSize = Integer.parseInt(poolSizeStr);
	}
	private boolean intialize() {
		try {
			for(int i=0;i<poolSize;i++) {
				Connection conn = ods.getConnection();
				databaseConnectionPool.add(conn);
			}
		} catch (SQLException e) {
			LOG.error("Error: "+e.getClass().getSimpleName(), e);
			return false;
		}
		return true;

	}
	private boolean testConnection(Connection c) {
		boolean check = false;
		if(com.mysql.jdbc.Connection.class.isInstance(c)) {
			com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection)c;
			try {
				conn.ping();
				check = true;
			} catch(SQLException e) {
				LOG.debug(e.getClass().getSimpleName(), e);
			}
		} else {
			check = true;
		}
		return check;
	}
	
	public Connection getConnection() {
		if(databaseConnectionPool.isEmpty()) {
			if(!initialized) {
				initialized = intialize();
			}
			LOG.debug("Waiting for database connection");
			boolean available = false;
			waitCount++;
			int iteration = 0;
			long start = System.currentTimeMillis();
			int maxIterations = (int) (timeout/1000);
			while(!available) {
				iteration++;
				try {
					synchronized (this) {
						wait(1000L);
					}
				} catch (InterruptedException e) {
					LOG.error(e.getClass().getSimpleName(), e);
				}
				available = !databaseConnectionPool.isEmpty() || iteration >= maxIterations;
			}
			long end = System.currentTimeMillis();
			long wait_time = end - start;
			totalWaits++;
			totalWaitTime += wait_time;
			waitCount--;
		}
		
		if(!databaseConnectionPool.isEmpty()) {
			Connection conn = databaseConnectionPool.remove(0);
			while(!testConnection(conn)) {
				try {
					conn.close();
					conn = ods.getConnection();
				} catch (SQLException e) {
					LOG.warn("Unable to get database connnection");
					if(databaseConnectionPool.isEmpty()) {
						LOG.warn("No good connections available");
						initialized = intialize();
						if(databaseConnectionPool.isEmpty()) {
							return null;
						}
					} 
				}
				conn = databaseConnectionPool.remove(0);
			}
			inUse++;
			StackTraceElement[] stack = Thread.currentThread().getStackTrace();
			LOG.debug("Connection("+conn.hashCode()+") checked out for use\n"+stack[2]);
			return conn;
		}
		
		LOG.warn("Timed out waiting for connection");
		timedout++;
		return null;
	}
	
	public void returnConnection(Connection conn) {
		if(conn == null) return;
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		if(!databaseConnectionPool.contains(conn)) {
			databaseConnectionPool.add(conn);
			inUse--;
			
			LOG.debug("Connection("+conn.hashCode()+") returned\n"+stack[2]);
		} else {
			LOG.debug("Attempted to return connection("+conn.hashCode()+") which is already in pool\n"+stack[2]);
		}
	}
	
	public int getPoolSize() {
		return poolSize;
	}
	
	public int getMaxSize() {
		return maxSize;
	}
	
	public int getFreeSize() {
		return databaseConnectionPool.size();
	}
	
	public int getInUseSize() {
		return inUse;
	}
	
	public String getName() {
		return poolName;
	}

	public void reset() throws Exception {
		LOG.info("Resetting connection pool");
		List<Connection> poolCopy = new ArrayList<Connection>(databaseConnectionPool);
		
		for(Connection conn : poolCopy) {
			try {
				conn.close();
			} catch (SQLException e) {
				LOG.error("Error: "+e.getClass().getSimpleName(), e);
				throw(e);
			}
			databaseConnectionPool.remove(conn);
		}
		
		try {
			for(int i=0;i<poolSize;i++) {
				Connection conn = ods.getConnection();
				databaseConnectionPool.add(conn);
			}
		} catch (SQLException e) {
			LOG.error("Error: "+e.getClass().getSimpleName(), e);
			throw(e);
		}
		LOG.info("Connection pool has been reset");
		
	}
	public void setPoolSize(int ps) {
		if(ps <= maxSize) {
			if(ps > poolSize) {
				try {
					for(int i=poolSize;i<ps;i++) {
						Connection conn = ods.getConnection();
						databaseConnectionPool.add(conn);
					}
				} catch (SQLException e) {
					LOG.error("Error: "+e.getClass().getSimpleName(), e);
				}
			}
			poolSize = ps;
			
		} else {
			LOG.warn("Attempt to set pool larger than the maximum pool size");
			LOG.warn("Set maximum pool size first if desired");
		}
	}
	
	public void setMaxPoolSize(int m) {
		maxSize = m;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	
}

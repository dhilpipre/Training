package com.newrelic.airline.reservations;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.newrelic.airline.reservations.pools.*;
import com.newrelic.airline.reservations.processing.Connection;


public class Main {

	private Connection connection;
	private long wait_period = 10000L;
	private int listening_port = 16500;
	private static final Logger LOG = Logger.getLogger(Main.class);
	private static final String dbConfigFilename = "DBConfiguration.properties";

	
	public static void main(String[] args) {
		Main instance = new Main();
		instance.setupMBeans();
		instance.start();
	}

	public Main() {
		String cf = "log4j.lcf";
		PropertyConfigurator.configure(cf);
		WatcherThread watcher = new WatcherThread(cf, dbConfigFilename);
		watcher.start();
		String lPort = System.getProperty("Listen.port","6500");
		if(lPort != null) {
			listening_port = Integer.parseInt(lPort);
		}
		String IPAdd = System.getProperty("IPAddress");
		InetAddress IP = null;
		if(IPAdd != null) {
			try {
				IP = InetAddress.getByName(IPAdd);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		if(IP != null) {
			connection = new Connection(IP, listening_port);
		} else {
			connection = new Connection(listening_port);
		}
		
		ReservationControllerFactory.getReservationController().setWait_period(wait_period);
		DatabasePurge.initialize();
		
//		connection.setWait_period(wait_period);
	}
	
	protected void setupMBeans() {
		try {
			String prefix = "com.newrelic.airlines.pools:type=RequestPool,name=";
			MBeanServer server = ManagementFactory.getPlatformMBeanServer();
			
			String[] pools = {"CancellationRequestPool","CancelSearchRequestPool","CreateFlightRequestPool","DBRefreshRequestPool",
					"DeleteFlightRequestPool","FlightSearchRequestPool","GetCitiesRequestPool","GetScheduleRequestPool",
					"GetSearchIDRequestPool","GetWaitPeriodRequestPool","ReservationRequestPool","RetrieveRequestPool",
					"SetWaitPeriodRequestPool","UpdateFlightRequestPool","RevenueRequestPool","CapacityRequestPool"};
			
			for(int i=0;i<pools.length;i++) {
				BaseRequestPool pool = null;
				
				switch (i) {
				case 0:
					pool = CancellationRequestPool.getInstance();
					break;
				case 1:
					pool = CancelSearchRequestPool.getInstance();
					pool.setMaxThreads(3);
					break;
				case 2:
					pool = CreateFlightRequestPool.getInstance();
					pool.setMaxThreads(3);
					break;
				case 3:
					pool = DBRefreshRequestPool.getInstance();
					pool.setMaxThreads(3);
					break;
				case 4:
					pool = DeleteFlightRequestPool.getInstance();
					pool.setMaxThreads(3);
					break;
				case 5:
					pool = FlightSearchRequestPool.getInstance();
					break;
				case 6:
					pool = GetCitiesRequestPool.getInstance();
					pool.setMaxThreads(5);
					break;
				case 7:
					pool = GetScheduleRequestPool.getInstance();
					pool.setMaxThreads(3);
					break;
				case 8:
					pool = GetSearchIDRequestPool.getInstance();
					pool.setMaxThreads(5);
					break;
				case 9:
					pool = GetWaitPeriodRequestPool.getInstance();
					pool.setMaxThreads(3);
					break;
				case 10:
					pool = ReservationRequestPool.getInstance();
					break;
				case 11:
					pool = RetrieveRequestPool.getInstance();
					break;
				case 12:
					pool = SetWaitPeriodRequestPool.getInstance();
					pool.setMaxThreads(3);
					break;
				case 13:
					pool = UpdateFlightRequestPool.getInstance();
					pool.setMaxThreads(3);
					break;
				case 14:
					pool = RevenueRequestPool.getInstance();
					pool.setMaxThreads(3);
					break;
				case 15:
					pool = CapacityRequestPool.getInstance();
					pool.setMaxThreads(3);
					break;
				default:
					break;
				}
				
				if(pool != null) {
					RequestPool rPoolBean = new RequestPool(pool);
					ObjectName oname = new ObjectName(prefix+pool.getClass().getSimpleName());
					server.registerMBean(rPoolBean, oname);
					
				}
			}
		
			
/*			CancellationRequestPool cPool = CancellationRequestPool.getInstance();
			RequestPool cPoolBean = new RequestPool(cPool);
			ObjectName oname1 = new ObjectName(prefix+cPool.getClass().getSimpleName());
			server.registerMBean(cPoolBean, oname1);
			
			FlightSearchRequestPool fsPool = FlightSearchRequestPool.getInstance();
			RequestPool fsPoolBean = new RequestPool(fsPool);
			ObjectName oname2 = new ObjectName(prefix+fsPool.getClass().getSimpleName());
			server.registerMBean(fsPoolBean, oname2);

			ReservationRequestPool rPool = ReservationRequestPool.getInstance();
			RequestPool rPoolBean = new RequestPool(rPool);
			ObjectName oname3 = new ObjectName(prefix+rPool.getClass().getSimpleName());
			server.registerMBean(rPoolBean, oname3);

			RetrieveRequestPool retPool = RetrieveRequestPool.getInstance();
			RequestPool retPoolBean = new RequestPool(retPool);
			ObjectName oname4 = new ObjectName(prefix+retPool.getClass().getSimpleName());
			server.registerMBean(retPoolBean, oname4);
*/
			WaitPeriod waitPeriod = new WaitPeriod();
			ObjectName on = new ObjectName("com.newrelic.airlines:type=Wait,name="+waitPeriod.getClass().getSimpleName());
			server.registerMBean(waitPeriod, on);
		} catch (MalformedObjectNameException e) {
			LOG.error("Error creating MBean", e);
		} catch (InstanceAlreadyExistsException e) {
			LOG.error("Error creating MBean", e);
		} catch (MBeanRegistrationException e) {
			LOG.error("Error creating MBean", e);
		} catch (NotCompliantMBeanException e) {
			LOG.error("Error creating MBean", e);
		}

	}
	public void start() {
		try {
			connection.listen();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

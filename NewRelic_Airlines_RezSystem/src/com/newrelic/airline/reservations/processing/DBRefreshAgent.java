package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.ReservationController;
import com.newrelic.airline.reservations.ReservationControllerFactory;
import com.newrelic.airline.reservations.ReservationControllerImpl;
import com.newrelic.airline.reservations.forms.DBRefreshResponse;
import com.newrelic.airline.reservations.pools.DatabaseConnectionPool;

public class DBRefreshAgent extends BaseAgent {

	private static final Logger LOG = Logger.getLogger(DBRefreshAgent.class);

	@Override
	public void processRequest() {
		boolean successful = true;
		ReservationController controller = ReservationControllerFactory.getReservationController();
		if(ReservationControllerImpl.class.isInstance(controller)) {
			ReservationControllerImpl impl = (ReservationControllerImpl)controller;
			DatabaseConnectionPool pool = impl.getDatabaseConnectionPool();
			
			try {
				pool.reset();
			} catch (Exception e) {
				successful = false;
				LOG.info("Failed to reset database connection pool",e);
			}
			
			if(successful) {
				LOG.info("Reset database connection pool");
			}
		} else {
			successful = false;
			LOG.info("Failed to reset database connection pool, controller was not the IMPL implementation");
		}

		try {
			ObjectOutputStream outStream = new ObjectOutputStream(clientSocket.getOutputStream());
			response = new DBRefreshResponse();
			((DBRefreshResponse) response).setSuccess(successful);
			outStream.writeObject(response);
		} catch (IOException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
	}

}

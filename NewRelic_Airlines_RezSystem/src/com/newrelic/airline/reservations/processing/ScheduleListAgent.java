package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

public class ScheduleListAgent extends BaseAgent {

	private static final Logger LOG = Logger.getLogger(ScheduleListAgent.class);

	@Override
	public void processRequest() {
		try {
			LOG.info("Received request for schedule");
			response = controller.getSchedule();
			
			ObjectOutputStream outStream = new ObjectOutputStream(clientSocket.getOutputStream());
			
			outStream.writeObject(response);
		} catch (IOException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
	}

}

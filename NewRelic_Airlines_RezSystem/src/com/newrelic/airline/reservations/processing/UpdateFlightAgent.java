package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.UpdateFlightRequest;

public class UpdateFlightAgent extends BaseAgent {

	private static final Logger LOG = Logger.getLogger(UpdateFlightAgent.class);
	
	@Override
	protected void processRequest() {
		try {
			UpdateFlightRequest req = (UpdateFlightRequest)request;
			
			response = controller.updateFlight(req);
			ObjectOutputStream outStream = new ObjectOutputStream(clientSocket.getOutputStream());
			
			outStream.writeObject(response);
		} catch (IOException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}

	}

	public void setRequest(UpdateFlightRequest req) {
		request = req;
	}
}

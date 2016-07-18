package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.DeleteFlightRequest;

public class DeleteFlightAgent extends BaseAgent {

	private static final Logger LOG = Logger.getLogger(DeleteFlightAgent.class);
	public void setRequest(DeleteFlightRequest req) {
		request = req;
	}
	
	@Override
	protected void processRequest() {
		try {
			DeleteFlightRequest req = (DeleteFlightRequest)request;
			response = controller.deleteFlight(req);
			
			ObjectOutputStream outStream = new ObjectOutputStream(clientSocket.getOutputStream());
			
			outStream.writeObject(response);
		} catch (IOException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
	}

}

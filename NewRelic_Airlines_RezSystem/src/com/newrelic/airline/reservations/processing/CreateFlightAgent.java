package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.BaseRequest;
import com.newrelic.airline.reservations.forms.CreateFlightRequest;

public class CreateFlightAgent extends BaseAgent {
	
	private static final Logger LOG = Logger.getLogger(CreateFlightAgent.class);

	@Override
	protected void processRequest() {
		if(CreateFlightRequest.class.isInstance(request)) {
			try {
				CreateFlightRequest fRequest = (CreateFlightRequest)request;
				response = controller.addNewFlightToSchedule(fRequest);
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				out.writeObject(response);
				out.close();
				clientSocket.close();
			} catch (IOException e) {
				LOG.error(e.getClass().getSimpleName(), e);
			}
			
		}

	}

	public void setRequest(BaseRequest r) {
		request = r;
	}
}

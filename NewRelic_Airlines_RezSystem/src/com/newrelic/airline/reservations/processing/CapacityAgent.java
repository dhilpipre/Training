package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.CapacityByDateRequest;
import com.newrelic.airline.reservations.forms.CapacityByDateResponse;

public class CapacityAgent extends BaseAgent {

	private static final Logger LOG = Logger.getLogger(CapacityAgent.class);
	
	public CapacityAgent() {
		super();
	}
	public CapacityAgent(String name) {
		super(name);
	}
	
	public CapacityAgent(CapacityByDateRequest req) {
		super();
	}
	
	@Override
	protected void processRequest() {
		if(CapacityByDateRequest.class.isInstance(request)) {
			try {
				CapacityByDateRequest req = (CapacityByDateRequest)request;
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				CapacityByDateResponse response = controller.getCapacityByDate(req);
				out.writeObject(response);
				out.close();
				clientSocket.close();
			} catch (IOException e) {
				LOG.error(e.getClass().getSimpleName(), e);
			}
		}
	}

}

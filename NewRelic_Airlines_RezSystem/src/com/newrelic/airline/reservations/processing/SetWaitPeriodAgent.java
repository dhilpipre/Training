package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.SetWaitPeriodRequest;

public class SetWaitPeriodAgent extends BaseAgent {
	
	private static final Logger LOG = Logger.getLogger(SetWaitPeriodAgent.class);

	@Override
	protected void processRequest() {
		if(SetWaitPeriodRequest.class.isInstance(request)) {
			try {
				response = controller.setWaitPeriod((SetWaitPeriodRequest) request);
				ObjectOutputStream outStream = new ObjectOutputStream(clientSocket.getOutputStream());
				
				outStream.writeObject(response);
			} catch (IOException e) {
				LOG.error(e.getClass().getSimpleName(), e);
			}
			
		}
		
	}

	public void setRequest(SetWaitPeriodRequest req) {
		request = req;
	}

}

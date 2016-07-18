package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.GetWaitPeriodRequest;

public class GetWaitPeriodAgent extends BaseAgent {

	static final Logger LOG = Logger.getLogger(GetWaitPeriodAgent.class);
	@Override
	protected void processRequest() {
		if(GetWaitPeriodRequest.class.isInstance(request)) {
			try {
				response = controller.getWaitPeriod((GetWaitPeriodRequest) request);
				ObjectOutputStream outStream = new ObjectOutputStream(clientSocket.getOutputStream());
				
				outStream.writeObject(response);
			} catch (IOException e) {
				LOG.error(e.getClass().getSimpleName(), e);
			}
			
		}
	}
	public void setRequest(GetWaitPeriodRequest req) {
		request = req;
	}

}

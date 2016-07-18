package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.CancelSearchRequest;
import com.newrelic.airline.reservations.forms.CancelSearchResponse;

public class CancelSearchAgent extends BaseAgent {
	private static final Logger LOG = Logger.getLogger(ScheduleListAgent.class);

	public void setRequest(CancelSearchRequest req) {
		request = req;
	}
	
	public CancelSearchRequest getRequest() {
		return (CancelSearchRequest) request;
	}
	
	@Override
	public void processRequest() {
		try {
//			LOG.info("Received request for schedule");
			CancelSearchResponse cancelSearchResponse = controller.cancelSearch((CancelSearchRequest) request);
			
			ObjectOutputStream outStream = new ObjectOutputStream(clientSocket.getOutputStream());
			
			outStream.writeObject(cancelSearchResponse);
			response = cancelSearchResponse;
		} catch (IOException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
	}

}

package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.SearchIDRequest;
import com.newrelic.airline.reservations.forms.SearchIDResponse;

public class SearchIDAgent extends BaseAgent {
	private static final Logger LOG = Logger.getLogger(ScheduleListAgent.class);

	public void setRequest(SearchIDRequest req) {
		request = req;
	}
	
	public SearchIDRequest getRequest() {
		return (SearchIDRequest) request;
	}
	
	@Override
	public void processRequest() {
		try {
			LOG.info("Received request for schedule");
			SearchIDResponse searchResponse = controller.getSearchID((SearchIDRequest) request);
			
			ObjectOutputStream outStream = new ObjectOutputStream(clientSocket.getOutputStream());
			
			outStream.writeObject(searchResponse);
			response = searchResponse;
		} catch (IOException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
	}

}

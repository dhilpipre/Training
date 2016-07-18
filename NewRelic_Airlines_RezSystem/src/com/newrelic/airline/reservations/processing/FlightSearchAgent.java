package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.FlightSearchRequest;
import com.newrelic.airline.reservations.forms.FlightSearchResults;

public class FlightSearchAgent extends BaseAgent {
	
//	private FlightSearchRequest request;
	private static final Logger LOG = Logger.getLogger(FlightSearchAgent.class);
	
	public FlightSearchAgent() {
		super();
	}
	
	public FlightSearchAgent(String name) {
		super(name);
	}
	public FlightSearchAgent(FlightSearchRequest req) {
		super();
		request = req;
	}
	
	@Override
	public void processRequest() {
		if (request != null && FlightSearchRequest.class.isInstance(request)) {
			try {
				FlightSearchRequest fRequest = (FlightSearchRequest)request;
				FlightSearchResults flights = controller.findFlights(fRequest);
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				out.writeObject(flights);
				out.close();
				clientSocket.close();
				response = flights;
			} catch (IOException e) {
				LOG.error(e.getClass().getSimpleName(), e);
			}
		}
//		FlightSearchRequestPool.returnThreadToPool(this);
	}
	public FlightSearchRequest getRequest() {
		if (request != null && FlightSearchRequest.class.isInstance(request)) {
			FlightSearchRequest fRequest = (FlightSearchRequest)request;
			return fRequest;
		}
		return null;
	}
	public void setRequest(FlightSearchRequest request) {
		this.request = request;
		dumpProperties();
	}

}

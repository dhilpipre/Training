package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.CitiesServedResponse;

public class GetCitiesAgent extends BaseAgent {

	private static final Logger LOG = Logger.getLogger(GetCitiesAgent.class);

	@Override
	public void processRequest() {
		try {
			CitiesServedResponse cities = controller.getCitiesServed();
			ObjectOutputStream outStream = new ObjectOutputStream(clientSocket.getOutputStream());
			
			outStream.writeObject(cities);
			response = cities;
		} catch (IOException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
	}
	
	
	
}

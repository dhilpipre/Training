package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.FlightRosterRequest;
import com.newrelic.airline.reservations.forms.FlightRosterResponse;

public class GetFlightRosterAgent extends BaseAgent {

	private static final Logger LOG = Logger.getLogger(GetFlightRosterAgent.class);

	public GetFlightRosterAgent(FlightRosterRequest req) {
		super();
		request = req;
	}
	
	@Override
	protected void processRequest() {
		if(request != null && FlightRosterRequest.class.isInstance(request)) {
			try {
				FlightRosterRequest req = (FlightRosterRequest)request;
				FlightRosterResponse res = controller.getFlightRoster(req);
				response = res;
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

				out.writeObject(response);
				out.close();
				clientSocket.close();
			} catch (IOException e) {
				LOG.error(e.getClass().getSimpleName(), e);
			}

		}

	}

	public FlightRosterRequest getRequest() {
		if(request != null && FlightRosterRequest.class.isInstance(request)) {
			return (FlightRosterRequest) request;
		}
		return null;
	}
	
	public void setRequest(FlightRosterRequest req) {
		request = req;
	}
}

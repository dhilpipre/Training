package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.CreateReservationResponse;
import com.newrelic.airline.reservations.forms.ReservationRequest;

public class RezAgent extends BaseAgent {
	
	private static final Logger LOG = Logger.getLogger(RezAgent.class);

	public RezAgent() {
		super();
	}
	
	public RezAgent(String name) {
		super(name);
	}
	public RezAgent(ReservationRequest req) {
		super();
		request = req;
	}
	
	@Override
	public void processRequest() {
		if (request != null && ReservationRequest.class.isInstance(request)) {
			try {
				ReservationRequest rRequest = (ReservationRequest)request;
				LOG.debug("Creating Reservation for "+ rRequest.getFirstName() + " " + rRequest.getLastName());
				CreateReservationResponse confNum = controller.createReservation(rRequest);
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				out.writeObject(confNum);
				out.close();
				clientSocket.close();
				LOG.debug("Created Reservation for "+ rRequest.getFirstName() + " " + rRequest.getLastName());
				response = confNum;
			} catch (IOException e) {
				LOG.error(e.getClass().getSimpleName(), e);
			}
		}
	}

	public ReservationRequest getRequest() {
		if (request != null && ReservationRequest.class.isInstance(request)) {
			return (ReservationRequest)request;
		}
		return null;
	}
	
	public void setRequest(ReservationRequest request) {
		this.request = request;
	}

}

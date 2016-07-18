package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.CancelResponse;
import com.newrelic.airline.reservations.forms.ReservationCancelRequest;

public class CancellationAgent extends BaseAgent {
	private static final Logger LOG = Logger.getLogger(CancellationAgent.class);

	public ReservationCancelRequest getRequest() {
		if(ReservationCancelRequest.class.isInstance(request)) {
			return (ReservationCancelRequest)request;
		}
		return null;
	}

	public void setRequest(ReservationCancelRequest request) {
		this.request = request;
	}

	public CancellationAgent() {
		super();
	}

	public CancellationAgent(String name) {
		super(name);
	}

	@Override
	public void processRequest() {
		if(request != null && ReservationCancelRequest.class.isInstance(request)) {
			try {
				ReservationCancelRequest cRequest = (ReservationCancelRequest)request;
				CancelResponse result = controller.cancelReservation(cRequest.getConfirmation());
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				out.writeObject(result);
				out.close();
				clientSocket.close();
				response = result;
			} catch (IOException e) {
				LOG.error(e.getClass().getSimpleName(), e);
			}

		}
	}
	
	
}

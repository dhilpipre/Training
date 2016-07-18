package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.FindReservationRequest;
import com.newrelic.airline.reservations.forms.FindReservationsResponse;
import com.newrelic.airline.reservations.forms.ReservationDetails;

public class RetrieveRezAgent extends BaseAgent {
	private static final Logger LOG = Logger.getLogger(RetrieveRezAgent.class);
	
	public RetrieveRezAgent() {
		super();
	}
	
	public RetrieveRezAgent(String name) {
		super(name);
	}
	public RetrieveRezAgent(FindReservationRequest req) {
		super();
		request = req;
	}
	
	@Override
	public void processRequest() {
		if (request != null && FindReservationRequest.class.isInstance(request)) {
			try {
				List<ReservationDetails> list = new ArrayList<ReservationDetails>();
				FindReservationRequest fRequest = (FindReservationRequest)request;
				if (fRequest.getConfirmation() != null) {
					ReservationDetails reservation = controller.findReservationByConf(fRequest.getConfirmation()).getReservationList().get(0);
					list.add(reservation);
				} else if (fRequest.getFirstName() != null && fRequest.getLastName() != null) {
					list = controller.findReservationsByName(fRequest.getFirstName(), fRequest.getLastName()).getReservationList();
				}
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

				response = new FindReservationsResponse(list);
				out.writeObject(response);
				out.close();
				clientSocket.close();
			} catch (IOException e) {
				LOG.error(e.getClass().getSimpleName(), e);
			}
		}
	}
	public FindReservationRequest getRequest() {
		if (request != null && FindReservationRequest.class.isInstance(request)) {
			return (FindReservationRequest)request;
		}
		return null;
	}
	public void setRequest(FindReservationRequest request) {
		this.request = request;
		dumpProperties();
	}


}

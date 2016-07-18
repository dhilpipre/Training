package com.newrelic.airline.reservations.processing;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.RevenueDateRequest;
import com.newrelic.airline.reservations.forms.RevenueRequest;
import com.newrelic.airline.reservations.forms.RevenueSinceRequest;

public class RevenueAgent extends BaseAgent {
	
	private static final Logger LOG = Logger.getLogger(RevenueAgent.class);
	
	public RevenueAgent() {
		super();
	}

	public RevenueAgent(String name) {
		super(name);
	}
	
	public RevenueAgent(RevenueRequest req) {
		super();
		request = req;
	}

	@Override
	protected void processRequest() {
		if(request != null && request instanceof RevenueSinceRequest) {
			try {
				RevenueSinceRequest req = (RevenueSinceRequest)request;
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				response = controller.getRevenue(req);
				out.writeObject(response);
				out.close();
				clientSocket.close();
			} catch (IOException e) {
				LOG.error(e.getClass().getSimpleName(), e);
			}
		} else if(request != null && request instanceof RevenueDateRequest) {
			try {
				RevenueDateRequest req = (RevenueDateRequest)request;
				ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
				response = controller.getRevenue(req);
				out.writeObject(response);
				out.close();
				clientSocket.close();
			} catch (IOException e) {
				LOG.error(e.getClass().getSimpleName(), e);
			}
		}
	}

}

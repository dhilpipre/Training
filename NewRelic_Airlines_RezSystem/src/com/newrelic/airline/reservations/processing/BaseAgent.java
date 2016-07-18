package com.newrelic.airline.reservations.processing;

import java.net.Socket;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.ReservationController;
import com.newrelic.airline.reservations.ReservationControllerFactory;
import com.newrelic.airline.reservations.forms.BaseRequest;
import com.newrelic.airline.reservations.forms.BaseResponse;

public abstract class BaseAgent extends Thread {
	
	private final Logger LOG = Logger.getLogger(BaseAgent.class);
	
	protected BaseRequest request;
	protected BaseResponse response;
	
	public BaseAgent(String name) {
		super(name);
	}

	public BaseAgent() {
		super();
	}

	protected static ReservationController controller = ReservationControllerFactory.getReservationController();
	protected Socket clientSocket;
	
	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		processRequest();
	}

	abstract protected void processRequest();
	
	protected BaseRequest getRequest() {
		return request;
	}

	protected BaseResponse getResponse() {
		return response;
	}

	protected void dumpProperties() {
		LOG.debug("Dumping Properties");
		Properties props = request.getProperties();
		Set<Object> keys = props.keySet();
		if(keys.size() == 0) {
			LOG.debug("\tNo properties found");
		}
		for(Object key : keys) {
			Object value = props.get(key);
			LOG.debug("\tProperty "+key.toString()+": "+value.toString());
		}
	}
	
}

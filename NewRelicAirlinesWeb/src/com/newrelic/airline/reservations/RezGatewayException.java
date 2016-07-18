package com.newrelic.airline.reservations;

public class RezGatewayException extends Exception {

	public RezGatewayException(String message) {
		super(message);
	}

	public RezGatewayException(Exception e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2081142353034688143L;
	
	

}

package com.newrelic.airline.reservations.forms;

import java.io.Serializable;
import java.util.Properties;

public class BaseResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5994190998517115344L;

	protected Properties responseProperties;
	
	public BaseResponse() {
		responseProperties = new Properties();
	}
 	
	public Properties getProperties() {
		return responseProperties;
	}
	
	public String getProperty(String name) {
		return responseProperties.getProperty(name);
	}
	
	public void setProperty(String name, String value) {
		responseProperties.put(name, value);
	}
	
	public void removeProperty(String name) {
		responseProperties.remove(name);
	}
}

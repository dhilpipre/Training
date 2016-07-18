package com.newrelic.airline.reservations.forms;

import java.io.Serializable;
import java.util.Properties;

public class BaseRequest implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6671256418490436004L;
	protected Properties requestProperties;
	
	public BaseRequest(BaseRequest b) {
		requestProperties = b.getProperties();
	}
	public BaseRequest() {
		requestProperties = new Properties();
	}
 	public Properties getProperties() {
 		if(requestProperties == null) {
 			requestProperties = new Properties();
 		}
		return requestProperties;
	}
	
	public String getProperty(String property) {
 		if(requestProperties == null) {
 			requestProperties = new Properties();
 		}
		return requestProperties.getProperty(property);
	}
	
	public void setProperty(String key, String value) {
 		if(requestProperties == null) {
 			requestProperties = new Properties();
 		}
		requestProperties.setProperty(key, value);
	}
	
	public void removeProperty(String key) {
 		if(requestProperties == null) {
 			requestProperties = new Properties();
 		}
		requestProperties.remove(key);
	}

}

package com.newrelic.airline.reservations.forms;

import java.io.Serializable;

public class CancelSearchRequest extends BaseRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1121857329192117997L;

	private String searchID;

	public String getSearchID() {
		return searchID;
	}

	protected void setSearchID(String searchID) {
		this.searchID = searchID;
	}

	public CancelSearchRequest(String searchID) {
		super();
		this.searchID = searchID;
	}
	
}

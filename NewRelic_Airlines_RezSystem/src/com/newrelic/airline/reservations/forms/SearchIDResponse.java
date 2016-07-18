package com.newrelic.airline.reservations.forms;

import java.io.Serializable;

public class SearchIDResponse extends BaseResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2344404375973875163L;

	private String searchID;

	public String getSearchID() {
		return searchID;
	}

	public void setSearchID(String searchID) {
		this.searchID = searchID;
	}

	public SearchIDResponse(String searchID) {
		super();
		this.searchID = searchID;
	}
	
	
}

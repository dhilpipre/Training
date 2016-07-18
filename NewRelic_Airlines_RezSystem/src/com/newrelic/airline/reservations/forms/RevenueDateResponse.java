package com.newrelic.airline.reservations.forms;

import java.util.ArrayList;
import java.util.List;

public class RevenueDateResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6708815374711127106L;

	private List<Revenue> revenues;
	private float RevenueSum;
	private int reservations;
	private int cancelations;
	
	public int getCancelations() {
		return cancelations;
	}

	public RevenueDateResponse() {
		revenues = new ArrayList<Revenue>();
		reservations = 0;
	}
	
	public void addRevenue(Revenue r) {
		revenues.add(r);
		if(r.getRevenue() > 0) {
			reservations++;
		} else if(r.getRevenue() < 0) {
			cancelations++;
		}
	}
	
	public void removeRevenue(Revenue r) {
		revenues.remove(r);
		reservations--;
	}
	
	public List<Revenue> getRevenues() {
		return revenues;
	}
	public void setRevenues(List<Revenue> revenues) {
		this.revenues = revenues;
	}

	public float getRevenueSum() {
		return RevenueSum;
	}

	public void setRevenueSum(float revenueSum) {
		RevenueSum = revenueSum;
	}

	public int getReservations() {
		return reservations;
	}
	
}

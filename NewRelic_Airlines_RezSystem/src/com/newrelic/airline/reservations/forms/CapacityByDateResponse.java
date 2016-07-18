package com.newrelic.airline.reservations.forms;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CapacityByDateResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1600961239486032069L;

	private Date start;
	private Date end;
	private List<CapacityRecord> capacityList;
	
	public List<CapacityRecord> getCapacityList() {
		return capacityList;
	}

	public CapacityByDateResponse() {
		super();
		capacityList = new ArrayList<CapacityRecord>();
	}
	
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	public void addCapacity(CapacityRecord record) {
		capacityList.add(record);
	}
	
	public float getDateCapacity(Date d) {
		float percent = 0;
		int taken = 0;
		int total = 0;
		
		for(CapacityRecord record : capacityList) {
			if(record.getFlightDate().equals(d)) {
				int totalSeats = record.getTotalSeats();
				int takenSeats = totalSeats - record.getAvailable();
				taken += takenSeats;
				total += totalSeats;
			}
		}
		percent = (float)taken/(float)total;
		return percent;
	}
	
	public float getAllCapacity(Date s, Date e) {
		float percent = 0;
		int taken = 0;
		int total = 0;
		for(CapacityRecord record : capacityList) {
			Date d = record.getFlightDate();
			if ((d.equals(s) || d.after(s)) && (d.equals(e) || d.before(e))) {
				int totalSeats = record.getTotalSeats();
				int takenSeats = totalSeats - record.getAvailable();
				taken += takenSeats;
				total += totalSeats;
			}
		}

		percent = (float)taken/(float)total;
		return percent;
	}
	
	public float getFlightCapacity(String flightNumber, Date s) {
		return getFlightCapacity(flightNumber, s, s);
	}
	
	public float getFlightCapacity(String flightNumber, Date s, Date e) {
		float percent = 0;
		int taken = 0;
		int total = 0;
		
		for(CapacityRecord record : capacityList) {
			if (record.getFlightNumber().equals(flightNumber)) {
				Date d = record.getFlightDate();
				if ((d.equals(s) || d.after(s)) && (d.equals(e) || d.before(e))) {
					int totalSeats = record.getTotalSeats();
					int takenSeats = totalSeats - record.getAvailable();
					taken += takenSeats;
					total += totalSeats;
				}
			}
		}
		percent = (float)taken/(float)total;
		return percent;
	}
}

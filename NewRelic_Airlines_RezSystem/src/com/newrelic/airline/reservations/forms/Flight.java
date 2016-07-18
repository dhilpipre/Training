package com.newrelic.airline.reservations.forms;

import java.io.Serializable;


public class Flight extends BaseRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4988152415688524117L;
	private String departAirport;
	private String arriveAirport;
	private String departure;
	private String arrival;
	private String aircraft;
	private String flightNumber;
	private Float price;
	
	
	public String getDepartAirport() {
		return departAirport;
	}
	public void setDepartAirport(String departAirport) {
		this.departAirport = departAirport;
	}
	public String getArriveAirport() {
		return arriveAirport;
	}
	public void setArriveAirport(String arriveAirport) {
		this.arriveAirport = arriveAirport;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public String getAircraft() {
		return aircraft;
	}
	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(flightNumber+"\t");
		sb.append(departAirport+"\t");
		sb.append(arriveAirport+"\t");
		sb.append(departure+"\t");
		sb.append(arrival+"\t");
		sb.append(aircraft+"\t");

		return sb.toString();
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	@Override
	public boolean equals(Object obj) {
		if(Flight.class.isInstance(obj)) {
			Flight f = (Flight)obj;
			return aircraft.equals(f.aircraft) && arrival.equals(f.arrival) && arriveAirport.equals(f.arriveAirport) && 
					departAirport.equals(f.departAirport) && departure.equals(f.departure) && flightNumber.equals(f.flightNumber)
					&& f.price == price;
		}
		return super.equals(obj);
	}
	
	
}

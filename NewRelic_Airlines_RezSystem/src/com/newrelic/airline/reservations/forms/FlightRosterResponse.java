package com.newrelic.airline.reservations.forms;

import java.util.Date;
import java.util.List;

public class FlightRosterResponse extends BaseResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8125135970050832238L;
	private int seatsOccupied;
	private int totalSeats;
	private String flightNumber;
	private String fromAirport;
	private String toAirport;
	private String departure;
	private String arrival;
	private String aircraft;
	private Date flightDate;
	
	public Date getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}
	public String getAircraft() {
		return aircraft;
	}
	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}
	private List<SeatInfo> seats;
	public int getSeatsOccupied() {
		return seatsOccupied;
	}
	public void setSeatsOccupied(int seatsOccupied) {
		this.seatsOccupied = seatsOccupied;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFromAirport() {
		return fromAirport;
	}
	public void setFromAirport(String fromAirport) {
		this.fromAirport = fromAirport;
	}
	public String getToAirport() {
		return toAirport;
	}
	public void setToAirport(String toAirport) {
		this.toAirport = toAirport;
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
	public List<SeatInfo> getSeats() {
		return seats;
	}
	public void setSeats(List<SeatInfo> seats) {
		this.seats = seats;
	}
	
}

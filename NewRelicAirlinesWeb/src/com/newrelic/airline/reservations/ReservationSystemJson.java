package com.newrelic.airline.reservations;



public interface ReservationSystemJson {

	public String cancelReservation(String json);
	
	public String findAvailableFlights(String json);
	
	public String getCitiesServed();
	
	public String listReservation(String json);
	
	public String listReservations(String json);
	
	public String makeReservation(String json);
	
}

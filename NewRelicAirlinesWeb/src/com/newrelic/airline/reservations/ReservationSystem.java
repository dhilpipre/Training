package com.newrelic.airline.reservations;

import java.util.Date;

import com.newrelic.airline.reservations.forms.ReservationDetails;

public interface ReservationSystem {

	public String cancelReservation(String confirmation);
	
	public FlightSearchResults findAvailableFlights(String from, String to, Date departureDate, Date returnDate);
	
	public String[] getCitiesServed();
	
	public ReservationDetails listReservation(String confirmation);
	
	public ReservationDetails[] listReservations(String first, String last);
	
	public String makeReservation(String firstName,String lastName,String fromAirport,String toAirport,String outboundFltNum,
			Date departDate, Date returnDate, String returnFltNum, String outSeat, String retSeat, Float outPrice, Float retPrice);
	
}

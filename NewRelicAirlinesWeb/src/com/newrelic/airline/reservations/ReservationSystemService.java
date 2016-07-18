package com.newrelic.airline.reservations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.newrelic.airline.reservations.forms.FlightDate;
import com.newrelic.airline.reservations.forms.ReservationCancelRequest;
import com.newrelic.airline.reservations.forms.ReservationDetails;
import com.newrelic.airline.reservations.forms.ReservationRequest;

public class ReservationSystemService implements ReservationSystem {

	static private final Logger LOG = Logger.getLogger(ReservationSystemService.class);
	
	public String cancelReservation(String confirmation) {
		ReservationCancelRequest request = new ReservationCancelRequest();
		request.setConfirmation(confirmation);
		String response = null;
		
		try {
			response = RezSystemGateway.getInstance().cancelReservation(request);
		} catch (RezGatewayException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
		
		return response;
	}
	
	public FlightSearchResults findAvailableFlights(String from, String to, Date departureDate, Date returnDate) {
		
		FlightSearchResults results = new FlightSearchResults();
		
		try {
			com.newrelic.airline.reservations.forms.FlightSearchResults results2 = RezSystemGateway.getInstance().findAvailFlights(from, to, departureDate, returnDate);
			List<FlightDate> outList = results2.getOutFlights();
			
			FlightDate[] out = new FlightDate[outList.size()];
			outList.toArray(out);
			results.setOutFlights(out);
			
			List<FlightDate> retList = results2.getReturnFlights();
			FlightDate[] ret = new FlightDate[retList.size()];
			retList.toArray(ret);
			
			results.setReturnFlights(ret);
			
		} catch (RezGatewayException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
		
		return results;
	}
	
	public String[] getCitiesServed() {
		String[] cities = new String[0];
		
		try {
			cities = RezSystemGateway.getInstance().getCitiesServed();
		} catch (RezGatewayException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
		return cities;
	}
	
	public ReservationDetails listReservation(String confirmation) {
		ReservationDetails details = new ReservationDetails();
		
		try {
			details = RezSystemGateway.getInstance().listReservation(confirmation);
		} catch (RezGatewayException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
		
		return details;
	}
	
	public ReservationDetails[] listReservations(String first, String last) {
		List<ReservationDetails> list = new ArrayList<ReservationDetails>();
		ReservationDetails[] reservations = new ReservationDetails[0];
		try {
			list = RezSystemGateway.getInstance().listReservations(first, last);
			reservations = new ReservationDetails[list.size()];
			list.toArray(reservations);
		} catch (RezGatewayException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
		
		return reservations;
		
	}

	@Override
	public String makeReservation(String firstName,String lastName,String fromAirport,String toAirport,String outboundFltNum,
			Date departDate, Date returnDate, String returnFltNum, String outSeat, String retSeat, Float outPrice, Float retPrice) {
		String confirmation = null;
		ReservationRequest request = new ReservationRequest();
		request.setFirstName(firstName);
		request.setLastName(lastName);
		request.setFromAirport(fromAirport);
		request.setToAirport(toAirport);
		request.setOutboundFltNum(outboundFltNum);
		request.setDepartDate(departDate);
		request.setReturnDate(returnDate);
		request.setReturnFltNum(returnFltNum);
		request.setOutSeat(outSeat);
		request.setRetSeat(retSeat);
		request.setOutPrice(outPrice);
		request.setReturnPrice(retPrice);
		
		try {
			confirmation = RezSystemGateway.getInstance().makeReservation(request);
		} catch (RezGatewayException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
		
		return confirmation;
	}
	
}

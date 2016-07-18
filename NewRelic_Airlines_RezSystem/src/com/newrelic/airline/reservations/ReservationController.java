package com.newrelic.airline.reservations;

import com.newrelic.airline.reservations.forms.CancelResponse;
import com.newrelic.airline.reservations.forms.CancelSearchRequest;
import com.newrelic.airline.reservations.forms.CancelSearchResponse;
import com.newrelic.airline.reservations.forms.CapacityByDateRequest;
import com.newrelic.airline.reservations.forms.CapacityByDateResponse;
import com.newrelic.airline.reservations.forms.CapacityByFlightsRequest;
import com.newrelic.airline.reservations.forms.CapacityByFlightsResponse;
import com.newrelic.airline.reservations.forms.CitiesServedResponse;
import com.newrelic.airline.reservations.forms.CreateFlightRequest;
import com.newrelic.airline.reservations.forms.CreateFlightResponse;
import com.newrelic.airline.reservations.forms.CreateReservationResponse;
import com.newrelic.airline.reservations.forms.DeleteFlightRequest;
import com.newrelic.airline.reservations.forms.DeleteFlightResponse;
import com.newrelic.airline.reservations.forms.FindReservationsResponse;
import com.newrelic.airline.reservations.forms.FlightRosterRequest;
import com.newrelic.airline.reservations.forms.FlightRosterResponse;
import com.newrelic.airline.reservations.forms.FlightSearchRequest;
import com.newrelic.airline.reservations.forms.FlightSearchResults;
import com.newrelic.airline.reservations.forms.GetWaitPeriodRequest;
import com.newrelic.airline.reservations.forms.GetWaitPeriodResponse;
import com.newrelic.airline.reservations.forms.ReservationRequest;
import com.newrelic.airline.reservations.forms.RevenueDateRequest;
import com.newrelic.airline.reservations.forms.RevenueDateResponse;
import com.newrelic.airline.reservations.forms.RevenueSinceRequest;
import com.newrelic.airline.reservations.forms.RevenueResponse;
import com.newrelic.airline.reservations.forms.ScheduleListResponse;
import com.newrelic.airline.reservations.forms.SearchIDRequest;
import com.newrelic.airline.reservations.forms.SearchIDResponse;
import com.newrelic.airline.reservations.forms.SetWaitPeriodRequest;
import com.newrelic.airline.reservations.forms.SetWaitPeriodResponse;
import com.newrelic.airline.reservations.forms.UpdateFlightRequest;
import com.newrelic.airline.reservations.forms.UpdateFlightResponse;

/**
 * Interface for handling requests that are received
 * ReservationControllerImpl is the implementation class
 * 
 * @author dhilpipre
 *
 */
public interface ReservationController {

	public FindReservationsResponse findReservationByConf(String confirmation);
	
	public CreateReservationResponse createReservation(ReservationRequest request);
	
	public FindReservationsResponse findReservationsByName(String firstName, String lastName);
	
	public CitiesServedResponse getCitiesServed();
	
	public FlightSearchResults findFlights(FlightSearchRequest request);
	
	public FindReservationsResponse findAllReservations();
	
	public CancelResponse cancelReservation(String confirmation);
	
	public void setWait_period(long wait);
	public long getWait_period();
	
	public ScheduleListResponse getSchedule();
	
	public SearchIDResponse getSearchID(SearchIDRequest request);
	
	public CancelSearchResponse cancelSearch(CancelSearchRequest request);
	
	public GetWaitPeriodResponse getWaitPeriod(GetWaitPeriodRequest request);
	
	public SetWaitPeriodResponse setWaitPeriod(SetWaitPeriodRequest request);
	
	public CreateFlightResponse addNewFlightToSchedule(CreateFlightRequest request);
	
	public DeleteFlightResponse deleteFlight(DeleteFlightRequest request);
	
	public UpdateFlightResponse updateFlight(UpdateFlightRequest request);
	
	public FlightRosterResponse getFlightRoster(FlightRosterRequest request);
	
	public RevenueResponse getRevenue(RevenueSinceRequest request);
	
	public RevenueDateResponse getRevenue(RevenueDateRequest request);
	
	public CapacityByDateResponse getCapacityByDate(CapacityByDateRequest request);

	public CapacityByFlightsResponse getCapacityByFlights(CapacityByFlightsRequest request);

}

package com.newrelic.airline.reservations;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.newrelic.action.ActionControl;
import com.newrelic.action.ActionRouter;
import com.newrelic.airline.reservations.forms.FlightDate;
import com.newrelic.airline.reservations.forms.FlightSearchResults;
import com.newrelic.airline.reservations.forms.ReservationCancelRequest;
import com.newrelic.airline.reservations.forms.ReservationDetails;
import com.newrelic.airline.reservations.forms.ReservationRequest;

public class ReservationActionControl implements ActionControl {

	private static final Logger LOG = Logger.getLogger(ReservationActionControl.class);
	private static final String SEARCHID = "Search-ID";

	@Override
	public ActionRouter performAction(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
		String action = request.getParameter("action");
		String page = "";
		HttpSession session = request.getSession();
		
		try {
			if(action.equals("findresbyconf")) {
				String confNumber = request.getParameter("confirmation");
				ReservationDetails details = RezSystemGateway.getInstance().listReservation(confNumber);
				session.setAttribute("reservationDetails", details);
				session.setAttribute("confirmation", confNumber);
				page = "reservationdisplay";
			} else if(action.equals("findbyname")) {
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String fullName = firstName + " " + lastName;
				List<ReservationDetails> details = RezSystemGateway.getInstance().listReservations(firstName, lastName);
				session.setAttribute("reservationList", details);
				session.setAttribute("fullName", fullName);
				page = "listReservations";

			} else if(action.equals("flightSearch")) {
				String departAirport = (request.getParameter("fromAirport")).replace('_', ' ');
				String toAirport = (request.getParameter("toAirport")).replace('_', ' ');
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String outDate = request.getParameter("outDate");
				String retDate = request.getParameter("retDate");
				Date outboundDate = null;
				Date returnDate = null;

				try {
					outboundDate = sdf.parse(outDate);
					returnDate = sdf.parse(retDate);
				} catch (ParseException e) {
					LOG.error("ParseException", e);
				}

				FlightSearchResults flights = RezSystemGateway.getInstance().findAvailFlights(departAirport, toAirport, outboundDate, returnDate);
				session.setAttribute("results", flights);
				session.setAttribute("fromAirport", departAirport);
				session.setAttribute("toAirport", toAirport);
				session.setAttribute("depDate", outDate);
				session.setAttribute("returnDate", retDate);
				session.setAttribute("selecting", "outbound");
				session.setAttribute(SEARCHID, flights.getProperty(SEARCHID));
				page = "availableFlights";
			} else if(action.equals("selectres")) {
				String selecting = (String)session.getAttribute("selecting");
				if(selecting == null) {
					selecting = "outbound";
				}
				boolean isOutbound = selecting.equalsIgnoreCase("outbound");
				int resNum = Integer.parseInt(request.getParameter("resnum"));
				FlightSearchResults results = (FlightSearchResults) session.getAttribute("results");
				String departAirport = (String) session.getAttribute("fromAirport");
				String toAirport = (String) session.getAttribute("toAirport");

				List<FlightDate> flights = null;
				if(isOutbound) {
					flights = results.getOutFlights();
				} else {
					flights = results.getReturnFlights();
				}
				FlightDate selected = flights.get(resNum);
				LOG.debug("Selected: "+selected);
				session.setAttribute(selecting, selected);
				if(selecting.equalsIgnoreCase("outbound")) {
					selecting = "return";
					session.setAttribute("selecting", selecting);
					page = "availableFlights";
					session.setAttribute("fromAirport", toAirport);
					session.setAttribute("toAirport", departAirport);
					session.setAttribute("depDate", session.getAttribute("returnDate"));
					session.setAttribute(SEARCHID, results.getProperty(SEARCHID));
				} else {
					page = "confirm";
				}
			} else if(action.equals("confirmRes")) {
				String searchID = (String) session.getAttribute(SEARCHID);
				String outSeat = request.getParameter("outseat");
				String retSeat = request.getParameter("retseat");
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				FlightDate outFD = (FlightDate) session.getAttribute("outbound");
				FlightDate retFD = (FlightDate) session.getAttribute("return");
				ReservationRequest resRequest = new ReservationRequest();
				resRequest.setProperty(SEARCHID, searchID);
				
				if(outSeat == null) {
					outSeat = outFD.getAvailableSeats().get(0);
				}
				if(retSeat == null) {
					retSeat = retFD.getAvailableSeats().get(0);
				}

				String dt = outFD.getFlight().getDeparture();
				Date adjusted = adjust(outFD.getFlightDate(), dt);
				resRequest.setDepartDate(adjusted);
				resRequest.setFirstName(firstName);
				resRequest.setFromAirport(outFD.getFlight().getDepartAirport());
				resRequest.setLastName(lastName);
				resRequest.setOutboundFltNum(outFD.getFlight().getFlightNumber());
				resRequest.setOutSeat(outSeat);
				resRequest.setRetSeat(retSeat);
				dt = retFD.getFlight().getDeparture();
				adjusted = adjust(retFD.getFlightDate(), dt);
				resRequest.setReturnDate(adjusted);
				resRequest.setReturnFltNum(retFD.getFlight().getFlightNumber());
				resRequest.setToAirport(retFD.getFlight().getDepartAirport());
				resRequest.setOutPrice(outFD.getFlight().getPrice());
				resRequest.setReturnPrice(retFD.getFlight().getPrice());

				String resResponse = RezSystemGateway.getInstance().makeReservation(resRequest);

				session.setAttribute("confirmation", resResponse);

				ReservationDetails details = RezSystemGateway.getInstance().listReservation(resResponse);
				session.setAttribute("reservationDetails", details);
				page = "reservationdisplay";

			} else if(action.equalsIgnoreCase("cancel")) {
				String searchID = (String) session.getAttribute(SEARCHID);
				ReservationCancelRequest cancel = new ReservationCancelRequest();
				if(searchID != null) {
					cancel.setProperty(SEARCHID, searchID);
				}
				cancel.setConfirmation((String)session.getAttribute("confirmation"));
				String result = RezSystemGateway.getInstance().cancelReservation(cancel);
				session.setAttribute("cancelResult", result);
				page = "main";
			}
		} catch (RezGatewayException e) {
			LOG.error(e.getClass().getSimpleName(), e);
			page = "errorpage";
		}

		return new ActionRouter(page);
	}

	private Date adjust(Date d, String hhmm) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int index = hhmm.indexOf(':');
		String hour = hhmm.substring(0, index);
		String minute = hhmm.substring(index+1, index+3);
		String ampm = hhmm.substring(index+4);
		cal.set(Calendar.HOUR, Integer.parseInt(hour));
		cal.set(Calendar.MINUTE, Integer.parseInt(minute));
		if(ampm.trim().equalsIgnoreCase("am")) {
			cal.set(Calendar.AM_PM, Calendar.AM);
		} else {
			cal.set(Calendar.AM_PM, Calendar.PM);
		}
		return cal.getTime();
	}

}

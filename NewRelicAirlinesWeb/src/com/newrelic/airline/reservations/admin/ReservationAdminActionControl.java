package com.newrelic.airline.reservations.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.newrelic.action.ActionControl;
import com.newrelic.action.ActionRouter;
import com.newrelic.airline.reservations.RezGatewayException;
import com.newrelic.airline.reservations.RezSystemGateway;
import com.newrelic.airline.reservations.forms.CreateFlightResponse;
import com.newrelic.airline.reservations.forms.DeleteFlightResponse;
import com.newrelic.airline.reservations.forms.Flight;
import com.newrelic.airline.reservations.forms.SetWaitPeriodResponse;
import com.newrelic.airline.reservations.forms.UpdateFlightResponse;

public class ReservationAdminActionControl implements ActionControl {

	private static final Logger LOG = Logger.getLogger(ReservationAdminActionControl.class);

	@Override
	public ActionRouter performAction(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String action = request.getParameter("action");
		String page = "";
		HttpSession session = request.getSession();
		
		try {
			if(action.equals("flightschedule")) {
				HashMap<String, List<Flight>> map = RezSystemGateway.getInstance().getSchedule();
				session.setAttribute("schedule", map);
				page = "displaySchedule";
			} else if(action.equals("getwaitperiod")) {
				Long wait_period = RezSystemGateway.getInstance().getWaitPeriod();
				session.setAttribute("wait_period", wait_period);
				page = "changewait";
			} else if(action.equals("setwaitperiod")) {
				
				long new_wait = Long.parseLong(request.getParameter("new_wait_period"));
				SetWaitPeriodResponse waitResponse = RezSystemGateway.getInstance().setWaitPeriod(new_wait );
				
				if(waitResponse == null) {
					session.setAttribute("set_wait_success", Boolean.FALSE);
				} else {
					session.setAttribute("new_wait_period", waitResponse.getNew_Wait_period());
					session.setAttribute("old_wait_period", waitResponse.getOld_wait_period());
					session.setAttribute("set_wait_success", Boolean.TRUE);
				}
				page = "waitchanged";
			} else if(action.equals("changeschedule")) {
				HashMap<String, List<Flight>> map = RezSystemGateway.getInstance().getSchedule();
				session.setAttribute("schedule", map);
				page = "changeschedule";
			} else if(action.equals("createflight")) {
				String fromAirport = request.getParameter("fromAirport");
				String toAirport = request.getParameter("toAirport");
				String flightnumber = request.getParameter("flightnumber");
				String aircraft = request.getParameter("aircraft");
				String depart = request.getParameter("depart");
				String arrival = request.getParameter("arrival");
				float price = Float.parseFloat(request.getParameter("price"));
				
				Flight flight = new Flight();
				flight.setAircraft(aircraft);
				flight.setArrival(arrival);
				flight.setArriveAirport(toAirport);
				flight.setDepartAirport(fromAirport);
				flight.setDeparture(depart);
				flight.setFlightNumber(flightnumber);
				flight.setPrice(price);
				
				CreateFlightResponse resp = RezSystemGateway.getInstance().createNewFlight(flight);
				
				Boolean b = resp.isSuccess();

				if(b != null && b) {
					HashMap<String, List<Flight>> map = RezSystemGateway.getInstance().getSchedule();
					session.setAttribute("schedule", map);
					page = "displaySchedule";
				} else {
					session.setAttribute("message", "Failed to add flight to schedule");
					page = "message";
				}
			} else if(action.equals("deleteflight")) {
				String flightNo = request.getParameter("flightno");
				DeleteFlightResponse resp = RezSystemGateway.getInstance().deleteFlight(flightNo);
				if(resp != null && resp.isSuccess()) {
					HashMap<String, List<Flight>> map = RezSystemGateway.getInstance().getSchedule();
					session.setAttribute("schedule", map);
					page = "displaySchedule";
				} else {
					session.setAttribute("message", "Failed to delete flight "+flightNo+" to schedule");
					page = "message";
				}
			} else if(action.equals("editflight")) {
				String route = request.getParameter("route");
				String flightNo = request.getParameter("flightno");
				Flight flightToEdit = null;
				
				HashMap<String, List<Flight>> map = RezSystemGateway.getInstance().getSchedule();
				List<Flight> list = map.get(route);
				for(int i=0;i<list.size() && flightToEdit == null;i++) {
					Flight f = list.get(i);
					if(f.getFlightNumber().equals(flightNo)) {
						flightToEdit = f;
					}
				}
				if(flightToEdit != null) {
					session.setAttribute("flightToEdit", flightToEdit);
					page = "editFlight";
				} else {
					session.setAttribute("message", "Failed to find flight "+flightNo+" to edit");
					page = "message";
				}
			} else if(action.equals("modifyflight")) {
				String fromAirport = request.getParameter("fromAirport");
				String toAirport = request.getParameter("toAirport");
				String flightnumber = request.getParameter("flightnumber");
				String aircraft = request.getParameter("aircraft");
				String depart = request.getParameter("depart");
				String arrival = request.getParameter("arrival");
				float price = Float.parseFloat(request.getParameter("price"));
				
				Flight flightToEdit = (Flight) session.getAttribute("flightToEdit");
				Flight modified = new Flight();
				modified.setAircraft(aircraft);
				modified.setArrival(arrival);
				modified.setArriveAirport(toAirport);
				modified.setDepartAirport(fromAirport);
				modified.setDeparture(depart);
				modified.setFlightNumber(flightnumber);
				modified.setPrice(price);
				
				if (!modified.equals(new Flight())) {
					UpdateFlightResponse resp = RezSystemGateway.getInstance().modifyFlight(flightToEdit, modified);
					if (resp != null && resp.isSuccess()) {
						HashMap<String, List<Flight>> map = RezSystemGateway.getInstance().getSchedule();
						session.setAttribute("schedule", map);
						page = "displaySchedule";
					} else {
						session.setAttribute("message", "Failed to update flight " + flightToEdit.getFlightNumber());
						page = "message";
					}
				} else {
					session.setAttribute("message", "No changes detected for flight " + flightToEdit.getFlightNumber());
					page = "message";
				}
			}
		} catch (RezGatewayException e) {
			LOG.error(e.getClass().getSimpleName(), e);
			page = "errorpage";
		}

		return new ActionRouter(page);
	}

}

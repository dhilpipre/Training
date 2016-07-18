<%@page import="java.util.ArrayList"%>
<%@page import="com.newrelic.airline.reservations.forms.Flight"%>
<%@page import="com.newrelic.airline.reservations.forms.FlightDate"%>
<%@page import="com.newrelic.airline.reservations.forms.FlightSearchResults"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	FlightSearchResults results = (FlightSearchResults)session.getAttribute("results");
	String fromAirport = (String)session.getAttribute("fromAirport");
	String toAirport = (String)session.getAttribute("toAirport");
	String depDate = (String)session.getAttribute("depDate");
	String retDate = (String)session.getAttribute("retDate");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
	String selecting = (String)session.getAttribute("selecting");
	selecting = selecting.substring(0, 1).toUpperCase() + selecting.substring(1).toLowerCase();
	
	String action = null;
	List<FlightDate> flightList = null;
	if(selecting.equalsIgnoreCase("outbound")) {
		if(results != null) {
			flightList = results.getOutFlights();
		} else {
			flightList = new ArrayList<FlightDate>();
		}
	} else {
		if(results != null) {
			flightList = results.getReturnFlights();
		} else {
			flightList = new ArrayList<FlightDate>();
		}
	}	
		action = "selectres";
 %>
<head>
	<link href="css/bg-image.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Available Flights <%=selecting %></title>
</head>
<body>

<jsp:include page="Header.jsp"/>
<table border="1">
	<tr><th style="color:white">From</th><th style="color:white">To</th><th style="color:white"><%=selecting %> Date</th></tr>
	<tr><td style="color:white"><%=fromAirport %></td><td style="color:white"><%=toAirport %></td><td style="color:white"><%=depDate %></td></tr>
</table>
<h3><%=selecting %> Flights:</h3>
		<form>
			<table border="1">
				<% if(!flightList.isEmpty()) { %>
				<tr><th>Flight Number</th><th>Departure Time</th><th>Arrival Time</th><th># Seats</th><th>Aircraft</th><th>Price</th></tr>
				<% 
				int i = 0;
					for(FlightDate fd : flightList) { 
					Flight flight = fd.getFlight();
				%>
					<tr><td  style="color:white"><%=fd.getFlightNumber() %></td><td  style="color:white"><%=flight.getDeparture() %></td><td  style="color:white"><%=flight.getArrival() %></td><td  style="color:white"><%=fd.getAvailableSeats().size() %></td><td  style="color:white"><%=flight.getAircraft() %></td><td  style="color:white"><%=flight.getPrice() %></td><td  style="color:white"><input value="Select" type="button" onclick="window.location.href='reservationcontrol.action?action=<%=action %>&resnum=<%=i%>'"></tr>
				<% 
						i++;
					} %>
			<% } else { %>
				<tr><th>No flights Available</th></tr>
			<% } %>
			</table>
		</form>
<jsp:include page="Footer.jsp"/>
</body>
</html>
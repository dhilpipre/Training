<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.newrelic.airline.reservations.forms.FlightDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="css/bg-image.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirm</title>
</head>
<%
	FlightDate outFD = (FlightDate)session.getAttribute("outbound");
	FlightDate retFD = (FlightDate)session.getAttribute("return");
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	String outDate = sdf.format(outFD.getFlightDate());
	String retDate = sdf.format(retFD.getFlightDate());
 %>
<body>
<jsp:include page="Header.jsp"/>
<h3 style="color:white">Confirm the Reservation</h3>

<form action="reservationcontrol.action" method="post">
	<input type="hidden" name="action" value="confirmRes">
	<h4>Passenger Information</h4>
	<table border="1">
		
		<tr><th>First Name</th><th>Last Name</th></tr>
		<tr><td  style="color:white"><input type="text" name="firstName"></td><td  style="color:white"><input type="text" name="lastName"></td></tr>
	</table>
	
	<h4>Flight Information</h4>
	<table border="1">
		<tr><td  style="color:white"><b>Outbound</b></td></tr>
		<tr><th>Date</th><th>Flight Number</th><th>From</th><th>To</th><th>Departs</th><th>Arrives</th><th>Aircraft</th><th>Price</th><th>Seat</th></tr>
		<tr>
			<td  style="color:white"><%=outDate %></td>
			<td  style="color:white"><%=outFD.getFlightNumber() %></td>
			<td  style="color:white"><%=outFD.getFlight().getDepartAirport() %></td>
			<td  style="color:white"><%=outFD.getFlight().getArriveAirport() %></td>
			<td  style="color:white"><%=outFD.getFlight().getDeparture() %></td>
			<td  style="color:white"><%=outFD.getFlight().getArrival() %></td>
			<td  style="color:white"><%=outFD.getFlight().getAircraft() %></td>
			<td  style="color:white"><%=outFD.getFlight().getPrice() %></td>
			
			<td  style="color:white">
				<select name="outseat">
					<%
						List<String> list = outFD.getAvailableSeats();
						for(String seat : list) {
					 %>
					 <option value="<%=seat%>"><%=seat %></option>
					<% } %> 
				</select>
			</td>
		</tr>
		<tr><td  style="color:white"><b>Return</b></td></tr>
		<tr><th>Date</th><th>Flight Number</th><th>From</th><th>To</th><th>Departs</th><th>Arrives</th><th>Aircraft</th><th>Price</th><th>Seat</th></tr>
		<tr>
			<td  style="color:white"><%=retDate %></td>
			<td  style="color:white"><%=retFD.getFlightNumber() %></td>
			<td  style="color:white"><%=retFD.getFlight().getDepartAirport() %></td>
			<td  style="color:white"><%=retFD.getFlight().getArriveAirport() %></td>
			<td  style="color:white"><%=retFD.getFlight().getDeparture() %></td>
			<td  style="color:white"><%=retFD.getFlight().getArrival() %></td>
			<td  style="color:white"><%=retFD.getFlight().getAircraft() %></td>
			<td  style="color:white"><%=retFD.getFlight().getPrice() %></td>
			
			<td  style="color:white">
				<select name="retseat">
					<%
						List<String> list2 = retFD.getAvailableSeats();
						for(String seat : list2) {
					 %>
					 <option  style="color:white" value="<%=seat%>"><%=seat %></option>
					<% } %> 
				</select>
			</td>
		</tr>
		<tr><td><input type="submit" value="Confirm"></td><td><input type="reset"></td></tr>
	</table>
	
</form>
<jsp:include page="Footer.jsp"/>
</body>
</html>
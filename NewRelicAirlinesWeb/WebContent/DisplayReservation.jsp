<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.newrelic.airline.reservations.forms.ReservationDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="css/bg-image.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	ReservationDetails details = (ReservationDetails)session.getAttribute("reservationDetails");
	String fullName = null;
	String departDateStr = null;
	String returnDateStr = null;
	String confirmation = (String)session.getAttribute("confirmation");
	if(details != null) {
		fullName = details.getFirstName() + " " + details.getLastName();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		departDateStr = sdf.format(details.getDepartDate());
		returnDateStr = sdf.format(details.getReturnDate());
	}
 %>
<title>Reservation <%=confirmation %></title>
</head>
<body>
<jsp:include page="Header.jsp"/>

	<% if(details != null) { %>
	<table border="1">
		<tr> <td style="color:white">Confirmation: <%=confirmation%></td></tr>
		<tr><td style="color:white"><%=fullName %></td></tr>
		<tr><td style="color:white">Outbound</td></tr>
		<tr>
			<td style="color:white">&nbsp;</td>
			<td style="color:white"><%=details.getOutFlightNumber() %></td>
			<td style="color:white"><%=departDateStr%></td>
			<td style="color:white"><%=details.getDepartTime() %></td>
			<td style="color:white"><%=details.getOutArriveTime() %></td>
			<td style="color:white"><%=details.getFromAirport() %></td>
			<td style="color:white"><%=details.getToAirport() %></td>
			<td style="color:white"><%=details.getOutSeat() %></td>
		</tr>
		<tr><td style="color:white">Return</td></tr>
		<tr>
			<td style="color:white">&nbsp;</td>
			<td style="color:white"><%=details.getRetFlightNumber() %></td>
			<td style="color:white"><%=returnDateStr%></td>
			<td style="color:white"><%=details.getReturnTime() %></td>
			<td style="color:white"><%=details.getRetArriveTime() %></td>
			<td style="color:white"><%=details.getToAirport() %></td>
			<td style="color:white"><%=details.getFromAirport() %></td>
			<td style="color:white"><%=details.getRetSeat() %></td>
		</tr>
		<tr><td style="color:white">Price</td><td style="color:white"><%=details.getPrice() %></td></tr>
		<tr><td><input type="button" value="Cancel Reservation" onclick="window.location.href='reservationcontrol.action?action=cancel&confirmation=<%=confirmation%>'"></td></tr>
	</table>
	<% } else { %>
		<h3 style="color:white">No Reservation Found</h3>
	<% } %>
	<br>
	<br>
<jsp:include page="Footer.jsp"/>
</body>
</html>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
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
	List<ReservationDetails> list = (List<ReservationDetails>)session.getAttribute("reservationList");
	String fullName = (String)session.getAttribute("fullName");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
 %>
<title>Reservations for <%=fullName%></title>
</head>
<body>
<jsp:include page="Header.jsp"/>
	<h2 style="color:white">Reservations for <%=fullName %></h2>
	
	<%
		if(list == null || list.isEmpty()) {
	%>
	
	<h3  style="color:white">No Reservations Found</h3>
	
	<%
		} else {
	%>
	<table border="1">
		<% for(ReservationDetails details : list) { 
			String departDateStr = sdf.format(details.getDepartDate());
			String returnDateStr = sdf.format(details.getReturnDate());
				
		%>
		<tr>
		<tr> <td style="color:white">Confirmation: <%=details.getConfirmationNumber()%></td></tr>
		<tr><td style="color:white">Outbound</td>
			<td style="color:white"><%=details.getOutFlightNumber() %></td>
			<td style="color:white"><%=departDateStr%></td>
			<td style="color:white"><%=details.getFromAirport() %></td>
			<td style="color:white"><%=details.getToAirport() %></td>
		</tr>
		<tr><td style="color:white">Return</td>
			<td style="color:white"><%=details.getRetFlightNumber() %></td>
			<td style="color:white"><%=returnDateStr%></td>
			<td style="color:white"><%=details.getToAirport() %></td>
			<td style="color:white"><%=details.getFromAirport() %></td>
		</tr>
		<% } %>
	</table>
	<% } %>
	<br>
<jsp:include page="Footer.jsp"/>
</body>
</html>
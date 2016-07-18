<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="com.newrelic.airline.reservations.forms.Flight"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Flight Schedule</h1>
<p>
<a href="admin.jsp">Return to Main Admin Page</a>
<%
	HashMap<String, List<Flight>> map = (HashMap<String, List<Flight>>)session.getAttribute("schedule");
	
 %>
 
 	<table>
 	 		<tr><th>Route</th><th>Flight Number</th><th>From</th><th>Departure Time</th><th>To</th><th>Arrival Time</th><th>Price</th></tr>
 	
 		<%
 			Set<String> keys = map.keySet();
 			for(String key : keys) {
 				List<Flight> list = map.get(key);
 		 %>
 		<tr><th><%=key %></th></tr>
 			<%
 				
 				for(Flight flight : list) {
 					String editLink = "../admincontrol.action?action=editflight&route="+key+"&flightno="+flight.getFlightNumber();
 					String deleteLink = "../admincontrol.action?action=deleteflight&route="+key+"&flightno="+flight.getFlightNumber();
 			 %>
			<tr>
				<td>&nbsp;</td>
				<td><%=flight.getFlightNumber() %></td>
				<td> <%=flight.getDepartAirport() %></td>
				<td> <%=flight.getDeparture() %></td>
				<td> <%=flight.getArriveAirport() %></td>
				<td> <%=flight.getArrival() %></td>
				<td> <%=flight.getPrice() %></td>
				<td><input type="button" name="Edit" value="Edit" onclick="window.location.href='<%=editLink%>'"></td><td><input type="button" name="Delete" value="Delete" onclick="location.href='<%=deleteLink%>'"></td>
			</tr>
 			<%
 				}
 			 %>
 		<%
 			}
 		 %>
 		 
 		 <tr><td><input type="button" value="Add New Flight to Schedule" onclick="location.href='AddFlight.jsp'"></td></tr>
 	</table>
</body>
</html>
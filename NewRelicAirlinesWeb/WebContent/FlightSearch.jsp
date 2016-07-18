<%@page import="com.newrelic.airline.reservations.RezSystemGateway"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="css/bg-image.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flight Search</title>
</head>
	<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
  <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
  <script src="js/jquery-1.9.1.js"></script>

  <script src="js/jquery-ui.js"></script>
  <script>

  $(function() {

    $( "#outdate" ).datepicker();
    $( "#retdate" ).datepicker();

  });

  </script>

<%
	RezSystemGateway gateway = RezSystemGateway.getInstance();
	String[] cities = gateway.getCitiesServed();
	String[] selectValues = new String[cities.length];
	
	for(int i=0;i<cities.length;i++) {
		selectValues[i] = cities[i].replace(' ', '_');
	}
	
 %>
 
 <script type="application/javascript">
 	function validateForm() {
 		var x = document.forms["searchform"]["fromAirport"].value;
 		var y = document.forms["searchform"]["toAirport"].value;
 		if(x == y) {
 			alert("Airports cannot be the same");
 			return false;
 		}
 		return true;
 	}
 </script>
<body>
<jsp:include page="Header.jsp"/>
<h1 style="color:white">Flight Search</h1>

	<form action="reservationcontrol.action" method="post" name="searchform" onsubmit="return validateForm()">
		<input type="hidden" name="action" id="action" value="flightSearch">
		<table border="1">
			<tr><td style="color:white">From</td><td style="color:white">
				<select name="fromAirport">
					<% for(int i=0;i<cities.length;i++)  {%>
						<option value=<%=selectValues[i] %>><%=cities[i] %></option>
					<% } %>
				</select>
				</td>
			</tr>
			<tr><td style="color:white">To</td><td style="color:white">
				<select name="toAirport">
					<% for(int i=0;i<cities.length;i++)  {%>
						<option value=<%=selectValues[i] %>><%=cities[i] %></option>
					<% } %>
				</select>
				</td>
			</tr>
			<tr><td style="color:white">Outbound Date (MM/DD/YYYY)</td><td style="color:white"><input type="text" id="outdate" name="outDate"></td></tr>
			<tr><td style="color:white">Return Date (MM/DD/YYYY)</td><td style="color:white"><input type="text" id="retdate" name="retDate"></td></tr>
			<tr>
				<td><input type="submit" value="Search"></td>
				<td><input type="reset"  value="Reset"></td>
			</tr>
			
		</table>
</form>
<jsp:include page="Footer.jsp"/>
</body>
</html>
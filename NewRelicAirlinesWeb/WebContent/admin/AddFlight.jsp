<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Flight</title>
</head>
<body>
<form action="admincontrol.action?action=createflight" method="post">

	<table>
		<tr>
			<th>Flight Number</th>
			<th>Departure City</th>
			<th>Arrival City</th>
			<th>Aircraft</th>
			<th>Departure Time</th>
			<th>Arrival Time</th>
			<th>Price</th>
		</tr>
		<tr>
			<td><input type="text" name="flightnumber" value="NR "></td>
			<td><input type="text" name="fromAirport"></td> 
			<td><input type="text" name="toAirport"></td>
			<td><input type="text" name="aircraft"></td>
			<td><input type="text" name="depart"></td>
			<td><input type="text" name="arrival"></td>
			<td><input type="text" name="price"></td>
		</tr>	
		<tr><td><input type="submit" value="Create"></td><td><input type="button" name="Cancel" value="Cancel" onclick="location.href='admin.jsp'"></td></tr>
	</table>
</form>
</body>
</html>
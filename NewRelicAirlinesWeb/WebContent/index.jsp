<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="css/bg-image.css" rel="stylesheet" type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to New Relic Airlines</title>
</head>
<body>
<jsp:include page="Header.jsp"/>
<h1 style="color:white">Welcome to New Relic Airlines</h1>
<h2 style="color:white">What would you like to do?</h2>
<table>
	<tr>
		<td style="color:white"><a href="FindByConfirmation.jsp">Find Reservation by Confirmation Number</a></td>
	</tr>
	<tr>
		<td style="color:white"><a href="FindByName.jsp">Find Reservation by Name</a></td>
	</tr>
	<tr>
		<td style="color:white"><a href="FlightSearch.jsp">Book a Flight</a></td>
	</tr>
</table>
<jsp:include page="Footer.jsp"/>
</body>
</html>
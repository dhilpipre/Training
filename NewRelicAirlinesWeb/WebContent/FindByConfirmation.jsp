<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="css/bg-image.css" rel="stylesheet" type="text/css"/>
<title>Get Reservation Details</title>
</head>
<body>
<jsp:include page="Header.jsp"/>
	<h3 style="color:white">Enter Confirmation Number</h3>
	<form action="reservationcontrol.action" method="post">
		<input type="hidden" name="action" id="action" value="findresbyconf">
	
	<table>
		<tr><td style="color:white">Confirmation Number</td><td style="color:white"><input type="text" name="confirmation"></td></tr>
		<tr>
			<td style="color:white"><input type="submit" value="Submit"></td>
			<td style="color:white"><input type="reset"  value="Reset"></td>
		</tr>
	</table>
	</form>
<jsp:include page="Footer.jsp"/>
</body>
</html>
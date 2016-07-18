<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change Wait Period</title>
</head>
<%
	long wait_period = (Long)session.getAttribute("wait_period");
%>
<body>
<form action="admincontrol.action?action=setwaitperiod" method="post">
	<table>
		<tr><th>Current Wait Period Value</th><th>New Value (in ms)</th></tr>
		<tr><td><%=wait_period %></td><td><input type="text" name="new_wait_period"></td></tr>
		<tr><td><input type="submit" value="Change"></td><td><input type="button" value="Cancel"  onclick="location.href='admin.jsp'"></td></tr>
	</table>
</form>
</body>
</html>
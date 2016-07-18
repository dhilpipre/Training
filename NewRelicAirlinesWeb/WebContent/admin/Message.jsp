<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Message</title>
</head>
<%
	String message = (String)session.getAttribute("message");
 %>
<body>

<h1>Message: <%=message %></h1>
<p>
<p>
<a href="admin.jsp">Return to Admin Page</a>
</body>
</html>
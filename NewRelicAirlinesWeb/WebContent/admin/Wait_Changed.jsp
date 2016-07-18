<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wait Period Changed</title>
</head>
<%
	boolean success = (Boolean)session.getAttribute("set_wait_success");
	long new_wait = -1L;
	long old_wait = -1L;
	
	if(success) {
		new_wait = (Long)session.getAttribute("new_wait_period");
		old_wait = (Long)session.getAttribute("old_wait_period");
	}
%>
<body>

<table>
	<%
		if(success) {
	%>
	<tr><th>Old Wait Period Value</th><th>New Wait Period Value</th></tr>
	<tr><td><%=old_wait %></td><td><%=new_wait %></td></tr>
	<%
		} else {
	%>
	<tr><th>Attempt to change wait period was unsuccessful</th></tr>
	<%	}
	%>
	
	<tr><td><a href="/admin/admin.jsp">Return to Admin Page</a></td></tr>
</table>
</body>
</html>
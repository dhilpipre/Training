package com.newrelic.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionControl {
	
	public ActionRouter performAction(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
	
}

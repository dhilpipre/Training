package com.newrelic.action;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ActionRouter {

	private final String key;
	private final boolean isForward;
	
	public ActionRouter(String k) {
		key = k;
		isForward = true;
	}
	
	public ActionRouter(String k, boolean isf) {
		key = k;
		isForward = isf;
	}
	
	public synchronized void route(GenericServlet servlet, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ResourceBundle bundle = (ResourceBundle) servlet.getServletContext().getAttribute("action-controls");
		String url = (String) bundle.getObject(key);
		if(isForward) {
			servlet.getServletContext().getRequestDispatcher(response.encodeURL(url)).forward(request, response);
		} else {
			response.sendRedirect(response.encodeRedirectURL(url));
		}
	}
}

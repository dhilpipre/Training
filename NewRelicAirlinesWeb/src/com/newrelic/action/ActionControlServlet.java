package com.newrelic.action;

import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ActionControlServlet extends HttpServlet implements Servlet {

	private static final Logger LOG = Logger.getLogger(ActionControlServlet.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1312839337404956561L;
	private ActionControlFactory factory = new ActionControlFactory();
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String actionClass = getActionClass(request);
			ActionControl action = factory.getAction(actionClass, getClass().getClassLoader());
			ActionRouter router = action.performAction(this, request, response);
			router.route(this, request, response);
		} catch(Exception e) {
			LOG.error(e.getClass().getSimpleName(), e);
			throw new ServletException(e);
		}
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ResourceBundle actions = null;
		try {
			actions = ResourceBundle.getBundle(config.getInitParameter("action-controls"));
			getServletContext().setAttribute("action-controls", actions);
		} catch(MissingResourceException e) {
			LOG.error(e.getClass().getSimpleName(), e);
		}
	}
	
	private String getActionClass(HttpServletRequest request) {
		ResourceBundle bundle = (ResourceBundle) getServletContext().getAttribute("action-controls");
		return (String) bundle.getObject(getActionKey(request));
	}

	private String getActionKey(HttpServletRequest request) {
		String path = request.getServletPath();
		int slash = path.lastIndexOf("/");
		int period = path.lastIndexOf(".");
		if(period > 0 && period > slash) {
			path = path.substring(slash+1,period);
		}
		return path;
	}
}

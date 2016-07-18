package com.newrelic;

import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

public class Log4jInit extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1610067368666414538L;

	public void init() {
		System.out.println("In init() of Log4jInit");
		String prefix = getServletContext().getRealPath("/");
		if(prefix != null) {
			if(!prefix.endsWith("/")) {
				prefix += "/";
			}
			String file = getInitParameter("log4j-init-file");
			if(file != null) {
				System.out.println("Getting configuration from "+prefix+file);
				PropertyConfigurator.configure(prefix+file);
			} else {
				System.out.println("did not find log4j-init-file");
			}
		} else {
			String file = getInitParameter("log4j-init-file");

			//InputStream input = getServletContext().getResourceAsStream(file);
			PropertyConfigurator.configure(file);
		}
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
	}
}

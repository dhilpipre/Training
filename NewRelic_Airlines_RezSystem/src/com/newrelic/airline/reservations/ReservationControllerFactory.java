package com.newrelic.airline.reservations;

/**
 * factory for getting the ReservationController instance
 * The controller is implemented as a singleton
 * 
 * @author dhilpipre
 *
 */
public class ReservationControllerFactory {
	static ReservationController controller = null;
	
	public static ReservationController getReservationController() {
		if(controller == null) {
			controller = new ReservationControllerImpl();
		}
		return controller;
	}
}

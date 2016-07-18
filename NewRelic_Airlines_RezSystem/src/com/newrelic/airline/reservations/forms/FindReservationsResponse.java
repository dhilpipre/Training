package com.newrelic.airline.reservations.forms;

import java.util.List;

public class FindReservationsResponse extends BaseResponse {
	public FindReservationsResponse(List<ReservationDetails> reservationList) {
		super();
		this.reservationList = reservationList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8819444762066348520L;
	private List<ReservationDetails> reservationList;

	public List<ReservationDetails> getReservationList() {
		return reservationList;
	}

	public void setReservationList(List<ReservationDetails> reservationList) {
		this.reservationList = reservationList;
	}
}

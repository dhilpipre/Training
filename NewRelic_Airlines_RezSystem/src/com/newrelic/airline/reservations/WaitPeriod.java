package com.newrelic.airline.reservations;

public class WaitPeriod implements WaitPeriodMXBean {

	@Override
	public long getWaitPeriod() {
		return ReservationControllerFactory.getReservationController().getWait_period();
	}

	@Override
	public void setWaitPeriod(long wp) {
		ReservationControllerFactory.getReservationController().setWait_period(wp);
	}

}

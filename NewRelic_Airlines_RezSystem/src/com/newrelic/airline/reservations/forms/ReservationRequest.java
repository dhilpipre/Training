package com.newrelic.airline.reservations.forms;

import java.io.Serializable;
import java.util.Date;

public class ReservationRequest extends BaseRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8569491606844988148L;
	
	private String firstName;
	private String lastName;
	private String fromAirport;
	private String toAirport;
	private String outboundFltNum;
	
	private Date departDate;
	private Date returnDate;
	private String returnFltNum;
	private String outSeat;
	private String retSeat;
	private Float outPrice;
	private Float returnPrice;
	
	public Float getReturnPrice() {
		return returnPrice;
	}
	public void setReturnPrice(Float returnPrice) {
		this.returnPrice = returnPrice;
	}
	public String getOutSeat() {
		return outSeat;
	}
	public void setOutSeat(String outSeat) {
		this.outSeat = outSeat;
	}
	public String getRetSeat() {
		return retSeat;
	}
	public void setRetSeat(String retSeat) {
		this.retSeat = retSeat;
	}
	public String getOutboundFltNum() {
		return outboundFltNum;
	}
	public void setOutboundFltNum(String outboundFltNum) {
		this.outboundFltNum = outboundFltNum;
	}
	public String getReturnFltNum() {
		return returnFltNum;
	}
	public void setReturnFltNum(String returnFltNum) {
		this.returnFltNum = returnFltNum;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFromAirport() {
		return fromAirport;
	}
	public void setFromAirport(String fromAirport) {
		this.fromAirport = fromAirport;
	}
	public String getToAirport() {
		return toAirport;
	}
	public void setToAirport(String toAirport) {
		this.toAirport = toAirport;
	}
	public Date getDepartDate() {
		return departDate;
	}
	public void setDepartDate(Date departDate) {
		this.departDate = departDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Float getOutPrice() {
		return outPrice;
	}
	public void setOutPrice(Float price) {
		this.outPrice = price;
	}

	
}

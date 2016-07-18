package com.newrelic.airline.reservations.forms;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class ReservationDetails extends BaseResponse implements Serializable {
	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger(ReservationDetails.class);
	private static final long serialVersionUID = -2205698513319180043L;
	private String firstName;
	private String lastName;
	private String fromAirport;
	private String toAirport;
	
	private Date departDate;
	private Date outDeparture;
	private Date outArrival;
	private Date returnDeparture;
	private Date returnArrival;
	
	private String departTime;
	private String outArriveTime;
	private Date returnDate;
	private String returnTime;
	private String retArriveTime;
	private String outSeat;
	private String retSeat;
	private String outFlightNumber;
	private String retFlightNumber;
	private String confirmationNumber;
	private boolean nextDayArrive = false;
	private Float outPrice  = new Float(0);
	private Float retPrice = new Float(0);
	private Float price = new Float(0);
	private Date created;
	
	public Float getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(Float oPrice) {
		this.outPrice = oPrice;
		price = outPrice + retPrice;
	}

	public Float getRetPrice() {
		return retPrice;
	}

	public void setRetPrice(Float rPrice) {
		this.retPrice = rPrice;
		price = outPrice + retPrice;
	}

	public Date getOutDeparture() {
		return outDeparture;
	}

	public void setOutDeparture(Date outDeparture) {
		this.outDeparture = outDeparture;
	}

	public Date getOutArrival() {
		return outArrival;
	}

	public void setOutArrival(Date outArrival) {
		this.outArrival = outArrival;
	}

	public Date getReturnDeparture() {
		return returnDeparture;
	}

	public void setReturnDeparture(Date returnDeparture) {
		this.returnDeparture = returnDeparture;
	}

	public Date getReturnArrival() {
		return returnArrival;
	}

	public void setReturnArrival(Date returnArrival) {
		this.returnArrival = returnArrival;
	}

	public String getConfirmationNumber() {
		return confirmationNumber;
	}

	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm aa");
		
		sb.append(confirmationNumber+" - ");
		sb.append(firstName+" ");
		sb.append(lastName+": \n");
		sb.append("\tOutbound:\n");
		sb.append("\t\t"+outFlightNumber+" "+sdf.format(departDate)+" "+departTime+" - "+outArriveTime);
		sb.append(" " + fromAirport + " to " + toAirport + ", Seat: "+outSeat);
		sb.append("\n");
		sb.append("\tReturn:\n");
		sb.append("\t\t"+retFlightNumber+" "+sdf.format(returnDate)+" "+returnTime+" - "+retArriveTime);
		sb.append(" " + toAirport + " to " + fromAirport + ", Seat: "+retSeat);
		sb.append("\n");
		
		return sb.toString();
	}

	public String getRetSeat() {
		return retSeat;
	}

	public void setRetSeat(String retSeat) {
		this.retSeat = retSeat;
	}
	
	public boolean isValid() {
		boolean valid = true;
		if(firstName == null) {
			LOG.info("Reservation is not valid: first name is null");
			return false;
		}
		if(lastName == null) {
			LOG.info("Reservation is not valid: last name is null");
			return false;
		}
		if(fromAirport == null) {
			LOG.info("Reservation is not valid: from airport is null");
			return false;
		}
		if(toAirport == null) {
			LOG.info("Reservation is not valid: to airport is null");
			return false;
		}
		if(departDate == null) {
			LOG.info("Reservation is not valid: departure date is null");
			return false;
		}
		if(returnDate == null) {
			LOG.info("Reservation is not valid: return date is null");
			return false;
		}
		if(outSeat == null) {
			LOG.info("Reservation is not valid: out seat is null");
			return false;
		}
		if(retSeat == null) {
			LOG.info("Reservation is not valid: return seat is null");
			return false;
		}
		if(outFlightNumber == null) {
			LOG.info("Reservation is not valid: out flight number null");
			return false;
		}
		if(retFlightNumber == null) {
			LOG.info("Reservation is not valid: return flight number is null");
			return false;
		}
		
		return valid;
	}
	
	public String getOutFlightNumber() {
		return outFlightNumber;
	}

	public void setOutFlightNumber(String outFlightNumber) {
		this.outFlightNumber = outFlightNumber;
	}

	public String getRetFlightNumber() {
		return retFlightNumber;
	}

	public void setRetFlightNumber(String retFlightNumber) {
		this.retFlightNumber = retFlightNumber;
	}

	public ReservationDetails() {
		
	}
	
	public ReservationDetails(ReservationRequest req) {
		firstName = req.getFirstName();
		lastName = req.getLastName();
		fromAirport = req.getFromAirport();
		toAirport = req.getToAirport();
		departDate = req.getDepartDate();
		returnDate = req.getReturnDate();
		outSeat = req.getOutSeat();
		retSeat = req.getRetSeat();
		outFlightNumber = req.getOutboundFltNum();
		retFlightNumber = req.getReturnFltNum();
		if(req.getOutPrice() != null && req.getReturnPrice() != null) {
			price = req.getOutPrice() + req.getReturnPrice();
		} else {
			price = 0F;
		}
		
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
	public String getDepartTime() {
		return departTime;
	}
	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public String getOutSeat() {
		return outSeat;
	}
	public void setOutSeat(String seat) {
		this.outSeat = seat;
	}

	public String getOutArriveTime() {
		return outArriveTime;
	}

	public void setOutArriveTime(String outArriveTime) {
		this.outArriveTime = outArriveTime;
	}

	public String getRetArriveTime() {
		return retArriveTime;
	}

	public void setRetArriveTime(String retArriveTime) {
		this.retArriveTime = retArriveTime;
	}

	public boolean isNextDayArrive() {
		return nextDayArrive;
	}

	public void setNextDayArrive(boolean nextDayArrive) {
		this.nextDayArrive = nextDayArrive;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
}

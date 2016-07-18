package com.newrelic.airline.reservations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.newrelic.airline.reservations.forms.ReservationDetails;

public class ReservationSystemServiceJson implements ReservationSystemJson {

	private ReservationSystemService service = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	private static final Logger LOG = Logger.getLogger(ReservationSystemServiceJson.class);
	
	public ReservationSystemServiceJson() {
		service = new ReservationSystemService();
	}

	@Override
	public String cancelReservation(String json) {
		JSONObject jsonObj = new JSONObject(json);
		String confirmation = jsonObj.getString("confirmation");
		String result = service.cancelReservation(confirmation);
		
		JSONObject obj = new JSONObject();
		obj.put("canceled", result);
		return obj.toString();
	}

	@Override
	public String findAvailableFlights(String json) {
		JSONObject jsonObj = new JSONObject(json);
		JSONArray outArray = new JSONArray();
		JSONArray retArray = new JSONArray();
		try {
			String from = jsonObj.getString("from"); 
			String to = jsonObj.getString("to");
			Date departureDate = sdf.parse(jsonObj.getString("departureDate"));
			Date returnDate = sdf.parse(jsonObj.getString("returnDate"));
			FlightSearchResults results = service.findAvailableFlights(from, to, departureDate, returnDate);
			outArray = new JSONArray(results.getOutFlights());
			retArray = new JSONArray(results.getReturnFlights());
		} catch (JSONException e) {
			LOG.error("Error parsing: "+json, e);
		} catch (ParseException e) {
			LOG.error("Error parsing dates from "+json, e);
		}
		JSONObject obj = new JSONObject();
		obj.put("outFlights", outArray);
		obj.put("returnFlights", retArray);
		return obj.toString();
	}

	@Override
	public String getCitiesServed() {
		String[] cities = service.getCitiesServed();
		LOG.info("GetCities:Received "+cities.length+" cities");
		
		JSONArray array = new JSONArray();
		for(int i=0;i<cities.length;i++) {
			array.put(cities[i]);
		}
		JSONObject obj = new JSONObject();
		obj.put("cities", array);
		LOG.info("GetCities:Returning "+obj.toString(3));
		return obj.toString();
	}

	@Override
	public String listReservation(String json) {
		JSONObject jsonObj = new JSONObject(json);
		String confirmation = jsonObj.getString("confirmation");
		JSONObject obj = new JSONObject(service.listReservation(confirmation));
		return obj.toString();
	}

	@Override
	public String listReservations(String json) {
		JSONObject jsonObj = new JSONObject(json);
		String first = jsonObj.getString("first");
		String last = jsonObj.getString("last");
		LOG.info("Received search request for "+first+" "+last);
		ReservationDetails[] results = service.listReservations(first, last);
		JSONArray array = new JSONArray(results);
//		for(int i =0;i<results.length;i++) {
//			JSONObject obj =  new JSONObject(results[i]);
//			array.put(obj);
//		}
		JSONObject responseObj = new JSONObject();
		responseObj.put("reservations", array);
		LOG.info("Returning "+responseObj.toString());
		return responseObj.toString();
	}

	@Override
	public String makeReservation(String json) {
		String result;
		try {
			JSONObject jsonObj = new JSONObject(json);
			
			String firstName = jsonObj.getString("firstName");
			String lastName = jsonObj.getString("lastName");
			String fromAirport = jsonObj.getString("fromAirport");
			String toAirport = jsonObj.getString("toAirport");
			String outboundFltNum = jsonObj.getString("outboundFlight");
			Date departDate = sdf.parse(jsonObj.getString("departDate"));
			Date returnDate = sdf.parse(jsonObj.getString("returnDate"));
			String returnFltNum = jsonObj.getString("returnFlight");
			String outSeat = jsonObj.getString("outSeat");
			String retSeat = jsonObj.getString("returnSeat");
			Float outPrice = (float) jsonObj.getDouble("outPrice"); 
			Float retPrice = (float) jsonObj.getDouble("returnPrice");
			result = service.makeReservation(firstName, lastName, fromAirport, toAirport, outboundFltNum, departDate, returnDate, returnFltNum, outSeat, retSeat, outPrice, retPrice);
			
			if(result == null) {
				result = "Received null from reservation system";
			}
		} catch (JSONException e) {
			result = "Error parsing json";
			LOG.error("Error parsing: "+json, e);
		} catch (ParseException e) {
			result = "Error parsing dates";
			LOG.error("Error parsing dates from "+json, e);
		}
		JSONObject obj = new JSONObject();
		obj.put("confirmation", result);
		return obj.toString();
	}
	
	
}

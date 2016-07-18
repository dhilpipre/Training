package com.newrelic.airline.reservations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Set;

import com.newrelic.airline.reservations.forms.ReservationDetails;

public class FixConfs {

	private static final String reservationFilename = "reservations.dat";
	private Hashtable<String, ReservationDetails> reservations;
	
	public static void main(String[] args) {
		try {
			FixConfs fix = new FixConfs();
			fix.fix();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FixConfs() {
		FileInputStream inStream = null;
		
		try {
			inStream = new FileInputStream(reservationFilename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Object obj = null;
		if(inStream != null) {
			try {
				ObjectInputStream in = new ObjectInputStream(inStream);

				obj = in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(obj != null) {
			reservations = (Hashtable<String, ReservationDetails>)obj;
		} else {
			reservations = new Hashtable<String, ReservationDetails>();
		}
		
		
	}
	public void fix() throws IOException {
		Hashtable<String, ReservationDetails> reservations2 = new Hashtable<String, ReservationDetails>();
		Set<String> keys = reservations.keySet();
		for(String key : keys) {
			ReservationDetails details = reservations.get(key);
			RandomString rs = new RandomString(6);
			String newConf = rs.nextString();
			while(reservations2.containsKey(newConf)) {
				newConf = rs.nextString();
			}
			if(details != null) {
				details.setConfirmationNumber(newConf);
				reservations2.put(newConf, details);
			}
		}
		FileOutputStream resOut = new FileOutputStream(reservationFilename);
		ObjectOutputStream resOutStream = new ObjectOutputStream(resOut);
		resOutStream.writeObject(reservations2);
		resOutStream.close();
	}
	
	
}

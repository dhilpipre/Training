package com.newrelic.airline.reservations;

import java.util.Random;

/*
 * Used to generate a pseduo confirmation code
 */
public class RandomString {
	  private static final char[] symbols = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N',
		  'O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};

	  private final Random random = new Random();

	  private final char[] buf;

	  public RandomString(int length) {
	    if (length < 1)
	      throw new IllegalArgumentException("length < 1: " + length);
	    buf = new char[length];
	  }

	  public String nextString() {
	    for (int idx = 0; idx < buf.length; ++idx) 
	      buf[idx] = symbols[random.nextInt(symbols.length)];
	    return new String(buf);
	  }
}

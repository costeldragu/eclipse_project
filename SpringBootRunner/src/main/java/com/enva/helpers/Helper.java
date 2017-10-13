package com.enva.helpers;

import java.util.Collections;

public class Helper {
	
	

	public static String stringRepeat(String string, int times, boolean newline) {
		final StringBuilder repeatString = new StringBuilder();
		Collections.nCopies(times, 0).stream().forEach(i -> repeatString.append(string));
		if (newline) {
			repeatString.append("\n\r");
		}
		return repeatString.toString();
	}

	/**
	 *
	 * @param string
	 * @param times
	 * @return
	 */
	public static String stringRepeat(String string, int times) {
		return stringRepeat(string, times, false);
	}

}

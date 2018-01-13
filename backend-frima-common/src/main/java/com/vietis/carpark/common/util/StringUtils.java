package com.vietis.carpark.common.util;

import java.util.StringTokenizer;

/**
 * Extends StringUtils
 *
 * @see org.springframework.util.StringUtils
 * @author thomc
 *
 */
public class StringUtils extends org.springframework.util.StringUtils {

	/*-----------------------------------------------
	* Constants
	*-----------------------------------------------*/
	public static final String EMPTY = "";

	/*-----------------------------------------------
	* Public
	*-----------------------------------------------*/

	public static String snakeCaseToCamelCase(String strInput) {
		StringTokenizer toekn = new StringTokenizer(strInput, "_");
		StringBuilder str = new StringBuilder(toekn.nextToken());
		while (toekn.hasMoreTokens()) {
			String s = toekn.nextToken();
			str.append(Character.toUpperCase(s.charAt(0)))
					.append(s.substring(1));
		}
		return str.toString();
	}

}

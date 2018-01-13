package com.vietis.carpark.constant;

/**
 * Manually Logging Format.
 *
 * @author thomc
 *
 */
public enum LogFormat {
	/**
	 * AOP Logging before format.<br>
	 * 0 = Class<br>
	 * 1 = Method<br>
	 * 2 = parameters<br>
	 */
	AOP_BEFORE("[AOP] before invoke, [%s].[%s] parameters = %s %n"),
	/**
	 * AOP Logging after format.<br>
	 * 0 = Class<br>
	 * 1 = Method<br>
	 * 2 = result<br>
	 */
	AOP_AFTER("[AOP] after invoke, [%s].[%s] result = %s %n");

	/*-----------------------------------------------
	* Property
	*-----------------------------------------------*/
	/**
	 * Logging pattern.
	 */
	private String pattern;

	/*-----------------------------------------------
	* Constructor
	*-----------------------------------------------*/
	private LogFormat(String pattern) {
		this.pattern = pattern;
	}

	/*-----------------------------------------------
	* Getter, Setter
	*-----------------------------------------------*/
	public String getPattern() {
		return pattern;
	}

}

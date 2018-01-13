package com.vietis.carpark.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AOP: Base
 * 
 * @author thomc
 *
 */
public abstract class BaseAdvisor {

	/*-----------------------------------------------
	* Property
	*-----------------------------------------------*/
	/**
	 * Logger
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
}

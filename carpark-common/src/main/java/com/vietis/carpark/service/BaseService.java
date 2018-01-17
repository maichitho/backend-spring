package com.vietis.carpark.service;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.vietis.carpark.common.util.CommonUtil;
import com.vietis.carpark.entity.User;
import com.vietis.carpark.repository.UserRepository;

/**
 * Base Service
 *
 * @author thomc
 *
 */
@Service
@PropertySource(value = {"classpath:application.properties"})
public class BaseService  {
	/*-----------------------------------------------
	* Property
	*-----------------------------------------------*/
	/**
	 * Logger
	 */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*-----------------------------------------------
	* @Autowired
	*-----------------------------------------------*/
	/**
	 * APP ENV
	 */
	@Autowired
	protected Environment environment;

	@Autowired
	protected UserRepository userDao;
	
	
	@Autowired
	private HttpServletRequest request;


	/**
	 * Check token in session is valid?
	 * 
	 * @author thomc
	 * @return
	 */
	public User checkTokenInSession() {
		if(CommonUtil.isEmpty(request.getHeader("access_token"))){
			return null;
		}
		return userDao.findOneByAccessTokenAndStatusAndIsDeleted(
				request.getHeader("access_token"), User.ACTIVE,
				false);
	}
}


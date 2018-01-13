package com.mulodo.fiveneed.service;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.mulodo.fiveneed.common.util.CommonUtil;
import com.mulodo.fiveneed.repository.UserRepository;
import com.vietis.carpark.entity.MstUser;

/**
 * Base Service
 *
 * @author thomc
 *
 */
@Service
@PropertySource(value = {"classpath:application.properties"})
public class BaseService {
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
	public MstUser checkTokenInSession() {
		if(CommonUtil.isEmpty(request.getHeader("access_token"))){
			return null;
		}
		return userDao.findOneByAccessTokenAndStatusAndIsDeleted(
				request.getHeader("access_token"), MstUser.ACTIVE,
				false);
	}
}


package com.mulodo.fiveneed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mulodo.fiveneed.bean.RequestBean;
import com.mulodo.fiveneed.bean.ResponseBean;
import com.mulodo.fiveneed.bean.request.SocialLoginRequestBean;
import com.mulodo.fiveneed.bean.response.LoginResponseBean;
import com.mulodo.fiveneed.constant.AppHttpStatus;
import com.mulodo.fiveneed.service.UserService;
import com.vietis.carpark.entity.MstUser;

@RestController
@RequestMapping("/member")
@PropertySource(value = { "classpath:application.properties" })
public class MemberController extends BaseController {
	@Autowired
	UserService userService;

	/**
	 * API M01
	 * 
	 * @author Danhloc
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> loginSystem(@RequestBody MstUser request) {
		ResponseBean response = new ResponseBean();
		try {
			userService.loginSystem(request, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}

	/**
	 * API M02
	 * 
	 * @author Danhloc
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<?> logOut(@RequestBody RequestBean request) {
		ResponseBean response = new ResponseBean();
		try {
			userService.logOut(request, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}

	/**
	 * API M03
	 * 
	 * @author Danhloc
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/forgot_password", method = RequestMethod.POST)
	public ResponseEntity<?> forgotPassword(@RequestBody MstUser request) {
		ResponseBean response = new ResponseBean();
		try {
			userService.resetPassword(request, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}

	/**
	 * API M05
	 * 
	 * @author Danhloc
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerSystem(@RequestBody MstUser request) {
		ResponseBean response = new ResponseBean();
		try {
			userService.regiserSystem(request, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}

	/**
	 * API M06
	 * 
	 * @author Danhloc
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public ResponseEntity<?> activeSystem(@RequestParam(value = "key", required = false) String activation_key) {
		ResponseBean response = new ResponseBean();
		try {
			userService.activeSystem(activation_key, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}

	/**
	 * API M07
	 * 
	 * @author ThoMC
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/social_login", method = RequestMethod.POST)
	public ResponseEntity<?> socialLoginSystem(@RequestBody SocialLoginRequestBean bean) {
		LoginResponseBean response = new LoginResponseBean();
		try {
			userService.socialLoginSystem(bean, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}

	/**
	 * API M10
	 * 
	 * @author ThoMC
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/profile/setting", method = RequestMethod.PUT)
	public ResponseEntity<ResponseBean> updateProfileSettingMember(@RequestBody MstUser profile) {
		ResponseBean response = new ResponseBean();
		try {
			userService.updateProfileSetting(profile, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}
}

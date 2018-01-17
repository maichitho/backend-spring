package com.vietis.carpark.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vietis.carpark.bean.RequestBean;
import com.vietis.carpark.bean.ResponseBean;
import com.vietis.carpark.bean.request.SocialLoginRequestBean;
import com.vietis.carpark.bean.response.LoginResponseBean;
import com.vietis.carpark.constant.AppHttpStatus;
import com.vietis.carpark.controller.BaseController;
import com.vietis.carpark.entity.User;
import com.vietis.carpark.service.UserService;

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
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> loginSystem(@RequestBody User request) {
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
	@PostMapping(value = "/logout")
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
	@PostMapping(value = "/forgot_password")
	public ResponseEntity<?> forgotPassword(@RequestBody User request) {
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

	@PostMapping(value = "/register")
	public ResponseEntity<?> registerSystem(@RequestBody User request) {
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

	@GetMapping(value = "/active")
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

	@PostMapping(value = "/social_login")
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
	@PutMapping(value = "/profile/setting")
	public ResponseEntity<ResponseBean> updateProfileSettingMember(@RequestBody User profile) {
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

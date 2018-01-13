package com.mulodo.fiveneed.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.mulodo.fiveneed.entity.MstUser;
import com.mulodo.fiveneed.entity.MstUserProfile;
import com.mulodo.fiveneed.entity.TblQuestion;
import com.mulodo.fiveneed.entity.TblRequestPayment;
import com.mulodo.fiveneed.service.BuyerService;
import com.mulodo.fiveneed.service.ProductService;
import com.mulodo.fiveneed.service.SellerService;
import com.mulodo.fiveneed.service.TodoService;
import com.mulodo.fiveneed.service.UserService;

@RestController
@RequestMapping("/member")
@PropertySource(value = { "classpath:application.properties" })
public class MemberController extends BaseController {
	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@Autowired
	BuyerService buyerService;

	@Autowired
	SellerService sellerService;

	@Autowired
	private TodoService todoService;

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
	 * API M08
	 * 
	 * @author ThoMC
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> getProfileMember() {
		ResponseBean response = new ResponseBean();
		try {
			userService.getProfile(response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}

	/**
	 * API M09
	 * 
	 * @author ThoMC
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.PUT)
	public ResponseEntity<ResponseBean> updateProfileMember(@RequestBody MstUserProfile profile) {
		ResponseBean response = new ResponseBean();
		try {
			userService.updateProfile(profile, response);
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

	/**
	 * API M20: list todo follow user_id
	 */

	@RequestMapping(value = "/todo", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseBean> findCategory(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "100") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "created_at") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "asc") String sortType) {

		ResponseBean response = new ResponseBean();
		try {
			todoService.findAllTodo(page, size, sortBy, sortType, response);
			return response(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage(e.getMessage());
			response.setStatus(AppHttpStatus.FAILED_TO_GET_DATA);
			return response(response);
		}

	}

	/**
	 * API M21: update status todo_follow id, user_id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/todo/{id}", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseBean> updateTodo(@PathVariable("id") long id) {
		ResponseBean response = new ResponseBean();
		try {

			todoService.updateTodo(id, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.FAILED_TO_UPDATE_DATA);
			response.setMessage(e.getMessage());
			response.setData(null);
			return response(response);
		}
	}

	/*
	 * API M19: list all product
	 * @param categoryId
	 * @return page
	 * Thiếu xét trạng thái wish
	 */
	@RequestMapping(value = "/product_category", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseBean> findAllProduct(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "100") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "createdBy") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "asc") String sortType) {

		ResponseBean response = new ResponseBean();
		try {
			productService.findAllProduct( page, size, sortBy, sortType, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.FAILED_TO_UPDATE_DATA);
			response.setMessage(e.getMessage());
			response.setData(null);
			return response(response);
		}
	}
	
	@RequestMapping(value = "/product_category/{category}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseBean> findProductByCategory(@PathVariable("category") String category,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "100") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "createdBy") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "asc") String sortType) {

		ResponseBean response = new ResponseBean();
		try {
			productService.findProductByCategory(category, page, size, sortBy, sortType, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.FAILED_TO_UPDATE_DATA);
			response.setMessage(e.getMessage());
			response.setData(null);
			return response(response);
		}
	}
	
	
	
	

	/*
	 * API M08
	 * 
	 * @author ThoMC
	 * 
	 * @param
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sale", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> getSaleInfo() {
		ResponseBean response = new ResponseBean();
		try {
			productService.getSaleInfo(response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}

	/**
	 * M12
	 * 
	 * @param page
	 * @param size
	 * @param sortBy
	 * @param sortType
	 * @return
	 */
	@RequestMapping(value = "/sale/history", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> searchMember(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "asc") String sortType) {
		ResponseBean response = new ResponseBean();
		try {
			productService.searchHistory(page, size, sortBy, sortType, response);
			return response(response);
		} catch (Exception e) {
			logger.error("searchUser", e);
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

	/**
	 * M13
	 * 
	 * @param page
	 * @param size
	 * @param sortBy
	 * @param sortType
	 * @return
	 */
	@RequestMapping(value = "/product_order/request_payment", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> searchNeedRequestPayment(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "asc") String sortType) {
		ResponseBean response = new ResponseBean();
		try {
			productService.searchNeedRequestPayment(page, size, sortBy, sortType, response);
			return response(response);
		} catch (Exception e) {
			logger.error("searchUser", e);
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

	/**
	 * M15
	 * 
	 * @param page
	 * @param size
	 * @param sortBy
	 * @param sortType
	 * @return
	 */
	@RequestMapping(value = "/sale/request_payment", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> searchRequestPayment(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "created_at") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "asc") String sortType) {
		ResponseBean response = new ResponseBean();
		try {
			productService.searchRequestPayment(page, size, sortBy, sortType, response);
			return response(response);
		} catch (Exception e) {
			logger.error("searchUser", e);
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

	/**
	 * M16
	 * 
	 * @param page
	 * @param size
	 * @param sortBy
	 * @param sortType
	 * @return
	 */
	@RequestMapping(value = "/leave", method = RequestMethod.PUT)
	public ResponseEntity<ResponseBean> leaveMember() {
		ResponseBean response = new ResponseBean();
		try {
			userService.leaveMember(response);
			return response(response);
		} catch (Exception e) {
			logger.error("searchUser", e);
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

	/**
	 * M17
	 * 
	 * @param page
	 * @param size
	 * @param sortBy
	 * @param sortType
	 * @return
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> saveContact(@RequestBody TblQuestion question) {
		ResponseBean response = new ResponseBean();
		try {
			userService.saveContact(question, response);
			return response(response);
		} catch (Exception e) {
			logger.error("searchUser", e);
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

	/**
	 * M18
	 * 
	 * @param page
	 * @param size
	 * @param sortBy
	 * @param sortType
	 * @return
	 */
	@RequestMapping(value = "/comment", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> getComment(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "asc") String sortType) {
		ResponseBean response = new ResponseBean();
		try {
			userService.getComment(page, size, sortBy, sortType, response);
			return response(response);
		} catch (Exception e) {
			logger.error("searchUser", e);
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

	/**
	 * M14
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/sale/request_payment", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> createRequestPayment(@Valid @RequestBody TblRequestPayment rp) {
		ResponseBean response = new ResponseBean();
		try {
			sellerService.createRequestPayment(rp, response);
			return response(response);
		} catch (Exception e) {
			logger.error("searchProductBuying", e);
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}
}

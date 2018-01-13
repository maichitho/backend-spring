package com.mulodo.fiveneed.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.mulodo.fiveneed.bean.ResponseBean;
import com.mulodo.fiveneed.constant.AppHttpStatus;
import com.mulodo.fiveneed.controller.BaseController;

/**
 * ExceptionController(ExceptionHandler)
 *
 * @author thomc
 *
 */
@ControllerAdvice
public class WebExceptionHandler extends BaseController {
	/*-----------------------------------------------
	* Property
	*-----------------------------------------------*/
	/**
	 * App Response
	 */
	public static final String RESPONSE = "response";

	/*-----------------------------------------------
	* Public
	*-----------------------------------------------*/
	/**
	 * AppException
	 *
	 * @param noResultException exception instance
	 * @param request http request
	 * @return HttpStatus.NOT_FOUND
	 */
	@ExceptionHandler(AppException.class)
	public ResponseEntity<ResponseBean> handleNoResultException(
			AppException appException, HttpServletRequest request) {

		this.logger.error("AppException: ", appException);

		// // Covert
		// ExceptionAttributes exceptionAttributes =
		// new DefaultExceptionAttributes();
		// Map<String, Object> responseBody = exceptionAttributes
		// .getExceptionAttributes(appException, request, HttpStatus.OK);
		//
		// // Add App Exception
		// responseBody.put(RESPONSE, appException.getResponse());

		return new ResponseEntity<ResponseBean>(appException.getResponse(),
				HttpStatus.OK);
	}

	/**
	 * All Exception
	 *
	 * @param exception exception instance
	 * @param request http request
	 * @return HttpStatus.INTERNAL_SERVER_ERROR
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseBean> handleException(Exception exception,
			HttpServletRequest request) {

		this.logger.error("Exception: ", exception);

		// // Covert
		// ExceptionAttributes exceptionAttributes =
		// new DefaultExceptionAttributes();
		// Map<String, Object> responseBody = exceptionAttributes
		// .getExceptionAttributes(exception, request, HttpStatus.OK);

		// Add App Exception
		ResponseBean response = new ResponseBean();
		response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}
}

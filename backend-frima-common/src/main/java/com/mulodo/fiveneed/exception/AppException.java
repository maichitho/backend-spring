package com.mulodo.fiveneed.exception;

import com.mulodo.fiveneed.bean.ResponseBean;
import com.mulodo.fiveneed.constant.AppHttpStatus;

public class AppException extends Exception {
	/*-----------------------------------------------
	* Property
	*-----------------------------------------------*/
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * HTTP status
	 */
	private AppHttpStatus status;

	/**
	 * Response
	 */
	private ResponseBean response;

	/*-----------------------------------------------
	* Constructor
	*-----------------------------------------------*/
	/**
	 * Constructor
	 *
	 * @param status AppHttpStatus
	 */
	public AppException(AppHttpStatus appHttpStatus) {
		super();
		this.status = appHttpStatus;
		this.response = new ResponseBean();
		this.response.setStatus(appHttpStatus);
	}

	/**
	 * Constructor
	 *
	 * @param appHttpStatus AppHttpStatus
	 * @param response ResponseBean
	 */
	public AppException(AppHttpStatus appHttpStatus, ResponseBean response) {
		super();
		this.status = appHttpStatus;
		this.response = response;
		this.response.setStatus(appHttpStatus);
	}

	/**
	 * Constructor
	 *
	 * @param message message
	 * @param appHttpStatus AppHttpStatus
	 * @param response ResponseBean
	 */
	public AppException(String message, AppHttpStatus appHttpStatus,
			ResponseBean response) {
		super(message);
		this.status = appHttpStatus;
		this.response = response;
		this.response.setStatus(appHttpStatus);
	}

	/*-----------------------------------------------
	* Getter
	*-----------------------------------------------*/
	/**
	 * @return the status
	 */
	public AppHttpStatus getStatus() {
		return status;
	}

	/**
	 * @return the response
	 */
	public ResponseBean getResponse() {
		return response;
	}
}

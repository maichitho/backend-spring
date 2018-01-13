package com.mulodo.fiveneed.bean;

import com.mulodo.fiveneed.constant.AppHttpStatus;

/**
 * Response
 *
 * @author thomc
 *
 */
public class ResponseBean {
	/*-----------------------------------------------
	* Property
	*-----------------------------------------------*/

	protected Object data;

	protected String message;

	/**
	 * AppHttpStatus
	 */
	protected AppHttpStatus status = AppHttpStatus.SUCCESS;

	/*-----------------------------------------------
	* Getter, Setter
	*-----------------------------------------------*/
	/**
	 * @return the status
	 */
	public AppHttpStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(AppHttpStatus status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}

package co.siten.myvtg.bean;

import java.io.Serializable;

import co.siten.myvtg.util.Constants;

/**
 * 
 * @author thomc
 *
 */
public class ResponseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String errorCode = Constants.ERROR_CODE_0;
	protected String message;
	protected Object object;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}

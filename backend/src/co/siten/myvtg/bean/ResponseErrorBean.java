package co.siten.myvtg.bean;

import co.siten.myvtg.util.Constants;

/**
 * 
 * @author thomc
 *
 */
public class ResponseErrorBean extends ResponseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResponseErrorBean() {
		errorCode = Constants.ERROR_CODE_1;
	}
	
	public ResponseErrorBean(Exception e) {
		message= e.getMessage();
		errorCode = Constants.ERROR_CODE_1;
	}
}

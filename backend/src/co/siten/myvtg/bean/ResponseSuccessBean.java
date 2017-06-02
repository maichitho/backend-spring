package co.siten.myvtg.bean;

import co.siten.myvtg.util.Constants;

/**
 * 
 * @author thomc
 *
 */
public class ResponseSuccessBean extends ResponseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object wsResponse = null;

	public ResponseSuccessBean() {
		errorCode = Constants.ERROR_CODE_0;
	}

	// public ResponseSuccessBean(ResponseBean res, Object wsResponse) {
	// this.message = res.message;
	// this.object = res.object;
	// this.wsResponse = wsResponse;
	// errorCode = Constants.ERROR_CODE_0;
	// }

	public Object getWsResponse() {
		return wsResponse;
	}

	public void setWsResponse(Object wsResponse) {
		this.wsResponse = wsResponse;
	}
}

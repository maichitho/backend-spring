package co.siten.myvtg.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author thomc
 *
 */
@XmlRootElement
public class RequestBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object sessionId;
	private String wsCode;
	private String apiKey;
	private LinkedHashMap<String, Object> wsRequest;

	public Object getSessionId() {
		return sessionId;
	}

	public void setSessionId(Object sessionId) {
		this.sessionId = sessionId;
	}

	public String getWsCode() {
		return wsCode;
	}

	public void setWsCode(String wsCode) {
		this.wsCode = wsCode;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public LinkedHashMap<String, Object> getWsRequest() {
		return wsRequest;
	}

	public void setWsRequest(LinkedHashMap<String, Object> wsRequest) {
		this.wsRequest = wsRequest;
	}
}

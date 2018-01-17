package com.vietis.carpark.bean.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vietis.carpark.bean.ResponseBean;

public class LoginResponseBean extends ResponseBean {
	
	@JsonProperty("auto_login_token")
	private String autoLoginToken = "";

	@JsonProperty("access_token")
	private String accessToken = "";

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAutoLoginToken() {
		return autoLoginToken;
	}

	public void setAutoLoginToken(String autoLoginToken) {
		this.autoLoginToken = autoLoginToken;
	}

}

package com.vietis.carpark.bean.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vietis.carpark.bean.ResponseBean;
import com.vietis.carpark.entity.MstUser;
import com.vietis.carpark.entity.MstUserProfile;

public class LoginResponseBean extends ResponseBean {
	@JsonProperty("auto_login_token")
	private String autoLoginToken="";
    
	@JsonProperty("access_token")
	private String accessToken="";
	
     private MstUserProfile userprofile;
	
	public MstUserProfile getUserprofile() {
		return userprofile;
	}

	public void setUserprofile(MstUserProfile userprofile) {
		this.userprofile = userprofile;
	}

	
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

package com.mulodo.fiveneed.bean.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mulodo.fiveneed.bean.ResponseBean;
import com.mulodo.fiveneed.entity.MstUser;
import com.mulodo.fiveneed.entity.MstUserProfile;

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

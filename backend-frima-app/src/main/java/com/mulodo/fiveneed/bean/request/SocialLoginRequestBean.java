package com.mulodo.fiveneed.bean.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mulodo.fiveneed.bean.RequestBean;

public class SocialLoginRequestBean extends RequestBean {
	@JsonProperty("provider_token")
	private String providerToken;

	@JsonProperty("provider_type")
	private String providerType;

	public String getProviderToken() {
		return providerToken;
	}

	public void setProviderToken(String providerToken) {
		this.providerToken = providerToken;
	}

	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

}

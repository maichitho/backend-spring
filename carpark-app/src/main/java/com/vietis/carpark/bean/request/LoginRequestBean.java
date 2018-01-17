package com.vietis.carpark.bean.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vietis.carpark.bean.RequestBean;

public class LoginRequestBean extends RequestBean {
	
	@JsonProperty("password")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("email")
	private String email;


}

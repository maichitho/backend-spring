package com.mulodo.fiveneed.bean.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mulodo.fiveneed.bean.RequestBean;

public class ChangePasswordRequestBean extends RequestBean {

	@JsonProperty("new_password")
	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


}

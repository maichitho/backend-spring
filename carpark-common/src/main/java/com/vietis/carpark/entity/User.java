package com.vietis.carpark.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * The persistent class for the mst_user database table.
 * 
 */
@Entity
@Table(name = "user")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NamedQuery(name = "User.findAll", query = "SELECT m FROM User m")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int ACTIVE = 2;
	public static final int DELETE = 3;
	public static final int CONFIRM = 1;
	public static final String LEAVE = "leave";
	public static final String PROVIDER_TYPE_LOCAL = "local";
	public static final String PROVIDER_TYPE_GOOGLE = "google";
	public static final String PROVIDER_TYPE_FACEBOOK = "facebook";
	public static final String PROVIDER_TYPE_LINKED = "linked";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@Column(name = "access_token")
	private String accessToken;

	@Column(name = "activation_key")
	private String activationKey;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "created_by")
	private Long createdBy;

	private String email;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Column(name = "is_sys_user")
	private Boolean isSysUser;

	@Column(name = "password")
	private String password;

	@Column(name = "provider_type")
	private String providerType;
	
	@Column(name = "provider_id")
	private String providerId;

	@Column(name = "role")
	private String role;

	@JsonIgnore
	private String salt;

	private int status;
	@JsonIgnore
	@Column(name = "temp_password")
	private String tempPassword;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "token_expire_date")
	private Date tokenExpiredDate;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "user_name")
	private String userName;

	@Transient
	private String oldPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getActivationKey() {
		return this.activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsSysUser() {
		return this.isSysUser;
	}

	public void setIsSysUser(Boolean isSysUser) {
		this.isSysUser = isSysUser;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProviderType() {
		return this.providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTempPassword() {
		return this.tempPassword;
	}

	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}

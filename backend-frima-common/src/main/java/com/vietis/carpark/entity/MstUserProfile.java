package com.vietis.carpark.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.vietis.carpark.common.util.CommonUtil;

/**
 * The persistent class for the mst_user_profile database table.
 * 
 */
@Entity
@Table(name = "mst_user_profile")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NamedQuery(name = "MstUserProfile.findAll", query = "SELECT m FROM MstUserProfile m")
public class MstUserProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	private Long userId;

	private String building;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "credit_card_expired")
	private String creditCardExpired;

	@Column(name = "credit_card_name")
	private String creditCardName;

	@Column(name = "credit_card_no")
	private String creditCardNo;

	private String description;

	private String district;

	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "home_address")
	private String homeAddress;

	@Column(name = "image_url")
	private String imageUrl;

	private String name;

	@Column(name = "name_kana")
	private String nameKana;

	@Column(name = "payment_method")
	private Integer paymentMethod;

	@Column(name = "phone_no")
	private String phoneNo;

	private String postcode;

	@Column(name = "prefecture_id")
	private Integer prefectureId;

	private String surname;

	@Column(name = "surname_kana")
	private String surnameKana;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "total_revenue")
	private BigDecimal totalRevenue;

	@Column(name = "total_product")
	private Integer totalProduct;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	private String email;

	private String status;

	@Transient
	private long id;

	// Total Request payment
	@Transient
	private BigDecimal totalRequestPayment;
	// Total need to request
	@Transient
	private BigDecimal totalNeedToRequest;
	// Total Request accepted
	@Transient
	private BigDecimal totalPaidPayment;
	// Total exprired
	@Transient
	private BigDecimal totalExpired;

	public MstUserProfile() {
	}

	public String getEmail() {
		return email;
	}

	public String getStatus() {
		return status;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return userId;
	}

	@Transient
	public BigDecimal getTotalRequestPayment() {
		return totalRequestPayment;
	}

	public void setTotalRequestPayment(BigDecimal totalRequestPayment) {
		this.totalRequestPayment = totalRequestPayment;
	}

	@Transient
	public BigDecimal getTotalNeedToRequest() {
		return totalNeedToRequest;
	}

	public void setTotalNeedToRequest(BigDecimal totalNeedToRequest) {
		this.totalNeedToRequest = totalNeedToRequest;
	}

	@Transient
	public BigDecimal getTotalPaidPayment() {
		return totalPaidPayment;
	}

	public void setTotalPaidPayment(BigDecimal totalPaidPayment) {
		this.totalPaidPayment = totalPaidPayment;
	}

	@Transient
	public BigDecimal getTotalExpired() {
		return totalExpired;
	}

	public void setTotalExpired(BigDecimal totalExpired) {
		this.totalExpired = totalExpired;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Integer getTotalProduct() {
		return totalProduct;
	}

	public void setTotalProduct(Integer totalProduct) {
		this.totalProduct = totalProduct;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getBuilding() {
		return this.building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreditCardExpired() {
		return this.creditCardExpired;
	}

	public void setCreditCardExpired(String creditCardExpired) {
		this.creditCardExpired = creditCardExpired;
	}

	public String getCreditCardName() {
		return this.creditCardName;
	}

	public void setCreditCardName(String creditCardName) {
		this.creditCardName = creditCardName;
	}

	public String getCreditCardNo() {
		return this.creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameKana() {
		return this.nameKana;
	}

	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}

	public Integer getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Integer getPrefectureId() {
		return this.prefectureId;
	}

	public void setPrefectureId(Integer prefectureId) {
		this.prefectureId = prefectureId;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSurnameKana() {
		return this.surnameKana;
	}

	public void setSurnameKana(String surnameKana) {
		this.surnameKana = surnameKana;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void copyInfo(MstUserProfile profile) {
		if (!CommonUtil.isEmpty(profile.getBuilding()))
			this.building = profile.getBuilding();
		if (!CommonUtil.isEmpty(profile.getCreditCardExpired()))
			this.creditCardExpired = profile.getCreditCardExpired();
		if (!CommonUtil.isEmpty(profile.getCreditCardName()))
			this.creditCardName = profile.getCreditCardName();
		if (!CommonUtil.isEmpty(profile.getCreditCardNo()))
			this.creditCardNo = profile.getCreditCardNo();
		if (!CommonUtil.isEmpty(profile.getDescription()))
			this.description = profile.getDescription();
		if (!CommonUtil.isEmpty(profile.getDistrict()))
			this.district = profile.getDistrict();
		if (!CommonUtil.isEmpty(profile.getHomeAddress()))
			this.homeAddress = profile.getHomeAddress();
		if (!CommonUtil.isEmpty(profile.getImageUrl()))
			this.imageUrl = profile.getImageUrl();
		if (!CommonUtil.isEmpty(profile.getName()))
			this.name = profile.getName();
		if (!CommonUtil.isEmpty(profile.getNameKana()))
			this.nameKana = profile.getNameKana();
		if (profile.getPaymentMethod() != 0)
			this.paymentMethod = profile.getPaymentMethod();
		if (!CommonUtil.isEmpty(profile.getPhoneNo()))
			this.phoneNo = profile.getPhoneNo();
		if (!CommonUtil.isEmpty(profile.getPostcode()))
			this.postcode = profile.getPostcode();
		if (profile.getPrefectureId() != 0)
			this.prefectureId = profile.getPrefectureId();
		if (!CommonUtil.isEmpty(profile.getSurname()))
			this.surname = profile.getSurname();
		if (!CommonUtil.isEmpty(profile.getSurnameKana()))
			this.surnameKana = profile.getSurnameKana();
//		if (!CommonUtil.isEmpty(profile.getEmail()))
//			this.email = profile.getEmail();
	}

}

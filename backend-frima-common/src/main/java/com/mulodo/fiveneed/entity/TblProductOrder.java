package com.mulodo.fiveneed.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the tbl_product_order database table.
 * 
 */
@Entity
@Table(name = "tbl_product_order")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NamedQuery(name = "TblProductOrder.findAll", query = "SELECT t FROM TblProductOrder t")
public class TblProductOrder implements Serializable {
	public static final int STATUS_0_CANCEL = 0;
	public static final int STATUS_1_WAIT_BUYER_PAY = 1;
	public static final int STATUS_2_WAIT_SELLER_DELIVER = 2;
	public static final int STATUS_3_WAIT_BUYER_RECEIVE = 3;
	public static final int STATUS_4_WAIT_ADMIN_PAY_SELLER = 4;
	public static final int STATUS_5_COMPLETED = 5;

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deliver_at")
	private Date deliverAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "received_at")
	private Date receivedAt;

	@Column(name = "status")
	private Integer status;

	private String district;

	@Column(name = "home_address")
	private String homeAddress;

	private String name;

	@Column(name = "name_kana")
	private String nameKana;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "payment_buy_at")
	private Date paymentBuyAt;

	@Column(name = "payment_method")
	private Integer paymentMethod;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "payment_seller_at")
	private Date paymentSellerAt;

	@Column(name = "phone_no")
	private String phoneNo;

	private String postcode;

	@Column(name = "prefecture_id")
	private Integer prefectureId;

	private BigDecimal price;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "product_image_url")
	private String productImageUrl;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "ship_fee_type_id")
	private Integer shipFeeTypeId;

	private String surname;

	@Column(name = "surname_kana")
	private String surnameKana;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "user_buy_id")
	private Long userBuyId;

	@Column(name = "user_sell_id")
	private Long userSellId;

	@Column(name = "request_payment_id")
	private Long requestPaymentId;

	@Column(name = "remain_payment")
	private BigDecimal remainPayment;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@Transient
	private TblProduct product;

	public TblProduct getProduct() {
		return product;
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	public void setProduct(TblProduct product) {
		this.product = product;
	}

	public TblProductOrder() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getRequestPaymentId() {
		return requestPaymentId;
	}

	public void setRequestPaymentId(Long requestPaymentId) {
		this.requestPaymentId = requestPaymentId;
	}

	public BigDecimal getRemainPayment() {
		return remainPayment;
	}

	public void setRemainPayment(BigDecimal remainPayment) {
		this.remainPayment = remainPayment;
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

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
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

	public Date getDeliverAt() {
		return this.deliverAt;
	}

	public void setDeliverAt(Date deliverAt) {
		this.deliverAt = deliverAt;
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

	public Date getPaymentBuyAt() {
		return this.paymentBuyAt;
	}

	public void setPaymentBuyAt(Date paymentBuyAt) {
		this.paymentBuyAt = paymentBuyAt;
	}

	public Integer getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Date getPaymentSellerAt() {
		return this.paymentSellerAt;
	}

	public void setPaymentSellerAt(Date paymentSellerAt) {
		this.paymentSellerAt = paymentSellerAt;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductImageUrl() {
		return this.productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getShipFeeTypeId() {
		return this.shipFeeTypeId;
	}

	public void setShipFeeTypeId(Integer shipFeeTypeId) {
		this.shipFeeTypeId = shipFeeTypeId;
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

	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUserBuyId() {
		return this.userBuyId;
	}

	public void setUserBuyId(Long userBuyId) {
		this.userBuyId = userBuyId;
	}

	public Long getUserSellId() {
		return this.userSellId;
	}

	public void setUserSellId(Long userSellId) {
		this.userSellId = userSellId;
	}

}

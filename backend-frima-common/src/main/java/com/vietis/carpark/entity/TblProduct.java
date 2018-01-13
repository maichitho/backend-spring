package com.vietis.carpark.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * The persistent class for the tbl_product database table.
 * 
 */
@Entity
@Table(name = "tbl_product")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NamedQuery(name = "TblProduct.findAll", query = "SELECT t FROM TblProduct t")
public class TblProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String STATUS_DRAFT = "draft";
	public static final String STATUS_PUBLISHED = "published";
	public static final String STATUS_COMPLETED = "completed";
	public static final String STATUS_BUYING = "buying";
	public static final String STATUS_CANCEL = "cancel";
	public static final Integer PRODUCT_TYPE_COLLECTION = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "category_id")
	private Integer categoryId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "deliver_day_id")
	private Integer deliverDayId;

	private String description;

	private BigDecimal fee;

	private String name;

	@Column(name = "prefecture_id")
	private Integer prefectureId;

	private BigDecimal price;

	@Column(name = "product_image_url")
	private String productImageUrl;

	@Column(name = "product_status_id")
	private Integer productStatusId;

	@Column(name = "product_type")
	private Integer productType;

	private BigDecimal profit;

	@Column(name = "ship_fee_type_id")
	private Integer shipFeeTypeId;

	@Column(name = "ship_method_id")
	private Integer shipMethodId;

	private String status;

	@Column(name = "store_type_id")
	private Integer storeTypeId;

	@Column(name = "category_name")
	private String categoryName;

	private String tags;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "volumn_id")
	private Integer volumnId;

	@Column(name = "volumn_value")
	private BigDecimal volumnValue;

	@Transient
	private String deliverDay;
	@Transient
	private String prefecture;
	@Transient
	private String productStatus;
	@Transient
	private String shipFeeType;
	@Transient
	private String shipMethod;
	@Transient
	private String storeType;
	@Transient
	private String volumn;
	@Transient
	private List<String> imageUrlList;
	@Transient
	private boolean wish;

	public boolean isWish() {
		return wish;
	}

	public void setWish(boolean wish) {
		this.wish = wish;
	}

	public List<String> getImageUrlList() {
		return imageUrlList;
	}

	public void setImageUrlList(List<String> imageUrlList) {
		this.imageUrlList = imageUrlList;
	}

	public String getDeliverDay() {
		return deliverDay;
	}

	public void setDeliverDay(String deliverDay) {
		this.deliverDay = deliverDay;
	}

	public String getPrefecture() {
		return prefecture;
	}

	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getShipFeeType() {
		return shipFeeType;
	}

	public void setShipFeeType(String shipFeeType) {
		this.shipFeeType = shipFeeType;
	}

	public String getShipMethod() {
		return shipMethod;
	}

	public void setShipMethod(String shipMethod) {
		this.shipMethod = shipMethod;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getVolumn() {
		return volumn;
	}

	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}

	public TblProduct() {
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public Integer getDeliverDayId() {
		return this.deliverDayId;
	}

	public void setDeliverDayId(Integer deliverDayId) {
		this.deliverDayId = deliverDayId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getFee() {
		return this.fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getProductImageUrl() {
		return this.productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public Integer getProductStatusId() {
		return this.productStatusId;
	}

	public void setProductStatusId(Integer productStatusId) {
		this.productStatusId = productStatusId;
	}

	public Integer getProductType() {
		return this.productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public BigDecimal getProfit() {
		return this.profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Integer getShipFeeTypeId() {
		return this.shipFeeTypeId;
	}

	public void setShipFeeTypeId(Integer shipFeeTypeId) {
		this.shipFeeTypeId = shipFeeTypeId;
	}

	public Integer getShipMethodId() {
		return this.shipMethodId;
	}

	public void setShipMethodId(Integer shipMethodId) {
		this.shipMethodId = shipMethodId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStoreTypeId() {
		return this.storeTypeId;
	}

	public void setStoreTypeId(Integer storeTypeId) {
		this.storeTypeId = storeTypeId;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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

	public Integer getVolumnId() {
		return this.volumnId;
	}

	public void setVolumnId(Integer volumnId) {
		this.volumnId = volumnId;
	}

	public BigDecimal getVolumnValue() {
		return this.volumnValue;
	}

	public void setVolumnValue(BigDecimal volumnValue) {
		this.volumnValue = volumnValue;
	}

}

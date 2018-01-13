package com.vietis.carpark.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the park database table.
 * 
 */
@Entity
@NamedQuery(name="Park.findAll", query="SELECT p FROM Park p")
public class Park implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String address;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updated")
	private Date lastUpdated;

	private float lat;

	private float lng;

	@Column(name="max_height")
	private float maxHeight;

	private String phone;

	public Park() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public float getLat() {
		return this.lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return this.lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public float getMaxHeight() {
		return this.maxHeight;
	}

	public void setMaxHeight(float maxHeight) {
		this.maxHeight = maxHeight;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
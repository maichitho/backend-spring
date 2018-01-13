package com.vietis.carpark.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the booking database table.
 * 
 */
@Entity
@NamedQuery(name="Booking.findAll", query="SELECT b FROM Booking b")
public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time")
	private Date endTime;

	private float fee;

	@Column(name="fee_service")
	private float feeService;

	@Column(name="park_id")
	private Long parkId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time")
	private Date startTime;

	private int status;

	@Column(name="user_id")
	private Long userId;

	@Column(name="vehicle_id")
	private Long vehicleId;

	public Booking() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public float getFee() {
		return this.fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

	public float getFeeService() {
		return this.feeService;
	}

	public void setFeeService(float feeService) {
		this.feeService = feeService;
	}

	public Long getParkId() {
		return this.parkId;
	}

	public void setParkId(Long parkId) {
		this.parkId = parkId;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getVehicleId() {
		return this.vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

}
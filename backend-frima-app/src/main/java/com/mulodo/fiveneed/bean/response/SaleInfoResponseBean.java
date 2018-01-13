package com.mulodo.fiveneed.bean.response;

import java.math.BigDecimal;

public class SaleInfoResponseBean {
	private BigDecimal revenue;
	private BigDecimal requested;
	private BigDecimal unrequest;

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	public BigDecimal getRequested() {
		return requested;
	}

	public void setRequested(BigDecimal requested) {
		this.requested = requested;
	}

	public BigDecimal getUnrequest() {
		return unrequest;
	}

	public void setUnrequest(BigDecimal unrequest) {
		this.unrequest = unrequest;
	}

}

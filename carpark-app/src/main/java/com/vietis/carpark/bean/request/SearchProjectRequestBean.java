package com.vietis.carpark.bean.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vietis.carpark.bean.RequestBean;

public class SearchProjectRequestBean extends RequestBean {

	@JsonProperty("project_name")
	private String projectName;

	@JsonProperty("create_date_from")
	private String createDateFrom;

	@JsonProperty("create_date_to")
	private String createDateTo;

	@JsonProperty("update_date_from")
	private String updateDateFrom;

	@JsonProperty("update_date_to")
	private String updateDateTo;

	private String client;

	@JsonProperty("client_address")
	private String clientAddress;

	private String building;

	@JsonProperty("building_address")
	private String buildingAddress;

	private String page;

	@JsonProperty("sort_column")
	private String sortColumn;

	@JsonProperty("sort_type")
	private String sortType;

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCreateDateFrom() {
		return createDateFrom;
	}

	public void setCreateDateFrom(String createDateFrom) {
		this.createDateFrom = createDateFrom;
	}

	public String getCreateDateTo() {
		return createDateTo;
	}

	public void setCreateDateTo(String createDateTo) {
		this.createDateTo = createDateTo;
	}

	public String getUpdateDateFrom() {
		return updateDateFrom;
	}

	public void setUpdateDateFrom(String updateDateFrom) {
		this.updateDateFrom = updateDateFrom;
	}

	public String getUpdateDateTo() {
		return updateDateTo;
	}

	public void setUpdateDateTo(String updateDateTo) {
		this.updateDateTo = updateDateTo;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getBuildingAddress() {
		return buildingAddress;
	}

	public void setBuildingAddress(String buildingAddress) {
		this.buildingAddress = buildingAddress;
	}

}

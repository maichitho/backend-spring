package com.mulodo.fiveneed.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


/**
 * The persistent class for the mst_config database table.
 * 
 */
@Entity
@Table(name = "mst_config")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NamedQuery(name = "MstConfig.findAll", query = "SELECT m FROM MstConfig m")
public class MstConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	@Column(name = "param_name")
	private Integer paramName;

	@Column(name = "param_value")
	private Integer paramValue;


	public MstConfig() {}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getParamName() {
		return paramName;
	}


	public void setParamName(Integer paramName) {
		this.paramName = paramName;
	}


	public Integer getParamValue() {
		return paramValue;
	}


	public void setParamValue(Integer paramValue) {
		this.paramValue = paramValue;
	}


}

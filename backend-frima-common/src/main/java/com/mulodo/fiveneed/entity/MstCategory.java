package com.mulodo.fiveneed.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


/**
 * The persistent class for the mst_category database table.
 * 
 */
@Entity
@Table(name="mst_category")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NamedQuery(name="MstCategory.findAll", query="SELECT m FROM MstCategory m")
public class MstCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Column(name="order_no")
	private Integer orderNo;

	@Column(name="parent_id")
	private Integer parentId;

	@Column(name="parents_id")
	private String parentsId;

	@Column(name="parents_name")
	private String parentsName;

	public MstCategory() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentsId() {
		return this.parentsId;
	}

	public void setParentsId(String parentsId) {
		this.parentsId = parentsId;
	}

	public String getParentsName() {
		return this.parentsName;
	}

	public void setParentsName(String parentsName) {
		this.parentsName = parentsName;
	}

}
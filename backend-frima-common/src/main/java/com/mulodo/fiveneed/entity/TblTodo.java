package com.mulodo.fiveneed.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Date;

/**
 * The persistent class for the tbl_todo database table.
 * 
 */
@Entity
@Table(name = "tbl_todo")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NamedQuery(name = "TblTodo.findAll", query = "SELECT t FROM TblTodo t")
public class TblTodo implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int TODO_TYPE_1_DELIVER = 1;
	public static final int TODO_TYPE_2_RECEIVE = 2;
	public static final int TODO_TYPE_3_COMMENT = 3;
	public static final int TODO_TYPE_4_CHAT = 4;
	public static final int TODO_TYPE_5_RATE = 5;

	public static final String TODO_CONTENT_1_DELIVER = "配送通知を行ってください";
	public static final String TODO_CONTENT_2_RECEIVE = "受取通知を行ってください";
	public static final String TODO_CONTENT_3_COMMENT = "コメントに返信してください";
	public static final String TODO_CONTENT_4_CHAT = "チャットに返信してください";
	public static final String TODO_CONTENT_5_RATE = "評価してください";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "from_user_id")
	private Long fromUserId;

	@Column(name = "from_user_name")
	private String fromUserName;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "is_read")
	private Boolean isRead;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "receive_user_id")
	private Long receiveUserId;

	@Column(name = "todo_content")
	private String todoContent;

	@Column(name = "todo_type")
	private Integer todoType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "updated_by")
	private Long updatedBy;

	public TblTodo() {
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

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getFromUserId() {
		return this.fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUserName() {
		return this.fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Boolean getIsRead() {
		return this.isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getReceiveUserId() {
		return this.receiveUserId;
	}

	public void setReceiveUserId(Long receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public String getTodoContent() {
		return this.todoContent;
	}

	public void setTodoContent(String todoContent) {
		this.todoContent = todoContent;
	}

	public Integer getTodoType() {
		return this.todoType;
	}

	public void setTodoType(Integer todoType) {
		this.todoType = todoType;
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

}

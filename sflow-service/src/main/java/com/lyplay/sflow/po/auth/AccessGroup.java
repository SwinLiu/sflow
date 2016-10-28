package com.lyplay.sflow.po.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lyplay.sflow.po.model.Principal;

/**
 * 权限组表
 * 
 * @author lyplay
 *
 */
@Table(name = "sflow_access_group")
public class AccessGroup implements Principal, Serializable {

	private static final long serialVersionUID = 8005266334683896381L;

	@Id
	@Column(length = 20)
	private String id;

	@Column(length = 20, nullable = false)
	private String groupName;

	@Column(length = 3)
	private Integer orderNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

}

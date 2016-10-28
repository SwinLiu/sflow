package com.lyplay.sflow.po.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lyplay.sflow.po.model.Principal;

/**
 * 用户权限组表
 * 
 * @author lyplay
 *
 */
@Table(name = "sflow_user_access_group")
public class UserAccessGroup implements Principal, Serializable {

	private static final long serialVersionUID = 2070912993664409572L;

	@Id
	@Column(length = 20)
	private String id;

	@Column(length = 20, nullable = false)
	private String userId;

	@Column(length = 20, nullable = false)
	private String accessGrpId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccessGrpId() {
		return accessGrpId;
	}

	public void setAccessGrpId(String accessGrpId) {
		this.accessGrpId = accessGrpId;
	}

}

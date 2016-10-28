package com.lyplay.sflow.po.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lyplay.sflow.po.model.Principal;

/**
 * 角色权限组表
 * 
 * @author lyplay
 *
 */
@Table(name = "sflow_role_access_group")
public class RoleAccessGroup implements Principal, Serializable {

	private static final long serialVersionUID = 3750897905419623656L;

	@Id
	@Column(length = 20)
	private String id;

	@Column(length = 20, nullable = false)
	private String roleId;

	@Column(length = 20, nullable = false)
	private String accessGrpId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getAccessGrpId() {
		return accessGrpId;
	}

	public void setAccessGrpId(String accessGrpId) {
		this.accessGrpId = accessGrpId;
	}

}

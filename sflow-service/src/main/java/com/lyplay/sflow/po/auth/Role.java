package com.lyplay.sflow.po.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lyplay.sflow.po.model.Principal;

/**
 * 角色对象
 * 
 * @author lyplay
 *
 */
@Table(name = "sflow_role")
public class Role implements Principal, Serializable {

	private static final long serialVersionUID = -4371785434259863415L;

	@Id
	@Column(length=20)
	private String id;
	
	@Column(length = 20, nullable = false)
	private String roleName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}

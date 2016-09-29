package com.lyplay.sflow.po.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 权限表
 * @author lyplay
 *
 */
public class ACL implements Serializable {

	private static final long serialVersionUID = 5921863824277705710L;
	

	@Id
	@Column(length=20)
	private String id;

	@Column(length=20)
	private String pType;
	
	@Column(length=20)
	private String pid;
	
	@Column(length=20)
	private String rType;
	
	@Column(length=20)
	private String rid;
	
	@Column(length=20)
	private Integer aclState;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpType() {
		return pType;
	}

	public void setpType(String pType) {
		this.pType = pType;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getrType() {
		return rType;
	}

	public void setrType(String rType) {
		this.rType = rType;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public Integer getAclState() {
		return aclState;
	}

	public void setAclState(Integer aclState) {
		this.aclState = aclState;
	}
	
}

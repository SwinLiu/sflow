package com.lyplay.sflow.po.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sflow_user_role")
public class UserRole implements Serializable {

	private static final long serialVersionUID = -6741029720099675617L;

	@Id
	@Column(length=20)
	private String id;
	
	@Column(length=20,nullable=false)
	private String uid;
	
	@Column(length=20,nullable=false)
	private String compId;
	
	@Column(length=20,nullable=false)
	private String rid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	
	
}

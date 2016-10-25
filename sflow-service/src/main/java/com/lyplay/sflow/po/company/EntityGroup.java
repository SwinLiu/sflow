package com.lyplay.sflow.po.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sflow_entity_group")
public class EntityGroup implements Serializable{

	private static final long serialVersionUID = -8899794525238751149L;

	@Id
	@Column(length=20)
	private String id;
	
	@Column(length=20)
	private String pid;
	
	@Column(length=20)
	private String cid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
	
	
}

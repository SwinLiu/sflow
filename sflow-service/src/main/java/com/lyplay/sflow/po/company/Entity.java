package com.lyplay.sflow.po.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 子公司表
 * @author lyplay
 *
 */
@Table(name = "sflow_entity")
public class Entity implements Serializable {

	private static final long serialVersionUID = 5378367252413538401L;

	@Id
	@Column(length=20)
	private String id;
	
	@Column(length=20)
	private String name;
	
	@Column(length=20)
	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}

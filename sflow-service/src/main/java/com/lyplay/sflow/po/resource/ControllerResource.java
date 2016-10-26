package com.lyplay.sflow.po.resource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lyplay.sflow.po.model.Resource;

@Table(name="sflow_controller_res")
public class ControllerResource implements Resource, Serializable{

	private static final long serialVersionUID = -4858405128969894327L;
	
	@Id
	@Column(length=20)
	private String id;
	
	@Column(length=50)
	private String name;
	
	@Column(length=50,nullable=false)
	private String className;
	
	@Column(length=5)
	private Integer order;

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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	
}

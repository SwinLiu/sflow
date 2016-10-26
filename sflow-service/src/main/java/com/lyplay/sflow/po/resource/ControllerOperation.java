package com.lyplay.sflow.po.resource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lyplay.sflow.po.model.Resource;

@Table(name="sflow_controller_opr")
public class ControllerOperation implements Resource, Serializable{

	private static final long serialVersionUID = -4858405128969894327L;
	
	@Id
	@Column(length=20)
	private String id;
	
	@Column(length=50)
	private String name;
	
	@Column(length=50,nullable=false)
	private String methodName;
	
	@Column(length=5)
	private Integer indexPos;
	
	@Column(length=20)
	private String resId;

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

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Integer getIndexPos() {
		return indexPos;
	}

	public void setIndexPos(Integer indexPos) {
		this.indexPos = indexPos;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	
	
	
}

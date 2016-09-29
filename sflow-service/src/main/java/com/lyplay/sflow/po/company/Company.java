package com.lyplay.sflow.po.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 总公司表
 * @author lyplay
 *
 */

@Table
public class Company implements Serializable{
	
	private static final long serialVersionUID = -4672672311575667360L;

	@Id
	@Column(length=20)
	private String id;
	
	@Column(length = 120, nullable = false)
	private String name;

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
	
}

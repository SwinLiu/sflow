package com.lyplay.sflow.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lyplay.sflow.po.model.Principal;

/**
 * 人员表
 * 
 * @author lyplay
 *
 */

@Table(name = "sflow_user")
public class User implements Principal,Serializable {

	private static final long serialVersionUID = 5941819848560615642L;

	@Id
	@Column(length = 20)
	private String id;
	
	@Column(length = 40, nullable = false)
	private String name;
	
	@Column(length = 1)
	private Integer sex;

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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

}

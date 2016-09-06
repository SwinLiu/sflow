package com.lyplay.sflow.po;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 人员表
 * 		
 * @author lyplay
 *
 */

@Table(name="sflow_user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 5941819848560615642L;
	
	private String id;
	private String name;
	private int sex;
	
	@Id
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
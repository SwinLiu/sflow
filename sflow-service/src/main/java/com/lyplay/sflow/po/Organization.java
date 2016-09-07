package com.lyplay.sflow.po;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 组织对象,该表可以生成完整的组织树
 * 		
 * @author lyplay
 *
 */

@Table(name="sflow_org")
public class Organization implements Serializable{
	
	private static final long serialVersionUID = -5862651851019953885L;
	
	@Id
	private String id;
	/**
	 * 组织机构名字
	 */
	private String name;
	/**
	 * 组织机构的类型
	 */
	private String typeId;
	/**
	 * 组织机构的排序号
	 */
	private Integer orderNo;
	/**
	 * 父组织的id
	 */
	private String pid;
	
	
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
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
}

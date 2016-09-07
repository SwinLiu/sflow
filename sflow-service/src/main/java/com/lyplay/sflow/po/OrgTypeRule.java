package com.lyplay.sflow.po;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 组织机构类型的规则表，用来设定组织机构之间的关系表
 * 		
 * @author lyplay
 *
 */

@Table(name="sflow_org_type_rule")
public class OrgTypeRule implements Serializable{
	
	private static final long serialVersionUID = 7612377508526313874L;
	
	@Id
	private String id;
	/**
	 * parent id
	 */
	private String pid;
	/**
	 * children id
	 */
	private String cid;
	/**
	 * 两者之间的数量，-1 代表无限个
	 */
	private Integer num;
	
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
}

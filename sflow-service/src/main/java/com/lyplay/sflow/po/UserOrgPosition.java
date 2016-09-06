package com.lyplay.sflow.po;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 人员组织岗位对应关系表
 * 		
 * @author lyplay
 *
 */

@Table(name="sflow_user_org_pos")
public class UserOrgPosition implements Serializable{
	
	private static final long serialVersionUID = -4005197459551131066L;
	
	private String id;
	private String userId;
	private String orgId;
	private String posId;
	
	@Id
	public String getId() {
		return id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getPosId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
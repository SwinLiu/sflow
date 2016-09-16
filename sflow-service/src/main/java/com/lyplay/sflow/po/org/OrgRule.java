package com.lyplay.sflow.po.org;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 组织规则表
 * 		
 * @author lyplay
 *
 */

@Table(name="sflow_org_rule")
public class OrgRule implements Serializable{
	
	private static final long serialVersionUID = -3806230227922572154L;
	
	@Id
	@Column(length = 20)
	private String id;
	
	@Column(length = 20, nullable = false)
	private String orgId;
	
	@Column(length = 20, nullable = false)
	private String managerType;
	
	@Column(length = 20, nullable = false)
	private String managerOrg;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getManagerType() {
		return managerType;
	}
	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}
	public String getManagerOrg() {
		return managerOrg;
	}
	public void setManagerOrg(String managerOrg) {
		this.managerOrg = managerOrg;
	}
	
}

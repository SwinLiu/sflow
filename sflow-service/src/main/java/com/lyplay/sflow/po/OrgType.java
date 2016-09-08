package com.lyplay.sflow.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 组织机构类型对象，用来设定系统中存在有哪些组织类型 e.g. 总公司 子公司 部门
 * 
 * @author lyplay
 *
 */

@Table(name = "sflow_org_type")
public class OrgType implements Serializable {

	private static final long serialVersionUID = -6286594645841276713L;

	@Id
	@Column(length = 20)
	private String id;

	@Column(length = 20, nullable = false)
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

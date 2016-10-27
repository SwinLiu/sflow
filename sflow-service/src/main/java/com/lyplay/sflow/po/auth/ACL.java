package com.lyplay.sflow.po.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lyplay.sflow.enums.PrincipalType;
import com.lyplay.sflow.enums.ResourceType;
import com.lyplay.sflow.exception.SysException;

/**
 * 权限表
 * @author lyplay
 *
 */
@Table(name = "sflow_acl")
public class ACL implements Serializable {

	private static final long serialVersionUID = 5921863824277705710L;
	

	@Id
	@Column(length=20)
	private String id;

	@Column(length=20)
	private String pType;
	
	@Column(length=20)
	private String pid;
	
	@Column(length=20)
	private String rType;
	
	@Column(length=20)
	private String rid;
	
	@Column(length=4)
	private Integer aclState;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPType() {
		return pType;
	}

	public void setPType(String pType) {
		this.pType = pType;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getRType() {
		return rType;
	}

	public void setRType(String rType) {
		this.rType = rType;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public Integer getAclState() {
		return aclState;
	}

	public void setAclState(Integer aclState) {
		this.aclState = aclState;
	}
	
	public void setPrincipalType(PrincipalType principalType) {
		this.pType = principalType.getValue();
	}
	
	public void setRourceType(ResourceType resourceType) {
		this.rType = resourceType.getValue();
	}
	
	public void setPermission(int index, boolean permit){
		
		if(index < 0 || index > 31){
			throw new SysException("permission index should between 0 ~ 31 .");
		}
		
		this.aclState = setBit(this.aclState, index, permit );
		
		
	}
	
	public int setBit(int state, int index, boolean permit){
		int temp = 1;
		temp = temp << index;
		if(permit){
			state = state | temp;
		}else{
			temp = ~temp;
			state = state & temp;
		}
		return state;
	}
	
	
	
	
	public boolean checkPermission(int index){
		
		
		return false;
	}
	
	
	
	

}

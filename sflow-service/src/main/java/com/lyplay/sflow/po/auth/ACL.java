package com.lyplay.sflow.po.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lyplay.sflow.enums.ACLAction;
import com.lyplay.sflow.enums.PrincipalType;
import com.lyplay.sflow.enums.ResourceType;
import com.lyplay.sflow.exception.SysException;

/**
 * 权限表
 * 
 * @author lyplay
 *
 */
@Table(name = "sflow_acl")
public class ACL implements Serializable {

	private static final long serialVersionUID = 5921863824277705710L;

	/**
	 * 主键Id
	 */
	@Id
	@Column(length = 20)
	private String id;

	/**
	 * 主体类型
	 */
	@Column(length = 20)
	private String pType;

	/**
	 * 主体主键Id
	 */
	@Column(length = 20)
	private String pId;

	/**
	 * 资源类型
	 */
	@Column(length = 20)
	private String rType;

	/**
	 * 资源主键Id
	 */
	@Column(length = 20)
	private String rId;

	/**
	 * 权限状态, 二进制位置表示权限。 4 -> 0000000100<br> 
	 * Create : 第一位 Red : 第二位 Update : 第三位 Delete : 第四位 Other : design by developer
	 */
	@Column(length = 4)
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

	public String getPId() {
		return pId;
	}

	public void setPId(String pId) {
		this.pId = pId;
	}

	public String getRType() {
		return rType;
	}

	public void setRType(String rType) {
		this.rType = rType;
	}

	public String getRId() {
		return rId;
	}

	public void setRId(String rId) {
		this.rId = rId;
	}

	public Integer getAclState() {
		return aclState;
	}

	public void setAclState(Integer aclState) {
		this.aclState = aclState;
	}

	public void setPrincipalType(PrincipalType principalType) {
		if (principalType == null) {
			throw new SysException("Principal Type can not be null.");
		}
		this.pType = principalType.getValue();
	}

	public void setRourceType(ResourceType resourceType) {
		if (resourceType == null) {
			throw new SysException("Resource Type can not be null.");
		}
		this.rType = resourceType.getValue();
	}

	public void setPermission(ACLAction action, boolean permit) {
		if (action == null) {
			throw new SysException("ACL Action can not be null.");
		}
		setPermission(action.getIndex(), permit);
	}

	/**
	 * 
	 * @param index
	 * @param permit
	 */
	public void setPermission(int index, boolean permit) {

		if (index < 0 || index > 31) {
			throw new SysException("permission index should between 0 ~ 31 .");
		}

		this.aclState = setBit(this.aclState, index, permit);

	}

	/**
	 * 
	 * @param state
	 * @param index
	 * @param permit
	 * @return
	 */
	public int setBit(int state, int index, boolean permit) {
		int temp = 1;
		temp = temp << index;
		if (permit) {
			state = state | temp;
		} else {
			temp = ~temp;
			state = state & temp;
		}
		return state;
	}

	public boolean checkPermission(ACLAction action) {
		if (action == null) {
			throw new SysException("ACL Action can not be null.");
		}
		return checkPermission(action.getIndex());
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public boolean checkPermission(int index) {
		int temp = 1;
		temp = temp << index;
		int num = this.aclState & temp;
		return num > 0;
	}

}

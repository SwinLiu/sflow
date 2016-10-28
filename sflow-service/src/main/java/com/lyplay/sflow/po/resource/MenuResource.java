package com.lyplay.sflow.po.resource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lyplay.sflow.po.model.Resource;

@Table(name = "sflow_menu_res")
public class MenuResource implements Resource, Serializable {

	private static final long serialVersionUID = -4858405128969894327L;

	@Id
	@Column(length = 20)
	private String id;

	@Column(length = 50)
	private String name;

	@Column(length = 20)
	private String menuPos;

	@Column(length = 50)
	private String href;

	@Column(length = 50)
	private String icon;

	@Column(length = 5)
	private Integer orderNum;

	@Column(length = 2)
	private Integer dispaly;

	@Column(length = 20)
	private String parentId;

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

	public String getMenuPos() {
		return menuPos;
	}

	public void setMenuPos(String menuPos) {
		this.menuPos = menuPos;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getDispaly() {
		return dispaly;
	}

	public void setDispaly(Integer dispaly) {
		this.dispaly = dispaly;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}

package com.lyplay.sflow.po.org;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Position 职位表
 * 
 * @author lyplay
 *
 */

@Table(name = "sflow_position")
public class Position implements Serializable {

	private static final long serialVersionUID = 3343662737395229366L;

	@Id
	@Column(length = 20)
	private String id;

	@Column(length = 50, nullable = false)
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

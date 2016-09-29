package com.lyplay.sflow.po.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 子公司表
 * @author lyplay
 *
 */
public class Entity implements Serializable {

	private static final long serialVersionUID = 5378367252413538401L;

	@Id
	@Column(length=20)
	private String id;
	
	@Column(length=20)
	private String name;
	
	@Column(length=20)
	private String address;
	
	
}

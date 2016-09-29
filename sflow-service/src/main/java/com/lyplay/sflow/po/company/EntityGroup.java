package com.lyplay.sflow.po.company;

import javax.persistence.Column;
import javax.persistence.Id;

public class EntityGroup {

	@Id
	@Column(length=20)
	private String id;
	
	@Column(length=20)
	private String pid;
	
	@Column(length=20)
	private String cid;
	
}

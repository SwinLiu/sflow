package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IOrganizationDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.Organization;

@Repository
public class OrganizationDAO extends BaseDAO<Organization> implements IOrganizationDAO{

	private static final Po<Organization> organization = new Po<Organization>(Organization.class);
	
	
}

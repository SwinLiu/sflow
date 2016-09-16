package com.lyplay.sflow.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IOrganizationDAO;
import com.lyplay.sflow.dto.TreeDto;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.org.Organization;

@Repository
public class OrganizationDAO extends BaseDAO<Organization> implements IOrganizationDAO{

	private static final Po<Organization> organization = new Po<Organization>(Organization.class);

	@Override
	public Pagination<Organization> findByParent(Integer pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TreeDto> tree(Integer typeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumByType(Integer pid, String type) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}

package com.lyplay.sflow.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IOrgTypeDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.org.OrgType;

@Repository
public class OrgTypeDAO extends BaseDAO<OrgType> implements IOrgTypeDAO{

	private static final Po<OrgType> orgType = new Po<OrgType>(OrgType.class);

	@Override
	public List<OrgType> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addOrgTypeRule(int pid, int cid, int num) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delOrgTypeRule(int pid, int cid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOrgTypeRule(int pid, int cid, int num) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<OrgType> listByRule(int pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNum(int pid, int cid) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}

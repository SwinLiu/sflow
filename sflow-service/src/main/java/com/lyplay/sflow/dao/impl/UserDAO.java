package com.lyplay.sflow.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IUserDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.User;

@Repository
public class UserDAO extends BaseDAO<User> implements IUserDAO{

	private static final Po<User> user = new Po<User>(User.class);

	@Override
	public Pagination<User> findByOrg(int oid, Integer posId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> listAllOrgIdByUser(int uid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

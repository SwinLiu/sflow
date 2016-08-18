package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IUserAccountDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.po.UserAccount;

@Repository
public class UserAccountDAO extends BaseDAO implements IUserAccountDAO{

	@Override
	public String geIdByPhone(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIdByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIdByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAccount get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(UserAccount userAccount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(UserAccount userAccount) {
		// TODO Auto-generated method stub
		return false;
	}

	
}

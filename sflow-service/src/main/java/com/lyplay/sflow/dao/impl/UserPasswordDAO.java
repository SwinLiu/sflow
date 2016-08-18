package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IUserPasswordDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.po.UserPassword;

@Repository
public class UserPasswordDAO extends BaseDAO implements IUserPasswordDAO{

	@Override
	public boolean save(UserPassword userPwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkPwd(UserPassword userPwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(UserPassword userPwd) {
		// TODO Auto-generated method stub
		return false;
	}
	
}

package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IUserPasswordDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.po.UserPassword;

@Repository
public class UserPasswordDAO extends BaseDAO implements IUserPasswordDAO{

	private static final String TABLE_NAME = "sflow_user_password";
	
	@Override
	public boolean save(UserPassword userPwd) {
		return this.save(userPwd);
	}

	@Override
	public boolean checkPwd(UserPassword userPwd) {
		String sql = String.format(" select count(0) from %s where id = ? and password = ? ",TABLE_NAME);
		return this.getJdbcTemplate().queryForObject(sql, Integer.class, userPwd.getId(), userPwd.getPassword()) > 0;
	}

	@Override
	public boolean update(UserPassword userPwd) {
		return this.update(userPwd);
	}
	
}

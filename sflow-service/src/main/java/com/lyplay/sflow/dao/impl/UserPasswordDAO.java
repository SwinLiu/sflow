package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IUserPasswordDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.UserPassword;

@Repository
public class UserPasswordDAO extends BaseDAO<UserPassword> implements IUserPasswordDAO{

	private static final Po<UserPassword> userPassword = new Po<UserPassword>(UserPassword.class);
	
	@Override
	public boolean savePwd(UserPassword userPwd) {
		return this.save(userPwd);
	}

	@Override
	public boolean checkPwd(UserPassword userPwd) {
		String sql = String.format(" select count(0) from %s where id = ? and password = ? ",userPassword.getTableName());
		return this.getJdbcTemplate().queryForObject(sql, Integer.class, userPwd.getId(), userPwd.getPassword()) > 0;
	}

	@Override
	public boolean updatePwd(UserPassword userPwd) {
		return this.update(userPwd);
	}
	
}

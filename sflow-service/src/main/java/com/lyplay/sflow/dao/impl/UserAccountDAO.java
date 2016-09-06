package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IUserAccountDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.orm.utils.ResultUtil;
import com.lyplay.sflow.po.UserAccount;

@Repository
public class UserAccountDAO extends BaseDAO<UserAccount> implements IUserAccountDAO{

	private static final Po<UserAccount> userAccount = new Po<UserAccount>(UserAccount.class);
	
	@Override
	public String getIdByPhone(String phone) {
		String sql = String.format(" select id from %s where phone = ? ",userAccount.getTableName());
		return ResultUtil.getFrist(this.getJdbcTemplate().queryForList(sql, String.class, phone));
	}

	@Override
	public String getIdByEmail(String email) {
		String sql = String.format(" select id from %s where email = ? ",userAccount.getTableName());
		return ResultUtil.getFrist(this.getJdbcTemplate().queryForList(sql, String.class, email));
	}

	@Override
	public String getIdByUserName(String userName) {
		String sql = String.format(" select id from %s where user_name = ? ",userAccount.getTableName());
		return ResultUtil.getFrist(this.getJdbcTemplate().queryForList(sql, String.class, userName));
	}

	@Override
	public UserAccount get(String id) {
		String sql = String.format(" select %s from %s where id = ? ",userAccount.getColumns(),userAccount.getTableName());
		return ResultUtil.getFrist(this.getJdbcTemplate().query(sql, userAccount.getRowMapper(), id));
	}

	@Override
	public boolean saveUserAccount(UserAccount userAccount) {
		return this.save(userAccount);
	}

	@Override
	public boolean updateUserAccount(UserAccount userAccount) {
		return this.saveOrUpdate(userAccount);
	}

	
}

package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IUserAccountDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.utils.CommonRowMapper;
import com.lyplay.sflow.orm.utils.ResultUtil;
import com.lyplay.sflow.po.UserAccount;

@Repository
public class UserAccountDAO extends BaseDAO implements IUserAccountDAO{

	private static final String TABLE_NAME = "sflow_user_account";
	private static final String COLUMN = "id,username,email,phone,status";
	private static CommonRowMapper<UserAccount> userAccountRowMapper = new CommonRowMapper<>(UserAccount.class);
	
	@Override
	public String geIdByPhone(String phone) {
		String sql = String.format(" select id from %s where phone = ? ",TABLE_NAME);
		return ResultUtil.getFrist(this.getJdbcTemplate().queryForList(sql, String.class, phone));
	}

	@Override
	public String getIdByEmail(String email) {
		String sql = String.format(" select id from %s where email = ? ",TABLE_NAME);
		return ResultUtil.getFrist(this.getJdbcTemplate().queryForList(sql, String.class, email));
	}

	@Override
	public String getIdByUserName(String userName) {
		String sql = String.format(" select id from %s where user_name = ? ",TABLE_NAME);
		return ResultUtil.getFrist(this.getJdbcTemplate().queryForList(sql, String.class, userName));
	}

	@Override
	public UserAccount get(String id) {
		String sql = String.format(" select %s from %s where id = ? ",COLUMN,TABLE_NAME);
		return ResultUtil.getFrist(this.getJdbcTemplate().query(sql, userAccountRowMapper, id));
	}

	@Override
	public boolean save(UserAccount userAccount) {
		return this.update(userAccount);
	}

	@Override
	public boolean update(UserAccount userAccount) {
		return this.update(userAccount);
	}

	
}

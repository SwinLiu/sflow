package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IUserLoginLogsDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.utils.CommonRowMapper;
import com.lyplay.sflow.orm.utils.PoUtil;
import com.lyplay.sflow.po.UserLoginLogs;

@Repository
public class UserLoginLogsDAO extends BaseDAO<UserLoginLogs> implements IUserLoginLogsDAO{

	private static final String TABLE_NAME = PoUtil.getTableName(UserLoginLogs.class);
	private static final String COLUMN = PoUtil.getTableColumns(UserLoginLogs.class);
	private static CommonRowMapper<UserLoginLogs> userLoginLogsRowMapper = new CommonRowMapper<>(UserLoginLogs.class);
	
	@Override
	public boolean saveLog(UserLoginLogs loginLog) {
		return this.save(loginLog);
	}

}

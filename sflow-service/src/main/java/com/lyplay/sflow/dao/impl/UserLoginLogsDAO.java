package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IUserLoginLogsDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.po.UserLoginLogs;

@Repository
public class UserLoginLogsDAO extends BaseDAO implements IUserLoginLogsDAO{

	@Override
	public boolean saveLog(UserLoginLogs loginLog) {
		return this.save(loginLog);
	}

}

package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.ILoginLogsDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.po.LoginLogs;

@Repository
public class LoginLogsDAO extends BaseDAO implements ILoginLogsDAO{

	@Override
	public boolean save(LoginLogs loginLog) {
		// TODO Auto-generated method stub
		return false;
	}

}

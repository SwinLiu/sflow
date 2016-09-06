package com.lyplay.sflow.dao;

import com.lyplay.sflow.orm.IBaseDAO;
import com.lyplay.sflow.po.UserLoginLogs;



public interface IUserLoginLogsDAO extends IBaseDAO<UserLoginLogs>{

	public boolean saveLog(UserLoginLogs loginLog);
	
}

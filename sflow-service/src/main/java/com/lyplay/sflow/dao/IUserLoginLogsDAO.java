package com.lyplay.sflow.dao;

import com.lyplay.sflow.po.UserLoginLogs;



public interface IUserLoginLogsDAO{

	public boolean saveLog(UserLoginLogs loginLog);
	
}

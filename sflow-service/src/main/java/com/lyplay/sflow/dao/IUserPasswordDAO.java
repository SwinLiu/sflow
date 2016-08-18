package com.lyplay.sflow.dao;

import com.lyplay.sflow.po.UserPassword;

public interface IUserPasswordDAO{

	public boolean save(UserPassword userPwd);
	
	public boolean checkPwd(UserPassword userPwd);
	
	public boolean update(UserPassword userPwd);
	
	
}

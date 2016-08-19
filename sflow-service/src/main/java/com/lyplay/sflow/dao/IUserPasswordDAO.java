package com.lyplay.sflow.dao;

import com.lyplay.sflow.po.UserPassword;

public interface IUserPasswordDAO{

	public boolean savePwd(UserPassword userPwd);
	
	public boolean checkPwd(UserPassword userPwd);
	
	public boolean updatePwd(UserPassword userPwd);
	
	
}

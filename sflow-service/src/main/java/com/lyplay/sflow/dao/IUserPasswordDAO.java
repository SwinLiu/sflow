package com.lyplay.sflow.dao;

import com.lyplay.sflow.orm.IBaseDAO;
import com.lyplay.sflow.po.UserPassword;

public interface IUserPasswordDAO extends IBaseDAO<UserPassword>{

	public boolean savePwd(UserPassword userPwd);
	
	public boolean checkPwd(UserPassword userPwd);
	
	public boolean updatePwd(UserPassword userPwd);
	
	
}

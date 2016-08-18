package com.lyplay.sflow.service;

import com.lyplay.sflow.po.UserAccount;
import com.lyplay.sflow.po.UserPassword;

public interface IUserAccountService {

	public boolean register(UserAccount userAccount, UserPassword userPwd);
	
	public UserAccount login(String loginAccount,String pwd);
	
}

package com.lyplay.sflow.service.impl;

import org.springframework.stereotype.Service;

import com.lyplay.sflow.po.UserAccount;
import com.lyplay.sflow.po.UserPassword;
import com.lyplay.sflow.service.IUserAccountService;

@Service
public class UserAccountServiceImpl implements IUserAccountService{

	@Override
	public boolean register(UserAccount userAccount, UserPassword userPwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserAccount login(String loginAccount, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.lyplay.sflow.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.lyplay.sflow.BaseTest;
import com.lyplay.sflow.dao.impl.UserAccountDAO;
import com.lyplay.sflow.dao.impl.UserLoginLogsDAO;
import com.lyplay.sflow.dao.impl.UserPasswordDAO;
import com.lyplay.sflow.po.UserAccount;

public class UserDAOTest extends BaseTest{

	@Resource
	private ISequenceDAO sequenceDAO;
	
	@Resource
	private UserAccountDAO userAccountDAO;
	
	@Resource
	private UserPasswordDAO userPasswordDAO;
	
	@Resource
	private UserLoginLogsDAO userLoginLogsDAO;
	
	
	@Test
	public void saveUser(){
		
		UserAccount userAccount = new UserAccount();
		userAccount.setId("U001");
		userAccount.setUserName("swin.liu");
		userAccountDAO.saveUserAccount(userAccount);
		
	}
	
	@Test
	public void updateUser(){
		
		UserAccount userAccount = new UserAccount();
		userAccount.setId("U001");
		userAccount.setUserName("swin.liu,asdf");
		userAccount.setPhone("1234567890");
		userAccount.setEmail("sqin.liu@gpayroll.com");
		userAccount.setStatus(123);
		userAccountDAO.updateUserAccount(userAccount);
		
	}
	
}

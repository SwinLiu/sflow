package com.lyplay.sflow.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.lyplay.sflow.BaseTest;
import com.lyplay.sflow.common.util.SHAUtil;
import com.lyplay.sflow.dao.impl.UserAccountDAO;
import com.lyplay.sflow.dao.impl.UserLoginLogsDAO;
import com.lyplay.sflow.dao.impl.UserPasswordDAO;
import com.lyplay.sflow.po.UserAccount;
import com.lyplay.sflow.po.UserPassword;

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
	public void saveUser() throws Exception{
		
		UserAccount userAccount = new UserAccount();
		userAccount.setId("u009");
		userAccount.setUserName("test");
		userAccount.setEmail("test@test.com");
		userAccount.setStatus(1);
		userAccountDAO.saveUserAccount(userAccount);
		
		userPasswordDAO.save(new UserPassword("u009",SHAUtil.shaEncode("test")));
		
	}
	
	@Test
	public void updateUser(){
		
		UserAccount userAccount = userAccountDAO.get("u009");
		userAccount.setUserName("swin.liu,asdf");
		userAccount.setPhone("1234567890");
		userAccountDAO.updateUserAccount(userAccount);
		
	}
	
	@Test
	public void getUser(){
		
		UserAccount userAccount = userAccountDAO.get("U001");
		System.out.println(userAccount);
		
	}
	
}

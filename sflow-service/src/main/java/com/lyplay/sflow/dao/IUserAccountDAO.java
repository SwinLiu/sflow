package com.lyplay.sflow.dao;

import com.lyplay.sflow.orm.IBaseDAO;
import com.lyplay.sflow.po.UserAccount;



public interface IUserAccountDAO extends IBaseDAO<UserAccount>{

	public String getIdByPhone(String phone);
	
	public String getIdByEmail(String email);
	
	public String getIdByUserName(String userName);
	
	public UserAccount get(String id);
	
	public boolean saveUserAccount(UserAccount userAccount);
	
	public boolean updateUserAccount(UserAccount userAccount);
	
}

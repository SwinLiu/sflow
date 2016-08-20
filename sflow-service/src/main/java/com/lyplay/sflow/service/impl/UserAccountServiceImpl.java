package com.lyplay.sflow.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lyplay.sflow.common.util.RegexChkUtil;
import com.lyplay.sflow.dao.IUserAccountDAO;
import com.lyplay.sflow.dao.IUserLoginLogsDAO;
import com.lyplay.sflow.dao.IUserPasswordDAO;
import com.lyplay.sflow.enums.LoginNameTypeEnum;
import com.lyplay.sflow.po.UserAccount;
import com.lyplay.sflow.po.UserPassword;
import com.lyplay.sflow.service.IUserAccountService;

@Service
public class UserAccountServiceImpl implements IUserAccountService{

	@Resource
	private IUserAccountDAO userAccountDAO;
	@Resource
	private IUserPasswordDAO userPasswordDAO;
	@Resource
	private IUserLoginLogsDAO userLoginLogsDAO;
	
	@Override
	public boolean register(UserAccount userAccount, UserPassword userPwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserAccount login(String loginAccount, String pwd) {
		
		String id = null;
		
		if(RegexChkUtil.startCheck(LoginNameTypeEnum.EMAIL.getRegex(), loginAccount)){
			id = userAccountDAO.getIdByEmail(loginAccount);
		}else if(RegexChkUtil.startCheck(LoginNameTypeEnum.PHONE.getRegex(), loginAccount)){
			id = userAccountDAO.getIdByPhone(loginAccount);
		}else if(RegexChkUtil.startCheck(LoginNameTypeEnum.USERNAME.getRegex(), loginAccount)){
			id = userAccountDAO.getIdByUserName(loginAccount);
		}
		
		if(StringUtils.isEmpty(id)){
			return null;
		}else{
			
			if(userPasswordDAO.checkPwd(new UserPassword(id,pwd))){
				return userAccountDAO.get(id);
			}else{
				return null;
			}
			
		}
		
	}

}

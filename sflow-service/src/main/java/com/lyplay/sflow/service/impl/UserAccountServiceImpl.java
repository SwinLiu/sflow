package com.lyplay.sflow.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyplay.sflow.common.enums.AccountStatusEnum;
import com.lyplay.sflow.common.enums.PasswdStatusEnum;
import com.lyplay.sflow.common.util.RegexChkUtil;
import com.lyplay.sflow.dao.ISequenceDAO;
import com.lyplay.sflow.dao.IUserAccountDAO;
import com.lyplay.sflow.dao.IUserLoginLogsDAO;
import com.lyplay.sflow.dao.IUserPasswordDAO;
import com.lyplay.sflow.enums.LoginNameTypeEnum;
import com.lyplay.sflow.enums.SequenceEnum;
import com.lyplay.sflow.po.Sequence;
import com.lyplay.sflow.po.UserAccount;
import com.lyplay.sflow.po.UserPassword;
import com.lyplay.sflow.service.IUserAccountService;

@Service
public class UserAccountServiceImpl implements IUserAccountService{

	@Resource
	private ISequenceDAO sequenceDAO;
	@Resource
	private IUserAccountDAO userAccountDAO;
	@Resource
	private IUserPasswordDAO userPasswordDAO;
	@Resource
	private IUserLoginLogsDAO userLoginLogsDAO;
	
	@Transactional
	@Override
	public boolean register(UserAccount userAccount, UserPassword userPwd) {
		
		Sequence sequence = sequenceDAO.getNextSequence(SequenceEnum.USER_ACCOUNT_SEQ.getName());
		userAccount.setId(sequence.getSequenceStr(true));
		userAccount.setStatus(AccountStatusEnum.ACTIVE);
		
		userPwd.setId(userAccount.getId());
		userPwd.setLockTime(null);
		userPwd.setStatus(PasswdStatusEnum.ACTIVE);
		
		userAccountDAO.save(userAccount);
		userPasswordDAO.save(userPwd);
		
		return true;
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

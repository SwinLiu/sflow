package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IUserOrgPositionDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.org.UserOrgPosition;

@Repository
public class UserOrgPositionDAO extends BaseDAO<UserOrgPosition> implements IUserOrgPositionDAO{

	private static final Po<UserOrgPosition> userOrgPosition = new Po<UserOrgPosition>(UserOrgPosition.class);
	
	
}

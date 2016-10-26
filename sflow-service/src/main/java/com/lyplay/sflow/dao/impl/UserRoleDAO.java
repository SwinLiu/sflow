package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IUserRoleDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.auth.UserRole;

@Repository
public class UserRoleDAO extends BaseDAO<UserRole> implements IUserRoleDAO{

	private static final Po<UserRole> userRole = new Po<UserRole>(UserRole.class);

	
}

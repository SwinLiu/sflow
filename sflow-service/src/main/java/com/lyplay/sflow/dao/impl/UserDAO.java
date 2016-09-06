package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IUserDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.User;

@Repository
public class UserDAO extends BaseDAO<User> implements IUserDAO{

	private static final Po<User> user = new Po<User>(User.class);
	
	
}

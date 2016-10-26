package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IRoleDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.auth.Role;

@Repository
public class RoleDAO extends BaseDAO<Role> implements IRoleDAO{

	private static final Po<Role> role = new Po<Role>(Role.class);

	
}

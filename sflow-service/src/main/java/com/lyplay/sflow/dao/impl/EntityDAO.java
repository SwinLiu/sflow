package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IEntityDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.company.Entity;

@Repository
public class EntityDAO extends BaseDAO<Entity> implements IEntityDAO{

	private static final Po<Entity> entity = new Po<Entity>(Entity.class);

	
}

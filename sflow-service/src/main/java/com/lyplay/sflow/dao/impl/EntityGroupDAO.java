package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IEntityGroupDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.company.EntityGroup;

@Repository
public class EntityGroupDAO extends BaseDAO<EntityGroup> implements IEntityGroupDAO{

	private static final Po<EntityGroup> entityGroup = new Po<EntityGroup>(EntityGroup.class);

	
}

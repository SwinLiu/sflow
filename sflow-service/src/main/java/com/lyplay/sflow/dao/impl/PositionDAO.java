package com.lyplay.sflow.dao.impl;

import org.springframework.stereotype.Repository;

import com.lyplay.sflow.dao.IPositionDAO;
import com.lyplay.sflow.orm.BaseDAO;
import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.orm.components.Po;
import com.lyplay.sflow.po.Position;

@Repository
public class PositionDAO extends BaseDAO<Position> implements IPositionDAO{

	private static final Po<Position> position = new Po<Position>(Position.class);

	@Override
	public Pagination<Position> find() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

package com.lyplay.sflow.dao;

import com.lyplay.sflow.orm.IBaseDAO;
import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.po.Position;

/**
 * 
 * @author lyplay
 *
 */

public interface IPositionDAO extends IBaseDAO<Position>{

	public Pagination<Position> find();
	
}

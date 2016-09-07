package com.lyplay.sflow.dao;

import java.util.List;

import com.lyplay.sflow.orm.IBaseDAO;
import com.lyplay.sflow.po.OrgType;

/**
 * 
 * @author lyplay
 *
 */

public interface IOrgTypeDAO extends IBaseDAO<OrgType>{
	
	public List<OrgType> list();
	
	
	
}

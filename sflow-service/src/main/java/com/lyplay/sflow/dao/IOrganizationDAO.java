package com.lyplay.sflow.dao;

import java.util.List;

import com.lyplay.sflow.dto.TreeDto;
import com.lyplay.sflow.orm.IBaseDAO;
import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.po.org.Organization;


/**
 * 
 * @author lyplay
 *
 */

public interface IOrganizationDAO extends IBaseDAO<Organization>{

	public Pagination<Organization> findByParent(Integer pid);
	
	/**
	 * get all the organization via typeId, if typeId is null , get all the org
	 * @param typeId
	 * @return
	 */
	public List<TreeDto> tree(Integer typeId);
	
	/**
	 * 
	 * @param pid
	 * @param type
	 * @return
	 */
	public int getNumByType(Integer pid, String type);
	
}

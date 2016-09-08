package com.lyplay.sflow.dao;

import java.util.List;

import com.lyplay.sflow.orm.IBaseDAO;
import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.po.User;


public interface IUserDAO extends IBaseDAO<User>{

	public Pagination<User> findByOrg(int oid, Integer posId);
	
	public List<Integer> listAllOrgIdByUser(int uid);
	
}

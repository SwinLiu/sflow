package com.lyplay.sflow.orm;

import java.util.List;

import com.lyplay.sflow.orm.components.Pagination;

public interface IBaseDao{
	
	public <T> List<T> list(String sql, Object[] params, Class<T> clazz);
	
	public <T> Pagination pageList(String sql, Object[] params, Class<T> clazz, Integer currentPage, Integer numPerPage);
	
	public int count(String sql, Object[] params);
	
	public <T> T load(Object po) throws Exception;
	
	public void update(Object po) throws Exception;
	
	public void batchUpdate(String sql,List<Object[]> paramsList);
	
	public void save(Object po) throws Exception;
	
	public void delete(Object po) throws Exception;
	
	
}

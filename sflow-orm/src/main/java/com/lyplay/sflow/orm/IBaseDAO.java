package com.lyplay.sflow.orm;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.lyplay.sflow.orm.components.Pagination;

public interface IBaseDAO{
	
	public <T> Pagination pageList(String sql, Object[] params, Class<T> clazz, Integer currentPage, Integer numPerPage);
	
	public boolean update(Object po);
	
	public boolean batchUpdate(String sql,List<Object[]> paramsList);
	
	public boolean save(Object po);
	
	public boolean saveOrUpdate(Object po);
	
	public boolean delete(Object po);
	
	public JdbcTemplate getJdbcTemplate();
	
}

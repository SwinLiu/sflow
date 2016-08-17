package com.lyplay.sflow.orm;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.lyplay.sflow.orm.components.Pagination;

public interface IBaseDAO{
	
	public <T> Pagination pageList(String sql, Object[] params, Class<T> clazz, Integer currentPage, Integer numPerPage);
	
	public void update(Object po);
	
	public void batchUpdate(String sql,List<Object[]> paramsList);
	
	public void save(Object po);
	
	public void saveOrUpdate(Object po);
	
	public void delete(Object po);
	
	public JdbcTemplate getJdbcTemplate();
	
}

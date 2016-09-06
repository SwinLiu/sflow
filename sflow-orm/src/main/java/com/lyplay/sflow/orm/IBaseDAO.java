package com.lyplay.sflow.orm;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.lyplay.sflow.orm.components.Pagination;

public interface IBaseDAO<T>{
	
	public Pagination<T> pageList(String sql, Object[] params, Class<T> clazz, Integer currentPage, Integer numPerPage);
	
	public boolean update(T po);
	
	public boolean batchUpdate(String sql,List<Object[]> paramsList);
	
	public boolean save(T po);
	
	public boolean saveOrUpdate(T po);
	
	public boolean delete(T po);
	
	public JdbcTemplate getJdbcTemplate();
	
}

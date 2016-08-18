package com.lyplay.sflow.orm;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.lyplay.sflow.orm.components.Pagination;

public class BaseDAO implements IBaseDAO{

	@Resource(name="jdbcBaseDao")
	private IBaseDAO baseDAO;
	
	
	@Override
	public <T> Pagination pageList(String sql, Object[] params, Class<T> clazz,
			Integer currentPage, Integer numPerPage) {
		return baseDAO.pageList(sql, params, clazz, currentPage, numPerPage);
	}

	@Override
	public boolean update(Object po) {
		return baseDAO.update(po);
	}

	@Override
	public boolean batchUpdate(String sql, List<Object[]> paramsList) {
		return baseDAO.batchUpdate(sql, paramsList);
	}

	@Override
	public boolean save(Object po) {
		return baseDAO.save(po);
	}

	@Override
	public boolean delete(Object po) {
		return baseDAO.delete(po);
	}

	@Override
	public boolean saveOrUpdate(Object po) {
		return baseDAO.saveOrUpdate(po);
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return baseDAO.getJdbcTemplate();
	}

}

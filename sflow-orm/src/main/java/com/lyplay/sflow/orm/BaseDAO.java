package com.lyplay.sflow.orm;

import java.util.List;

import javax.annotation.Resource;

import com.lyplay.sflow.orm.components.Pagination;

public class BaseDAO implements IBaseDAO{

	@Resource(name="jdbcBaseDao")
	private IBaseDAO baseDAO;
	
	
	@Override
	public <T> List<T> list(String sql, Object[] params, Class<T> clazz) {
		return baseDAO.list(sql, params, clazz);
	}

	@Override
	public <T> Pagination pageList(String sql, Object[] params, Class<T> clazz,
			Integer currentPage, Integer numPerPage) {
		return baseDAO.pageList(sql, params, clazz, currentPage, numPerPage);
	}

	@Override
	public int count(String sql, Object[] params) {
		return baseDAO.count(sql, params);
	}

	@Override
	public <T> T load(Object po) {
		return baseDAO.load(po);
	}

	@Override
	public void update(Object po) {
		baseDAO.update(po);
	}

	@Override
	public void batchUpdate(String sql, List<Object[]> paramsList) {
		baseDAO.batchUpdate(sql, paramsList);
	}

	@Override
	public void save(Object po) {
		baseDAO.save(po);
	}

	@Override
	public void delete(Object po) {
		baseDAO.delete(po);
	}

}

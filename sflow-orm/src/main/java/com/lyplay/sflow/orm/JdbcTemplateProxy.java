package com.lyplay.sflow.orm;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.orm.components.ReturnIdPreparedStatementCreator;
import com.lyplay.sflow.orm.utils.CatalogUtils;
import com.lyplay.sflow.orm.utils.PagingUtils;

public class JdbcTemplateProxy {
	
	private JdbcTemplate jdbcTemplate;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//------------- select -----------------//
	
	public <T> List<T> query(String sql, RowMapper<T> rowMapper) throws DataAccessException{
		//dynamic change catalog name
		sql = CatalogUtils.changeCatalog(sql);
		try{
			return jdbcTemplate.query(sql, rowMapper);
		}catch(DataAccessException e){
			logger.error("Error SQL: " + sql);
			throw e;
		}
	}
	
	public <T> List<T> query(String sql, Object[] params, RowMapper<T> rowMapper) throws DataAccessException{
		//dynamic change catalog name
		sql = CatalogUtils.changeCatalog(sql);
		try{
			return jdbcTemplate.query(sql, params, rowMapper);
		}catch(DataAccessException e){
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for(Object p:params){
				sb.append(p + " | ");
			}
			sb.append("]");
			logger.error("Error SQL: " + sql + " Params: " + sb.toString());
			throw e;
		}
	}
	
	public Map<String, Object> queryForMap(String sql) throws DataAccessException {
		//dynamic change catalog name
		sql = CatalogUtils.changeCatalog(sql);
		try{
			return jdbcTemplate.queryForMap(sql);
		}catch(DataAccessException e){
			logger.error("Error SQL: " + sql);
			throw e;
		}
	}
	
	public Map<String, Object> queryForMap(String sql, Object[] params) throws DataAccessException {
		//dynamic change catalog name
		sql = CatalogUtils.changeCatalog(sql);
		try{
			return jdbcTemplate.queryForMap(sql, params);
		}catch(DataAccessException e){
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for(Object p:params){
				sb.append(p + " | ");
			}
			sb.append("]");
			logger.error("Error SQL: " + sql + " Params: " + sb.toString());
			throw e;
		}
	}
	
	public <T> Pagination queryForPage(String sql, Object[] params, RowMapper<T> rowMapper, Integer currentPage, Integer numPerPage) throws DataAccessException{
		//dynamic change catalog name
		sql = CatalogUtils.changeCatalog(sql);
		
		Pagination page = PagingUtils.getPagination(currentPage,numPerPage);
		
		try{
			
			StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM ( ");
			totalSQL.append(sql);
			totalSQL.append(" ) totalTable ");
			page.setTotalRows(jdbcTemplate.queryForObject(totalSQL.toString(), params,Integer.class));
			
			page.setTotalPages();
			page.setStartIndex();
			page.setLastIndex();
			
			if (page.getCurrentPage() <= 1) {
				sql = PagingUtils.getPagingQuery(sql, false);
				params = ArrayUtils.add(params, params.length, page.getLastIndex());
			} else {
				sql = PagingUtils.getPagingQuery(sql, true);
				params = ArrayUtils.add(params, params.length, page.getLastIndex());
				params = ArrayUtils.add(params, params.length, page.getStartIndex());
			}
			
			page.setResultList(jdbcTemplate.query(sql, params, rowMapper));
			
			return page;
			
		}catch(DataAccessException e){
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for(Object p:params){
				sb.append(p + " | ");
			}
			sb.append("]");
			logger.error("Error SQL: " + sql + " Params: " + sb.toString());
			throw e;
		}
	}
	
	
	
	//------------ update ------------------//
	public int update(String sql, Object[] params) throws DataAccessException{
		//dynamic change catalog name
		sql = CatalogUtils.changeCatalog(sql);
		try{
			return jdbcTemplate.update(sql, params);
		}catch(DataAccessException e){
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for(Object p:params){
				sb.append(p + " | ");
			}
			sb.append("]");
			logger.error("Error SQL: " + sql + " Params: " + sb.toString());
			throw e;
		}	
	}
	
	public int[] batchUpdate(String sql, final BatchPreparedStatementSetter pss) throws DataAccessException{
		//dynamic change catalog name
		sql = CatalogUtils.changeCatalog(sql);
		try{
			return jdbcTemplate.batchUpdate(sql, pss);
		}catch(DataAccessException e){
			logger.error("Error SQL: " + sql);
			throw e;
		}
	}
	
	public int insert(String sql,Object[] params,String autoGeneratedColumnName)
			throws DataAccessException{
		//dynamic change catalog name
		sql = CatalogUtils.changeCatalog(sql);
		
		ReturnIdPreparedStatementCreator psc = new ReturnIdPreparedStatementCreator(sql, params, autoGeneratedColumnName);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try{
			jdbcTemplate.update(psc, keyHolder);
		}catch(DataAccessException e){
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for(Object p:params){
				sb.append(p + " | ");
			}
			sb.append("]");
			logger.error("Error SQL: " + sql + " Params: " + sb.toString());
			throw e;
		}
		
		return keyHolder.getKey().intValue();
	}
	
}

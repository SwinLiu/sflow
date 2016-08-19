package com.lyplay.sflow.orm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lyplay.sflow.orm.components.BatchUpdateSetter;
import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.orm.components.ReturnIdPreparedStatementCreator;
import com.lyplay.sflow.orm.components.SqlParamsPairs;
import com.lyplay.sflow.orm.utils.CatalogUtils;
import com.lyplay.sflow.orm.utils.CommonRowMapper;
import com.lyplay.sflow.orm.utils.IdUtils;
import com.lyplay.sflow.orm.utils.ModelSqlUtils;
import com.lyplay.sflow.orm.utils.PagingUtils;
import com.lyplay.sflow.orm.utils.PrintUtil;

@Repository("jdbcBaseDao")
public class JdbcBaseDAO implements IBaseDAO{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public <T> Pagination pageList(String sql, Object[] params, Class<T> clazz,
			Integer currentPage, Integer numPerPage) {
		
		//dynamic change catalog name
		sql = CatalogUtils.changeCatalog(sql);
		
		Pagination page = PagingUtils.getPagination(currentPage,numPerPage);
		
		try{
			
			StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM ( ");
			totalSQL.append(sql);
			totalSQL.append(" ) totalTable ");
			printErrSql(totalSQL.toString(), params);
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
			
			printErrSql(sql, params);
			page.setResultList(jdbcTemplate.query(sql, params, new CommonRowMapper<>(clazz)));
			
			return page;
			
		}catch(DataAccessException e){
			printErrSql(sql,params);
			throw e;
		}
		
	}
	
	
	@Override
	public boolean update(Object po) {
		SqlParamsPairs sqlAndParams = null;
		try {
			sqlAndParams = ModelSqlUtils.getUpdateFromObject(po);
		} catch (Exception e) {
			logger.error(" Po class get update sql failed : {} ", po.getClass().getName());
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		printDebugSql(sqlAndParams);
		return update(sqlAndParams);
		
	}
	
	@Override
	public boolean batchUpdate(String sql,List<Object[]> paramsList){
		
		BatchUpdateSetter batchUpdateSetter = new BatchUpdateSetter(paramsList);
		
		sql = CatalogUtils.changeCatalog(sql);
		try{
			printDebugSql(sql,paramsList);
			jdbcTemplate.batchUpdate(sql, batchUpdateSetter);
			return true;
		}catch(DataAccessException e){
			printErrSql(sql,paramsList);
			throw e;
		}
		
	}
	
	
	@Override
	public boolean save(Object po) {
		try {
			String autoGeneratedColumnName = IdUtils.getAutoGeneratedId(po);
			if(!"".equals(autoGeneratedColumnName)){
				
				int idValue = save(po, autoGeneratedColumnName);
				
				IdUtils.setAutoIncreamentIdValue(po,autoGeneratedColumnName,idValue);
				
				return true;
			}else{
				SqlParamsPairs sqlAndParams = ModelSqlUtils.getInsertFromObject(po);
				printDebugSql(sqlAndParams);
				return update(sqlAndParams);
			}
		} catch (Exception e) {
			logger.error(" save po object failed : {} ", po.getClass().getName());
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}		
	}
	
	
	@Override
	public boolean delete(Object po) {
		
		SqlParamsPairs sqlAndParams = null;
		try {
			sqlAndParams = ModelSqlUtils.getDeleteFromObject(po);
		} catch (Exception e) {
			logger.error(" delete po object failed : {} ", po.getClass().getName());
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		printDebugSql(sqlAndParams);
		return update(sqlAndParams);	
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public boolean saveOrUpdate(Object po) {
		// TODO Auto-generated method stub
		return false;
	}

	
	private boolean update(SqlParamsPairs sqlAndParams){
		//dynamic change catalog name
		sqlAndParams.setSql(CatalogUtils.changeCatalog(sqlAndParams.getSql()));
		try{
			printDebugSql(sqlAndParams);
			return jdbcTemplate.update(sqlAndParams.getSql(), sqlAndParams.getParams()) > 0;
		}catch(DataAccessException e){
			printErrSql(sqlAndParams);
			throw e;
		}
	}

	private int save(Object po, String autoGeneratedColumnName) throws Exception {

		SqlParamsPairs sqlAndParams = ModelSqlUtils.getInsertFromObject(po);

		return insert(sqlAndParams, autoGeneratedColumnName);
	}
	
	public int insert(SqlParamsPairs sqlAndParams,String autoGeneratedColumnName)
			throws DataAccessException{
		//dynamic change catalog name
		ReturnIdPreparedStatementCreator psc = new ReturnIdPreparedStatementCreator(sqlAndParams.getSql(), sqlAndParams.getParams(), autoGeneratedColumnName);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try{
			printDebugSql(sqlAndParams);
			jdbcTemplate.update(psc, keyHolder);
		}catch(DataAccessException e){
			printErrSql(sqlAndParams);
			throw e;
		}
		
		return keyHolder.getKey().intValue();
	}
	
	private void printErrSql(String sql, Object[] params){
		logger.error("Error SQL: " + sql + " Params: " + PrintUtil.printArray(params));
	}
	
	private void printErrSql(SqlParamsPairs sqlParamsPairs){
		printErrSql(sqlParamsPairs.getSql(), sqlParamsPairs.getParams());
	}
	
	private void printErrSql(String sql, List<Object[]> params){
		logger.error("Error SQL: " + sql + " Params: " + PrintUtil.printListArray(params));
	}
	
	private void printDebugSql(String sql, Object[] params){
		logger.debug("Run SQL: " + sql + " Params: " + PrintUtil.printArray(params));
	}
	
	private void printDebugSql(SqlParamsPairs sqlParamsPairs){
		printDebugSql(sqlParamsPairs.getSql(), sqlParamsPairs.getParams());
	}
	
	private void printDebugSql(String sql, List<Object[]> params){
		logger.error("Run SQL: " + sql + " Params: " + PrintUtil.printListArray(params));
	}
	
	
	
	
	
}

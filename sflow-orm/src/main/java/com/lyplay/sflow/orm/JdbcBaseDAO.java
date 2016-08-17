package com.lyplay.sflow.orm;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lyplay.sflow.orm.components.BatchUpdateSetter;
import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.orm.components.SqlParamsPairs;
import com.lyplay.sflow.orm.exception.NoColumnAnnotationFoundException;
import com.lyplay.sflow.orm.exception.NoDataFoundException;
import com.lyplay.sflow.orm.exception.NoIdAnnotationFoundException;
import com.lyplay.sflow.orm.utils.IdUtils;
import com.lyplay.sflow.orm.utils.ModelSqlUtils;

@Repository("jdbcBaseDao")
public class JdbcBaseDAO implements IBaseDAO{
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	
	//JdbcTemplateTool use proxy instead of directly use jdbcTemplate, cause it can do some AOP function in proxy. That makes code more clear.
	private JdbcTemplateProxy _proxy;
	
	//return the singleton proxy
	private JdbcTemplateProxy getProxy(){
		if(_proxy == null){
			_proxy = new JdbcTemplateProxy();
			_proxy.setJdbcTemplate(jdbcTemplate);
		}
		return _proxy;
	}
	
	
	// --------- select ------------//
	
	/**
	 * get a list of clazz
	 * @param sql
	 * @param params
	 * @param clazz
	 * @return
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> list(String sql, Object[] params, Class<T> clazz) {
		
		//call jdbcTemplate to query for result
		List<T> list = null;
		if (params == null || params.length == 0) {
			list = getProxy().query(sql, new BeanPropertyRowMapper(clazz));
		} else {
			list = getProxy().query(sql, params, new BeanPropertyRowMapper(clazz));
		}
		
		//return list
		return list;
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> Pagination pageList(String sql, Object[] params, Class<T> clazz,
			Integer currentPage, Integer numPerPage) {
		return getProxy().queryForPage(sql, params, new BeanPropertyRowMapper(clazz), currentPage, numPerPage);
	}
	
	
	/**
	 * get count
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int count(String sql, Object[] params) {
		
		int rowCount = 0;
		try{
			Map<String, Object> resultMap = null;
			if (params == null || params.length == 0) {
				resultMap = getProxy().queryForMap(sql);
			} else {
				resultMap = getProxy().queryForMap(sql, params);
			}
			Iterator<Map.Entry<String, Object>> it = resultMap.entrySet().iterator();
			if(it.hasNext()){
				Map.Entry<String, Object> entry = it.next();
				rowCount = ((BigDecimal)entry.getValue()).intValue();
			}
		}catch(EmptyResultDataAccessException e){
			
		}
		
		return rowCount;
	}
	
	/**
	 * get object by id
	 * @param clazz
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T load(Object po) throws Exception {
		
		//turn class to sql
		SqlParamsPairs sqlAndParams = null;
		try {
			sqlAndParams = ModelSqlUtils.getLoadFromObject(po);
		} catch (Exception e) {
			logger.error(" Po class get load sql failed : {} ", po.getClass().getName());
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		
		//query for list
		List list = this.list(sqlAndParams.getSql(), sqlAndParams.getParams(), po.getClass());
		if (list.size() > 0) {
			return (T) list.get(0);
		}else{
			throw new NoDataFoundException(po.getClass());
		}
	}
	
	
	/**
	 * get object by id
	 * @param clazz
	 * @param id
	 * @return
	 * @throws NoIdAnnotationFoundException
	 * @throws NoColumnAnnotationFoundException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T get(Class clazz, Object id) throws NoIdAnnotationFoundException, NoColumnAnnotationFoundException {
		
		//turn class to sql
		SqlParamsPairs sqlAndParams;
		try {
			sqlAndParams = ModelSqlUtils.getGetFromObject(clazz, id);
		} catch (Exception e) {
			logger.error(" get sql of po class by id failed : {} ", clazz.getName());
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		
		//query for list
		List<T> list = this.list(sqlAndParams.getSql(), sqlAndParams.getParams(), clazz);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	
	
	// --------- update ------------//
	
	@Override
	public void update(Object po) throws Exception {
		SqlParamsPairs sqlAndParams = null;
		try {
			sqlAndParams = ModelSqlUtils.getUpdateFromObject(po);
		} catch (Exception e) {
			logger.error(" Po class get update sql failed : {} ", po.getClass().getName());
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		
		getProxy().update(sqlAndParams.getSql(), sqlAndParams.getParams());
	}
	
	@Override
	public void batchUpdate(String sql,List<Object[]> paramsList){
		
		BatchUpdateSetter batchUpdateSetter = new BatchUpdateSetter(paramsList);
		
		getProxy().batchUpdate(sql, batchUpdateSetter);
	}
	
	
	// --------- insert ------------//
	@Override
	public void save(Object po) throws Exception {
		try {
			String autoGeneratedColumnName = IdUtils.getAutoGeneratedId(po);
			if(!"".equals(autoGeneratedColumnName)){
				
				int idValue = save(po, autoGeneratedColumnName);
				
				IdUtils.setAutoIncreamentIdValue(po,autoGeneratedColumnName,idValue);
			}else{
				SqlParamsPairs sqlAndParams = ModelSqlUtils.getInsertFromObject(po);
				
				getProxy().update(sqlAndParams.getSql(), sqlAndParams.getParams());
			}
		} catch (Exception e) {
			logger.error(" save po object failed : {} ", po.getClass().getName());
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}		
	}
	
	
	private int save(Object po, String autoGeneratedColumnName) throws Exception {

		SqlParamsPairs sqlAndParams = ModelSqlUtils.getInsertFromObject(po);

		String sql = sqlAndParams.getSql();
		
		return getProxy().insert(sql, sqlAndParams.getParams(), autoGeneratedColumnName);
	}
	
	
	// --------- delete ------------//
	@Override
	public void delete(Object po) throws Exception{
		
		SqlParamsPairs sqlAndParams = null;
		try {
			sqlAndParams = ModelSqlUtils.getDeleteFromObject(po);
		} catch (Exception e) {
			logger.error(" delete po object failed : {} ", po.getClass().getName());
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		
		String sql = sqlAndParams.getSql();
		
		getProxy().update(sql, sqlAndParams.getParams());	
	}


	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	
	
	
	
	
	
	
	
	
	
}

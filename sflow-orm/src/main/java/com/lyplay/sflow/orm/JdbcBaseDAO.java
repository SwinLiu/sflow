package com.lyplay.sflow.orm;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lyplay.sflow.orm.components.BatchUpdateSetter;
import com.lyplay.sflow.orm.components.Pagination;
import com.lyplay.sflow.orm.components.SqlParamsPairs;
import com.lyplay.sflow.orm.utils.IdUtils;
import com.lyplay.sflow.orm.utils.ModelSqlUtils;

@Repository("jdbcBaseDao")
public class JdbcBaseDAO implements IBaseDAO{
	
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
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> Pagination pageList(String sql, Object[] params, Class<T> clazz,
			Integer currentPage, Integer numPerPage) {
		return getProxy().queryForPage(sql, params, new BeanPropertyRowMapper(clazz), currentPage, numPerPage);
	}
	
	
	@Override
	public void update(Object po) {
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
	
	
	@Override
	public void save(Object po) {
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
	
	
	@Override
	public void delete(Object po) {
		
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

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public void saveOrUpdate(Object po) {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
	
	
	
	
	
	
}

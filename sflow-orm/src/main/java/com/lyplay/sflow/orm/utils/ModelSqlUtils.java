package com.lyplay.sflow.orm.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyplay.sflow.orm.components.SqlParamsPairs;
import com.lyplay.sflow.orm.exception.NoColumnAnnotationFoundException;
import com.lyplay.sflow.orm.exception.NoIdAnnotationFoundException;

public class ModelSqlUtils {

	private static Logger logger = LoggerFactory.getLogger(ModelSqlUtils.class);
	
	
	public static <T> SqlParamsPairs getInsertFromObject(T po) throws Exception{
		
		
		StringBuffer insertSql = new StringBuffer();
		
		StringBuffer paramsSql = new StringBuffer();
		
		List<Object> params = new ArrayList<Object>();
		
		String tableName = getTableName(po.getClass());
		
		insertSql.append("insert into " + tableName + " (");
		
		int count=0;
		
		Field[] fields = po.getClass().getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			
			if("serialVersionUID".equals(f.getName())){
				continue;
			}
			
			Method getter = getGetter(po.getClass(), f);
			
			if(getter == null){
				continue;
			}
			
			Object value = getter.invoke(po);
			if(value == null){
				continue;
			}
			
			Transient tranAnno = getter.getAnnotation(Transient.class);
			if(tranAnno != null){
				continue;
			}
			
			String columnName = getColumnNameFromGetter(getter, f);
			
			if(count!=0){
				insertSql.append(",");
			}
			insertSql.append(columnName);
			
			if(count!=0){
				paramsSql.append(",");
			}
			paramsSql.append("?");
			
			params.add(value);
			count++;
		}
		
		insertSql.append(") values (");
		insertSql.append(paramsSql + ")");
		
		SqlParamsPairs sqlAndParams = new SqlParamsPairs(insertSql.toString(), params.toArray());
		logger.debug(sqlAndParams.toString());
		
		return sqlAndParams;
		
	}

	
	private static <T> Method getGetter(Class<T> clazz, Field f){
		String getterName = "get" + ColnumNameUtils.capitalize(f.getName());
		Method getter = null;
		try {
			getter = clazz.getMethod(getterName);
		} catch (Exception e) {
			logger.debug(getterName + " doesn't exist!",e);
		}
		return getter;
	}



	private static <T> String getTableName(Class<T> clazz) {
		
		Table tableAnno = clazz.getAnnotation(Table.class);
		if(tableAnno != null){
			if(tableAnno.catalog() != null){
				return tableAnno.catalog() + "." + tableAnno.name();
			}
			return tableAnno.name();
		}
		//if Table annotation is null
		String className = clazz.getName();
		// 1. ex: className : DemoTest  -->  tableName : demo_test 
		//return ColnumNameUtils.camel2underscore(className.substring(className.lastIndexOf(".")+1));
		// 2. ex: className : DemoTest  -->  tableName : demotest 
		return className.substring(className.lastIndexOf(".")+1).toLowerCase();
	}
	
	
	
	public static SqlParamsPairs getUpdateFromObject(Object po) throws Exception{
		
		StringBuffer updateSql = new StringBuffer();
		
		StringBuffer whereSql = new StringBuffer();
		
		List<Object> params = new ArrayList<Object>();
		List<Object> id_params = new ArrayList<Object>();
		
		String tableName = getTableName(po.getClass());
		
		updateSql.append(" update " + tableName + " set ");
		
		Field[] fields = po.getClass().getDeclaredFields();
		
		int count = 0;
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			
			Method getter = getGetter(po.getClass(),f);
			
			if(getter == null){
				continue;
			}
			
			Object value = getter.invoke(po);
			if(value == null){
				continue;
			}
			
			Transient tranAnno = getter.getAnnotation(Transient.class);
			if(tranAnno != null){
				continue;
			}
			
			String columnName = getColumnNameFromGetter(getter,f);
			
			Id idAnno = getter.getAnnotation(Id.class);
			if(idAnno != null){
				if(whereSql.length() > 0){
					whereSql.append("and");
				}
				whereSql.append(" " + columnName + " = ? ");
				id_params.add(value);
				continue;
			}
			
			
			params.add(value);
			
			if(count!=0){
				updateSql.append(",");
			}
			updateSql.append(" " + columnName + " = ? ");
			
			count++;
		}
		
		updateSql.append(" where ");
		updateSql.append(whereSql);
		params.addAll(id_params);
		
		SqlParamsPairs sqlAndParams = new SqlParamsPairs(updateSql.toString(),params.toArray());
		logger.debug(sqlAndParams.toString());
		
		return sqlAndParams;
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static SqlParamsPairs getDeleteFromObject(Object po) throws Exception{
		
		
		StringBuffer deleteSql = new StringBuffer();
		StringBuffer whereSql = new StringBuffer();
		
		List<Object> params = new ArrayList<Object>();
		
		String tableName = getTableName(po.getClass());
		
		deleteSql.append("delete from " + tableName + " where ");
		
		Class clazz = po.getClass();
		
		Field[] fields = clazz.getDeclaredFields();
		
		Id idAnno = null;
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			
			Method getter = getGetter(clazz,f);
			
			if(getter == null){
				continue;
			}
			
			idAnno = getter.getAnnotation(Id.class);
			if(idAnno == null){
				continue;
			}
			
			String columnName = getColumnNameFromGetter(getter,f);
			
			if(whereSql.length() > 0){
				whereSql.append("and");
			}
			whereSql.append(" " + columnName + " = ? ");
			
			params.add(getter.invoke(po, new Object[]{}));
			
		}
		
		if(whereSql.length() == 0){
			throw new NoIdAnnotationFoundException(clazz);
		}else{
			deleteSql.append(whereSql);
		}
		
		SqlParamsPairs sqlAndParams = new SqlParamsPairs(deleteSql.toString(),params.toArray());
		logger.debug(sqlAndParams.toString());
		
		return sqlAndParams;
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> SqlParamsPairs getLoadFromObject(Object po) throws Exception{
		
		Class clazz = po.getClass();
		
		StringBuffer loadSql = new StringBuffer();
		StringBuffer whereSql = new StringBuffer();
		
		String tableName = getTableName(clazz);
		
		loadSql.append("select * from " + tableName + " where ");
		
		List<Object> params = new ArrayList<Object>();
		
		Field[] fields = clazz.getDeclaredFields();
		
		Id idAnno = null;
		
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			
			Method getter = getGetter(clazz,f);
			
			if(getter == null){
				continue;
			}
			
			idAnno = getter.getAnnotation(Id.class);
			if(idAnno == null){
				continue;
			}
			
			//get column name
			String columnName = getColumnNameFromGetter(getter,f);
			
			if(whereSql.length() > 0){
				whereSql.append("and");
			}
			whereSql.append(" " + columnName + " = ? ");
			
			params.add(getter.invoke(po, new Object[]{}));
			
		}
		
		if(whereSql.length() == 0){
			throw new NoIdAnnotationFoundException(clazz);
		}else{
			loadSql.append(whereSql);
		}
		
		SqlParamsPairs sqlAndParams = new SqlParamsPairs(loadSql.toString(),params.toArray());
		logger.debug(sqlAndParams.toString());
		
		return sqlAndParams;
	}
	
	
	public static <T> SqlParamsPairs getGetFromObject(Class<T> clazz,Object id) throws NoIdAnnotationFoundException, NoColumnAnnotationFoundException{
		
		StringBuffer getSql = new StringBuffer();
		
		String tableName = getTableName(clazz);
		
		getSql.append("select * from " + tableName + " where ");
		
		Field[] fields = clazz.getDeclaredFields();
		
		Id idAnno = null;
		
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			
			Method getter = getGetter(clazz,f);
			
			if(getter == null){
				continue;
			}
			
			idAnno = getter.getAnnotation(Id.class);
			if(idAnno == null){
				continue;
			}
			
			//get column name
			String columnName = getColumnNameFromGetter(getter,f);

			getSql.append(columnName + " = ?");
			
			break;
		}
		
		if(idAnno == null){
			throw new NoIdAnnotationFoundException(clazz);
		}
		
		SqlParamsPairs sqlAndParams = new SqlParamsPairs(getSql.toString(),new Object[]{id});
		logger.debug(sqlAndParams.toString());
		
		return sqlAndParams;
	}


	/**
	 * use getter to guess column name, if there is annotation then use annotation value, if not then guess from field name
	 * @param getter
	 * @param clazz
	 * @param f
	 * @return
	 * @throws NoColumnAnnotationFoundException
	 */
	private static String getColumnNameFromGetter(Method getter,Field f){
		String columnName = "";
		Column columnAnno = getter.getAnnotation(Column.class);
		if(columnAnno != null){
			columnName = columnAnno.name();
		}
		
		if(columnName == null || "".equals(columnName)){
			// 1. ex : name : testName -> test_name
			columnName = ColnumNameUtils.camel2underscore(f.getName());
			// 2. ex : name : testName -> testname
			//columnName = f.getName().toLowerCase();
		}
		return columnName;
	}
}

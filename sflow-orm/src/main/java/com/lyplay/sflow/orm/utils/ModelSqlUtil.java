package com.lyplay.sflow.orm.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Transient;

import com.lyplay.sflow.orm.components.SqlParamsPairs;
import com.lyplay.sflow.orm.exception.NoIdAnnotationFoundException;
import com.lyplay.sflow.orm.exception.NoIdValueFoundException;

public class ModelSqlUtil {

	public static <T> SqlParamsPairs getInsertFromObject(T po) throws Exception{
		
		
		StringBuffer insertSql = new StringBuffer();
		
		StringBuffer paramsSql = new StringBuffer();
		
		List<Object> params = new ArrayList<Object>();
		
		String tableName = PoUtil.getTableName(po.getClass());
		
		insertSql.append("insert into " + tableName + " (");
		
		int count=0;
		
		Field[] fields = po.getClass().getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			
			if("serialVersionUID".equals(f.getName())){
				continue;
			}
			
			Method getter = PoUtil.getGetter(po.getClass(), f);
			
			if(getter == null){
				continue;
			}
			
			Object value = getter.invoke(po);
			if(value == null){
				continue;
			}
			
			Transient tranAnno = f.getAnnotation(Transient.class);
			if(tranAnno != null){
				continue;
			}
			
			String columnName = PoUtil.getColumnName(f);
			
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
		
		return sqlAndParams;
		
	}

	
	public static SqlParamsPairs getUpdateFromObject(Object po) throws Exception{
		
		StringBuffer updateSql = new StringBuffer();
		
		StringBuffer whereSql = new StringBuffer();
		
		List<Object> params = new ArrayList<Object>();
		List<Object> id_params = new ArrayList<Object>();
		
		String tableName = PoUtil.getTableName(po.getClass());
		
		updateSql.append(" update " + tableName + " set ");
		
		Field[] fields = po.getClass().getDeclaredFields();
		
		int count = 0;
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			
			if("serialVersionUID".equals(f.getName())){
				continue;
			}
			
			Method getter = PoUtil.getGetter(po.getClass(),f);
			
			if(getter == null){
				continue;
			}
			
			Transient tranAnno = f.getAnnotation(Transient.class);
			if(tranAnno != null){
				continue;
			}
			
			String columnName = PoUtil.getColumnName(f);
			Object value = getter.invoke(po);
			
			Id idAnno = f.getAnnotation(Id.class);
			if(idAnno != null){
				if(value == null){
					throw new NoIdValueFoundException(po.getClass());
				}
				if(whereSql.length() > 0){
					whereSql.append("and");
				}
				whereSql.append(" " + columnName + " = ? ");
				id_params.add(value);
				continue;
			}else{
				
				params.add(value);
				
				if(count!=0){
					updateSql.append(",");
				}
				updateSql.append(" " + columnName + " = ? ");
				
				count++;
				
			}
			
			
		}
		
		updateSql.append(" where ");
		if(whereSql.length() > 0){
			updateSql.append(whereSql);
		}else{
			throw new NoIdAnnotationFoundException(po.getClass());
		}
		params.addAll(id_params);
		
		SqlParamsPairs sqlAndParams = new SqlParamsPairs(updateSql.toString(),params.toArray());
		
		return sqlAndParams;
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static SqlParamsPairs getDeleteFromObject(Object po) throws Exception{
		
		
		StringBuffer deleteSql = new StringBuffer();
		StringBuffer whereSql = new StringBuffer();
		
		List<Object> params = new ArrayList<Object>();
		
		String tableName = PoUtil.getTableName(po.getClass());
		
		deleteSql.append("delete from " + tableName + " where ");
		
		Class clazz = po.getClass();
		
		Field[] fields = clazz.getDeclaredFields();
		
		Id idAnno = null;
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			
			if("serialVersionUID".equals(f.getName())){
				continue;
			}
			
			Method getter = PoUtil.getGetter(clazz,f);
			
			if(getter == null){
				continue;
			}
			
			idAnno = f.getAnnotation(Id.class);
			if(idAnno == null){
				continue;
			}
			
			String columnName = PoUtil.getColumnName(f);
			
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
		
		return sqlAndParams;
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> SqlParamsPairs getLoadFromObject(Object po) throws Exception{
		
		Class clazz = po.getClass();
		
		StringBuffer loadSql = new StringBuffer();
		StringBuffer selectSql = new StringBuffer();
		StringBuffer whereSql = new StringBuffer();
		
		List<Object> params = new ArrayList<Object>();
		
		Field[] fields = clazz.getDeclaredFields();
		
		int count = 0;
		
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			
			if("serialVersionUID".equals(f.getName())){
				continue;
			}
			
			Method getter = PoUtil.getGetter(clazz,f);
			
			if(getter == null){
				continue;
			}
			
			Transient tranAnno = f.getAnnotation(Transient.class);
			if(tranAnno != null){
				continue;
			}
			
			String columnName = PoUtil.getColumnName(f);
			
			if(count!=0){
				selectSql.append(",");
			}
			selectSql.append(columnName);
			count++;
			
			Id idAnno = f.getAnnotation(Id.class);
			if(idAnno == null){
				continue;
			}
			
			if(whereSql.length() > 0){
				whereSql.append("and");
			}
			whereSql.append(" " + columnName + " = ? ");
			
			params.add(getter.invoke(po, new Object[]{}));
			
		}
		
		if(whereSql.length() == 0){
			throw new NoIdAnnotationFoundException(clazz);
		}else if(params.size() == 0){
			throw new NoIdValueFoundException(clazz);
		}else{
			String tableName = PoUtil.getTableName(clazz);
			loadSql.append(String.format("select %s from %s where %s",selectSql.toString(), tableName, whereSql.toString()));
		}
		
		SqlParamsPairs sqlAndParams = new SqlParamsPairs(loadSql.toString(),params.toArray());
		
		return sqlAndParams;
	}
	
}

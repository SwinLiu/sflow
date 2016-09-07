package com.lyplay.sflow.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import com.lyplay.sflow.orm.components.SqlParamsPairs;
import com.lyplay.sflow.orm.utils.PoUtil;


public class PoDDLUtil {

	public <T> String generateDeleteSql(Class<T> clazz){
		String tableName = PoUtil.getTableName(clazz);
		return String.format("Drop table %s \n", tableName);
	}
	
	public <T> String generateCreateSql(Class<T> clazz){
		
		
		StringBuffer createSql = new StringBuffer();
		
		String tableName = PoUtil.getTableName(clazz);
		
		createSql.append(String.format("-- Create table %s \n", tableName));
		
		createSql.append("CREATE TABLE  " + tableName + " ( \n");
		
		Field[] fields = clazz.getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			
			Field f = fields[i];
			
			if("serialVersionUID".equals(f.getName())){
				continue;
			}
			
			Method getter = PoUtil.getGetter(clazz, f);
			
			if(getter == null){
				continue;
			}
			
			
			Transient tranAnno = f.getAnnotation(Transient.class);
			if(tranAnno != null){
				continue;
			}
			
			String columnName = PoUtil.getColumnNameFromGetter(f);
			
		}
		
		createSql.append(");\n");
	    generatePrimaryKey();
	    return createSql.toString();
	    
	}
	
	public void generatePrimaryKey(){
		
	}
	
	public void generateUnionKey(){
		
	}
	
}

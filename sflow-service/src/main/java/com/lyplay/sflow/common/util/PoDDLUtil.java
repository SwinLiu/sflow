package com.lyplay.sflow.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.lyplay.sflow.orm.utils.PoUtil;


public class PoDDLUtil {

	private static final String NEW_LINE = "\r\n";
	private static final String TABLE_SPACE = "\t";
	private static final int DEFAULT_LENGTH = 50;
	
	public static <T> String generateDeleteSql(String tableName){
		return String.format("Drop table  IF EXISTS %s ;%s", tableName, NEW_LINE);
	}
	
	public static <T> String generateCreateSql(Class<T> clazz){
		
		
		StringBuffer createSql = new StringBuffer();
		
		String tableName = PoUtil.getTableName(clazz);
		
		createSql.append(NEW_LINE);
		createSql.append(String.format("-- Delete table %s for PO %s ", tableName, PoUtil.getClassName(clazz))).append(NEW_LINE);
		
		createSql.append(generateDeleteSql(tableName));
		
		createSql.append(NEW_LINE);
		createSql.append(String.format("-- Create table %s for PO %s ", tableName, PoUtil.getClassName(clazz))).append(NEW_LINE);
		
		createSql.append(String.format("CREATE TABLE %s ( ", tableName));
		
		Field[] fields = clazz.getDeclaredFields();
		List<String> primaryKeys = new ArrayList<String>();
		List<String> uniqueKeys = new ArrayList<String>();
		int count = 0;
		for (int i = 0; i < fields.length; i++) {
			if(i!=0){
				createSql.append(NEW_LINE);
			}
			
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
			String columnName = PoUtil.getColumnName(f);
			
			createSql.append(TABLE_SPACE).append(columnName).append(TABLE_SPACE);
			
			createSql.append(convertJava2Mysql(f));
			
			if(!isNullable(f)){
				createSql.append(" NOT NULL");
			}
			
			if(isPrimarkkey(f)){
				primaryKeys.add(columnName);
			}
			
			if(isUniquekey(f)){
				uniqueKeys.add(columnName);
			}
			
			createSql.append(" ,");
			count++;
		}
		
		if(count > 0){
			createSql.deleteCharAt(createSql.length()-1);
		}
		createSql.append(NEW_LINE);
		
		createSql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;").append(NEW_LINE);
		createSql.append(generatePrimaryKey(tableName,primaryKeys));
		//createSql.append(generateUnionKey(tableName,uniqueKeys));
		createSql.append(NEW_LINE);
	    return createSql.toString();
	    
	}
	
	public static String generatePrimaryKey(String tableName, List<String> primaryKeys){
		StringBuffer primaryKeySql = new StringBuffer();
		if(CollectionUtils.isNotEmpty(primaryKeys)){
			primaryKeySql.append(NEW_LINE);
			primaryKeySql.append(String.format("-- Create Primary Key for Table %s ", tableName, tableName));
			primaryKeySql.append(NEW_LINE);
			primaryKeySql.append(String.format("ALTER TABLE %s ADD CONSTRAINT pk_%s PRIMARY KEY (%s); ",
					tableName, tableName, StringUtils.join(primaryKeys.toArray(),",")));
			primaryKeySql.append(NEW_LINE);
		}
		return primaryKeySql.toString();
	}
	
	public static String generateUnionKey(String tableName, List<String> uniqueKeys){
		StringBuffer uniqueKeySql = new StringBuffer();
		if(CollectionUtils.isNotEmpty(uniqueKeys)){
			uniqueKeySql.append(NEW_LINE);
			uniqueKeySql.append(String.format("-- Create Unique Key for Table %s ", tableName, tableName));
			
			int idx = 1;
			for(String columnName : uniqueKeys){
				uniqueKeySql.append(NEW_LINE);
				uniqueKeySql.append(String.format("ALTER TABLE %s ADD CONSTRAINT uk%s_%s UNIQUE (%s);",
						tableName, idx, tableName, columnName));
				idx++;
			}
			uniqueKeySql.append(NEW_LINE);
		}
		return uniqueKeySql.toString();
	}
	
	
	/**
	 * 
	 * 将java类型转换为Mysql类型
	 * 
	 * @param field
	 * @param col
	 * @return
	 */
	private static String convertJava2Mysql(Field field) {
		// TODO 此处可根据自己需要做类型转换
		String type = null;
		Column col = field.getAnnotation(Column.class);
		
		if (field.getType().equals(java.util.Date.class)
				|| field.getType().equals(java.sql.Date.class)) {
			type = "datetime";
		} else if (field.getType().equals(Integer.class)
				|| field.getType().equals(Double.class)) {
			if(col == null){
				type = "int";
			}else{
				type = String.format("int(%s)", col.length());
			}
			
		} else {
			if(col == null){
				type = String.format("varchar(%s)", DEFAULT_LENGTH);
			}else{
				type = String.format("varchar(%s)", col.length());
			}
			
		}
		return type;
	}
	
	private static boolean isPrimarkkey(Field field) {
		Id id = field.getAnnotation(Id.class);
		if(id == null){
			return false;
		}else{
			return true;
		}
	}
	
	private static boolean isNullable(Field field) {
		Column col = field.getAnnotation(Column.class);
		if(col == null){
			return true;
		}else{
			return col.nullable();
		}
	}
	
	private static boolean isUniquekey(Field field) {
		Id id = field.getAnnotation(Id.class);
		if(id == null){
			Column col = field.getAnnotation(Column.class);
			if(col == null){
				return false;
			}else{
				return col.unique();
			}
		}else{
			return false;
		}
	}
	
	
}

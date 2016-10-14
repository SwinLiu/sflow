package com.lyplay.sflow.orm.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyplay.sflow.orm.exception.IdValueNotEqualsException;
import com.lyplay.sflow.orm.exception.NoColumnAnnotationFoundException;
import com.lyplay.sflow.orm.exception.NoIdValueFoundException;

public class PoUtil {

	private static Logger logger = LoggerFactory.getLogger(PoUtil.class);
 
    /**
     * 比较两个BEAN或MAP对象的值是否相等 
     * 如果是BEAN与MAP对象比较时MAP中的key值应与BEAN的属性值名称相同且字段数目要一致
     * @param source
     * @param target
     * @return
     */
    public static boolean PoEquals(Object source, Object target) throws NoIdValueFoundException, IdValueNotEqualsException, Exception {
        
    	if (source == null && target == null) {
            return true;
        }
    	
    	if (source == null || target == null) {
            return false;
        }
    	
    	boolean rv = true;
    	
        Class<?> srcClass = source.getClass();
        Field[] srcFields = srcClass.getDeclaredFields();
        
        for (int i = 0; i < srcFields.length; i++) {
			Field srcf = srcFields[i];
			
			if("serialVersionUID".equals(srcf.getName())){
				continue;
			}
			
			Method sourceGetter = getGetter(srcClass, srcf);
			
			if(sourceGetter == null){
				continue;
			}
			
			Transient tranAnno = srcf.getAnnotation(Transient.class);
			if(tranAnno != null){
				continue;
			}
			
			Object sourceValue = sourceGetter.invoke(source);
			Object targetValue = sourceGetter.invoke(target);
			
			Id idAnno = srcf.getAnnotation(Id.class);
			if(idAnno != null){
				if(sourceValue == null || targetValue == null){
					throw new NoIdValueFoundException(srcClass);
				}else{
					if(sourceValue.equals(targetValue)){
						continue;
					}else{
						throw new IdValueNotEqualsException(srcClass);
					}
				}
			}else{
				if(sourceValue == null && targetValue == null){
					continue;
				}else if(sourceValue == null || targetValue == null){
					rv = false;
					break;
				}else if(sourceValue.equals(targetValue)){
					continue;
				}else{
					rv = false;
					break;
				}
				
			}
			
		}
        
        logger.debug("THE EQUALS RESULT IS " + rv);
        return rv;
    }
 
    public static <T> Method getGetter(Class<T> clazz, Field f){
		String getterName = "get" + ColumnNameUtil.capitalize(f.getName());
		Method getter = null;
		try {
			getter = clazz.getMethod(getterName);
		} catch (Exception e) {
			logger.error(getterName + " doesn't exist!",e);
		}
		return getter;
	}
    
    /**
	 * use getter to guess column name, if there is annotation then use annotation value, if not then guess from field name
	 * @param getter
	 * @param clazz
	 * @param f
	 * @return
	 * @throws NoColumnAnnotationFoundException
	 */
	public static String getColumnName(Field f){
		String columnName = "";
		Column columnAnno = f.getAnnotation(Column.class);
		if(columnAnno != null){
			columnName = columnAnno.name();
		}
		
		if(columnName == null || "".equals(columnName)){
			// 1. ex : name : testName -> test_name
			columnName = ColumnNameUtil.camel2underscore(f.getName());
			// 2. ex : name : testName -> testname
			//columnName = f.getName().toLowerCase();
		}
		return columnName;
	}
	
	public static <T> String getTableName(Class<T> clazz) {
		
		Table tableAnno = clazz.getAnnotation(Table.class);
		if(tableAnno != null){
			if(StringUtils.isNotEmpty(tableAnno.catalog())){
				return tableAnno.catalog() + "." + tableAnno.name();
			}
			return tableAnno.name();
		}
		//if Table annotation is null
		String className = clazz.getName();
		// 1. ex: className : DemoTest  -->  tableName : demo_test 
		return ColumnNameUtil.camel2underscore(className.substring(className.lastIndexOf(".")+1));
		// 2. ex: className : DemoTest  -->  tableName : demotest 
		//return className.substring(className.lastIndexOf(".")+1).toLowerCase();
	}
	
	public static <T> String getClassName(Class<T> clazz){
		String className = clazz.getName();
		return className.substring(className.lastIndexOf(".")+1);
	}
	
	public static <T> String getTableColumns(Class<T> clazz){
		
		StringBuffer columns = new StringBuffer();
		
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
				columns.append(",");
			}
			columns.append(columnName);
			count++;
			
		}
		
		return columns.toString();
	}
 
}

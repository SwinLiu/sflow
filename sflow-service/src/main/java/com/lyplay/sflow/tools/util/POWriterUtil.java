package com.lyplay.sflow.tools.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class POWriterUtil {
	
	private static Logger logger = LoggerFactory.getLogger(POWriterUtil.class);
	
	public static void writePO(String tableName, Map<String, String> tableColumnMap, String generatePath) {
		
		Collection<String> imports = new HashSet<>();
		List<String> fileds = new ArrayList<>();
		List<String> getterSetters = new ArrayList<>();

		imports.add("import java.io.Serializable;");
		Iterator<Entry<String, String>> iterator = tableColumnMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			String columnName = entry.getKey();
			String fieldClass = entry.getValue();

			setImports(fieldClass, imports);
			setFileds(columnName, fieldClass, fileds);
			setGetterSetter(columnName, fieldClass, getterSetters);
		}

		String poClazzCode = CommonWriterUtil.getPOClazzCode(tableName, imports, fileds, getterSetters);
		logger.info(poClazzCode);

		String mName = CommonWriterUtil.moduleName;
		String filePath = String.format("%s/com/gpayroll/%s/po", generatePath, mName.replace(".", "/"));
		FileWriterUtil.write(filePath, String.format("%s.java", CommonWriterUtil.convert2JavaClazzName(tableName)), poClazzCode);
	}
	
	
	
	public static void setImports(String fieldClass, Collection<String> imports) {
		if (fieldClass.startsWith("java.lang.") || fieldClass.equals("byte[]")) {
			return;
		}
		imports.add(String.format("import %s;", fieldClass));
	}

	public static void setFileds(String columnName, String fieldClass, List<String> fileds) {
		int lastIndexOf = fieldClass.lastIndexOf(".");
		String fieldName = CommonWriterUtil.convert2JavaFieldName(columnName);
		fileds.add(String.format("private %s %s;", fieldClass.substring(lastIndexOf + 1), fieldName));
	}

	public static void setGetterSetter(String columnName, String fieldClass, List<String> getterSetter) {
		int lastIndexOf = fieldClass.lastIndexOf(".");
		String type = fieldClass.substring(lastIndexOf + 1);
		String fieldName = CommonWriterUtil.convert2JavaFieldName(columnName);
		String firstUpperFieldName = CommonWriterUtil.unCapitalize(columnName, true);

		// getter
		getterSetter.add(String.format("public %s get%s() {%s", type, firstUpperFieldName, CommonWriterUtil.NEW_LINE));
		getterSetter.add(String.format("%sreturn this.%s;%s", CommonWriterUtil.TABLE_SPACE, fieldName, CommonWriterUtil.NEW_LINE));
		getterSetter.add(String.format("}%s%s", CommonWriterUtil.NEW_LINE, CommonWriterUtil.NEW_LINE));

		// setter
		getterSetter.add(String.format("public void set%s(%s %s) {%s", firstUpperFieldName, type, fieldName, CommonWriterUtil.NEW_LINE));
		getterSetter.add(String.format("%sthis.%s = %s;%s", CommonWriterUtil.TABLE_SPACE, fieldName, fieldName, CommonWriterUtil.NEW_LINE));
		getterSetter.add(String.format("}%s%s", CommonWriterUtil.NEW_LINE, CommonWriterUtil.NEW_LINE));
	}
	
	
	public static String getPOClazzCode(String tableName, Collection<String> imports, List<String> fileds, List<String> getterSetters) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("package com.gpayroll.%s.po;", CommonWriterUtil.moduleName)).append(CommonWriterUtil.NEW_LINE).append(CommonWriterUtil.NEW_LINE);
		for (String importStr : imports) {
			sb.append(importStr).append(CommonWriterUtil.NEW_LINE);
		}
		sb.append(CommonWriterUtil.NEW_LINE);

		sb.append(String.format("public class %s implements Serializable {", CommonWriterUtil.convert2JavaClazzName(tableName))).append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append(String.format("private static final long serialVersionUID = -4%sL;", RandomStringUtils.randomNumeric(18)))
				.append(CommonWriterUtil.NEW_LINE).append(CommonWriterUtil.NEW_LINE);

		for (String filedStr : fileds) {
			sb.append(CommonWriterUtil.TABLE_SPACE).append(filedStr).append(CommonWriterUtil.NEW_LINE).append(CommonWriterUtil.NEW_LINE);
		}

		for (String getterSetter : getterSetters) {
			sb.append(CommonWriterUtil.TABLE_SPACE).append(getterSetter);
		}

		sb.append("}");

		return sb.toString();
	}
	
}

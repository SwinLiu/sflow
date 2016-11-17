package com.lyplay.sflow.tools.util;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonWriterUtil {

	public static final String NEW_LINE = "\r\n";
	public static final String TABLE_SPACE = "\t";
	public static String moduleName = PropertiesUtil.getProperty("moduleName");
	
	public static String getPOClazzCode(String tableName, Collection<String> imports, List<String> fileds, List<String> getterSetters) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("package com.gpayroll.%s.po;", moduleName)).append(NEW_LINE).append(NEW_LINE);
		for (String importStr : imports) {
			sb.append(importStr).append(NEW_LINE);
		}
		sb.append(NEW_LINE);

		sb.append(String.format("public class %s implements Serializable {", convert2JavaClazzName(tableName))).append(NEW_LINE);
		sb.append(TABLE_SPACE).append(String.format("private static final long serialVersionUID = -4%sL;", RandomStringUtils.randomNumeric(18)))
				.append(NEW_LINE).append(NEW_LINE);

		for (String filedStr : fileds) {
			sb.append(TABLE_SPACE).append(filedStr).append(NEW_LINE).append(NEW_LINE);
		}

		for (String getterSetter : getterSetters) {
			sb.append(TABLE_SPACE).append(getterSetter);
		}

		sb.append("}");

		return sb.toString();
	}
	
	
	public static String convert2JavaClazzName(String tableName) {
		return unCapitalize(tableName, true);
	}
	
	public static String unCapitalize(String str, boolean firstUpperCase) {
		char[] charArray = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (char tmpChar : charArray) {
			if (tmpChar == '_' || tmpChar == '-') {
				firstUpperCase = true;
			} else if (firstUpperCase) {
				sb.append(Character.toUpperCase(tmpChar));
				firstUpperCase = false;
			} else {
				sb.append(tmpChar);
			}
		}

		return sb.toString();
	}
	
	public static String convert2JavaFieldName(String columnName) {
		return unCapitalize(columnName, false);
	}
	
}

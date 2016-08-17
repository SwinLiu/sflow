package com.lyplay.sflow.orm.utils;

import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbutils.BeanProcessor;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

public class CommonBeanProcessor extends BeanProcessor {

	private Map<String, String> columnToPropertyOverrides;

	public CommonBeanProcessor() {
		this.columnToPropertyOverrides = new HashMap<>();
	}

	public CommonBeanProcessor(Map<String, String> columnToPropertyOverrides) {
		if (null != columnToPropertyOverrides) {
			this.columnToPropertyOverrides = columnToPropertyOverrides;
		} else {
			this.columnToPropertyOverrides = new HashMap<>();
		}
	}

	@Override
	protected int[] mapColumnsToProperties(ResultSetMetaData rsmd, PropertyDescriptor[] props) throws SQLException {
		int cols = rsmd.getColumnCount();
		int[] columnToProperty = new int[cols + 1];
		Arrays.fill(columnToProperty, PROPERTY_NOT_FOUND);

		for (int col = 1; col <= cols; col++) {
			String columnName = rsmd.getColumnLabel(col);
			if (null == columnName || 0 == columnName.length()) {
				columnName = rsmd.getColumnName(col);
			}
			String propertyName = columnToPropertyOverrides.get(columnName);
			if (propertyName == null) {
				propertyName = columnName;
			}

			String firstUpperCaseFieldName = unCapitalize(columnName.toLowerCase(), true);
			String firstLowerCaseFieldName = unCapitalize(columnName.toLowerCase(), false);
			mapperColumnToFieldCaseSensitive(props, columnToProperty, col, propertyName, firstUpperCaseFieldName, firstLowerCaseFieldName);

			// Ignore Case
			if (PROPERTY_NOT_FOUND == columnToProperty[col]) {
				mappingColumnToFieldIgnoreCase(props, columnToProperty, col, propertyName, firstUpperCaseFieldName);
			}
		}

		return columnToProperty;
	}

	private void mappingColumnToFieldIgnoreCase(PropertyDescriptor[] props, int[] columnToProperty, int col, String propertyName, String firstUpperCaseFieldName) {
		for (int i = 0; i < props.length; i++) {
			if (propertyName.equalsIgnoreCase(props[i].getName()) || firstUpperCaseFieldName.equalsIgnoreCase(props[i].getName())) {
				columnToProperty[col] = i;
				break;
			}
		}
	}

	private void mapperColumnToFieldCaseSensitive(PropertyDescriptor[] props, int[] columnToProperty, int col, String propertyName,
			String firstUpperCaseFieldName, String firstLowerCaseFieldName) {
		for (int i = 0; i < props.length; i++) {
			if (propertyName.equals(props[i].getName()) || firstUpperCaseFieldName.equals(props[i].getName())
					|| firstLowerCaseFieldName.equals(props[i].getName())) {
				columnToProperty[col] = i;
				break;
			}
		}
	}

	protected static String unCapitalize(String str, boolean firstUpperCase) {
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

	@Override
	protected Object processColumn(ResultSet rs, int index, Class<?> propType) throws SQLException {
		if (!propType.isPrimitive() && rs.getObject(index) == null) {
			return null;
		}

		if (propType.equals(byte[].class)) {
			LobHandler lobHandler = new DefaultLobHandler();
			return lobHandler.getBlobAsBytes(rs, index);
		} else if (propType.equals(Date.class)) {
			return rs.getTimestamp(index);
		} else {
			return super.processColumn(rs, index, propType);
		}
	}
}
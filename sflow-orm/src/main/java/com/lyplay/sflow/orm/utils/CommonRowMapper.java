package com.lyplay.sflow.orm.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class CommonRowMapper<T> implements RowMapper<T> {

	protected Class<T> entityClazz;
	protected CommonBeanProcessor beanProcessor;

	public CommonRowMapper(Class<T> entityClazz) {
		this.entityClazz = entityClazz;
		this.beanProcessor = new CommonBeanProcessor();
	}

	public CommonRowMapper(Map<String, String> columnToPropertyOverrides, Class<T> entityClazz) {
		this.entityClazz = entityClazz;
		this.beanProcessor = new CommonBeanProcessor(columnToPropertyOverrides);
	}

	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		return (T) beanProcessor.toBean(rs, entityClazz);
	}
}
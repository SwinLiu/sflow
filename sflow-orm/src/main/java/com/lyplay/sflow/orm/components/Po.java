package com.lyplay.sflow.orm.components;

import com.lyplay.sflow.orm.utils.CommonRowMapper;
import com.lyplay.sflow.orm.utils.PoUtil;

public class Po<T> {

	private String tableName;
	private String columns;
	private CommonRowMapper<T> rowMapper;
	
	public Po(Class<T> clazz) {
		this.tableName = PoUtil.getTableName(clazz);
		this.columns = PoUtil.getTableColumns(clazz);
		this.rowMapper = new CommonRowMapper<T>(clazz);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public CommonRowMapper<T> getRowMapper() {
		return rowMapper;
	}

	public void setRowMapper(CommonRowMapper<T> rowMapper) {
		this.rowMapper = rowMapper;
	}
	
	
	
	

}

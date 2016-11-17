package com.lyplay.sflow.tools.db2object;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyplay.sflow.tools.util.AbstractDAOWriterUtil;
import com.lyplay.sflow.tools.util.CommonWriterUtil;
import com.lyplay.sflow.tools.util.DAOImplWriterUtil;
import com.lyplay.sflow.tools.util.POWriterUtil;
import com.lyplay.sflow.tools.util.PropertiesUtil;

/**
 * Generate JdbcTemplate Code by table
 * 
 */
public class JdbcTemplateCodeGenerator {
	private static Logger logger = LoggerFactory.getLogger(JdbcTemplateCodeGenerator.class);
	private String limitCondition = "rownum < 2";
	
	private Connection getConnection() throws Exception {
		try {
			String driverClass = PropertiesUtil.getProperty("jdbc.driverClass");
			String url = PropertiesUtil.getProperty("jdbc.url");
			String user = PropertiesUtil.getProperty("jdbc.user");
			String password = PropertiesUtil.getProperty("jdbc.password");

			Class.forName(driverClass);

			if (driverClass.contains("mysql")) {
				limitCondition = "limit 1";
			}
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			throw e;
		}
	}

	private Collection<String> getTables() {
		String tables = PropertiesUtil.getProperty("tables");
		return new HashSet<String>(Arrays.asList(tables.split(",")));
	}

	private String getGeneratePath() {
		String path = PropertiesUtil.getProperty("generate.path");
		if (StringUtils.isBlank(path)) {
			path = System.getProperty("user.home") + "/Desktop";
		}

		File folder = new File(path + "/com");
		if (folder.exists() && folder.canWrite()) {
			deleteDir(folder);
		}

		folder.mkdirs();
		return path;
	}

	public void generate() throws Exception {
		Connection conn = null;
		try {
			conn = getConnection();
			String generatePath = this.getGeneratePath();
			Collection<String> tables = this.getTables();
			List<String> daoList = new ArrayList<String>();
			for (String tableName : tables) {
				tableName = tableName.toLowerCase();
				String querySql = String.format("select * from %s where %s", tableName, limitCondition);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(querySql);

				Map<String, String> tableColumnMap = new LinkedHashMap<>();
				List<String> columnList = new ArrayList<>();
				ResultSetMetaData metaData = rs.getMetaData();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					String columnName = metaData.getColumnName(i);
					String columnClassName = metaData.getColumnClassName(i);
					int precision = metaData.getPrecision(i);
					int scale = metaData.getScale(i);

					columnClassName = determineJavaType(columnClassName, precision, scale);

					columnList.add(columnName);
					tableColumnMap.put(columnName.toLowerCase(), columnClassName);
				}

				POWriterUtil.writePO(tableName, tableColumnMap, generatePath);
				//DAOWriterUtil.writeDAO(tableName, tableColumnMap, generatePath, StringUtils.join(columnList, ","));
				AbstractDAOWriterUtil.writeDAO(tableName, tableColumnMap, generatePath, StringUtils.join(columnList, ","));
				DAOImplWriterUtil.writeDAO(tableName, generatePath, "sg");
				DAOImplWriterUtil.writeDAO(tableName, generatePath, "hk");
				DAOImplWriterUtil.writeDAO(tableName, generatePath, "mal");
				DAOImplWriterUtil.writeDAO(tableName, generatePath, "chn");
				DAOImplWriterUtil.writeDAO(tableName, generatePath, "tha");
				daoList.add(CommonWriterUtil.convert2JavaClazzName(tableName)+"DAO");
			}
			logger.info(StringUtils.join(daoList,"\n"));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
	}

	private String determineJavaType(String columnClassName, int precision, int scale) {
		try {
			if (columnClassName.equalsIgnoreCase(Timestamp.class.getCanonicalName())) {
				return Date.class.getCanonicalName();
			} else if (columnClassName.equals(BigDecimal.class.getCanonicalName())) {
				if (0 == scale) {
					if (String.valueOf(Integer.MAX_VALUE).length() > precision) {
						return Integer.class.getCanonicalName();
					} else if (String.valueOf(Long.MAX_VALUE).length() > precision) {
						return Long.class.getCanonicalName();
					}
				}
			} else if (java.sql.Clob.class.isAssignableFrom(Class.forName(columnClassName))) {
				return String.class.getCanonicalName();
			} else if (java.sql.Blob.class.isAssignableFrom(Class.forName(columnClassName))) {
				return byte[].class.getCanonicalName();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return columnClassName;
	}

	



	protected boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

	public static void main(String[] args) throws Exception {
		new JdbcTemplateCodeGenerator().generate();
	}
}

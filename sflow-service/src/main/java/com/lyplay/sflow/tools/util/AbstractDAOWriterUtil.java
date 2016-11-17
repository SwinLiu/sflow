package com.lyplay.sflow.tools.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AbstractDAOWriterUtil {
	
	private static Logger logger = LoggerFactory.getLogger(DAOWriterUtil.class);
	
	public static void writeDAO(String tableName, Map<String, String> tableColumnMap, String generatePath, String columnList) {
		String poClazzName = CommonWriterUtil.convert2JavaClazzName(tableName);
		String daoClazzName = String.format("%sDAO", poClazzName);
		String daoClazzCode = getDAOClazzCode(tableName, daoClazzName, poClazzName, columnList);
		logger.info(daoClazzCode);

		String mName = CommonWriterUtil.moduleName;
		String filePath = String.format("%s/com/gpayroll/%s/dao", generatePath, mName.replace(".", "/"));
		FileWriterUtil.write(filePath, String.format("%s.java", daoClazzName), daoClazzCode);
	}
	
	public static String getDAOClazzCode(String tableName, String daoClazzName, String poClazzName, String columnList) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("package com.gpayroll.%s.dao;", CommonWriterUtil.moduleName)).append(CommonWriterUtil.NEW_LINE).append(CommonWriterUtil.NEW_LINE);

		sb.append("import org.slf4j.Logger;").append(CommonWriterUtil.NEW_LINE);
		sb.append("import org.slf4j.LoggerFactory;").append(CommonWriterUtil.NEW_LINE);

		sb.append("import com.gpayroll.common.annotation.SupportLocation;").append(CommonWriterUtil.NEW_LINE);
		sb.append("import com.gpayroll.common.base.DynamicBaseDAO;").append(CommonWriterUtil.NEW_LINE);
		sb.append("import com.gpayroll.common.base.CommonRowMapper;").append(CommonWriterUtil.NEW_LINE);
		sb.append(String.format("import com.gpayroll.%s.po.%s;", CommonWriterUtil.moduleName, poClazzName)).append(CommonWriterUtil.NEW_LINE).append(CommonWriterUtil.NEW_LINE);

		String rowMapperName = String.format("%sRowMapper", CommonWriterUtil.unCapitalize(tableName, false));

		sb.append("@SupportLocation").append(CommonWriterUtil.NEW_LINE);
		sb.append(String.format("public class %s extends DynamicBaseDAO {", daoClazzName)).append(CommonWriterUtil.NEW_LINE);

		sb.append(CommonWriterUtil.TABLE_SPACE).append(String.format("private static final Logger LOG = LoggerFactory.getLogger(%s.class);", daoClazzName)).append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append(String.format("private static final String TABLE_NAME = \"%s\";", tableName)).append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append(String.format("private static final String COLUMN = \"%s\";", columnList)).append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE)
				.append(String.format("private static CommonRowMapper<%s> %s = new CommonRowMapper<>(%s.class);", poClazzName, rowMapperName, poClazzName))
				.append(CommonWriterUtil.NEW_LINE).append(CommonWriterUtil.NEW_LINE);

		// method getColumn
		sb.append(CommonWriterUtil.TABLE_SPACE).append("@Override").append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append("public String getColumn() {").append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append(CommonWriterUtil.TABLE_SPACE).append("return COLUMN;").append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append("}").append(CommonWriterUtil.NEW_LINE).append(CommonWriterUtil.NEW_LINE);

		
		sb.append("}");

		return sb.toString();
	}
}

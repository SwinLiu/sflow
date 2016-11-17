package com.lyplay.sflow.tools.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DAOImplWriterUtil {
	
	private static Logger logger = LoggerFactory.getLogger(DAOWriterUtil.class);
	
	public static void writeDAO(String tableName, String generatePath, String loc) {
		String poClazzName = CommonWriterUtil.convert2JavaClazzName(tableName);
		String daoClazzName = String.format("%s%sDAO", loc.toUpperCase(), poClazzName);
		String daoClazzCode = getDAOClazzCode(daoClazzName, poClazzName, loc);
		logger.info(daoClazzCode);

		String mName = CommonWriterUtil.moduleName;
		String filePath = String.format("%s/com/gpayroll/%s/dao/impl", generatePath, mName.replace(".", "/"));
		FileWriterUtil.write(filePath, String.format("%s.java", daoClazzName), daoClazzCode);
	}
	
	public static String getDAOClazzCode(String daoClazzName, String poClazzName, String loc) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("package com.gpayroll.%s.dao.impl;", CommonWriterUtil.moduleName)).append(CommonWriterUtil.NEW_LINE).append(CommonWriterUtil.NEW_LINE);

		sb.append("import javax.annotation.Resource;").append(CommonWriterUtil.NEW_LINE);
		sb.append("import org.springframework.jdbc.core.JdbcTemplate;").append(CommonWriterUtil.NEW_LINE);
		sb.append("import org.springframework.stereotype.Repository;").append(CommonWriterUtil.NEW_LINE);
		sb.append("import org.springframework.transaction.PlatformTransactionManager;").append(CommonWriterUtil.NEW_LINE);
		sb.append("import org.springframework.transaction.TransactionDefinition;").append(CommonWriterUtil.NEW_LINE);
		
		sb.append(String.format("import com.gpayroll.common.base.%sBaseDAO;",loc.toUpperCase())).append(CommonWriterUtil.NEW_LINE);

		sb.append(String.format("import com.gpayroll.%s.dao.%s;", CommonWriterUtil.moduleName, String.format("%sDAO", poClazzName))).append(CommonWriterUtil.NEW_LINE).append(CommonWriterUtil.NEW_LINE);

		sb.append(String.format("@Repository(\"%s%sDAO\")", loc.toLowerCase(),poClazzName)).append(CommonWriterUtil.NEW_LINE);
		sb.append(String.format("public class %s extends %sDAO {", daoClazzName,poClazzName)).append(CommonWriterUtil.NEW_LINE);

		sb.append(CommonWriterUtil.TABLE_SPACE).append(String.format("@Resource(name = \"%sBaseDAO\")", loc.toLowerCase())).append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append(String.format("private %sBaseDAO baseDao;", loc.toUpperCase())).append(CommonWriterUtil.NEW_LINE);
		
		sb.append(CommonWriterUtil.TABLE_SPACE).append("@Override").append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append("public JdbcTemplate getJdbcTemplate() {").append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append(CommonWriterUtil.TABLE_SPACE).append("return baseDao.getJdbcTemplate();").append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append("}").append(CommonWriterUtil.NEW_LINE);
		
		sb.append(CommonWriterUtil.TABLE_SPACE).append("@Override").append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append("public PlatformTransactionManager getTxManager() {").append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append(CommonWriterUtil.TABLE_SPACE).append("return baseDao.getTxManager();").append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append("}").append(CommonWriterUtil.NEW_LINE);
		
		sb.append(CommonWriterUtil.TABLE_SPACE).append("@Override").append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append("public TransactionDefinition getTxDefinition() {").append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append(CommonWriterUtil.TABLE_SPACE).append("return baseDao.getTxDefinition();").append(CommonWriterUtil.NEW_LINE);
		sb.append(CommonWriterUtil.TABLE_SPACE).append("}").append(CommonWriterUtil.NEW_LINE);
		
		
		sb.append("}");

		return sb.toString();
	}
}

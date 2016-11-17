package com.lyplay.sflow.tools.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private static Properties props = new Properties();

	static {
		load("db2object.properties");
	}

	public static void load(String propFile) {
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(propFile);
			if (null == is) {
				throw new RuntimeException(String.format("Cannot Find Properties File %s from ClassPath.", propFile));
			}
			props.load(is);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			close(is);
		}
	}

	private static void close(InputStream is) {
		if (null != is) {
			try {
				is.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}
}

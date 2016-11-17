package com.lyplay.sflow.tools.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileWriterUtil {
	private static Logger logger = LoggerFactory.getLogger(FileWriterUtil.class);

	private FileWriterUtil() {

	}

	public static void write(String filePath, String fileName, String content) {
		FileOutputStream fos = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}

			String fullPath = filePath + '/' + fileName;
			fos = new FileOutputStream(fullPath);
			fos.write(content.getBytes());
			fos.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
	}
}

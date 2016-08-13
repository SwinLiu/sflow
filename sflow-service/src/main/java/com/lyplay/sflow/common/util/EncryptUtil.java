package com.lyplay.sflow.common.util;

public class EncryptUtil {

	/**
	 * @Title: DoEncrypt
	 * @Description: 对String进行加密的方法
	 * @param @param str
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String DoEncrypt(String str) {
		StringBuffer enStrBuff = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int tmpch = str.charAt(i);
			tmpch ^= 1;
			tmpch ^= 0xa;
			enStrBuff.append(Integer.toHexString(tmpch));
		}

		return enStrBuff.toString().toUpperCase();
	}

	/**
	 * @Title: DoDecrypt
	 * @Description: 对String进行解密的方法
	 * @param @param str
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String DoDecrypt(String str) {
		String deStr = str.toLowerCase();
		StringBuffer deStrBuff = new StringBuffer();
		for (int i = 0; i < deStr.length(); i += 2) {
			String subStr = deStr.substring(i, i + 2);
			int tmpch = Integer.parseInt(subStr, 16);
			tmpch ^= 1;
			tmpch ^= 0xa;
			deStrBuff.append((char) tmpch);
		}

		return deStrBuff.toString();
	}
	
}

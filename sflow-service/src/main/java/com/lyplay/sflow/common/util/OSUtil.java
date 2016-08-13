package com.lyplay.sflow.common.util;


public class OSUtil {
	private static String OSName;
	
	//init
	static{
		OSName = System.getProperty("os.name").toLowerCase().trim();
	}
	
	public static String getOSName(){
		return OSName;
	}
	
	public static boolean isWindows(){
		return ((OSName.indexOf("window") != -1) ? true : false);
	}
	
	public static boolean isLinux(){
		return ((OSName.indexOf("linux") != -1) ? true : false);
	}
	
	public static boolean isUnix(){
		return ((OSName.indexOf("unix") != -1 || OSName.indexOf("ux") != -1) ? true : false);
	}
	
}

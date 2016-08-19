package com.lyplay.sflow.orm.utils;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;


public class ResultUtil {
	
	public static <T> T getFrist(List<T> list){
		if(CollectionUtils.isEmpty(list)){
			return null;
		}else{
			return list.get(0);
		}
	}
	
}

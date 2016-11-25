package com.lyplay.sflow.common;

import java.util.Map;

import org.junit.Test;

import com.lyplay.sflow.common.util.RSAUtil;
import com.lyplay.sflow.po.auth.ACL;

public class UtilTest {

	
	@Test
	public void setTest(){
		
		ACL acl = new ACL();
		
		System.out.println(acl.setBit(0, 5, true));
		
		System.out.println(Integer.toBinaryString(acl.setBit(0, 5, true)));
		
		
	}
	
	@Test
	public void rsaUtil() throws Exception{
		
		Map<String, String> map = RSAUtil.genKeyPair();
		
		System.out.println(map.get(RSAUtil.PRIVATE_KEY));
		System.out.println(map.get(RSAUtil.PUBLIC_KEY));
		
		
	}
	
}

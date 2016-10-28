package com.lyplay.sflow.common;

import org.junit.Test;

import com.lyplay.sflow.po.auth.ACL;

public class UtilTest {

	
	@Test
	public void setTest(){
		
		ACL acl = new ACL();
		
		System.out.println(acl.setBit(0, 5, true));
		
		System.out.println(Integer.toBinaryString(acl.setBit(0, 5, true)));
		
		
	}
	
}

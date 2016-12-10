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
		
		Map<String, String> map = RSAUtil.getKeyPair();
		String publicKeyStr = map.get(RSAUtil.PUBLIC_KEY);
		String privateKeyStr = map.get(RSAUtil.PRIVATE_KEY);
		
		
		
		System.out.println(RSAUtil.PRIVATE_KEY + " : ");
		System.out.println(privateKeyStr);
		System.out.println(RSAUtil.PUBLIC_KEY + " : ");
		System.out.println(publicKeyStr);
		
		String data = "haha";
		
		System.out.println("before : " + data);
		
		String encryptString = RSAUtil.encryptByPublicKey(data, publicKeyStr);
		System.out.println("encryptByPublicKey : ");
		System.out.println(encryptString);
		
		String decryptString = RSAUtil.decryptByPrivateKey(encryptString, privateKeyStr);
		System.out.println("decryptByPrivateKey : ");
		System.out.println(decryptString);
		
	}
	
}

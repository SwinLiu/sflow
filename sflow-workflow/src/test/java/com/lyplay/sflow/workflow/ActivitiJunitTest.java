package com.lyplay.sflow.workflow;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:activiti.cfg.xml" })
public class ActivitiJunitTest extends AbstractJUnit4SpringContextTests {

	@Resource
	ProcessEngine processEngine;
	
	@Test
	public void testSave() throws Exception{
		
		System.out.println("processEngine:"+processEngine);
		
	}
	
	
}

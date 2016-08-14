package org.sflow.orm;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.sflow.orm.po.Employee;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.lyplay.sflow.orm.IBaseDAO;

@ContextConfiguration(locations = { "classpath:config/spring-test.xml" })
public class OrmJunitTest extends AbstractJUnit4SpringContextTests {

	@Resource
	IBaseDAO baseDao;
	
	@Test
	public void testSave() throws Exception{
		
		Employee ee = new Employee();
		ee.setId(18);
		ee.setName("11111");
		Date now = new Date();
		ee.setJoindate(now);
		ee.setAge(33);
		
		baseDao.delete(ee);
		
		baseDao.save(ee);
		
	}
	
	
}

/**
 * 
 */
package com.kelvem.sample.test.system;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kelvem.sample.system.model.SysDomainModel;
import com.kelvem.sample.system.service.SysDomainService;

/**
 * @author kelvem
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({"classpath*:spring/**/spring-context-*.xml"})
public class SysDomainServiceTest {

	@Autowired SysDomainService sysDomainService;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.kelvem.sample.system.service.SysDomainService#querySysDomain(java.lang.String)}.
	 */
	@Test
	public void testQuerySysDomain() {
		List<SysDomainModel> list = sysDomainService.querySysDomain("A");
		System.out.println(list.size());
	}

	/**
	 * Test method for {@link com.kelvem.sample.system.service.SysDomainService#saveSysDomain(com.kelvem.sample.system.model.SysDomainModel)}.
	 */
	@Test
	public void testSaveSysDomain() {
	}

	/**
	 * Test method for {@link com.kelvem.sample.system.service.SysDomainService#updateSysDomain(com.kelvem.sample.system.model.SysDomainModel)}.
	 */
	@Test
	public void testUpdateSysDomain() {
	}

	/**
	 * Test method for {@link com.kelvem.sample.system.service.SysDomainService#deleteSysDomain(com.kelvem.sample.system.model.SysDomainModel)}.
	 */
	@Test
	public void testDeleteSysDomain() {
	}

}

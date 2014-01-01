package com.kelvem.sample.test.system;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kelvem.common.model.PageResults;
import com.kelvem.sample.system.model.SysUserModel;
import com.kelvem.sample.system.queryvo.SysUserInVO;
import com.kelvem.sample.system.service.SysUserService;

@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({"classpath*:spring/**/spring-context-*.xml"})
public class SysUserServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private SysUserService sysUserService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Before //在每个测试用例方法之前都会执行
	public void init(){
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
	}
	
	@After //在每个测试用例执行完之后执行
	public void destory(){
	}

	@Test
	@Transactional  //使用该注释会使用事务，而且在测试完成之后会回滚事务，也就是说在该方法中做出的一切操作都不会对数据库中的数据产生任何影响
	@Rollback(true) //这里设置为false，就让事务不回滚
	public void testQuerySysUser() {
		SysUserInVO sysUserInVO = new SysUserInVO();
		PageResults<SysUserModel> page = sysUserService.querySysUser(0, 10, sysUserInVO);
		System.out.println(page.getPageCount());
//		fail("Not yet implemented");
	}

	@Test
	public void testGetSysUserById() {
//		fail("Not yet implemented");
	}

	@Test
	@Transactional  //使用该注释会使用事务，而且在测试完成之后会回滚事务，也就是说在该方法中做出的一切操作都不会对数据库中的数据产生任何影响
	@Rollback(true) //这里设置为false，就让事务不回滚
	public void testSaveSysUser() {
//		fail("Not yet implemented");
		SysUserModel sysUser = new SysUserModel();
		sysUser.setUserLogonName("B");
		sysUser.setSysUserName("B");
		sysUser.setUserPassword("B");
		try {
			sysUserService.saveSysUser(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateSysUser() {
//		fail("Not yet implemented");
	}

	@Test
	public void testDeleteSysUser() {
//		fail("Not yet implemented");
	}

}

package com.kelvem.sample.test.system;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kelvem.sample.system.cache.MenuCache;
import com.kelvem.sample.system.cache.MenuCache.MenuTree;

@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({"classpath*:spring/**/spring-context-*.xml"})
public class MenuCacheTest {

	@Autowired private MenuCache menuCache;
	
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
	public void testMenuCache() {
		MenuTree tree = menuCache.getMenuTree();
		System.out.println("###");
		System.out.println(tree.toJson());
		System.out.println("###");
	}

	
	public MenuCache getMenuCache() {
		return menuCache;
	}

	public void setMenuCache(MenuCache menuCache) {
		this.menuCache = menuCache;
	}

}

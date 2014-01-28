/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.service.SysRoleService.java
 * 所含类: SysRoleService.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-28	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kelvem.common.model.PageResults;
import com.kelvem.sample.system.dao.SysRoleDao;
import com.kelvem.sample.system.model.SysRoleModel;
import com.kelvem.sample.system.queryvo.SysRoleInVO;

/**
 * <p>SysRoleService</p>
 *
 * <p>系统角色管理模块Service</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysRoleService
 * @author kelvem
 * @version 1.0
 * 
 */
@Component("sysRoleService")
public class SysRoleService {
	
	private static Log log = LogFactory.getLog(SysRoleService.class);

	@Autowired private SysRoleDao sysRoleDao; 
	
	/**
	 * <p>查询系统角色列表</p>
	 * 
	 * @param pageNo 第几页
	 * @param pageSize 每页显示条数
	 * @param sysRoleInVO 查询条件VO 
	 * @return PageResults<SysRoleModel> 系统角色列表
	 * @see
	 */
	public PageResults<SysRoleModel> querySysRole(int pageNo, int pageSize, SysRoleInVO sysRoleInVO){ 
		
		PageResults<SysRoleModel> pr = this.sysRoleDao.querySysRole(pageNo, pageSize, sysRoleInVO);
				
		List<SysRoleModel> list = new ArrayList<SysRoleModel>(pr.getResults().size());
		
		for (SysRoleModel obj : pr.getResults()) {
			
//			obj.setStrOccurTime(DateUtils.getDateTimeString(obj.getOccurTime()));
//			obj.setStrDealTime(DateUtils.getDateTimeString(obj.getDealTime()));
			
			list.add(obj);
		}
		
		pr.setResults(list);
		
		return pr;
	}
	
	/**
	 * <p>查询系统所有角色</p>
	 * 
	 * @return SysRoleModel 系统角色
	 * @see
	 */
	public List<SysRoleModel> queryAllSysRole(){
		
		return this.getSysRoleDao().queryAllSysRole();
	}
	
	/**
	 * <p>查询系统角色</p>
	 * 
	 * @param id 唯一ID
	 * @return SysRoleModel 系统角色
	 * @see
	 */
	public SysRoleModel getSysRoleById(Integer id){
		
		SysRoleModel result = this.getSysRoleDao().getSysRoleById(id);
		return result;
	}
	
	/**
	 * <p>保存系统角色</p>
	 * 
	 * @param sysRole 系统角色
	 * @return Integer keyId
	 * @see
	 */
	public Integer saveSysRole(SysRoleModel sysRole){
		
		return this.getSysRoleDao().saveSysRole(sysRole);
	}
	
	/**
	 * <p>更新系统角色</p>
	 * 
	 * @param sysRole 系统角色
	 * @return void
	 * @see
	 */
	public void updateSysRole(SysRoleModel sysRole){
				
		this.getSysRoleDao().updateSysRole(sysRole);
	}
	
	/**
	 * <p>删除系统角色</p>
	 * 
	 * @param sysRole 系统角色
	 * @return void
	 * @see
	 */
	public void deleteSysRole(SysRoleModel sysRole){

		this.getSysRoleDao().deleteSysRole(sysRole);
	}
	
	/**
	 * <p>查询系统角色</p>
	 * 
	 * @param sysRole 系统角色
	 * @return void
	 * @see
	 */
	public List<SysRoleModel> querySysRole(Integer sysAuthorityId){
		
		return this.getSysRoleDao().querySysRole(sysAuthorityId);
	}
	
	/**
	 * <p>查询系统菜单表</p>
	 * 
	 * @param menuId	菜单Id
	 * @param menuName	菜单名称
	 * @param menuUrl	菜单地址
	 * @return List<SysRoleModel> 系统菜单表
	 * @see
	 */
	public List<SysRoleModel> querySysRole(Integer menuId, String menuName, String menuUrl) {
		
		return this.getSysRoleDao().querySysRole(menuId, menuName, menuUrl);
	}

	public SysRoleDao getSysRoleDao() {
		return sysRoleDao;
	}

	public void setSysRoleDao(SysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}
	
}

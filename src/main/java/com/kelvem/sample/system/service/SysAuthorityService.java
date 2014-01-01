/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.service.SysAuthorityService.java
 * 所含类: SysAuthorityService.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-09	kelvem			创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.sample.system.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kelvem.common.model.PageResults;
import com.kelvem.sample.system.dao.SysAuthorityDao;
import com.kelvem.sample.system.model.SysAuthorityModel;
import com.kelvem.sample.system.queryvo.SysAuthorityInVO;

/**
 * <p>SysAuthorityService</p>
 *
 * <p>系统权限表管理模块Service</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysAuthorityService
 * @author kelvem
 * @version 1.0
 * 
 */
@Component("sysAuthorityService")
public class SysAuthorityService {
	
	private static Log log = LogFactory.getLog(SysAuthorityService.class);

	@Autowired
	private SysAuthorityDao sysAuthorityDao; 
	
	/**
	 * <p>查询系统权限表列表</p>
	 * 
	 * @param pageNo 第几页
	 * @param pageSize 每页显示条数
	 * @param sysAuthorityInVO 查询条件VO 
	 * @return PageResults<SysAuthorityModel> 系统权限表列表
	 * @see
	 */
	public PageResults<SysAuthorityModel> querySysAuthority(int pageNo, int pageSize, SysAuthorityInVO sysAuthorityInVO){ 
		
		PageResults<SysAuthorityModel> pr = this.sysAuthorityDao.querySysAuthority(pageNo, pageSize, sysAuthorityInVO);
				
		List<SysAuthorityModel> list = new ArrayList<SysAuthorityModel>(pr.getResults().size());
		
		for (SysAuthorityModel obj : pr.getResults()) {
			
//			obj.setStrOccurTime(DateUtils.getDateTimeString(obj.getOccurTime()));
//			obj.setStrDealTime(DateUtils.getDateTimeString(obj.getDealTime()));
			
			list.add(obj);
		}
		
		pr.setResults(list);
		
		return pr;
	}
	
	/**
	 * <p>查询系统权限表</p>
	 * 
	 * @param id 唯一ID
	 * @return SysAuthorityModel 系统权限表
	 * @see
	 */
	public SysAuthorityModel getSysAuthorityById(Integer id){
		
		SysAuthorityModel result = this.getSysAuthorityDao().getSysAuthorityById(id);
		return result;
	}
	
	/**
	 * <p>保存系统权限表</p>
	 * 
	 * @param sysAuthority 系统权限表
	 * @return void
	 * @see
	 */
	public void saveSysAuthority(SysAuthorityModel sysAuthority){
		
		this.getSysAuthorityDao().saveSysAuthority(sysAuthority);
	}
	
	/**
	 * <p>更新系统权限表</p>
	 * 
	 * @param sysAuthority 系统权限表
	 * @return void
	 * @see
	 */
	public void updateSysAuthority(SysAuthorityModel sysAuthority){
				
		this.getSysAuthorityDao().updateSysAuthority(sysAuthority);
	}
	
	/**
	 * <p>删除系统权限表</p>
	 * 
	 * @param sysAuthority 系统权限表
	 * @return void
	 * @see
	 */
	public void deleteSysAuthority(SysAuthorityModel sysAuthority){

		this.getSysAuthorityDao().deleteSysAuthority(sysAuthority);
	}

	/**
	 * <p>查询系统权限表</p>
	 * 
	 * @param id	权限Id
	 * @param name	权限名称
	 * @param url	权限地址
	 * @return List<SysAuthorityModel> 系统权限表
	 * @see
	 */
	public List<SysAuthorityModel> querySysAuthority(Integer id, String name, String url){
		
		List<SysAuthorityModel> result = this.getSysAuthorityDao().querySysAuthority(id, name, url);
		return result;
	}
	
	
	public SysAuthorityDao getSysAuthorityDao() {
		return sysAuthorityDao;
	}

	public void setSysAuthorityDao(SysAuthorityDao sysAuthorityDao) {
		this.sysAuthorityDao = sysAuthorityDao;
	}
	
}

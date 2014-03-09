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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kelvem.common.ModelConstant;
import com.kelvem.common.model.PageResults;
import com.kelvem.common.utils.RegxUtil;
import com.kelvem.sample.system.dao.SysAuthorityDao;
import com.kelvem.sample.system.model.SysAuthorityModel;
import com.kelvem.sample.system.model.SysRoleModel;
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

	@Autowired private SysAuthorityDao sysAuthorityDao; 
	@Autowired private SysRoleService sysRoleService; 
	
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
	 * @return Integer	系统权限表唯一ID
	 * @see
	 */
	public Integer saveSysAuthority(SysAuthorityModel sysAuthority){
		
		return this.getSysAuthorityDao().saveSysAuthority(sysAuthority);
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


	public SysAuthorityModel recordAuth(String url){

		List<SysAuthorityModel> list = this.querySysAuthority(null, null, url);

		SysAuthorityModel sysAuthority = null;
		
		if (list == null || list.size() <= 0) {
			sysAuthority = this.createSysAuthority(url);
			this.saveSysAuthority(sysAuthority);
			log.info("record Auth '" + sysAuthority.getSysAuthorityUrl() + "', id=" + sysAuthority.getSysAuthorityId());
		} else {
			sysAuthority = list.get(0);
		}
		
		return sysAuthority;
	}
	
	public void recordMenuWithAuthMapping(SysRoleModel currSysMenu, String url){
		
		// 记录url的auth
		SysAuthorityModel sysAuthority = this.recordAuth(url);
		currSysMenu = sysRoleService.getSysRoleById(currSysMenu.getSysRoleId());
		
		// Save relationship
		Set<SysAuthorityModel> sysAuthoritySet = currSysMenu.getSysAuthoritySet();

		if (sysAuthoritySet != null) {
			for (SysAuthorityModel sysAuthorityModel : sysAuthoritySet) {
				if (sysAuthorityModel.getSysAuthorityUrl().equals(sysAuthority.getSysAuthorityUrl())) {
					// 已经有菜单和权限的关系，则直接结束
					return;
				}
			}
		}

		HashSet<SysAuthorityModel> buf = new HashSet<SysAuthorityModel>();
		buf.addAll(sysAuthoritySet);
		buf.add(sysAuthority);
		
		for (SysAuthorityModel sysAuthorityModel : buf) {
			log.debug("sysAuthorityModel id=" + sysAuthorityModel.getSysAuthorityId() + ",  " + sysAuthorityModel.getSysAuthorityUrl());
		}
		
		currSysMenu.setSysAuthoritySet(buf);
		sysRoleService.updateSysRole(currSysMenu);
		log.info("记录菜单与权限的关系 SysRole(" + currSysMenu.getMenuUrl() + ")与SysAuthority(" + sysAuthority.getSysAuthorityUrl() + ")");
	}
	
	public SysAuthorityModel createSysAuthority(String url) {

		SysAuthorityModel model = new SysAuthorityModel();
		
		String authName = url;
		if (url != null 
				&& url.length() > 0 
				&& url.startsWith("/")
				&& url.contains("*")) {
			authName = url.substring(url.lastIndexOf("/") + 1);
		}
		model.setSysAuthorityName(authName);
		model.setSysAuthorityUrl(url);
		model.setSysAuthorityType(getAuthType(url));
		model.setControlType("login");
		model.setCreateTime(new Date());
		model.setStatusCode(10);
		model.setStatusChangeTime(new Date());
		model.setDelFlag(ModelConstant.FLAG_DEL_FALSE);
		
		return model;
	}

	public static String getAuthType(String url){
		
		if (url == null || url.length() <= 0) {
			return "unknow";
		} else if (RegxUtil.contain(url, "(Query|query|List|list|Get|get|Detail|detail)(|Done).(jsp|do|action|html)")) {
			return "read";
		} else if (RegxUtil.contain(url, "(Add|add)(|Done).(jsp|do|action|html)")) {
			return "add";
		} else if (RegxUtil.contain(url, "(Update|update|Save|save|Confirm|confirm)(|Done).(jsp|do|action|html)")) {
			return "update";
		} else if (RegxUtil.contain(url, "(Delete|delete|Del|del)(|Done).(jsp|do|action|html)")) {
			return "delete";
		} else if (RegxUtil.contain(url, ".(css|js|jpg|JPG|png|PNG|gif|GIF)$")) {
			return "resource";
		}
		
		String authType = "unknow";
		int matchCnt = 0;
		
		if (RegxUtil.contain(url, "(Query|query|List|list|Get|get|Detail|detail)[^.]*.(jsp|do|action|html)")) {
			authType = "read";
			matchCnt++;
		}
		if (RegxUtil.contain(url, "(Add|add)[^.]*.(jsp|do|action|html)")) {
			authType = "add";
			matchCnt++;
		}
		if (RegxUtil.contain(url, "(Update|update|Save|save|Confirm|confirm)[^.]*.(jsp|do|action|html)")) {
			authType = "update";
			matchCnt++;
		}
		if (RegxUtil.contain(url, "(Delete|delete|Del|del)[^.]*.(jsp|do|action|html)")) {
			authType = "delete";
			matchCnt++;
		}
		
		if (matchCnt > 1) {
			return "unknow";
		} else {
			return authType;
		}
	}

	public boolean getAuthControlledFlag(String url) {
		
		if (url == null) {
			return false;
		}
		String buf = url.trim().toLowerCase();
		if (buf.endsWith(".js") || buf.endsWith(".css") 
			|| buf.endsWith(".jpg") || buf.endsWith(".jpeg") || buf.endsWith(".gif") || buf.endsWith(".png")) {
			return false;
		}
		return true;
	}
	
	public SysAuthorityDao getSysAuthorityDao() {
		return sysAuthorityDao;
	}

	public void setSysAuthorityDao(SysAuthorityDao sysAuthorityDao) {
		this.sysAuthorityDao = sysAuthorityDao;
	}
	
}

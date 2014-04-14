/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.action.SysRoleAction.java
 * 所含类: SysRoleAction.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2014-03-05	kelvem			创建文件，实现基本功能
 * ============================================================*/
 package com.kelvem.sample.system.action;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kelvem.common.base.ActionBase;
import com.kelvem.common.model.PageResults;
import com.kelvem.sample.system.cache.MenuCache;
import com.kelvem.sample.system.model.SysRoleModel;
import com.kelvem.sample.system.queryvo.SysRoleInVO;
import com.kelvem.sample.system.service.SysRoleService;

/**
 * <p>SysRoleAction</p>
 *
 * <p>角色管理模块Action</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysRoleAction
 * @author kelvem
 * @version 1.0
 */
@Component("sysRoleAction")
@Scope("prototype")
public class SysRoleAction extends ActionBase {

// SysRole -> SysRole
// sysRole -> sysRole
// 角色           -> 角色
	
	private static Log log = LogFactory.getLog(SysRoleAction.class);
	
	private static final long serialVersionUID = 1L;
	
	@Autowired SysRoleService sysRoleService;
	@Autowired MenuCache menuCache;
	
	private PageResults<SysRoleModel> pageResult;
	private SysRoleInVO queryVo;
	private SysRoleModel sysRole;
	private List<Integer> selected;
	
	public SysRoleAction(){
		super();
	}
	
	// http://localhost:8080/sshsample/page/system/sysRole_sysRoleList.action
	public String sysRoleList() throws Exception {

		queryPageResult();
		return SUCCESS;
	}
	
	// http://localhost:8080/sshsample/page/system/sysRole_sysUserRoleList.action
	public String sysUserRoleList() throws Exception {

		queryPageResult();
		return SUCCESS;
	}
	
	// http://localhost:8080/sshsample/page/system/sysRole_sysUserRoleList.action
	public String sysUserRoleUpdate() throws Exception {

		System.out.println("selected = " + this.selected);
		queryPageResult();
		return SUCCESS;
	}
	
	// http://localhost:8080/sshsample/page/system/sysRole_sysRoleList.action
	public String sysRoleRoleList() throws Exception {

		queryPageResult();
		return SUCCESS;
	}

	// http://localhost:8080/SSH2Sample/page/system/sysRole_sysRoleDetail.action?sysRole.sysRoleId=1
	public String sysRoleDetail() throws Exception {

		Integer sysRoleId = sysRole.getSysRoleId();
		this.sysRole = sysRoleService.getSysRoleById(sysRoleId);
		
		return SUCCESS;
	}

	// http://localhost:8080/SSH2Sample/page/system/sysRole_sysRoleUpdate.action?sysRole.sysRoleId=1
	public String sysRoleUpdate() throws Exception {

		Integer sysRoleId = sysRole.getSysRoleId();
		this.sysRole = sysRoleService.getSysRoleById(sysRoleId);
		
		return SUCCESS;
	}

	// http://localhost:8080/SSH2Sample/page/system/sysRole_sysRoleUpdateDone.action?sysRole.sysRoleId=1
	public String sysRoleUpdateDone() throws Exception {

		try {
			log.info("Update SysRole id = " + sysRole.getSysRoleId());
			
			sysRoleService.updateSysRole(this.sysRole);
			super.showMsg("更改角色成功(角色ID=" + this.sysRole.getSysRoleId() + ")");
			
		} catch (Exception e) {
			String errMsg = "更改角色失败 (Id:" + sysRole.getSysRoleId() + ")";
			log.info(errMsg, e);
			this.showMsg(errMsg);
		}
		
		queryPageResult();
		
		return LIST;
	}

	// http://localhost:8080/SSH2Sample/page/system/sysRole_sysRoleAdd.action
	public String sysRoleAdd() throws Exception {
		
		return SUCCESS;
	}

	// http://localhost:8080/SSH2Sample/page/system/sysRole_sysRoleAddDone.action
	public String sysRoleAddDone() throws Exception {

		try {
			log.info("Add SysRole name = " + sysRole.getSysRoleName());

			sysRoleService.saveSysRole(this.sysRole);
			super.showMsg("增加角色成功(Id:" + sysRole.getSysRoleId() + ")");
			
		} catch (Exception e) {
			String errMsg = "增加角色失败 (Name:" + sysRole.getSysRoleName() + ")";
			log.info(errMsg, e);
			this.showMsg(errMsg);
		}
		
		queryPageResult();
		
		return LIST;
	}

	// http://localhost:8080/SSH2Sample/page/system/sysRole_sysRoleDel.action?sysRole.sysRoleId=4
	public String sysRoleDel() throws Exception {

		try {
			log.info("Delete SysRole id = " + sysRole.getSysRoleId());
			SysRoleModel delSysRole = sysRoleService.getSysRoleById(sysRole.getSysRoleId());
		
			sysRoleService.deleteSysRole(delSysRole);
			this.showMsg("删除角色成功 (Id:" + sysRole.getSysRoleId() + ")");
		} catch (Exception e) {
			String errMsg = "删除角色失败 (Id:" + sysRole.getSysRoleId() + ")";
			log.info(errMsg, e);
			this.showMsg(errMsg);
		}
		
		queryPageResult();
		
		return LIST;
	}
	
	private void queryPageResult(){
//		pageResult = sysRoleService.querySysRole(super.getPageNo(), super.getPageSize(), queryVo);
		pageResult = sysRoleService.querySysRole(super.getPageNo(), 999, queryVo);
	}

	//http://localhost:8080/SSH2Sample/page/sysRole_listSysRoleJson.action
	public String listSysRoleJson() throws Exception {
		
		pageResult = sysRoleService.querySysRole(super.getPageNo(), 10, null);
		
		this.setJsonData(pageResult);
		
		return "json";
	}
	
	public PageResults<SysRoleModel> getPageResult() {
		return pageResult;
	}
	public void setPageResult(PageResults<SysRoleModel> pageResult) {
		this.pageResult = pageResult;
	}

	public SysRoleInVO getQueryVo() {
		return queryVo;
	}
	public void setQueryVo(SysRoleInVO queryVo) {
		this.queryVo = queryVo;
	}

	public SysRoleModel getSysRole() {
		return sysRole;
	}
	public void setSysRole(SysRoleModel sysRole) {
		this.sysRole = sysRole;
	}

	public List<Integer> getSelected() {
		return selected;
	}
	public void setSelected(List<Integer> selected) {
		this.selected = selected;
	}

}

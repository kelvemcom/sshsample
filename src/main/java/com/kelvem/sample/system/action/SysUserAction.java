/**============================================================
 * 版权：kelvem.com 版权所有 (c) 2012 - 2014
 * 文件：com.kelvem.sample.system.action.SysUserAction.java
 * 所含类: SysUserAction.java
 * 修改记录：
 * 日期                           作者                            内容
 * =============================================================
 * 2013-11-10	kelvem			创建文件，实现基本功能
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
import com.kelvem.sample.system.model.SysUserModel;
import com.kelvem.sample.system.queryvo.SysUserInVO;
import com.kelvem.sample.system.service.SysUserService;

/**
 * <p>SysUserAction</p>
 *
 * <p>用户管理模块Action</p>
 *
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem.com</p>
 * 
 * @ClassName SysUserAction
 * @author kelvem
 * @version 1.0
 */
@Component("sysUserAction")
@Scope("prototype")
public class SysUserAction extends ActionBase {

// SysUser -> SysUser
// sysUser -> sysUser
// 用户           -> 用户
	
	private static Log log = LogFactory.getLog(SysUserAction.class);
	
	private static final long serialVersionUID = 1L;
	
	@Autowired SysUserService sysUserService;
	@Autowired MenuCache menuCache;
	
	private PageResults<SysUserModel> pageResult;
	private SysUserInVO queryVo;
	private SysUserModel sysUser;
	private List<Integer> selected;
	
	public SysUserAction(){
		super();
	}
	
	// http://localhost:8080/sshsample/page/system/sysUser_sysUserList.action
	public String sysUserList() throws Exception {

		queryPageResult();
		return SUCCESS;
	}

	// http://localhost:8080/SSH2Sample/page/system/sysUser_sysUserDetail.action?sysUser.sysUserId=1
	public String sysUserDetail() throws Exception {

		Integer sysUserId = sysUser.getSysUserId();
		this.sysUser = sysUserService.getSysUserById(sysUserId);
		
		return SUCCESS;
	}

	// http://localhost:8080/SSH2Sample/page/system/sysUser_sysUserUpdate.action?sysUser.sysUserId=1
	public String sysUserUpdate() throws Exception {

		Integer sysUserId = sysUser.getSysUserId();
		this.sysUser = sysUserService.getSysUserById(sysUserId);
		
		return SUCCESS;
	}

	// http://localhost:8080/SSH2Sample/page/system/sysUser_sysUserUpdateDone.action?sysUser.sysUserId=1
	public String sysUserUpdateDone() throws Exception {

		try {
			log.info("Update SysUser id = " + sysUser.getSysUserId());
			
			sysUserService.updateSysUser(this.sysUser);
			super.showMsg("更改用户成功(用户ID=" + this.sysUser.getSysUserId() + ")");
			
		} catch (Exception e) {
			String errMsg = "更改用户失败 (Id:" + sysUser.getSysUserId() + ")";
			log.info(errMsg, e);
			this.showMsg(errMsg);
		}
		
		queryPageResult();
		
		return LIST;
	}

	// http://localhost:8080/SSH2Sample/page/system/sysUser_sysUserAdd.action
	public String sysUserAdd() throws Exception {
		
		return SUCCESS;
	}

	// http://localhost:8080/SSH2Sample/page/system/sysUser_sysUserAddDone.action
	public String sysUserAddDone() throws Exception {

		try {
			log.info("Add SysUser name = " + sysUser.getSysUserName());

			sysUserService.saveSysUser(this.sysUser);
			super.showMsg("增加用户成功(Id:" + sysUser.getSysUserId() + ")");
			
		} catch (Exception e) {
			String errMsg = "增加用户失败 (Name:" + sysUser.getSysUserName() + ")";
			log.info(errMsg, e);
			this.showMsg(errMsg);
		}
		
		queryPageResult();
		
		return LIST;
	}

	// http://localhost:8080/SSH2Sample/page/system/sysUser_sysUserDel.action?sysUser.sysUserId=4
	public String sysUserDel() throws Exception {

		try {
			log.info("Delete SysUser id = " + sysUser.getSysUserId());
			SysUserModel delSysUser = sysUserService.getSysUserById(sysUser.getSysUserId());
		
			sysUserService.deleteSysUser(delSysUser);
			this.showMsg("删除用户成功 (Id:" + sysUser.getSysUserId() + ")");
		} catch (Exception e) {
			String errMsg = "删除用户失败 (Id:" + sysUser.getSysUserId() + ")";
			log.info(errMsg, e);
			this.showMsg(errMsg);
		}
		
		queryPageResult();
		
		return LIST;
	}
	
	private void queryPageResult(){
//		pageResult = sysUserService.querySysUser(super.getPageNo(), super.getPageSize(), queryVo);
		pageResult = sysUserService.querySysUser(super.getPageNo(), 10, queryVo);
	}

	//http://localhost:8080/SSH2Sample/page/sysUser_listSysUserJson.action
	public String listSysUserJson() throws Exception {
		
		pageResult = sysUserService.querySysUser(super.getPageNo(), 10, null);
		
		this.setJsonData(pageResult);
		
		return "json";
	}
	
	public PageResults<SysUserModel> getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResults<SysUserModel> pageResult) {
		this.pageResult = pageResult;
	}

	public SysUserInVO getQueryVo() {
		return queryVo;
	}

	public void setQueryVo(SysUserInVO queryVo) {
		this.queryVo = queryVo;
	}

	public SysUserModel getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserModel sysUser) {
		this.sysUser = sysUser;
	}

	public List<Integer> getSelected() {
		return selected;
	}

	public void setSelected(List<Integer> selected) {
		this.selected = selected;
	}

}

package com.kelvem.sample.quartz;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kelvem.common.base.QuartzJobDetailBase;
import com.kelvem.sample.system.service.SysUserService;

@Component("sampleJobV3")
public class SampleJobV3 extends QuartzJobDetailBase {

	private static final long serialVersionUID = 7745338819946081351L;
	
	private SysUserService sysUserService;
	
	public void execute() {
		System.out.println(new Date());
	}

	@Override
	protected boolean isConcurrent() {
		return false;
	}

	public SysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

}

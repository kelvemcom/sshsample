package com.kelvem.sample.quartz;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.kelvem.sample.system.service.SysUserService;

@Component("sampleJobV4")
public class SampleJobV4 extends QuartzJobBean {

	@Autowired
	private SysUserService sysUserService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		System.out.println(new Date());
	}

}

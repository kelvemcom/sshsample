/**============================================================
 * 修改记录：
 * 日期                           	作者                            内容
 * =============================================================
 * 2014-04-24		kelvem		       创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.common.base;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

/**
 * <p>QuartzJobDetailBase</p>
 * 
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName QuartzJobDetailBase
 * @author kelvem
 * @version 1.0
 */
public abstract class QuartzJobDetailBase extends MethodInvokingJobDetailFactoryBean implements Serializable {
	
	private static final long serialVersionUID = 8232746752577785172L;

	// private static Log log = LogFactory.getLog(QuartzJobDetailBase.class);
	
	public QuartzJobDetailBase(){
		this.setTargetObject(this);
		super.setTargetMethod("execute");
		super.setConcurrent(isConcurrent());
	}

	protected abstract boolean isConcurrent();
	
	protected abstract void execute();
}

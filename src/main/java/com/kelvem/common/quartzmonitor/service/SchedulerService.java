package com.kelvem.common.quartzmonitor.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.kelvem.common.quartzmonitor.dao.QuartzDao;

@Component("schedulerService")
public class SchedulerService implements ISchedulerService {
	
	private static Log log = LogFactory.getLog(SchedulerService.class);

	private SchedulerFactoryBean schedulerFactoryBean;
	private Scheduler scheduler;
	private QuartzDao quartzDao;
	
	private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);

	@Autowired
	public void setScheduler(@Qualifier("quartzSchedulerFactoryDB") SchedulerFactoryBean schedulerFactoryBean) {
		this.schedulerFactoryBean = schedulerFactoryBean;
		this.scheduler = this.schedulerFactoryBean.getScheduler();
	}

	@Autowired	
	public void setQuartzDao(@Qualifier("quartzDao")QuartzDao quartzDao) {
		this.quartzDao = quartzDao;
	}

	
	public void schedule(String cronExpression) {
		schedule("", cronExpression);
	}

	public void schedule(String name, String cronExpression) {
		schedule( name,  cronExpression,Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(String name, String cronExpression,String group) {
		try {
			schedule(name, new CronExpression(cronExpression),group);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public void schedule(CronExpression cronExpression) {
		schedule(null, cronExpression);
	}

	public void schedule(String name, CronExpression cronExpression) {
		schedule( name,  cronExpression,Scheduler.DEFAULT_GROUP) ;
	}
	
	public void schedule(String name, CronExpression cronExpression,String group) {
		if (name == null || name.trim().equals("")) {
			name = UUID.randomUUID().toString();
		}else{
			//在名称后添加UUID，保证名称的唯一性
			name +="&"+UUID.randomUUID().toString();
		}

		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		
		try {
			for (String buf : scheduler.getCalendarNames()) {
				System.out.println(buf);
			}	
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
		
		try {
//			// com.kelvem.sample.quartz.SampleJobV4
			System.out.println(scheduler.getJobKeys(null).size());
			JobKey key = scheduler.getJobKeys(null).iterator().next();
			JobDetail jobDetail = scheduler.getJobDetail(key);
			System.out.println(jobDetail.getDescription());
			System.out.println(jobDetail.getClass());
			System.out.println(jobDetail.getJobClass());
			System.out.println(jobDetail.getJobDataMap());
			System.out.println(jobDetail);
//			scheduler.addJob(jobDetail, true);
//
//			CronTrigger cronTrigger = new CronTrigger(name, group, jobDetail.getName(),
//					Scheduler.DEFAULT_GROUP);
//			cronTrigger.setCronExpression(cronExpression);
//			scheduler.scheduleJob(cronTrigger);
//			scheduler.rescheduleJob(cronTrigger.getName(), cronTrigger.getGroup(), cronTrigger);
//			
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	public void schedule(Date startTime) {
		schedule(startTime, Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(Date startTime,String group) {
		schedule(startTime, null,group);
	}

	public void schedule(String name, Date startTime) {
		schedule(name, startTime,Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(String name, Date startTime,String group) {
		schedule(name, startTime, null,group);
	}

	public void schedule(Date startTime, Date endTime) {
		schedule(startTime, endTime, Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(Date startTime, Date endTime,String group) {
		schedule(startTime, endTime, 0,group);
	}

	public void schedule(String name, Date startTime, Date endTime) {
		schedule(name, startTime, endTime,Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(String name, Date startTime, Date endTime,String group) {
		schedule(name, startTime, endTime, 0,group);
	}

	public void schedule(Date startTime, Date endTime, int repeatCount) {
		schedule( startTime, endTime, 0,Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(Date startTime, Date endTime, int repeatCount,String group) {
		schedule(null, startTime, endTime, 0,group);
	}

	public void schedule(String name, Date startTime, Date endTime, int repeatCount) {
		schedule(name, startTime, endTime, 0, Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(String name, Date startTime, Date endTime, int repeatCount,String group) {
		schedule(name, startTime, endTime, 0, 1L,group);
	}

	public void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval) {
		schedule(startTime, endTime, repeatCount, repeatInterval,Scheduler.DEFAULT_GROUP);
	}
	
	public void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval,String group) {
		schedule(null, startTime, endTime, repeatCount, repeatInterval,group);
	}

	public void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval) {
		this.schedule( name , startTime,  endTime,  repeatCount,  repeatInterval,  Scheduler.DEFAULT_GROUP);
	}

	public void schedule(Map<String, String> map) {
		// TODO Auto-generated method stub
	}
	
	public void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval,String group ) {
		if (name == null || name.trim().equals("")) {
			name = UUID.randomUUID().toString();
		}else{
			//在名称后添加UUID，保证名称的唯一性
			name +="&"+UUID.randomUUID().toString();
		}
//
//		try {
//			scheduler.addJob(jobDetail, true);
//
//			SimpleTrigger SimpleTrigger = new SimpleTrigger(name, group, jobDetail.getName(),
//					Scheduler.DEFAULT_GROUP, startTime, endTime, repeatCount, repeatInterval);
//			scheduler.scheduleJob(SimpleTrigger);
//			scheduler.rescheduleJob(SimpleTrigger.getName(), SimpleTrigger.getGroup(), SimpleTrigger);
//		} catch (SchedulerException e) {
//			throw new RuntimeException(e);
//		}
//	}
//	
//	public void schedule(Map<String,String> map) {
//		
//		String temp = null;
//		//实例化SimpleTrigger
//		SimpleTrigger SimpleTrigger = new SimpleTrigger();
//		
//		//这些值的设置也可以从外面传入，这里采用默放值		
//		SimpleTrigger.setJobName(jobDetail.getName());		
//		SimpleTrigger.setJobGroup(Scheduler.DEFAULT_GROUP);		
//		SimpleTrigger.setRepeatInterval(1000L);
//		
//		//设置名称
//		temp = map.get(Constant.TRIGGERNAME);		
//		if (StringUtils.isEmpty(StringUtils.trim(temp)) ){
//			temp = UUID.randomUUID().toString();
//		}else{
//			//在名称后添加UUID，保证名称的唯一性
//			temp +="&"+UUID.randomUUID().toString();
//		}
//		SimpleTrigger.setName(temp);
//		
//		//设置Trigger分组
//		temp = map.get(Constant.TRIGGERGROUP);
//		if(StringUtils.isEmpty(temp)){
//			temp = Scheduler.DEFAULT_GROUP;
//		}
//		SimpleTrigger.setGroup(temp);
//		
//		//设置开始时间
//		temp = map.get(Constant.STARTTIME);
//		if(StringUtils.isNotEmpty(temp)){
//			SimpleTrigger.setStartTime(this.parseDate(temp));
//		}
//		
//		//设置结束时间
//		temp = map.get(Constant.ENDTIME);
//		if(StringUtils.isNotEmpty(temp)){
//			SimpleTrigger.setEndTime(this.parseDate(temp));
//		}
//		
//		//设置执行次数
//		temp = map.get(Constant.REPEATCOUNT);
//		if(StringUtils.isNotEmpty(temp) && NumberUtils.toInt(temp) > 0){
//			SimpleTrigger.setRepeatCount(NumberUtils.toInt(temp));
//		}
//		
//		//设置执行时间间隔
//		temp = map.get(Constant.REPEATINTERVEL);
//		if(StringUtils.isNotEmpty(temp) && NumberUtils.toLong(temp) > 0){
//			SimpleTrigger.setRepeatInterval(NumberUtils.toLong(temp)*1000);
//		}
//
//		try {
//			scheduler.addJob(jobDetail, true);
//		
//			scheduler.scheduleJob(SimpleTrigger);
//			scheduler.rescheduleJob(SimpleTrigger.getName(), SimpleTrigger.getGroup(), SimpleTrigger);
//		} catch (SchedulerException e) {
//			throw new RuntimeException(e);
//		}
	}
	
	public List<Map<String, Object>> getQrtzTriggers(){
		return quartzDao.getQrtzTriggers();
	}
	
	public void pauseTrigger(String triggerName,String group){		
		try {
			log.info("pauseTrigger params(triggerName=" + triggerName + ", group=" + group + ")");
			TriggerKey triggerKey = new TriggerKey(triggerName, group);
			
			Trigger trigger = scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				String msg = scheduler.getCalendarNames() + "中不含该Trigger,请确认";
				log.warn(msg);
				throw new RuntimeException(msg);
			}
			scheduler.pauseTrigger(triggerKey);//停止触发器
			
		} catch (SchedulerException e) {
			log.warn(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	public void resumeTrigger(String triggerName,String group){			
		try {
			log.info("resumeTrigger params(triggerName=" + triggerName + ", group=" + group + ")");
			TriggerKey triggerKey = new TriggerKey(triggerName, group);
			
			Trigger trigger = scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				String msg = scheduler.getCalendarNames() + "中不含该Trigger,请确认";
				log.warn(msg);
				throw new RuntimeException(msg);
			}
			scheduler.resumeTrigger(triggerKey);//重启触发器
			
		} catch (SchedulerException e) {
			log.warn(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	public boolean removeTrigdger(String triggerName,String group){		
		try {
			log.info("removeTrigdger params(triggerName=" + triggerName + ", group=" + group + ")");
			TriggerKey triggerKey = new TriggerKey(triggerName, group);
			
			Trigger trigger = scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				String msg = scheduler.getCalendarNames() + "中不含该Trigger,请确认";
				log.warn(msg);
				throw new RuntimeException(msg);
			}
			scheduler.pauseTrigger(triggerKey);//停止触发器
			
			return scheduler.unscheduleJob(triggerKey);//移除触发器
			
		} catch (SchedulerException e) {
			log.warn(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	private Date parseDate(String time){
		try {
			return DateUtils.parseDate(time, new String[]{"yyyy-MM-dd HH:mm"});
		} catch (ParseException e) {			
			logger.error("日期格式错误{}，正确格式为：yyyy-MM-dd HH:mm",time);
			throw new RuntimeException(e);
		}
	}
}

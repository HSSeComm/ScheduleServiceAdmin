package com.schedule.demo.service;

import static org.quartz.CronScheduleBuilder.cronSchedule;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.schedule.demo.dao.ScheduleLogDao;
import com.schedule.demo.vo.ScheduleJob;

@Component
public class QuartzManager {

	@Autowired
	private ScheduleLogDao scheduleLogDao;
	
	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();

	public ScheduleJob addJob(ScheduleJob job) throws SchedulerException {
		Scheduler sched = gSchedulerFactory.getScheduler();
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("appUrl", job.getAppUrl());
		jobDataMap.put("jobId", job.getJobId());
		jobDataMap.put("successfulCode", job.getSuccessfulCode());
		jobDataMap.put("scheduleLogDao", scheduleLogDao);
		JobDetail jobDetail = JobBuilder.newJob(job.getJobClass()).withIdentity(job.getJobName(), job.getJobGroupName())
				.usingJobData(jobDataMap).build();
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getTriggerName(), job.getTriggerGroupName())
				.withSchedule(cronSchedule(job.getCornExpr())).forJob(job.getJobName(), job.getJobGroupName()).build();
		sched.scheduleJob(jobDetail, trigger);
		sched.start();
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getTriggerName(), job.getTriggerGroupName());
		TriggerState triggerState = sched.getTriggerState(triggerKey);
		job.setStatus(triggerState.name());
		return job;
	}
	
	public void updateJobDetail(ScheduleJob job,String exsistingName) throws SchedulerException{
		if("NORMAL".equals(job.getStatus())){
			deleteJob(job,exsistingName);
			addJob(job);	
		}
	}

	public ScheduleJob pauseJob(ScheduleJob job) throws SchedulerException {
		deleteJob(job);
		return job;
	}

	public ScheduleJob resumeJob(ScheduleJob job) throws SchedulerException {
		addJob(job);
		return job;
	}

	public void deleteJob(ScheduleJob job) throws SchedulerException {
		Scheduler scheduler = gSchedulerFactory.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroupName());
		scheduler.deleteJob(jobKey);
	}
	
	public void deleteJob(ScheduleJob job,String exsistingName) throws SchedulerException {
		Scheduler scheduler = gSchedulerFactory.getScheduler();
		JobKey jobKey = JobKey.jobKey(exsistingName, job.getJobGroupName());
		scheduler.deleteJob(jobKey);
	}
}

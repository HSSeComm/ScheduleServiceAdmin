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
import org.springframework.stereotype.Component;

import com.schedule.demo.vo.ScheduleJob;

@Component
public class QuartzManager {

	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();

	public ScheduleJob addJob(ScheduleJob job) throws SchedulerException {
		Scheduler sched = gSchedulerFactory.getScheduler();
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("appUrl", job.getAppUrl());
		jobDataMap.put("successfulCode", job.getSuccessfulCode());
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

	public void updateJobCron(ScheduleJob job) throws SchedulerException {
		Scheduler scheduler = gSchedulerFactory.getScheduler();

		TriggerKey triggerKey = TriggerKey.triggerKey(job.getTriggerName(), job.getTriggerGroupName());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCornExpr());

		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

		scheduler.rescheduleJob(triggerKey, trigger);

	}

	public ScheduleJob pauseJob(ScheduleJob job) throws SchedulerException {
		Scheduler scheduler = gSchedulerFactory.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getTriggerName(), job.getTriggerGroupName());
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroupName());
		scheduler.pauseJob(jobKey);
		TriggerState triggerState = scheduler.getTriggerState(triggerKey);
		job.setStatus(triggerState.name());
		return job;
	}

	public ScheduleJob resumeJob(ScheduleJob job) throws SchedulerException {
		Scheduler scheduler = gSchedulerFactory.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getTriggerName(), job.getTriggerGroupName());
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroupName());
		scheduler.resumeJob(jobKey);
		TriggerState triggerState = scheduler.getTriggerState(triggerKey);
		job.setStatus(triggerState.name());
		return job;
	}

	public void deleteJob(ScheduleJob job) throws SchedulerException {
		Scheduler scheduler = gSchedulerFactory.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroupName());
		scheduler.deleteJob(jobKey);
	}
}

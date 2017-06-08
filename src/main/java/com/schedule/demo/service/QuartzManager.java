package com.schedule.demo.service;

import static org.quartz.CronScheduleBuilder.cronSchedule;

import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import com.schedule.demo.jobs.SimpleJob;
import com.schedule.demo.vo.ScheduleJob;

@Component
public class QuartzManager {

	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
	private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";

	public void addJob(ScheduleJob job) throws SchedulerException {
		Scheduler sched = gSchedulerFactory.getScheduler();
		JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity(job.getJobName(), job.getJobGroupName())
				.build();
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getTriggerName(), job.getTriggerGroupName())
				.withSchedule(cronSchedule(job.getCornExpr())).forJob(job.getJobName(), job.getJobGroupName()).build();
		sched.scheduleJob(trigger);
		sched.start();
	}

}

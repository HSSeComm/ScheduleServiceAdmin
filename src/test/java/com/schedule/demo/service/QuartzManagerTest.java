package com.schedule.demo.service;

import org.junit.Test;
import org.quartz.SchedulerException;

import com.schedule.demo.jobs.SimpleJob;
import com.schedule.demo.vo.ScheduleJob;

public class QuartzManagerTest {
	
	@Test
	public void testAddJob() throws SchedulerException{
		ScheduleJob job = new ScheduleJob();
		job.setJobName("test");
		job.setJobClass(SimpleJob.class);
		job.setCornExpr("*/5 * * * * ?");
		job.setJobGroupName("test");
		job.setTriggerGroupName("test");
		job.setTriggerName("abc");
		
		QuartzManager quartzManager = new QuartzManager();
		quartzManager.addJob(job);
		
		while(true){
			
		}
	}
}

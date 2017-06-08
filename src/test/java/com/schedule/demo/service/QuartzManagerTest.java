package com.schedule.demo.service;

import org.junit.Test;
import org.quartz.SchedulerException;

import com.schedule.demo.jobs.SimpleJob;
import com.schedule.demo.vo.ScheduleJob;

public class QuartzManagerTest {
	
	@Test
	public void testAddJob() throws SchedulerException, InterruptedException{
		ScheduleJob job = new ScheduleJob();
		job.setJobId("123456");
		job.setJobClass(SimpleJob.class);
		job.setCornExpr("*/5 * * * * ?");
		job.setJobGroupName("test");
		job.setTriggerGroupName("test123");
		job.setTriggerName("456");
		
		QuartzManager quartzManager = new QuartzManager();
		quartzManager.addJob(job);
		
		Thread.sleep(10000);
		
		job.setCornExpr("*/1 * * * * ?");
		quartzManager.updateJobCron(job);
		System.out.println(job.getStatus());
		
		Thread.sleep(5000);
		
		quartzManager.pauseJob(job);
		System.out.println(job.getStatus());
		
		Thread.sleep(5000);
		
		quartzManager.resumeJob(job);
		System.out.println(job.getStatus());
		
		Thread.sleep(5000);
		
		quartzManager.deleteJob(job);
		
		while(true){
			
		}
	}
}

package com.schedule.demo.service;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.schedule.demo.jobs.SimpleJob;
import com.schedule.demo.vo.ScheduleJob;

@Component
public class ScheduleJobService {

	@Autowired
	private QuartzManager quartzManager;

	public boolean addScheduleJob(ScheduleJob job) {
		if (job == null) {
			return false;
		}
		job.setJobClass(SimpleJob.class);
		job.setTriggerName("testTriggerName");
		job.setTriggerGroupName("testTriggerGroupName");
		job.setJobGroupName("testJobGroupName");
		boolean successful = false;
		// save job information to db

		// submit to ScheduleService
		try {
			quartzManager.addJob(job);
			successful = true;
		} catch (SchedulerException e) {
			successful = false;
		}
		return successful;
	}

}

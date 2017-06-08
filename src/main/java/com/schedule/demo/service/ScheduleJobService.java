package com.schedule.demo.service;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.schedule.demo.vo.ScheduleJob;

@Component
public class ScheduleJobService {

	@Autowired
	private QuartzManager quartzManager;

	public boolean addScheduleJob(ScheduleJob job) {
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

package com.schedule.demo.service;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.schedule.demo.jobs.SimpleJob;
import com.schedule.demo.vo.ScheduleJob;

@Component
public class ScheduleJobService {

	@Autowired
	private QuartzManager quartzManager;

	public List<ScheduleJob> getJobs() {

		return null;
	}

	public ScheduleJob getScheduleJob(Long id) {
		ScheduleJob ScheduleJobs = null;

		return ScheduleJobs;
	}

	public boolean addScheduleJob(ScheduleJob job) {
		if (job == null) {
			return false;
		}
		job.setJobClass(SimpleJob.class);
		long current = System.currentTimeMillis();
		// job.setJobId(current);
		job.setTriggerName("testTriggerName-" + current);
		job.setTriggerGroupName("testTriggerGroupName");
		job.setJobGroupName("testJobGroupName-" + current);
		boolean isSuccessful = false;
		// save job information to db

		// submit to ScheduleService
		try {
			quartzManager.addJob(job);
			isSuccessful = true;
		} catch (SchedulerException e) {
			isSuccessful = false;
		}
		return isSuccessful;
	}

	public boolean updateScheduleJob(ScheduleJob job) {
		if (job == null) {
			return false;
		}
		boolean successful = false;
		// get job details by id

		// populate job updated details

		// update latest in db

		// update to ScheduleService
		try {
			quartzManager.updateJobCron(job);
			successful = true;
		} catch (SchedulerException e) {
			successful = false;
		}
		return successful;
	}

	public boolean changeStatus(Long id, String status) {
		boolean successful = false;
		// get job details by id

		// populate job updated details

		// update latest in db

		// update to ScheduleService
		try {
			ScheduleJob job = new ScheduleJob();
			if ("NORMAL".equals(status)) {
				quartzManager.resumeJob(job);
			} else if ("PAUSE".equals(status)) {
				quartzManager.pauseJob(job);
			}
			successful = true;
		} catch (SchedulerException e) {
			successful = false;
		}
		return successful;
	}

	public boolean deleteStatus(Long id) {
		boolean successful = false;
		// get job details by id

		// delete job in db

		// update to ScheduleService
		try {
			ScheduleJob job = new ScheduleJob();
			quartzManager.deleteJob(job);
			successful = true;
		} catch (SchedulerException e) {
			successful = false;
		}
		return successful;
	}

}

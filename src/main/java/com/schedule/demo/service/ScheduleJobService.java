package com.schedule.demo.service;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.schedule.demo.dao.ScheduleJobDao;
import com.schedule.demo.jobs.SimpleJob;
import com.schedule.demo.vo.ScheduleJob;

@Component
public class ScheduleJobService {

	@Autowired
	private QuartzManager quartzManager;
	@Autowired
	private ScheduleJobDao scheduleJobDao;

	public List<ScheduleJob> getJobs() {
		return scheduleJobDao.queryScheduleJobs();
	}

	public ScheduleJob getScheduleJob(Long id) {
		return scheduleJobDao.getScheduleJobById(id);
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
		job.setJobGroupName("G-" + job.getJobName());
		boolean isSuccessful = false;
		// save job information to db
		Long jobId = scheduleJobDao.insert(job);
		job.setJobId(jobId);
		// submit to ScheduleService
		try {
			quartzManager.addJob(job);
			isSuccessful = true;
		} catch (SchedulerException e) {
			isSuccessful = false;
		}
		return isSuccessful;
	}

	public boolean updateScheduleJob(Long id, ScheduleJob job) {
		if (job == null) {
			return false;
		}
		boolean successful = false;
		// get job details by id
		ScheduleJob scheduleJob = scheduleJobDao.getScheduleJobById(id);
		// populate job updated details
		scheduleJob.setAppUrl(job.getAppUrl());
		scheduleJob.setSuccessfulCode(job.getSuccessfulCode());
		scheduleJob.setJobName(job.getJobName());
		scheduleJob.setHttpMehtod(job.getHttpMehtod());
		// update latest in db
		scheduleJobDao.update(scheduleJob);
		// update to ScheduleService
		try {
			quartzManager.updateJobCron(scheduleJob);
			successful = true;
		} catch (SchedulerException e) {
			successful = false;
		}
		return successful;
	}

	public boolean changeStatus(Long id, String status) {
		boolean successful = false;
		// get job details by id
		ScheduleJob scheduleJob = scheduleJobDao.getScheduleJobById(id);
		// update latest in db
		scheduleJob.setStatus(status);
		scheduleJobDao.update(scheduleJob);
		// update to ScheduleService
		try {
			if ("NORMAL".equals(status)) {
				quartzManager.resumeJob(scheduleJob);
			} else if ("PAUSE".equals(status)) {
				quartzManager.pauseJob(scheduleJob);
			}
			successful = true;
		} catch (SchedulerException e) {
			successful = false;
		}
		return successful;
	}

	public boolean deleteJob(Long id) {
		boolean successful = false;
		// get job details by id
		ScheduleJob scheduleJob = scheduleJobDao.getScheduleJobById(id);
		// delete job in db
		scheduleJobDao.delete(id);
		// update to ScheduleService
		try {
			quartzManager.deleteJob(scheduleJob);
			successful = true;
		} catch (SchedulerException e) {
			successful = false;
		}
		return successful;
	}

}

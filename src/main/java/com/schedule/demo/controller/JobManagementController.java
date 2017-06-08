package com.schedule.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.schedule.demo.service.ScheduleJobService;
import com.schedule.demo.vo.ScheduleJob;

@RestController
@RequestMapping("/ScheduleJobs")
public class JobManagementController {

	@Autowired
	private ScheduleJobService scheduleJobService;

	/**
	 * Get All jobs
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody List<ScheduleJob> getJobs() {

		return scheduleJobService.getJobs();
	}

	/**
	 * Get a single job details
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ScheduleJob getScheduleJob(@PathVariable Long id) {
		ScheduleJob ScheduleJobs = null;

		return ScheduleJobs;
	}

	/**
	 * 
	 * @param scheduleJobs
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createScheduleJob(@RequestBody ScheduleJob scheduleJob) {
		boolean saveStatus = scheduleJobService.addScheduleJob(scheduleJob);
		return saveStatus ? "success" : "failed";
	}

	/**
	 * Remove a job
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteScheduleJobs(@PathVariable Long id) {

		return "success";
	}

	/**
	 * Active/Suspend job
	 * 
	 * @param id
	 * @param ScheduleJob
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public String changeScheduleJobStatus(@PathVariable Long id, @RequestBody ScheduleJob scheduleJob) {

		return "success";
	}

	/**
	 * Update job information
	 * 
	 * @param id
	 * @param ScheduleJob
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String updateScheduleJobDetails(@PathVariable long id, @RequestBody ScheduleJob scheduleJob) {

		return "success";
	}
}

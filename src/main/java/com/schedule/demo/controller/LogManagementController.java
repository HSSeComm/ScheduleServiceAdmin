package com.schedule.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.schedule.demo.service.ScheduleLogService;
import com.schedule.demo.vo.ScheduleLog;

@RestController
@RequestMapping("/ScheduleLogs")
public class LogManagementController {
	
	@Autowired
	private ScheduleLogService scheduleLogService;

	/**
	 * Get All jobs
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody List<ScheduleLog> getFailCount() {

		return scheduleLogService.countFail();
	}
}

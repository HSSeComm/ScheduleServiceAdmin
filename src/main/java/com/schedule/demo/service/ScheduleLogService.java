package com.schedule.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.schedule.demo.dao.ScheduleLogDao;
import com.schedule.demo.vo.ScheduleLog;

@Component
public class ScheduleLogService {

	@Autowired
	private ScheduleLogDao scheduleLogDao;
	
	public List<ScheduleLog> countFail() {
		return scheduleLogDao.countFail();
	}
}

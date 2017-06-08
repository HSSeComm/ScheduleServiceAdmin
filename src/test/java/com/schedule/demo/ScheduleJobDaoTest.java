package com.schedule.demo;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.schedule.demo.dao.ScheduleJobDao;
import com.schedule.demo.vo.ScheduleJob;

public class ScheduleJobDaoTest extends BaseJunit4Test{
	@Resource  
    private ScheduleJobDao scheduleJobDao;  
 
	@Test // 标明是测试方法
	public void insert() {
		ScheduleJob scheduleJob=new ScheduleJob();
		scheduleJob.setJobName("test1");
		scheduleJob.setCornExpr("test2");
		int id=scheduleJobDao.insert(scheduleJob);
	    System.out.println(id);
	}
	
	@Test // 标明是测试方法
	public void list() {
		ScheduleJob scheduleJob=new ScheduleJob();
		scheduleJob.setJobName("test1");
		scheduleJob.setCornExpr("test2");
		List<ScheduleJob> list=scheduleJobDao.queryScheduleJobs();
	    System.out.println(list.size());
	}
}

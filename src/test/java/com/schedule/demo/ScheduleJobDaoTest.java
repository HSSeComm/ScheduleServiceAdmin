package com.schedule.demo;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.schedule.demo.dao.ScheduleJobDao;
import com.schedule.demo.vo.ScheduleJob;

public class ScheduleJobDaoTest extends BaseJunit4Test{
	@Resource  
    private ScheduleJobDao scheduleJobDao;  
 
	@Test 
	public void insert() {
		ScheduleJob scheduleJob=new ScheduleJob();
		scheduleJob.setJobName("test1");
		scheduleJob.setCornExpr("test2");
		long id=scheduleJobDao.insert(scheduleJob);
	    System.out.println(id);
	}
	
	@Test 
	public void update() {
		ScheduleJob scheduleJob=new ScheduleJob();
		scheduleJob.setJobId(1L);
		scheduleJob.setJobName("test");
		scheduleJob.setCornExpr("test3");
		long id=scheduleJobDao.update(scheduleJob);
	    System.out.println(id);
	}
	
	@Test 
	public void list() {
		ScheduleJob scheduleJob=new ScheduleJob();
		scheduleJob.setJobName("test1");
		scheduleJob.setCornExpr("test2");
		List<ScheduleJob> list=scheduleJobDao.queryScheduleJobs();
	    System.out.println(list.size());
	}
	
	@Test 
	public void getById() {
		ScheduleJob job=scheduleJobDao.getScheduleJobById(1L);
	    
	}
	
	@Test 
	public void delete() {
		int flag=scheduleJobDao.delete(2L);
		System.out.println(flag);
	}
}

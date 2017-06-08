package com.schedule.demo;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.schedule.demo.dao.ScheduleJobDao;
import com.schedule.demo.vo.ScheduleJob;

public class ScheduleJobDaoTest extends BaseJunit4Test{
	@Resource  
    private ScheduleJobDao scheduleJobDao;  
 
	@Test // 标明是测试方法
	@Transactional // 标明此方法需使用事务
	@Rollback(false) // 标明使用完此方法后事务不回滚,true时为回滚
	public void insert() {
		ScheduleJob scheduleJob=new ScheduleJob();
		scheduleJob.setJobName("test");
		scheduleJob.setCornExpr("test");
		int id=scheduleJobDao.insert(scheduleJob);
	    System.out.println(id);
	}
}

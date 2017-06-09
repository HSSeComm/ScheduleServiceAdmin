package com.schedule.demo;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.schedule.demo.dao.ScheduleJobDao;
import com.schedule.demo.vo.ScheduleJob;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ScheduleJobDaoTest {
	@Resource
	private ScheduleJobDao scheduleJobDao;

	@Test
	public void insert() {
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobName("test1");
		scheduleJob.setCornExpr("test2");
		Long id = scheduleJobDao.insert(scheduleJob);
		Assert.assertTrue(id>0);
	}

	@Test
	public void update() {
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobId(1L);
		scheduleJob.setJobName("test");
		scheduleJob.setCornExpr("test3");
		long id = scheduleJobDao.update(scheduleJob);
		System.out.println(id);
	}

	@Test
	public void list() {
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobName("test1");
		scheduleJob.setCornExpr("test2");
		List<ScheduleJob> list = scheduleJobDao.queryScheduleJobs();
		System.out.println(list.size());
	}

	@Test
	public void getById() {
		ScheduleJob job = scheduleJobDao.getScheduleJobById(1L);

	}

	@Test
	public void delete() {
		int flag = scheduleJobDao.delete(2L);
		System.out.println(flag);
	}
}

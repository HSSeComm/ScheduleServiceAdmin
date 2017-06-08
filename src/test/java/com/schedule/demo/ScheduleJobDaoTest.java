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
 
	@Test // �����ǲ��Է���
	@Transactional // �����˷�����ʹ������
	@Rollback(false) // ����ʹ����˷��������񲻻ع�,trueʱΪ�ع�
	public void insert() {
		ScheduleJob scheduleJob=new ScheduleJob();
		scheduleJob.setJobName("test");
		scheduleJob.setCornExpr("test");
		int id=scheduleJobDao.insert(scheduleJob);
	    System.out.println(id);
	}
}

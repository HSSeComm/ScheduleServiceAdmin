package com.schedule.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.schedule.demo.service.ScheduleJobService;

@SpringBootApplication
public class ScheduleServiceAdminApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(ScheduleServiceAdminApplication.class, args);
		run.getBean(ScheduleJobService.class).rebuildJobs();
	}
}

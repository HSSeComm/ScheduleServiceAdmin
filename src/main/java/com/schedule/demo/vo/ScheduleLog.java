package com.schedule.demo.vo;

import java.sql.Date;

public class ScheduleLog {
	private Long logId;
	private Long jobId;
	private String jobName;
	private String appUrl;
	private String httpMehtod;
	private String successfulCode;
	private String insertDate;
	
	public ScheduleLog(){
		
	}

	public ScheduleLog(Long logId, Long jobId) {
		super();
		this.logId = logId;
		this.jobId = jobId;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getHttpMehtod() {
		return httpMehtod;
	}

	public void setHttpMehtod(String httpMehtod) {
		this.httpMehtod = httpMehtod;
	}

	public String getSuccessfulCode() {
		return successfulCode;
	}

	public void setSuccessfulCode(String successfulCode) {
		this.successfulCode = successfulCode;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	
	
}

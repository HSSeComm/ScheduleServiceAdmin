package com.schedule.demo.vo;

import java.sql.Date;

public class ScheduleLog {
	private Long logId;
	private Long jobId;
	private String jobName;
	private String reason;
	private Date insertDate;
	
	public ScheduleLog(){
		
	}

	public ScheduleLog(Long logId, Long jobId, String jobName, String reason) {
		super();
		this.logId = logId;
		this.jobId = jobId;
		this.jobName = jobName;
		this.reason = reason;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
	
}

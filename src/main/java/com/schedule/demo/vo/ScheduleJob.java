package com.schedule.demo.vo;

public class ScheduleJob {

	private long jobId;
	private String jobName;
	private String cornExpr;
	private String jobGroupName;
	private Class jobClass;
	private String triggerName;
	private String triggerGroupName;
	private String status;

	// client information:
	private String appUrl;
	private String httpMehtod;
	private String successfulCode;

	public ScheduleJob(){
		
	}
	
	public ScheduleJob(String jobName, String cornExpr, String jobGroupName, Class jobClass,
			String triggerName, String triggerGroupName) {
		super();
		this.jobName = jobName;
		this.cornExpr = cornExpr;
		this.jobGroupName = jobGroupName;
		this.jobClass = jobClass;
		this.triggerName = triggerName;
		this.triggerGroupName = triggerGroupName;
	}
	
	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getCornExpr() {
		return cornExpr;
	}

	public void setCornExpr(String cornExpr) {
		this.cornExpr = cornExpr;
	}

	public String getJobGroupName() {
		return jobGroupName;
	}

	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	public Class getJobClass() {
		return jobClass;
	}

	public void setJobClass(Class jobClass) {
		this.jobClass = jobClass;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroupName() {
		return triggerGroupName;
	}

	public void setTriggerGroupName(String triggerGroupName) {
		this.triggerGroupName = triggerGroupName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}

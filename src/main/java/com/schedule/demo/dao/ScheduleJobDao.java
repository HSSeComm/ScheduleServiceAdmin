package com.schedule.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.schedule.demo.vo.ScheduleJob;

public class ScheduleJobDao {
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public long insert(ScheduleJob scheduleJob) {
		int isHas = this.getJdbcTemplate().queryForObject("select count(job_id) from schedule_job where job_name=?",
				new Object[] { scheduleJob.getJobName() }, Integer.class);
		if (isHas > 0) {
			return -1;
		}

		int flag = getJdbcTemplate().update(
				"INSERT INTO schedule_job(job_name,corn_expr,job_group_name,job_class,trigger_name,trigger_group_name,status,app_url,http_mehtod,successful_code) VALUES (?,?,?,?,?,?,?,?,?,?)",
				scheduleJob.getJobName(), scheduleJob.getCornExpr(), scheduleJob.getJobGroupName(),
				scheduleJob.getClass() == null ? null : scheduleJob.getClass().getName(), scheduleJob.getTriggerName(),
				scheduleJob.getTriggerGroupName(), scheduleJob.getStatus(), scheduleJob.getAppUrl(),
				scheduleJob.getHttpMehtod(), scheduleJob.getSuccessfulCode());
		if (flag > 0) {
			long id= this.getJdbcTemplate().queryForObject("select last_insert_id()", Long.class);
			scheduleJob.setJobId(id);
			return id;
		}
		return flag;

	}

	public int update(ScheduleJob scheduleJob) {

		return getJdbcTemplate().update(
				"update schedule_job set job_name=?,corn_expr=?,job_group_name=?,job_class=?,trigger_name=?,trigger_group_name=?,app_url=?,http_mehtod=?,successful_code=? where job_id=?",
				scheduleJob.getJobName(), scheduleJob.getCornExpr(), scheduleJob.getJobGroupName(),
				scheduleJob.getClass() == null ? null : scheduleJob.getClass().getName(), scheduleJob.getTriggerName(),
				scheduleJob.getTriggerGroupName(), scheduleJob.getAppUrl(), scheduleJob.getHttpMehtod(),
				scheduleJob.getSuccessfulCode(), scheduleJob.getJobId());

	}

	public int updateStatus(ScheduleJob scheduleJob) {

		return getJdbcTemplate().update("update schedule_job set status=? where job_id=?", scheduleJob.getStatus(),
				scheduleJob.getJobId());

	}

	public int delete(ScheduleJob scheduleJob) {

		return getJdbcTemplate().update("delete from schedule_job where job_id=?", scheduleJob.getJobId());

	}

	public List<ScheduleJob> queryScheduleJobs() {
		return getJdbcTemplate().query("select * from schedule_job", new RowMapper<ScheduleJob>() {

			public ScheduleJob mapRow(ResultSet rs, int arg1) throws SQLException {
				ScheduleJob scheduleJob = new ScheduleJob();
				scheduleJob.setJobId(rs.getLong("job_id"));
				scheduleJob.setJobName(rs.getString("job_name"));
				scheduleJob.setCornExpr(rs.getString("corn_expr"));
				scheduleJob.setJobGroupName(rs.getString("job_group_name"));
				scheduleJob.setTriggerName(rs.getString("trigger_name"));
				scheduleJob.setTriggerGroupName(rs.getString("trigger_group_name"));
				scheduleJob.setStatus(rs.getString("status"));
				scheduleJob.setAppUrl(rs.getString("app_url"));
				scheduleJob.setHttpMehtod(rs.getString("http_mehtod"));
				scheduleJob.setSuccessfulCode(rs.getString("successful_code"));
				scheduleJob.setJobName(rs.getString("job_name"));
				scheduleJob.setJobName(rs.getString("job_name"));
				scheduleJob.setJobName(rs.getString("job_name"));
				try {
					Class jobClass = Class.forName(rs.getString("job_class"));
					scheduleJob.setJobClass(jobClass);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				return scheduleJob;
			}

		});
	}
}

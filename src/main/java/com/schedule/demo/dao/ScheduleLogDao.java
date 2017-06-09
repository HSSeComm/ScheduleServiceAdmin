package com.schedule.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.schedule.demo.vo.ScheduleLog;

@Repository
public class ScheduleLogDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public long insert(ScheduleLog scheduleLog) {

		int flag = getJdbcTemplate().update("INSERT INTO schedule_log(job_id,call_status) VALUES (?,?)",
				scheduleLog.getJobId(), scheduleLog.getCallStatus());
		if (flag > 0) {
			long id = this.getJdbcTemplate().queryForObject("select last_insert_id()", Long.class);
			scheduleLog.setLogId(id);
			return id;
		}
		return flag;

	}

	public List<ScheduleLog> queryScheduleLogs() {
		return getJdbcTemplate().query("select t.log_id,t.job_id,t.insert_date,s.job_name,s.app_url,t.call_status from schedule_log t  inner join schedule_job s on t.job_id = s.job_id", new RowMapper<ScheduleLog>() {

			@Override
			public ScheduleLog mapRow(ResultSet rs, int arg1) throws SQLException {
				ScheduleLog scheduleLog = new ScheduleLog();
				scheduleLog.setLogId(rs.getLong("log_id"));
				scheduleLog.setJobId(rs.getLong("job_id"));
				scheduleLog.setInsertDate(rs.getDate("insert_date")==null?"":simple.format(rs.getDate("insert_date")));
				scheduleLog.setAppUrl(rs.getString("app_url"));
				scheduleLog.setJobName(rs.getString("job_name"));
				scheduleLog.setCallStatus(rs.getBoolean("call_status"));
				return scheduleLog;
			}

		});
	}

	public List<ScheduleLog> queryScheduleLogsByJobId(Long jogId) {
		return getJdbcTemplate().query("select t.log_id,t.job_id,t.insert_date,s.job_name,s.app_url,t.call_status from schedule_log t  inner join schedule_job s on t.job_id = s.job_id where t.job_id=?", new Object[] { jogId }, new RowMapper<ScheduleLog>() {

			@Override
			public ScheduleLog mapRow(ResultSet rs, int arg1) throws SQLException {
				ScheduleLog scheduleLog = new ScheduleLog();
				scheduleLog.setLogId(rs.getLong("log_id"));
				scheduleLog.setJobId(rs.getLong("job_id"));
				scheduleLog.setAppUrl(rs.getString("app_url"));
				scheduleLog.setJobName(rs.getString("job_name"));
				scheduleLog.setCallStatus(rs.getBoolean("call_status"));
				scheduleLog.setInsertDate(rs.getDate("insert_date")==null?"":simple.format(rs.getDate("insert_date")));
				return scheduleLog;
			}

		});
	}
	
	public ScheduleLog getScheduleLogById(Long logId) {
		return getJdbcTemplate().queryForObject("select t.log_id,t.job_id,t.insert_date,s.job_name,s.app_url,t.call_status from schedule_log t  inner join schedule_job s on t.job_id = s.job_id  where t.log_id=?", new Object[] { logId },
				new RowMapper<ScheduleLog>() {

					@Override
					public ScheduleLog mapRow(ResultSet rs, int arg1) throws SQLException {
						ScheduleLog scheduleLog = new ScheduleLog();
						scheduleLog.setLogId(rs.getLong("log_id"));
						scheduleLog.setJobId(rs.getLong("job_id"));
						scheduleLog.setAppUrl(rs.getString("app_url"));
						scheduleLog.setJobName(rs.getString("job_name"));
						scheduleLog.setCallStatus(rs.getBoolean("call_status"));
						scheduleLog.setInsertDate(rs.getDate("insert_date")==null?"":simple.format(rs.getDate("insert_date")));
						return scheduleLog;
					}

				});
	}
}
package com.schedule.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public long insert(ScheduleLog scheduleLog) {

		int flag = getJdbcTemplate().update("INSERT INTO schedule_log(job_id,job_name,reason) VALUES (?,?,?)",
				scheduleLog.getJobId(), scheduleLog.getJobName(), scheduleLog.getReason());
		if (flag > 0) {
			long id = this.getJdbcTemplate().queryForObject("select last_insert_id()", Long.class);
			scheduleLog.setLogId(id);
			return id;
		}
		return flag;

	}

	public List<ScheduleLog> queryScheduleLogs() {
		return getJdbcTemplate().query("select * from schedule_log", new RowMapper<ScheduleLog>() {

			@Override
			public ScheduleLog mapRow(ResultSet rs, int arg1) throws SQLException {
				ScheduleLog scheduleLog = new ScheduleLog();
				scheduleLog.setLogId(rs.getLong("log_id"));
				scheduleLog.setJobId(rs.getLong("job_id"));
				scheduleLog.setJobName(rs.getString("job_name"));
				scheduleLog.setReason(rs.getString("reason"));
				scheduleLog.setInsertDate(rs.getDate("insert_date"));

				return scheduleLog;
			}

		});
	}

	public ScheduleLog getScheduleLogById(Long logId) {
		return getJdbcTemplate().queryForObject("select * from schedule_log where log_id=?", new Object[] { logId },
				new RowMapper<ScheduleLog>() {

					@Override
					public ScheduleLog mapRow(ResultSet rs, int arg1) throws SQLException {
						ScheduleLog scheduleLog = new ScheduleLog();
						scheduleLog.setLogId(rs.getLong("log_id"));
						scheduleLog.setJobId(rs.getLong("job_id"));
						scheduleLog.setJobName(rs.getString("job_name"));
						scheduleLog.setReason(rs.getString("reason"));
						scheduleLog.setInsertDate(rs.getDate("insert_date"));

						return scheduleLog;
					}

				});
	}
}
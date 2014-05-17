package com.kelvem.common.quartzmonitor.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kelvem.common.quartzmonitor.Constant;

@Repository("quartzDao")
public class QuartzDao {

	@Autowired private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> getQrtzTriggers() {
		String sql = "SELECT t.SCHED_NAME, t.TRIGGER_NAME, t.TRIGGER_GROUP, t.NEXT_FIRE_TIME, t.PREV_FIRE_TIME, t.START_TIME, t.END_TIME, t.TRIGGER_STATE," 
				+ " jd.JOB_CLASS_NAME, jd.IS_DURABLE, jd.IS_NONCONCURRENT, "
				+ " ct.CRON_EXPRESSION "
				+ " FROM QRTZ_TRIGGERS t, QRTZ_JOB_DETAILS jd, QRTZ_CRON_TRIGGERS ct" 
				+ " WHERE t.SCHED_NAME = jd.SCHED_NAME AND t.SCHED_NAME = ct.SCHED_NAME"
				+ " order by start_time;";
		
		List<Map<String, Object>> results = getJdbcTemplate().queryForList(sql);
		long val = 0;
		String temp = null;
		for (Map<String, Object> map : results) {
			temp = MapUtils.getString(map, "trigger_name");
			if(StringUtils.indexOf(temp, "&") != -1){
				map.put("display_name", StringUtils.substringBefore(temp, "&"));
			}else{
				map.put("display_name", temp);
			}
			
			val = MapUtils.getLongValue(map, "next_fire_time");
			if (val > 0) {
				map.put("next_fire_time", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
			}

			val = MapUtils.getLongValue(map, "prev_fire_time");
			if (val > 0) {
				map.put("prev_fire_time", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
			}

			val = MapUtils.getLongValue(map, "start_time");
			if (val > 0) {
				map.put("start_time", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
			}
			
			val = MapUtils.getLongValue(map, "end_time");
			if (val > 0) {
				map.put("end_time", DateFormatUtils.format(val, "yyyy-MM-dd HH:mm:ss"));
			}
			
			map.put("statu",Constant.status.get(MapUtils.getString(map, "trigger_state")));
		}

		return results;
	}
	
	

	private JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(this.dataSource);
	}
}

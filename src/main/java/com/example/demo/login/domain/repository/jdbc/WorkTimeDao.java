package com.example.demo.login.domain.repository.jdbc;

import com.example.demo.login.domain.model.WorkTime;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class WorkTimeDao {
    private final JdbcTemplate jdbc;

    public void insertWorkTime(WorkTime workTime) throws DataAccessException {
        //1件登録
        jdbc.update("INSERT INTO workTime("
                        + " monthId,"
                        + " workDay,"
                        + " startTime,"
                        + " breakTime,"
                        + " endTime)"
                        + " VALUES(?, ?, ?, ?, ?)"
                , workTime.getMonthId()
                , workTime.getWorkDay()
                , workTime.getStartTime()
                , workTime.getBreakTime()
                , workTime.getEndTime());
    }

    public List<WorkTime> getWorkTimeListByMonthId(int monthId) throws DataAccessException {
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM workTime"
                        + " WHERE monthId = ? "
                , monthId);
//		return getList.stream().map(this::convert).collect(toList());

        List<WorkTime> workTimeList = new ArrayList<>();
        for (Map<String, Object> map : getList) {
            WorkTime workTime = convert(map);
            workTimeList.add(workTime);
        }
        return workTimeList;
    }

    private WorkTime convert(Map<String, Object> map) {
        WorkTime workTime = new WorkTime();
        workTime.setMonthId((int) map.get("monthId"));
        workTime.setWorkDay(((java.sql.Date) map.get("workDay")).toLocalDate());
        workTime.setStartTime(((java.sql.Time) map.get("startTime")).toLocalTime());
        workTime.setBreakTime(((java.sql.Time) map.get("breakTime")).toLocalTime());
        workTime.setEndTime(((java.sql.Time) map.get("endTime")).toLocalTime());
        workTime.setWorkTimeMinute((int) map.get("workTimeMinute"));
        return workTime;
    }
}

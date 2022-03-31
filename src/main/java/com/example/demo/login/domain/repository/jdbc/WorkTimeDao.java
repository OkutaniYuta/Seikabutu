package com.example.demo.login.domain.repository.jdbc;

import com.example.demo.login.domain.model.WorkTime;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}

package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.WorkTime;
import com.example.demo.login.domain.repository.jdbc.WorkTimeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkTimeService {
    private final WorkTimeDao workTimeDao;

    public void insertWorkTime(WorkTime workTime) {
        workTimeDao.insertWorkTime(workTime);
    }

    public List<WorkTime> getWorkTimeList(int monthId) {
        return workTimeDao.getWorkTimeListByMonthId(monthId);
    }
}

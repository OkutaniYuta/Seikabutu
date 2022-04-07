package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.WorkTime;
import com.example.demo.login.domain.repository.jdbc.ContractDao;
import com.example.demo.login.domain.repository.jdbc.ContractMonthDao;
import com.example.demo.login.domain.repository.jdbc.WorkTimeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkTimeService {
    private final WorkTimeDao workTimeDao;
    private final ContractDao contractDao;
    private final ContractMonthDao contractMonthDao;

    public void insertWorkTime(int userId, LocalDate workDay, LocalTime startTime, LocalTime breakTime, LocalTime endTime) {
        int contractId = contractDao.getContractIdByUserId(userId);
        int monthId = contractMonthDao.getMonthIdByContractId(contractId);
        insertWorkTimeInMonth(monthId, workDay, startTime, breakTime, endTime);
    }

    public void insertWorkTimeInMonth(int monthId, LocalDate workDay, LocalTime startTime, LocalTime breakTime, LocalTime endTime) {
        WorkTime workTime = new WorkTime();
        workTime.setMonthId(monthId);
        workTime.setWorkDay(workDay);
        workTime.setStartTime(startTime);
        workTime.setBreakTime(breakTime);
        workTime.setEndTime(endTime);
        workTimeDao.insertWorkTime(workTime);
    }

    public List<WorkTime> getWorkTimeList(int monthId) {
        return workTimeDao.getWorkTimeListByMonthId(monthId);
    }
}

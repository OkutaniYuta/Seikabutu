package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.WorkTime;
import com.example.demo.login.domain.repository.jdbc.ContractDao;
import com.example.demo.login.domain.repository.jdbc.ContractMonthDao;
import com.example.demo.login.domain.repository.jdbc.WorkTimeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public void updateWorkTime(LocalDate workDay, LocalTime startTime, LocalTime breakTime, LocalTime endTime) {
        WorkTime workTime = new WorkTime();
        workTime.setWorkDay(workDay);
        workTime.setStartTime(startTime);
        workTime.setBreakTime(breakTime);
        workTime.setEndTime(endTime);
        workTimeDao.updateWorkTime(workTime);
    }

    public List<WorkTime> getWorkTimeList(int monthId) {
        YearMonth yearMonth = contractMonthDao.getYearMonth(monthId);
        List<WorkTime> workTimeListInDB = workTimeDao.getWorkTimeListByMonthId(monthId); // DBから2日と3日の2件

        List<WorkTime> list = new ArrayList<>(); // 2月なら28日分のデータを格納する
        int bound = yearMonth.lengthOfMonth();
        for (int i = 1; i <= bound; i++) {
            LocalDate date = yearMonth.atDay(i);
            boolean found = false;
            for (WorkTime x : workTimeListInDB) {
                if (x.getWorkDay().equals(date)) {
                    found = true;
                    list.add(x);
                    break;
                }
            }
            if (!found) {
                WorkTime workTime = new WorkTime();
                workTime.setWorkDay(date);
                list.add(workTime);
            }
        }
        return list;


//        return IntStream.rangeClosed(1, yearMonth.lengthOfMonth())
//                .mapToObj(i -> yearMonth.atDay(i))
//                .map(date -> workTimeListInDB.stream().filter(x -> x.getWorkDay().equals(date)).findFirst().orElseGet(() -> {
//                    WorkTime workTime = new WorkTime();
//                    workTime.setWorkDay(date);
//                    return workTime;
//                }))
//                .collect(Collectors.toList());
    }

    public List<WorkTime> getWorkTimeListByMonthId(int monthId) {
        return workTimeDao.getWorkTimeListByMonthId(monthId);
    }

    public void deleteWorkTimeInMonthByWorkDay(LocalDate formatChangeWorkDay) {
        workTimeDao.getWorkTimeListDelete(formatChangeWorkDay);
    }

    public static LocalDate convertStringToLocalDate(String workDay) {
        return LocalDate.parse(workDay, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}

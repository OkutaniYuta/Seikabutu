package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.WorkTime;
import com.example.demo.login.domain.repository.jdbc.WorkTimeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorkTimeService {
    // TODO: UserIdをキーにContractIdを取得(１番大きいやつ)。そのContractIdをキーにMonthId(1番大きいもの)を取得。そのMonthIdをworkTimeテーブルに登録する。
    private final WorkTimeDao workTimeDao;


    //workTimeテーブルにinsert
    public void insertWorkTime(WorkTime workTime) {
        //insert実行
        workTimeDao.insertWorkTime(workTime);
    }
}

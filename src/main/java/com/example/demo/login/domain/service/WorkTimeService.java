package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.Contract;
import com.example.demo.login.domain.repository.jdbc.WorkTimeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorkTimeService {
    // TODO: UserIdをキーにContractIdを取得(１番大きいやつ)。そのContractIdをキーにMonthId(1番大きいもの)を取得。そのMonthIdをworkTimeテーブルに登録する。
    private final WorkTimeDao workTimeDao;


    //workTimeテーブルにinsert
    public void insertWorkTime(Contract contract) {
        //insert実行
        workTimeDao.insertContract(contract);
    }
}

package com.example.demo.login.domain.model;

import lombok.Data;

@Data
public class ContractMonth {
    //contractテーブル
    private int monthId;
    private int contractId;
    private int year;
    private int month;
}

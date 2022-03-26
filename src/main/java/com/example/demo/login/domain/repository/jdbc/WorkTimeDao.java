package com.example.demo.login.domain.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WorkTimeDao {
    private final JdbcTemplate jdbc;

}

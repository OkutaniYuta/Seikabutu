/* 従業員テーブルのデータ（第３章で作成） */
INSERT INTO employee (employee_id, employee_name, age)
VALUES(1, '山田太郎', 30);

INSERT INTO user (userId, userName, email, password, role, userStatus, requestedAt)
VALUES('1', '奥谷裕太', 'okuoku@com', 'okuokou', 'ROLE_ADMIN', '0', '20211214')

-- INSERT INTO contract (contractId,
--     userId,
--     contractTime,
--     startTime,
--     breakTime,
--     endTime,
--     startDate,
--     officeName,)

-- VALUES('1', '1', '200', '09:00', '1', '19:00', '2021-01-01', '株式会社LIM')
/* 従業員テーブルのデータ（第３章で作成） */
INSERT INTO employee (employee_id, employee_name, age)
VALUES(1, '山田太郎', 30);

/* アドミン権限用 */
INSERT INTO m_user (user_id, user_name, email, password, role, userStatus, requestedAt)
VALUES('1', '奥谷裕太', 'okuoku@com', 'okuokou', 'ROLE_ADMIN', '0', '20211214')


/* ユーザーマスタのデータ */
INSERT INTO m_user (user_id, user_name, email, password, role, userStatus, requestedAt)
VALUES('2', 'おくたにゆうた', 'okuoku2@com', 'okuokou2', 'ROLE_ADMIN', '1', '20211216')
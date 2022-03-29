--user
INSERT INTO user (userId, userName, email, password, role, userStatus, requestedAt)
VALUES(1, '奥谷裕太', 'okuoku@com', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 1, 1, '2022-01-19');

--contract
INSERT INTO contract (contractId, userId, contractTime, startTime, breakTime, endTime, startDate, endDate, officeName)
VALUES(1, 1, 200, '09:00:00', '01:00:00', '19:00:00', '2021-01-01', '2022-01-01', '株式会社LIM');

INSERT INTO contract (contractId, userId, contractTime, startTime, breakTime, endTime, startDate, endDate, officeName)
VALUES(2, 1, 200, '09:00:00', '01:00:00', '19:00:00', '2022-01-01', '2022-02-01', '(新)株式会社おっくん');

INSERT INTO contract (contractId, userId, contractTime, startTime, breakTime, endTime, startDate, endDate, officeName)
VALUES(3, 1, 200, '09:00:00', '01:00:00', '19:00:00', '2000-01-01', '2001-02-01', '株式会社おっくん');

--month
INSERT INTO month (monthId, contractId, year, month)
VALUES(1, 1, 2022, 3);

INSERT INTO month (monthId, contractId, year, month)
VALUES(2, 2, 2022, 4);

INSERT INTO month (monthId, contractId, year, month)
VALUES(3, 2, 2022, 5);

INSERT INTO month (monthId, contractId, year, month)
VALUES(4, 3, 2022, 5);

--workTime
INSERT INTO workTime (workTimeId, monthId, workDay, startTime, breakTime, endTime, workTimeMinute)
VALUES(1, 1, '2021-01-01', '01:00:00', '01:00:00', '01:00:00', 99999);

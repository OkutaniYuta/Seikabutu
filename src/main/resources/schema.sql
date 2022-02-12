-- CREATE TABLE IF NOT EXISTS employee (
--     employee_id INT PRIMARY KEY,
--     employee_name VARCHAR(50),
--     age INT
-- );

/* ユーザー */
CREATE TABLE IF NOT EXISTS user (
    userId INTEGER IDENTITY(1,1) PRIMARY KEY,
    userName VARCHAR(100),
    email VARCHAR(50),
    password VARCHAR(100),
    role INTEGER(1),
    userStatus INTEGER(1),
    requestedAt VARCHAR(19)
);

CREATE TABLE IF NOT EXISTS contract (
    contractId INTEGER(3) PRIMARY KEY,
    userId INTEGER(3),
    contractTime INTEGER(3),
    startTime TIME(8),
    breakTime TIME(8),
    endTime TIME(8),
    startDate DATE(10),
    officeName VARCHAR(100),
);
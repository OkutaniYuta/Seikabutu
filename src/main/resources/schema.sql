CREATE TABLE IF NOT EXISTS user (
    userId INTEGER IDENTITY(1,1) PRIMARY KEY,
    userName VARCHAR(100),
    email VARCHAR(50) UNIQUE,
    password VARCHAR(100),
    role INTEGER(1),
    userStatus INTEGER(1),
    requestedAt VARCHAR(19)
);

CREATE TABLE IF NOT EXISTS contract (
    contractId INTEGER IDENTITY(1,1) PRIMARY KEY,
    userId INTEGER(3),
    contractTime VARCHAR(3),
    startTime TIME(8),
    breakTime TIME(8),
    endTime TIME(8),
    startDate DATE(10),
    endDate DATE(10),
    officeName VARCHAR(100),
);

CREATE TABLE IF NOT EXISTS month (
    monthId INTEGER IDENTITY(1,1) PRIMARY KEY,
    contractId INTEGER(3),
    year INTEGER(3),
    month INTEGER(3),
);
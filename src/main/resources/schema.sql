/* ユーザーマスタ */
CREATE TABLE IF NOT EXISTS user (
    userId INT(3) PRIMARY KEY,
    userName VARCHAR(100),
    email VARCHAR(50),
    password VARCHAR(50),
    role INT(1),
    userStatus INT(1),
    requestedAt VARCHAR(19)
);
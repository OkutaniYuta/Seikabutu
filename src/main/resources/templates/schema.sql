CREATE TABLE IF NOT EXISTS employee (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(50),
    age INT
);

/* ユーザーマスタ */
CREATE TABLE IF NOT EXISTS m_user (
    user_id INTEGER(3) PRIMARY KEY,
    user_name VARCHAR(100),
    email VARCHAR(50),
    password VARCHAR(50),
    role INTEGER(1),
    userStatus INTEGER(1),
    requestedAt VARCHAR(19)
)
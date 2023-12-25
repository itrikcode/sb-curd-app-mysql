-- V1__Create_User_Table.sql
CREATE TABLE IF NOT EXISTS login (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);

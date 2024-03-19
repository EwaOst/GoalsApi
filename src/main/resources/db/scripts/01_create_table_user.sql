CREATE TABLE IF NOT EXISTS userData
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name           VARCHAR(128) NOT NULL,
    email               VARCHAR(255),
    age                 INT

    );
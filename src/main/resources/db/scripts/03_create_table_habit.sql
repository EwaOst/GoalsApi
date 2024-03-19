CREATE TABLE IF NOT EXISTS habit
(
    id                          BIGINT AUTO_INCREMENT PRIMARY KEY,
    HABIT_NAME                  VARCHAR(128),
    HABIT_FREQUENCY             VARCHAR(128),
    TRACK BOOLEAN,
    ACTIVITY                    VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id)       REFERENCES userData(id)
    );
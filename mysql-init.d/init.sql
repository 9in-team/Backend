CREATE USER 'test'@'localhost' IDENTIFIED BY 'test';
CREATE USER 'test'@'%' IDENTIFIED BY 'test';

GRANT ALL PRIVILEGES ON *.* TO 'test'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'test'@'%';

CREATE DATABASE testdb DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

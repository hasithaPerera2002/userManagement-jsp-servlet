create database userdb;
use userdb;
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       age VARCHAR(10),
                       address VARCHAR(255),
                       userType ENUM('ADMIN', 'STAFF'),
                       gender ENUM('MALE', 'FEMALE', 'OTHER')
);

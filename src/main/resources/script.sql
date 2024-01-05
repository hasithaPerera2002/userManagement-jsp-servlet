create database userdb;
use userdb;
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       age VARCHAR(10),
                       address VARCHAR(255),
                       userType ENUM('ADMIN', 'STAFF'),
                       phone VARCHAR(20)
);
CREATE TABLE supplier (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          category VARCHAR(100),
                          quantity INT,
                          price DECIMAL(10, 2),
                          email VARCHAR(100) NOT NULL
);
CREATE TABLE item (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,

                      quantity INT,
                      price DECIMAL(10, 2),
                      supplier_id INT,
                      FOREIGN KEY (supplier_id) REFERENCES supplier(id)
);


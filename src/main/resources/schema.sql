
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

-- ---
-- Table 'employees'
-- 従業員マスタ
-- ---

DROP TABLE IF EXISTS employees;
		
CREATE TABLE employees (
  id serial PRIMARY KEY,
  per_cd VARCHAR NOT NULL ,
  role_id INT NOT NULL,
  shop_cd VARCHAR NOT NULL,
  department_id INT NOT NULL ,
  fname VARCHAR ,
  lname VARCHAR ,
  birth_dt VARCHAR ,
  password VARCHAR ,
  work_per_week INT  ,
  work_hours INT  ,
  rest_flg VARCHAR(1)  DEFAULT '0' ,
  del_flg VARCHAR(1)  DEFAULT '0' 
) ;

-- ---
-- Table 'roles'
-- 権限マスタ
-- ---

DROP TABLE IF EXISTS roles;
		
CREATE TABLE roles (
  id serial PRIMARY KEY,
  name VARCHAR
);

-- ---
-- Table 'departments'
-- 部門マスタ
-- ---

DROP TABLE IF EXISTS departments;
		
CREATE TABLE departments (
  id serial PRIMARY KEY,
  name VARCHAR
) ;

-- ---
-- Table 'dayoffs'
-- 休日トラン
-- ---

DROP TABLE IF EXISTS dayoffs;
		
CREATE TABLE dayoffs (
  id serial PRIMARY KEY,
  employee_id INTEGER ,
  type_id INTEGER ,
  date DATE
) ;

-- ---
-- Table 'dayoff_types'
-- 休日タイプマスタ
-- ---

DROP TABLE IF EXISTS dayoff_types;
		
CREATE TABLE dayoff_types (
  id serial PRIMARY KEY,
  name VARCHAR 
) ;

-- ---
-- Table 'shifts'
-- シフトトラン
-- ---

DROP TABLE IF EXISTS shifts;
		
CREATE TABLE shifts (
  id serial PRIMARY KEY,
  employee_id INT ,
  department_id INT ,
  date DATE ,
  start_time TIME,
  end_time TIME
) ;

-- ---
-- Table 'shops'
-- 店舗マスタ
-- ---

DROP TABLE IF EXISTS shops;
		
CREATE TABLE shops (
  shop_cd VARCHAR PRIMARY KEY,
  name VARCHAR,
  open_time TIME ,
  close_time TIME 
) ;

-- ---
-- Foreign Keys 
-- ---

ALTER TABLE employees ADD FOREIGN KEY (role_id) REFERENCES roles (id);
ALTER TABLE employees ADD FOREIGN KEY (shop_cd) REFERENCES shops (shop_cd);
ALTER TABLE employees ADD FOREIGN KEY (department_id) REFERENCES departments (id);
ALTER TABLE dayoffs ADD FOREIGN KEY (employee_id) REFERENCES employees (id);
ALTER TABLE dayoffs ADD FOREIGN KEY (type_id) REFERENCES dayoff_types (id);
ALTER TABLE shifts ADD FOREIGN KEY (employee_id) REFERENCES employees (id);
ALTER TABLE shifts ADD FOREIGN KEY (department_id) REFERENCES departments (id);

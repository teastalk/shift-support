DROP TABLE IF EXISTS t_dayoffs;
DROP TABLE IF EXISTS t_shifts;
DROP TABLE IF EXISTS m_dayoff_types;
DROP TABLE IF EXISTS m_employees;
DROP TABLE IF EXISTS m_stores;
DROP TABLE IF EXISTS m_departments;
DROP TABLE IF EXISTS m_roles;

CREATE TABLE m_roles (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE m_departments (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE m_stores (
    store_code VARCHAR PRIMARY KEY,
    name VARCHAR UNIQUE NOT NULL,
    open TIME NOT NULL,
    close TIME NOT NULL
);

CREATE TABLE m_employees (
    employee_code VARCHAR PRIMARY KEY,
    role_id INTEGER NOT NULL,
    store_code VARCHAR NOT NULL,
    department_id INTEGER NOT NULL,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    date_of_birth VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    work_per_week INTEGER NOT NULL,
    work_per_day INTEGER NOT NULL,
    deleted_at TIMESTAMP ,
    FOREIGN KEY (role_id) REFERENCES m_roles (id),
    FOREIGN KEY (store_code) REFERENCES m_stores (store_code),
    FOREIGN KEY (department_id) REFERENCES m_departments (id)
);

CREATE TABLE m_dayoff_types (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR UNIQUE NOT NULL
);

CREATE TABLE t_shifts (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    employee_code VARCHAR NOT NULL,
    department_id INTEGER NOT NULL,
    "date" DATE NOT NULL,
    "start" TIME NOT NULL,
    "end" TIME NOT NULL,
    FOREIGN KEY (employee_code) REFERENCES m_employees (employee_code),
    FOREIGN KEY (department_id) REFERENCES m_departments (id)
);

CREATE TABLE t_dayoffs (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    employee_code VARCHAR NOT NULL,
    type_id INTEGER NOT NULL,
    "date" DATE NOT NULL,
    FOREIGN KEY (employee_code) REFERENCES m_employees (employee_code),
    FOREIGN KEY (type_id) REFERENCES m_dayoff_types (id)
);
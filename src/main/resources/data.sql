

--権限
INSERT INTO m_roles (name) VALUES('管理者');
INSERT INTO m_roles (name) VALUES('店長');
INSERT INTO m_roles (name) VALUES('労務');
INSERT INTO m_roles (name) VALUES('部門長');
INSERT INTO m_roles (name) VALUES('従業員');

--部門
INSERT INTO m_departments (name) VALUES('農産');
INSERT INTO m_departments (name) VALUES('畜産');
INSERT INTO m_departments (name) VALUES('水産');
INSERT INTO m_departments (name) VALUES('惣菜');
INSERT INTO m_departments (name) VALUES('食品');
INSERT INTO m_departments (name) VALUES('日配');
INSERT INTO m_departments (name) VALUES('事務（労務）');

--休日タイプ
INSERT INTO m_dayoff_types (name) VALUES('公休');
INSERT INTO m_dayoff_types (name) VALUES('希望休');
INSERT INTO m_dayoff_types (name) VALUES('有休');

--店舗
INSERT INTO m_stores (store_code, name, open,close) VALUES('396', '蟹江', '07:00:00', '23:00:00');

--従業員
INSERT INTO m_employees (employee_code, role_id, store_code, department_id, first_name, last_name, date_of_birth,password, work_per_week, work_per_day)
VALUES ('24010101', 1, '396', 1, '三島', '慧吾', '19911129','19911129', 5, 8);


--権限
INSERT INTO roles (name) VALUES('管理者');
INSERT INTO roles (name) VALUES('店長');
INSERT INTO roles (name) VALUES('労務');
INSERT INTO roles (name) VALUES('部門長');
INSERT INTO roles (name) VALUES('従業員');

--部門
INSERT INTO departments (name) VALUES('農産');
INSERT INTO departments (name) VALUES('畜産');
INSERT INTO departments (name) VALUES('水産');
INSERT INTO departments (name) VALUES('惣菜');
INSERT INTO departments (name) VALUES('食品');
INSERT INTO departments (name) VALUES('日配');
INSERT INTO departments (name) VALUES('事務（労務）');

--休日タイプ
INSERT INTO dayoff_types (name) VALUES('公休');
INSERT INTO dayoff_types (name) VALUES('希望休');
INSERT INTO dayoff_types (name) VALUES('有休');

--店舗
INSERT INTO shops (shop_cd, name, open_time, close_time) VALUES('396', '蟹江', '07:00:00', '23:00:00');

--従業員
INSERT INTO employees (per_cd, role_id, shop_cd, department_id, fname, lname, birth_dt, work_per_week, work_hours, rest_flg)
VALUES ('24010101', 1, '396', 1, '三島', '慧吾', '19911129', '5', '8', '1');
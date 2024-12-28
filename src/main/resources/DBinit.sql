-- ---
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

-- ---
-- Table 'employees'
-- 従業員マスタ
-- ---

DROP TABLE IF EXISTS `employees`;
		
CREATE TABLE `employees` (
  `id` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
  `per_cd` VARCHAR NOT NULL DEFAULT 'NULL' COMMENT '従業員番号 [24010101][入社年月+連番]',
  `role_id` INTEGER NOT NULL DEFAULT 0 COMMENT '権限ID',
  `shop_id` INTEGER NULL DEFAULT NULL,
  `department_id` INTEGER NULL DEFAULT NULL COMMENT '部門ID',
  `fname` VARCHAR NULL DEFAULT NULL,
  `lname` VARCHAR NULL DEFAULT NULL,
  `birth_dt` VARCHAR NULL DEFAULT '19700101',
  `password` VARCHAR NULL DEFAULT NULL,
  `work_per_week` INTEGER NULL DEFAULT NULL COMMENT '週あたりの稼働日数[1~6]',
  `work_hours` INTEGER NULL DEFAULT NULL COMMENT '1日あたりの契約時間',
  `rest_flg` VARCHAR(1) NULL DEFAULT '0' COMMENT '契約に1時間休憩があるかどうか',
  `del_flg` VARCHAR(1) NULL DEFAULT '0' COMMENT '0:在職,1:退職',
  PRIMARY KEY (`id`)
) COMMENT '従業員マスタ';

-- ---
-- Table 'roles'
-- 権限マスタ
-- ---

DROP TABLE IF EXISTS `roles`;
		
CREATE TABLE `roles` (
  `id` INTEGER NOT NULL AUTO_INCREMENT DEFAULT NULL,
  `name` VARCHAR NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) COMMENT '権限マスタ';

-- ---
-- Table 'departments'
-- 部門マスタ
-- ---

DROP TABLE IF EXISTS `departments`;
		
CREATE TABLE `departments` (
  `id` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
  `name` VARCHAR NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) COMMENT '部門マスタ';

-- ---
-- Table 'dayoffs'
-- 休日トラン
-- ---

DROP TABLE IF EXISTS `dayoffs`;
		
CREATE TABLE `dayoffs` (
  `id` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
  `employee_id` INTEGER NULL DEFAULT NULL,
  `type_id` INTEGER NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) COMMENT '休日トラン';

-- ---
-- Table 'dayoff_types'
-- 休日タイプマスタ
-- ---

DROP TABLE IF EXISTS `dayoff_types`;
		
CREATE TABLE `dayoff_types` (
  `id` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
  `name` VARCHAR NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) COMMENT '休日タイプマスタ';

-- ---
-- Table 'shifts'
-- シフトトラン
-- ---

DROP TABLE IF EXISTS `shifts`;
		
CREATE TABLE `shifts` (
  `id` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
  `employee_id` INTEGER NULL DEFAULT NULL,
  `department_id` INTEGER NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  `start` TIME NULL DEFAULT NULL,
  `end` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) COMMENT 'シフトトラン';

-- ---
-- Table 'shops'
-- 店舗マスタ
-- ---

DROP TABLE IF EXISTS `shops`;
		
CREATE TABLE `shops` (
  `shop_cd` VARCHAR NULL DEFAULT NULL COMMENT '店舗コード',
  `name` VARCHAR NULL DEFAULT NULL,
  `open_time` TIME NULL DEFAULT NULL,
  `close_time` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`shop_cd`)
) COMMENT '店舗マスタ';

-- ---
-- Foreign Keys 
-- ---

ALTER TABLE `employees` ADD FOREIGN KEY (role_id) REFERENCES `roles` (`id`);
ALTER TABLE `employees` ADD FOREIGN KEY (shop_id) REFERENCES `shops` (`shop_cd`);
ALTER TABLE `employees` ADD FOREIGN KEY (department_id) REFERENCES `departments` (`id`);
ALTER TABLE `dayoffs` ADD FOREIGN KEY (employee_id) REFERENCES `employees` (`id`);
ALTER TABLE `dayoffs` ADD FOREIGN KEY (type_id) REFERENCES `dayoff_types` (`id`);
ALTER TABLE `shifts` ADD FOREIGN KEY (employee_id) REFERENCES `employees` (`id`);
ALTER TABLE `shifts` ADD FOREIGN KEY (department_id) REFERENCES `departments` (`id`);

-- ---
-- Table Properties
-- ---

-- ALTER TABLE `employees` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `roles` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `departments` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `dayoffs` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `dayoff_types` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `shifts` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `shops` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- INSERT INTO `employees` (`id`,`per_cd`,`role_id`,`shop_id`,`department_id`,`fname`,`lname`,`birth_dt`,`password`,`work_per_week`,`work_hours`,`rest_flg`,`del_flg`) VALUES
-- ('','','','','','','','','','','','','');
-- INSERT INTO `roles` (`id`,`name`) VALUES
-- ('','');
-- INSERT INTO `departments` (`id`,`name`) VALUES
-- ('','');
-- INSERT INTO `dayoffs` (`id`,`employee_id`,`type_id`,`date`) VALUES
-- ('','','','');
-- INSERT INTO `dayoff_types` (`id`,`name`) VALUES
-- ('','');
-- INSERT INTO `shifts` (`id`,`employee_id`,`department_id`,`date`,`start`,`end`) VALUES
-- ('','','','','','');
-- INSERT INTO `shops` (`shop_cd`,`name`,`open_time`,`close_time`) VALUES
-- ('','','','');
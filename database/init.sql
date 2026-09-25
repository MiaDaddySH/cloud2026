CREATE DATABASE IF NOT EXISTS `db2024`
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_0900_ai_ci;

USE `db2024`;

DROP TABLE IF EXISTS `t_pay`;

CREATE TABLE `t_pay` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `pay_no` varchar(50) NOT NULL COMMENT '支付流水号',
  `order_no` varchar(50) NOT NULL COMMENT '订单流水号',
  `user_id` int DEFAULT '1' COMMENT '用户账号ID',
  `amount` decimal(8,2) NOT NULL DEFAULT '9.90' COMMENT '交易金额',
  `deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '删除标志，默认0不删除，1删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='支付交易表';

INSERT INTO `t_pay` (`pay_no`, `order_no`)
VALUES ('pay17203699', '6544bafb424a');

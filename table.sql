CREATE TABLE `customer_code` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `customer_key` varchar(10) NOT NULL,
  `customer` varchar(25) DEFAULT NULL,
  `sysdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`index`),
  UNIQUE KEY `customer_code_customer_key_uindex` (`customer_key`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customer_info` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `customer_key` varchar(10) DEFAULT NULL,
  `customer_name` varchar(15) DEFAULT NULL,
  `customer_email` varchar(35) DEFAULT NULL,
  `sysdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`index`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `smtp_reservation` (
  `reservation_code` char(30) NOT NULL,
  `group_code` char(15) DEFAULT NULL,
  `customer` varchar(25) DEFAULT NULL,
  `email_title` varchar(50) DEFAULT NULL,
  `email_form` text,
  `customer_address` text,
  `sys_send_date` datetime DEFAULT NULL,
  `state` char(2) DEFAULT 'N',
  `sysdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`reservation_code`),
  UNIQUE KEY `smtp_reservation_reservation_code_uindex` (`reservation_code`),
  KEY `smtp_reservation_smtp_reservation_group_group_code_fk` (`group_code`),
  CONSTRAINT `smtp_reservation_smtp_reservation_group_group_code_fk` FOREIGN KEY (`group_code`) REFERENCES `smtp_reservation_group` (`group_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `smtp_reservation_group` (
  `group_code` char(15) NOT NULL,
  `group_title` varchar(50) DEFAULT NULL,
  `reservation_date` datetime DEFAULT NULL,
  `sys_send_date` datetime DEFAULT NULL,
  `state` char(2) DEFAULT 'N',
  `sysdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`group_code`),
  UNIQUE KEY `smtp_reservation_group_group_code_uindex` (`group_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


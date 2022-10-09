drop database navigator;
create database navigator;
use navigator;


CREATE TABLE `city` (
`id` bigint NOT NULL AUTO_INCREMENT,
`name` varchar(255) NOT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE `distance` (
`id` bigint NOT NULL AUTO_INCREMENT,
`distance` decimal(19,2) DEFAULT NULL,
`city1_id` bigint NOT NULL,
`city2_id` bigint NOT NULL,
PRIMARY KEY (`id`),
KEY `FK_city1_id` (`city1_id`),
CONSTRAINT `FK_city1_id` FOREIGN KEY (`city1_id`) REFERENCES `city` (`id`),
KEY `FK_city2_id` (`city2_id`),
CONSTRAINT `FK_city2_id` FOREIGN KEY (`city2_id`) REFERENCES `city` (`id`)
);
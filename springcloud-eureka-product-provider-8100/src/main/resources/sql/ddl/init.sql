DROP DATABASE IF EXISTS `springcloud`;
CREATE DATABASE `springcloud`;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `stock` int(10) unsigned DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
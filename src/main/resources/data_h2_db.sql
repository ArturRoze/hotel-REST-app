CREATE SCHEMA  IF NOT EXISTS `hotel_h2_db`;
USE `hotel_h2_db`;

--
-- Table structure for table `additional_option`
--

DROP TABLE IF EXISTS `additional_option`;

CREATE TABLE `additional_option` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `room_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `additional_option` VALUES (1,'clean',25,2),(2,'wash',20,1),(3,'breakfast',15,3);

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;

CREATE TABLE `hotel` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

INSERT INTO `hotel` VALUES (1,'Redisson'),(2,'Redisson'),(3,'Redisson'),(4,'Redisson'),(5,'Redisson');

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `category` varchar(45) NOT NULL,
  `startBookingDate` timestamp NULL DEFAULT NULL,
  `endBookingDate` timestamp NULL DEFAULT NULL,
  `price` double NOT NULL,
  `user_id` BIGINT(20) DEFAULT NULL,
  `hotel_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

INSERT INTO `room` VALUES (1,1,'single',NULL,NULL,50,1,1),(2,2,'bouble',NULL,NULL,75,2,1),(3,3,'apartment',NULL,NULL,120,3,1),(4,4,'double',NULL,NULL,75,4,1),(5,5,'single',NULL,NULL,50,5,1);

-- Table structure for table `user`

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES (1,'a','Artur','Roze'),(2,'b','Denis','Galan'),(3,'c','Lina','Roze'),(4,'d','Vova','Bomber'),(5,'e','Svetlana','Boyun');


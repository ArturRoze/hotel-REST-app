DROP TABLE IF EXISTS `additional_option`;

CREATE TABLE `additional_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `booking_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `additional_option` VALUES (1,'clean',10,1),(2,'wash',15,2),(3,'breakfast',20,3);

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;

CREATE TABLE `booking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` bigint(20) NOT NULL,
  `room_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `booking` VALUES (1,'2018-03-06','2018-03-06 13:22:39',1,1),(2,'2018-03-06 13:22:39','2018-03-06 13:22:39',2,2),(3,'2018-03-06 13:22:39','2018-03-06 13:22:39',3,3);

DROP TABLE IF EXISTS `hotel`;

CREATE TABLE `hotel` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `hotel` VALUES (1,'Redisson');

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` int(10) unsigned NOT NULL,
  `category` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `hotel_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `room` VALUES (1,1,'SINGLE',50,1),(2,2,'DOUBLE',75,1),(3,3,'APARTMENT',120,1),(4,4,'SINGLE',50,1),(5,5,'DOUBLE',75,1);

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES (1,'sky','Artur','Roze'),(2,'spider','Lina','Roze'),(3,'crusher','Svetlana','Bukova'),(4,'duck','Misha','Korch'),(5,'iceman','Alpin','Gold');
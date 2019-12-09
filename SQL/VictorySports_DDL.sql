# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: us-cdbr-iron-east-01.cleardb.net (MySQL 5.5.56-log)
# Database: heroku_ddcf4e007ad9f47
# Generation Time: 2018-12-16 06:39:12 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table action_type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `action_type`;

CREATE TABLE `action_type` (
  `action_name` varchar(100) NOT NULL,
  `default_score` decimal(8,4) unsigned zerofill DEFAULT '0000.0000',
  PRIMARY KEY (`action_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `action_type` WRITE;
/*!40000 ALTER TABLE `action_type` DISABLE KEYS */;

INSERT INTO `action_type` (`action_name`, `default_score`)
VALUES
	('ATTENDANCE',0002.0000),
	('CHECK-IN',0001.0000),
	('CHECK-OUT',0001.0000),
	('DIET',0001.0000),
	('GROUND MARKING',0001.0000),
	('PRACTICE MATCH',0002.0000),
	('PROPS',0001.0000),
	('SESSIONS AIM and SCHEDULE',0001.0000),
	('SESSIONS PLAN',0002.0000);

/*!40000 ALTER TABLE `action_type` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table activity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `activity`;

CREATE TABLE `activity` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
  `coach_id` int(11) NOT NULL,
  `hub_id` int(11) DEFAULT NULL,
  `status` varchar(100) NOT NULL,
  `created_on` int(16) NOT NULL,
  `updated_on` int(16) NOT NULL,
  PRIMARY KEY (`activity_id`),
  KEY `activity_hub_fk_idx` (`hub_id`),
  KEY `activity_coach_fk_idx` (`coach_id`),
  KEY `activity_status_fk_idx` (`status`),
  CONSTRAINT `activity_coach_fk` FOREIGN KEY (`coach_id`) REFERENCES `coach` (`coach_id`),
  CONSTRAINT `activity_hub_fk` FOREIGN KEY (`hub_id`) REFERENCES `hub` (`hub_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_status_fk` FOREIGN KEY (`status`) REFERENCES `activity_status` (`status`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;

INSERT INTO `activity` (`activity_id`, `coach_id`, `hub_id`, `status`, `created_on`, `updated_on`)
VALUES
	(291,111,81,'REVIEW',1544940372,1544940372),
	(331,111,81,'IN-PROGRESS',1544941692,1544941692);

/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table activity_detail
# ------------------------------------------------------------

DROP TABLE IF EXISTS `activity_detail`;

CREATE TABLE `activity_detail` (
  `activity_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) NOT NULL,
  `action_name` varchar(100) NOT NULL,
  `detail1` varchar(300) NOT NULL,
  `detail2` varchar(300) DEFAULT NULL,
  `detail3` varchar(300) DEFAULT NULL,
  `detail4` varchar(300) DEFAULT NULL,
  `detail5` varchar(300) DEFAULT NULL,
  `detail6` varchar(300) DEFAULT NULL,
  `detail7` varchar(300) DEFAULT NULL,
  `score` decimal(8,4) unsigned zerofill DEFAULT '0000.0000',
  `created_on` int(16) NOT NULL,
  `updated_on` int(16) NOT NULL,
  PRIMARY KEY (`activity_detail_id`),
  KEY `activity_actiontype_fk_idx` (`action_name`),
  KEY `activitydetail_activity_fk` (`activity_id`),
  CONSTRAINT `activitydetail_action_fk` FOREIGN KEY (`action_name`) REFERENCES `action_type` (`action_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activitydetail_activity_fk` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `activity_detail` WRITE;
/*!40000 ALTER TABLE `activity_detail` DISABLE KEYS */;

INSERT INTO `activity_detail` (`activity_detail_id`, `activity_id`, `action_name`, `detail1`, `detail2`, `detail3`, `detail4`, `detail5`, `detail6`, `detail7`, `score`, `created_on`, `updated_on`)
VALUES
	(271,291,'CHECK-IN','Check In',NULL,NULL,NULL,NULL,NULL,NULL,0000.0000,1544940372,1544940372),
	(281,291,'CHECK-OUT','Check In',NULL,NULL,NULL,NULL,NULL,NULL,0000.0000,1544940541,1544940541),
	(291,331,'CHECK-IN','Check In',NULL,NULL,NULL,NULL,NULL,NULL,0000.0000,1544941692,1544941692);

/*!40000 ALTER TABLE `activity_detail` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table activity_status
# ------------------------------------------------------------

DROP TABLE IF EXISTS `activity_status`;

CREATE TABLE `activity_status` (
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `activity_status` WRITE;
/*!40000 ALTER TABLE `activity_status` DISABLE KEYS */;

INSERT INTO `activity_status` (`status`)
VALUES
	('CLOSED'),
	('IN-PROGRESS'),
	('REVIEW');

/*!40000 ALTER TABLE `activity_status` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table attendance
# ------------------------------------------------------------

DROP TABLE IF EXISTS `attendance`;

CREATE TABLE `attendance` (
  `student_id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `created_on` int(16) NOT NULL,
  `updated_on` int(16) NOT NULL,
  PRIMARY KEY (`student_id`,`activity_id`),
  KEY `student_activity_fk_idx` (`activity_id`),
  CONSTRAINT `student_activity_fk` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_attendance_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table coach
# ------------------------------------------------------------

DROP TABLE IF EXISTS `coach`;

CREATE TABLE `coach` (
  `coach_id` int(11) NOT NULL AUTO_INCREMENT,
  `coach_name` varchar(100) NOT NULL,
  `sports_id` int(11) DEFAULT NULL,
  `active` char(1) DEFAULT 'Y',
  `profile_image` varchar(300) DEFAULT '',
  `education_detail_doc` varchar(300) DEFAULT NULL,
  `experience_detail_doc` varchar(300) DEFAULT NULL,
  `about` varchar(300) DEFAULT NULL,
  `created_on` bigint(16) NOT NULL,
  `updated_on` bigint(16) NOT NULL,
  `document` varchar(300) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `zipcode` varchar(300) DEFAULT NULL,
  `dob` bigint(16) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`coach_id`),
  KEY `coach_sport_fk_idx` (`sports_id`),
  CONSTRAINT `coach_sport_fk` FOREIGN KEY (`sports_id`) REFERENCES `sport` (`sports_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `coach` WRITE;
/*!40000 ALTER TABLE `coach` DISABLE KEYS */;

INSERT INTO `coach` (`coach_id`, `coach_name`, `sports_id`, `active`, `profile_image`, `education_detail_doc`, `experience_detail_doc`, `about`, `created_on`, `updated_on`, `document`, `address`, `zipcode`, `dob`)
VALUES
	(111,'Raj Kumar',31,'Y',NULL,'M.Tech @ BITS Pilani','Paypal','Code Hard',1544940250456,1544940250456,NULL,'Sholinganalur','600041',0000000000012345),
	(121,'Janani',31,'A','s3-ap-southeast-1.amazonaws.com/victorysports/coach/121profile_image.jpg','engineer','PP','Code Hard',1544941892964,1544941892964,'s3-ap-southeast-1.amazonaws.com/victorysports/coach/121id_proof.jpg','Chennai','12345',0000011234567890);

/*!40000 ALTER TABLE `coach` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table hub
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hub`;

CREATE TABLE `hub` (
  `hub_id` int(11) NOT NULL AUTO_INCREMENT,
  `hub_name` varchar(100) NOT NULL,
  `hub_address` varchar(500) NOT NULL,
  `pin` int(6) NOT NULL,
  `created_on` int(16) NOT NULL,
  `updated_on` int(16) NOT NULL,
  `supported_by` varchar(100) DEFAULT '',
  PRIMARY KEY (`hub_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `hub` WRITE;
/*!40000 ALTER TABLE `hub` DISABLE KEYS */;

INSERT INTO `hub` (`hub_id`, `hub_name`, `hub_address`, `pin`, `created_on`, `updated_on`, `supported_by`)
VALUES
	(81,'PayPal','Futura IT Park, 334, Elcot Sez, Rajiv Gandhi Salai, Chennai',600119,1544939600,1544939600,'PayPal');

/*!40000 ALTER TABLE `hub` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table hub_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hub_item`;

CREATE TABLE `hub_item` (
  `item_id` int(11) NOT NULL,
  `hub_id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `active` char(1) NOT NULL DEFAULT 'Y',
  `created_on` int(16) DEFAULT NULL,
  `updated_on` int(16) DEFAULT NULL,
  PRIMARY KEY (`item_id`,`hub_id`),
  KEY `hub_fk_idx` (`hub_id`),
  CONSTRAINT `hub_fk` FOREIGN KEY (`hub_id`) REFERENCES `hub` (`hub_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `item_fk` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(200) NOT NULL,
  `active` char(1) NOT NULL DEFAULT 'Y',
  `created_on` int(16) NOT NULL,
  `updated_on` int(16) NOT NULL,
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `item_name_UNIQUE` (`item_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;

INSERT INTO `item` (`item_id`, `item_name`, `active`, `created_on`, `updated_on`)
VALUES
	(21,'BALL','Y',0,0),
	(31,'1/2 FT GROUND MARKER','Y',0,0),
	(41,'1 FEET GROUND MARKER','Y',0,0);

/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sport
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sport`;

CREATE TABLE `sport` (
  `sports_id` int(11) NOT NULL AUTO_INCREMENT,
  `sports_name` varchar(100) NOT NULL,
  `active` char(1) DEFAULT 'Y',
  `created_on` int(16) NOT NULL,
  `updated_on` int(16) NOT NULL,
  PRIMARY KEY (`sports_id`),
  UNIQUE KEY `sports_name_UNIQUE` (`sports_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sport` WRITE;
/*!40000 ALTER TABLE `sport` DISABLE KEYS */;

INSERT INTO `sport` (`sports_id`, `sports_name`, `active`, `created_on`, `updated_on`)
VALUES
	(31,'FOOT BALL','Y',0,0),
	(41,'BOXING','Y',0,0);

/*!40000 ALTER TABLE `sport` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table student
# ------------------------------------------------------------

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(100) NOT NULL,
  `hub_id` int(11) NOT NULL,
  `sport_id` int(11) NOT NULL,
  `active` char(1) NOT NULL DEFAULT 'Y',
  `status` varchar(100) NOT NULL,
  `dob` date NOT NULL,
  `aadhar` varchar(300) NOT NULL,
  `educational_institution` varchar(100) DEFAULT NULL,
  `cource` varchar(100) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `jercy_size` int(11) DEFAULT NULL,
  `shoe_size` int(11) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  `height` decimal(5,2) NOT NULL,
  `address` varchar(500) NOT NULL,
  `land_mark` varchar(300) DEFAULT NULL,
  `father_name` varchar(100) DEFAULT NULL,
  `father_occupation` varchar(100) DEFAULT NULL,
  `father_income` int(11) DEFAULT NULL,
  `mother_name` varchar(100) DEFAULT NULL,
  `mother_occupation` varchar(100) DEFAULT NULL,
  `mother_income` int(11) DEFAULT NULL,
  `siblings` varchar(300) DEFAULT NULL,
  `post_office` varchar(100) DEFAULT NULL,
  `profile_image` varchar(300) DEFAULT NULL,
  `professional_image` varchar(300) DEFAULT NULL,
  `created_on` int(16) NOT NULL,
  `updated_on` int(16) NOT NULL,
  `medical_record` varchar(1500) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `student_hub_fk_idx` (`hub_id`),
  KEY `student_sport_idx` (`sport_id`),
  KEY `student_status_fk_idx` (`status`),
  CONSTRAINT `student_hub_fk` FOREIGN KEY (`hub_id`) REFERENCES `hub` (`hub_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_sport` FOREIGN KEY (`sport_id`) REFERENCES `sport` (`sports_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_status_fk` FOREIGN KEY (`status`) REFERENCES `student_status` (`status`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;

INSERT INTO `student` (`student_id`, `student_name`, `hub_id`, `sport_id`, `active`, `status`, `dob`, `aadhar`, `educational_institution`, `cource`, `year`, `jercy_size`, `shoe_size`, `weight`, `height`, `address`, `land_mark`, `father_name`, `father_occupation`, `father_income`, `mother_name`, `mother_occupation`, `mother_income`, `siblings`, `post_office`, `profile_image`, `professional_image`, `created_on`, `updated_on`, `medical_record`)
VALUES
	(41,'test',81,31,'A','ACTIVE','1990-12-30','123123',NULL,NULL,1991,NULL,NULL,20,10.00,'asdfasdfasdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1544942334,1544942334,NULL);

/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table student_status
# ------------------------------------------------------------

DROP TABLE IF EXISTS `student_status`;

CREATE TABLE `student_status` (
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `student_status` WRITE;
/*!40000 ALTER TABLE `student_status` DISABLE KEYS */;

INSERT INTO `student_status` (`status`)
VALUES
	('ACTIVE'),
	('DROPPED');

/*!40000 ALTER TABLE `student_status` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table student_tournament
# ------------------------------------------------------------

DROP TABLE IF EXISTS `student_tournament`;

CREATE TABLE `student_tournament` (
  `student_id` int(11) NOT NULL,
  `tournament_id` int(11) NOT NULL,
  `achievements` varchar(500) DEFAULT NULL,
  `created_on` int(16) NOT NULL,
  `updated_on` int(16) NOT NULL,
  PRIMARY KEY (`student_id`),
  KEY `tournament_fk_idx` (`tournament_id`),
  CONSTRAINT `student_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tournament_fk` FOREIGN KEY (`tournament_id`) REFERENCES `tournament` (`tournament_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table tournament
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tournament`;

CREATE TABLE `tournament` (
  `tournament_id` int(11) NOT NULL AUTO_INCREMENT,
  `tournament_name` varchar(300) NOT NULL,
  `start` int(16) NOT NULL,
  `end` int(16) NOT NULL,
  `sport_id` int(11) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `created_on` int(16) NOT NULL,
  `updated_on` int(16) NOT NULL,
  PRIMARY KEY (`tournament_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL DEFAULT '',
  `user_type_id` int(11) NOT NULL,
  `login_id` varchar(100) NOT NULL,
  `coach_id` int(11) DEFAULT NULL,
  `created_on` bigint(16) NOT NULL,
  `updated_on` bigint(16) NOT NULL,
  `status` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_id_UNIQUE` (`login_id`),
  KEY `user_coach_fk_idx` (`coach_id`),
  KEY `user_type_id_idx` (`user_type_id`),
  CONSTRAINT `user_coach_fk` FOREIGN KEY (`coach_id`) REFERENCES `coach` (`coach_id`),
  CONSTRAINT `user_type_id` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`user_id`, `user_name`, `user_type_id`, `login_id`, `coach_id`, `created_on`, `updated_on`, `status`)
VALUES
	(651,'Raj Kumar',31,'prajkumar.cse@gmail.com',111,1544940250456,1544940250456,'A'),
	(661,'Janani',31,'ginerva123@gmail.com',121,0,0,'A');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_type`;

CREATE TABLE `user_type` (
  `user_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(100) NOT NULL,
  PRIMARY KEY (`user_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;

INSERT INTO `user_type` (`user_type_id`, `type`)
VALUES
	(21,'ADMIN'),
	(31,'COACH');

/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

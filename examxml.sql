# Host: localhost  (Version 5.7.16-log)
# Date: 2016-12-09 13:45:10
# Generator: MySQL-Front 5.4  (Build 4.33)
# Internet: http://www.mysqlfront.de/

/*!40101 SET NAMES utf8 */;

#
# Structure for table "class2014001"
#

DROP TABLE IF EXISTS `class2014001`;
CREATE TABLE `class2014001` (
  `student_id` int(10) NOT NULL,
  `studen_name` varchar(10) NOT NULL,
  `finished` int(1) DEFAULT NULL,
  `score` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "class2014001"
#

INSERT INTO `class2014001` VALUES (10001,'霍宗盛',0,NULL);

#
# Structure for table "login_chief"
#

DROP TABLE IF EXISTS `login_chief`;
CREATE TABLE `login_chief` (
  `id` int(10) NOT NULL,
  `name` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "login_chief"
#

INSERT INTO `login_chief` VALUES (101,'淑红','123');

#
# Structure for table "login_student"
#

DROP TABLE IF EXISTS `login_student`;
CREATE TABLE `login_student` (
  `id` int(10) NOT NULL,
  `name` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `age` int(3) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "login_student"
#

INSERT INTO `login_student` VALUES (10001,'霍宗聖','123',20,'云南省昆明市云南大学呈贡校区，楸苑3栋B210'),(10005,'测试学生1','456',20,'测试学生1的住址');

#
# Structure for table "login_teacher"
#

DROP TABLE IF EXISTS `login_teacher`;
CREATE TABLE `login_teacher` (
  `id` int(4) NOT NULL,
  `name` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "login_teacher"
#

INSERT INTO `login_teacher` VALUES (1001,'孙宇宁','123');

#
# Structure for table "stu10001"
#

DROP TABLE IF EXISTS `stu10001`;
CREATE TABLE `stu10001` (
  `class_id` int(10) NOT NULL,
  `class_name` varchar(10) NOT NULL,
  `score` int(3) DEFAULT NULL,
  `exam_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "stu10001"
#

INSERT INTO `stu10001` VALUES (2014001,'高等数学',-1,'2016-11-27'),(2014002,'离散数学',0,'2016-12-25'),(2014115,'通信技术',0,'2016-12-07'),(2014006,'flash动画设计',90,'2016-11-28');

#
# Structure for table "tea1001"
#

DROP TABLE IF EXISTS `tea1001`;
CREATE TABLE `tea1001` (
  `id` int(11) NOT NULL,
  `class_name` varchar(10) NOT NULL,
  `num` int(11) NOT NULL,
  `status` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "tea1001"
#

INSERT INTO `tea1001` VALUES (2014001,'高等数学',50,0);

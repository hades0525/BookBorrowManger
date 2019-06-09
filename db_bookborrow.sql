/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.18-log : Database - db_bookborrow
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_bookborrow` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_bookborrow`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `ISBN` char(10) NOT NULL,
  `typeid` varchar(20) NOT NULL,
  `bookname` varchar(30) NOT NULL,
  `author` varchar(30) NOT NULL,
  `publish` varchar(30) NOT NULL,
  `publishdate` date NOT NULL,
  `printtime` int(11) NOT NULL,
  `unitprice` float NOT NULL,
  PRIMARY KEY (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`ISBN`,`typeid`,`bookname`,`author`,`publish`,`publishdate`,`printtime`,`unitprice`) values ('0000000000','1','java','王岩','清华大学','2015-01-11',3,32.6),('1111111111','2','高等数学','王晶','同济大学','2016-08-08',2,32),('2222222222','2','线性代数','李洁','同济大学','2017-01-01',5,99.9),('333333333','2','离散数学','何伟','北京大学','2016-04-06',2,25.6),('333333339','2','离散数学','何伟','北京大学','2016-04-06',2,25.6),('7777777777','1','c语言','余家','北京大学','2016-05-06',3,35.6);

/*Table structure for table `booktype` */

DROP TABLE IF EXISTS `booktype`;

CREATE TABLE `booktype` (
  `id` int(11) NOT NULL,
  `typename` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `booktype` */

insert  into `booktype`(`id`,`typename`) values (1,'计算机'),(2,'数学'),(3,'音乐');

/*Table structure for table `borrowbook` */

DROP TABLE IF EXISTS `borrowbook`;

CREATE TABLE `borrowbook` (
  `readerid` char(8) NOT NULL,
  `ISBN` char(10) NOT NULL,
  `borrowdate` date DEFAULT NULL,
  `returndate` date DEFAULT NULL,
  `fine` float DEFAULT NULL,
  PRIMARY KEY (`readerid`,`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `borrowbook` */

insert  into `borrowbook`(`readerid`,`ISBN`,`borrowdate`,`returndate`,`fine`) values ('13245646','0000000000','2017-01-01',NULL,0),('13245646','1111111111','2017-05-12','2017-05-13',NULL),('13245646','7777777777','2017-05-13',NULL,NULL),('20156545','6666666666','2017-05-11','2017-05-20',NULL),('20165858','1111111111','2016-06-11',NULL,0);

/*Table structure for table `reader` */

DROP TABLE IF EXISTS `reader`;

CREATE TABLE `reader` (
  `readerid` char(8) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `name` char(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` char(4) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `dept` varchar(20) DEFAULT NULL,
  `regdate` date DEFAULT NULL,
  PRIMARY KEY (`readerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reader` */

insert  into `reader`(`readerid`,`type`,`name`,`age`,`sex`,`phone`,`dept`,`regdate`) values ('11111111',1,'妞妞',20,'女','1594564756','中文','2017-05-14'),('12345678',1,'王七',33,'男','1888888888','计算机','2017-05-11'),('13245646',2,'李勇一',45,'男','1746413464','数学','2012-01-26'),('20165858',1,'李四',33,'男','115451345','美术','2016-07-01'),('20166666',1,'张三',21,'男','1567431341','计科','2010-05-01'),('87654321',2,'奇虎',32,'女','15474547754','中文','2017-05-12');

/*Table structure for table `readertype` */

DROP TABLE IF EXISTS `readertype`;

CREATE TABLE `readertype` (
  `id` int(11) NOT NULL,
  `typename` varchar(20) DEFAULT NULL,
  `maxborrownum` int(11) DEFAULT NULL,
  `limit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `readertype` */

insert  into `readertype`(`id`,`typename`,`maxborrownum`,`limit`) values (1,'学生',5,30),(2,'老师',5,45),(3,'职工',4,30);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `users` */

insert  into `users`(`id`,`name`,`password`) values (1,'admin','123'),(2,'fff','1234'),(4,'hhu','123'),(5,'thomas','123'),(6,'jui','123');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

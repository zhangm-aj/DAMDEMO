/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.49 : Database - damdemo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`damdemo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `damdemo`;

/*Table structure for table `dam_user` */

DROP TABLE IF EXISTS `dam_user`;

CREATE TABLE `dam_user` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '姓名',
  `email` varchar(90) NOT NULL DEFAULT '' COMMENT '电子邮件',
  `password` varchar(512) NOT NULL DEFAULT '' COMMENT '加密密码',
  `created` date NOT NULL COMMENT '创建时间',
  `screenName` varchar(20) NOT NULL DEFAULT '' COMMENT '姓名',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Table structure for table `example_quick_file` */

DROP TABLE IF EXISTS `example_quick_file`;

CREATE TABLE `example_quick_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'file id',
  `name` varchar(200) NOT NULL COMMENT '文件名',
  `length` bigint(20) unsigned zerofill NOT NULL DEFAULT '00000000000000000000' COMMENT '文件大小',
  `translate_status` varchar(20) DEFAULT NULL COMMENT '转换状态',
  `databag_status` varchar(20) DEFAULT NULL COMMENT '离线包状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `project_id` int(20) DEFAULT NULL,
  `folder_id` bigint(20) NOT NULL,
  `version` float DEFAULT NULL,
  `is_folder` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1681934386546145 DEFAULT CHARSET=utf8;

/*Table structure for table `example_quick_integrate` */

DROP TABLE IF EXISTS `example_quick_integrate`;

CREATE TABLE `example_quick_integrate` (
  `id` bigint(20) NOT NULL COMMENT 'integrate id',
  `name` varchar(200) NOT NULL COMMENT '集成项目名',
  `file_num` int(11) NOT NULL COMMENT '集成文件数目',
  `integrate_status` varchar(50) NOT NULL COMMENT '集成状态，prepare，processing，success，failed',
  `databag_status` varchar(50) DEFAULT NULL COMMENT '离线包状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快速入门集成模型表';

/*Table structure for table `example_quick_integrate_file` */

DROP TABLE IF EXISTS `example_quick_integrate_file`;

CREATE TABLE `example_quick_integrate_file` (
  `id` bigint(20) NOT NULL,
  `integrate_id` bigint(20) NOT NULL,
  `file_id` bigint(20) NOT NULL,
  `file_name` varchar(200) NOT NULL,
  `specialty` varchar(32) DEFAULT NULL,
  `specialty_sort` float(255,0) DEFAULT NULL,
  `floor` varchar(32) DEFAULT NULL,
  `floor_sort` float(255,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `integrate_id` (`integrate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `file_message` */

DROP TABLE IF EXISTS `file_message`;

CREATE TABLE `file_message` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) DEFAULT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `created` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `t_contents` */

DROP TABLE IF EXISTS `t_contents`;

CREATE TABLE `t_contents` (
  `cid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `titlePic` varchar(55) DEFAULT NULL,
  `slug` varchar(200) DEFAULT NULL,
  `created` int(10) unsigned DEFAULT '0',
  `modified` int(10) unsigned DEFAULT '0',
  `content` text COMMENT '内容文字',
  `authorId` int(10) unsigned DEFAULT '0',
  `type` varchar(16) DEFAULT 'post',
  `status` varchar(16) DEFAULT 'publish',
  `tags` varchar(200) DEFAULT NULL,
  `categories` varchar(200) DEFAULT NULL,
  `hits` int(10) unsigned DEFAULT '0',
  `commentsNum` int(10) unsigned DEFAULT '0',
  `allowComment` tinyint(1) DEFAULT '1',
  `allowPing` tinyint(1) DEFAULT '1',
  `allowFeed` tinyint(1) DEFAULT '1',
  `username` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE,
  UNIQUE KEY `slug` (`slug`) USING BTREE,
  KEY `created` (`created`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Table structure for table `t_project` */

DROP TABLE IF EXISTS `t_project`;

CREATE TABLE `t_project` (
  `created` int(10) unsigned DEFAULT NULL,
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `pname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Table structure for table `t_relationships` */

DROP TABLE IF EXISTS `t_relationships`;

CREATE TABLE `t_relationships` (
  `cid` int(10) unsigned NOT NULL,
  `mid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`cid`,`mid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.25 : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `blog`;

/*Table structure for table `a1_c` */

CREATE TABLE `a1_c` (
  `x3` int(11) NOT NULL AUTO_INCREMENT,
  `x1` int(11) NOT NULL,
  `x2` int(11) NOT NULL,
  PRIMARY KEY (`x3`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `seq` */

CREATE TABLE `seq` (
  `seq_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'seqId',
  PRIMARY KEY (`seq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='seq';

/*Table structure for table `sys_authgroup` */

CREATE TABLE `sys_authgroup` (
  `SYS_AUTHGROUP_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限组ID',
  `SYS_AUTHGROUP_NAME` varchar(45) DEFAULT NULL COMMENT '权限组名称',
  `SYS_AUTHGROUP_DESC` varchar(1000) DEFAULT NULL COMMENT '权限组描述',
  `MENU_LEVEL_1` varchar(100) DEFAULT NULL COMMENT '一级菜单',
  `MENU_LEVEL_2` varchar(100) DEFAULT NULL COMMENT '二级菜单',
  `MENU_LEVEL_3` varchar(100) DEFAULT NULL COMMENT '三级菜单',
  `MENU_LEVEL_4` varchar(100) DEFAULT NULL COMMENT '四级菜单',
  `SYS_AUTHORITY_ID` varchar(1000) DEFAULT NULL COMMENT '权限ID',
  `MENU_PARAMS` varchar(1000) DEFAULT NULL COMMENT '权限ID',
  `STATUS_CODE` int(11) DEFAULT '10' COMMENT '状态标志(未启用,启用)',
  `STATUS_CHANGE_TIME` timestamp NULL DEFAULT NULL COMMENT '状态修改时间',
  `DEL_FLAG` int(11) DEFAULT '0' COMMENT '删除标志 0=未删除1=已删除',
  `DEL_TIME` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `CREATE_TIME` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`SYS_AUTHGROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限组表';

/*Table structure for table `sys_authority` */

CREATE TABLE `sys_authority` (
  `SYS_AUTHORITY_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `SYS_AUTHORITY_NAME` varchar(45) DEFAULT NULL COMMENT '权限名称',
  `SYS_AUTHORITY_DESC` varchar(1000) DEFAULT NULL COMMENT '权限描述',
  `SYS_AUTHORITY_URL` varchar(1000) DEFAULT NULL COMMENT '权限URL地址',
  `SYS_AUTHORITY_TYPE` varchar(45) DEFAULT 'unknow' COMMENT '权限类型(read,update,add,delete,resource,unknow)',
  `CONTROL_TYPE` varchar(45) DEFAULT 'login' COMMENT '状态标志(none,login,role,admin) ',
  `STATUS_CODE` int(11) DEFAULT '10' COMMENT '状态标志(未启用,启用)',
  `STATUS_CHANGE_TIME` timestamp NULL DEFAULT NULL COMMENT '状态修改时间',
  `DEL_FLAG` int(11) DEFAULT '0' COMMENT '删除标志 0=未删除1=已删除',
  `DEL_TIME` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `CREATE_TIME` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`SYS_AUTHORITY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='系统权限表';

/*Table structure for table `sys_role` */

CREATE TABLE `sys_role` (
  `SYS_ROLE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `SYS_ROLE_NAME` varchar(45) DEFAULT NULL COMMENT '角色名称',
  `SYS_ROLE_DESC` varchar(1000) DEFAULT NULL COMMENT '角色描述',
  `MENU_LEVEL_1` varchar(100) DEFAULT NULL COMMENT '一级菜单',
  `MENU_LEVEL_2` varchar(100) DEFAULT NULL COMMENT '二级菜单',
  `MENU_LEVEL_3` varchar(100) DEFAULT NULL COMMENT '三级菜单',
  `MENU_LEVEL_4` varchar(100) DEFAULT NULL COMMENT '四级菜单',
  `SYS_AUTHORITY_ID` int(11) DEFAULT NULL COMMENT '权限ID',
  `MENU_URL` varchar(200) DEFAULT NULL COMMENT '菜单url',
  `MENU_PARAMS` varchar(1000) DEFAULT NULL COMMENT '菜单参数',
  `MENU_SORT` int(11) DEFAULT NULL COMMENT '菜单排序',
  `STATUS_CODE` int(11) DEFAULT '10' COMMENT '状态标志(0=未启用,10=启用)',
  `STATUS_CHANGE_TIME` timestamp NULL DEFAULT NULL COMMENT '状态修改时间',
  `DEL_FLAG` int(11) DEFAULT '0' COMMENT '删除标志(0=未删除,1=已删除)',
  `DEL_TIME` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `CREATE_TIME` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`SYS_ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Table structure for table `sys_role_authority_mapping` */

CREATE TABLE `sys_role_authority_mapping` (
  `sys_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_authority_id` int(11) NOT NULL,
  PRIMARY KEY (`sys_role_id`,`sys_authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_user` */

CREATE TABLE `sys_user` (
  `sys_user_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `sys_USER_NAME` varchar(100) NOT NULL COMMENT '用户显示名',
  `USER_LOGON_NAME` varchar(100) NOT NULL COMMENT '用户登录名',
  `USER_PASSWORD` varchar(100) NOT NULL COMMENT '密码',
  `USER_TYPE_CODE` int(11) DEFAULT NULL COMMENT '用户类型',
  `PERSON_INFO_ID` int(11) DEFAULT NULL COMMENT '人个信息ID',
  `USER_DESCS` varchar(1000) DEFAULT NULL COMMENT '描述',
  `STATUS_CODE` int(11) DEFAULT '10' COMMENT '状态标志',
  `STATUS_CHANGE_TIME` timestamp NULL DEFAULT NULL COMMENT '状态修改时间',
  `DEL_FLAG` int(11) DEFAULT '0' COMMENT '删除标志',
  `DEL_TIME` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `CREATE_TIME` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`sys_user_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Table structure for table `sys_user_role_mapping` */

CREATE TABLE `sys_user_role_mapping` (
  `sys_user_id` int(11) NOT NULL,
  `sys_role_id` int(11) NOT NULL,
  PRIMARY KEY (`sys_user_id`,`sys_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t1_a` */

CREATE TABLE `t1_a` (
  `x1` int(11) NOT NULL AUTO_INCREMENT,
  `c` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`x1`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `t1_b` */

CREATE TABLE `t1_b` (
  `x2` int(11) NOT NULL AUTO_INCREMENT,
  `x1` int(11) NOT NULL,
  `a` varchar(45) DEFAULT NULL,
  `c` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`x2`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `tbfile` */

CREATE TABLE `tbfile` (
  `fileid` int(11) NOT NULL AUTO_INCREMENT,
  `filename` varchar(200) DEFAULT NULL,
  `filesize` int(11) DEFAULT NULL,
  `filebody` longblob,
  `createuserid` int(11) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  PRIMARY KEY (`fileid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `test_tx` */

CREATE TABLE `test_tx` (
  `count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

CREATE TABLE `user` (
  `user_id` int(12) NOT NULL DEFAULT '3' COMMENT '用户ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户名',
  `last_login_time` date DEFAULT NULL COMMENT '最后登录时间',
  `user_info_id` int(11) DEFAULT NULL COMMENT '个人信息扩展id',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Table structure for table `user_info` */

CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_info_id` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `user_visit_log` */

CREATE TABLE `user_visit_log` (
  `user_visit_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(512) DEFAULT NULL,
  `encoding` varchar(20) DEFAULT NULL,
  `content_length` int(11) DEFAULT NULL,
  `method_type` varchar(20) DEFAULT NULL,
  `protocol` varchar(50) DEFAULT NULL,
  `server_ip` varchar(50) DEFAULT NULL,
  `server_port` varchar(20) DEFAULT NULL,
  `project_name` varchar(50) DEFAULT NULL,
  `path` varchar(512) DEFAULT NULL,
  `query_string` varchar(512) DEFAULT NULL,
  `user_principal` varchar(512) DEFAULT NULL,
  `user_ip` varchar(50) DEFAULT NULL,
  `session_id` varchar(100) DEFAULT NULL,
  `browser` varchar(100) DEFAULT NULL,
  `cookie` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`user_visit_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2344 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

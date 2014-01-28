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

DROP TABLE IF EXISTS `a1_c`;

CREATE TABLE `a1_c` (
  `x3` int(11) NOT NULL AUTO_INCREMENT,
  `x1` int(11) NOT NULL,
  `x2` int(11) NOT NULL,
  PRIMARY KEY (`x3`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `a1_c` */

insert  into `a1_c`(`x3`,`x1`,`x2`) values (1,1,1),(2,1,2),(3,2,3),(4,3,4),(5,2,3);

/*Table structure for table `seq` */

DROP TABLE IF EXISTS `seq`;

CREATE TABLE `seq` (
  `seq_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'seqId',
  PRIMARY KEY (`seq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='seq';

/*Data for the table `seq` */

insert  into `seq`(`seq_id`) values (1),(2),(3),(4),(5);

/*Table structure for table `sys_authgroup` */

DROP TABLE IF EXISTS `sys_authgroup`;

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

/*Data for the table `sys_authgroup` */

/*Table structure for table `sys_authority` */

DROP TABLE IF EXISTS `sys_authority`;

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

/*Data for the table `sys_authority` */

insert  into `sys_authority`(`SYS_AUTHORITY_ID`,`SYS_AUTHORITY_NAME`,`SYS_AUTHORITY_DESC`,`SYS_AUTHORITY_URL`,`SYS_AUTHORITY_TYPE`,`CONTROL_TYPE`,`STATUS_CODE`,`STATUS_CHANGE_TIME`,`DEL_FLAG`,`DEL_TIME`,`CREATE_TIME`,`UPDATE_TIME`) values (1,'/page/system/sysUser_sysUserList.action',NULL,'/page/system/sysUser_sysUserList.action','read','login',10,'2013-11-25 01:41:07',NULL,NULL,'2013-11-25 01:41:07',NULL),(2,'/bootstrap/css/bootstrap.css',NULL,'/bootstrap/css/bootstrap.css','resource','login',10,'2013-11-25 01:41:08',NULL,NULL,'2013-11-25 01:41:08',NULL),(3,'/bootstrap/ext/dataTables.bootstra.css',NULL,'/bootstrap/ext/dataTables.bootstra.css','resource','login',10,'2013-11-25 01:41:08',NULL,NULL,'2013-11-25 01:41:08',NULL),(4,'/jquery/jquery-2.0.2.js',NULL,'/jquery/jquery-2.0.2.js','resource','login',10,'2013-11-25 01:41:08',NULL,NULL,'2013-11-25 01:41:08',NULL),(5,'/jquery/plugin/jquery.pagination.js',NULL,'/jquery/plugin/jquery.pagination.js','resource','login',10,'2013-11-25 01:41:08',NULL,NULL,'2013-11-25 01:41:08',NULL),(6,'/bootstrap/js/bootstrap.js',NULL,'/bootstrap/js/bootstrap.js','resource','login',10,'2013-11-25 01:41:08',NULL,NULL,'2013-11-25 01:41:08',NULL),(7,'/page/system/sysUser_sysUserAdd.action',NULL,'/page/system/sysUser_sysUserAdd.action','add','login',10,'2013-11-25 01:41:15',NULL,NULL,'2013-11-25 01:41:15',NULL),(8,'/bootstrap/css/bootstrap-responsive.css',NULL,'/bootstrap/css/bootstrap-responsive.css','resource','login',10,'2013-11-25 01:41:15',NULL,NULL,'2013-11-25 01:41:15',NULL),(9,'/page/system/sysUser_sysUserAddDone.action',NULL,'/page/system/sysUser_sysUserAddDone.action','add','login',10,'2013-11-25 01:41:26',NULL,NULL,'2013-11-25 01:41:26',NULL),(10,'/page/system/sysUser_sysUserDetail.action',NULL,'/page/system/sysUser_sysUserDetail.action','unknow','login',10,'2013-11-25 01:43:45',NULL,NULL,'2013-11-25 01:43:45',NULL),(11,'/page/system/sysUser_sysUserUpdate.action',NULL,'/page/system/sysUser_sysUserUpdate.action','update','login',10,'2013-11-25 01:43:46',NULL,NULL,'2013-11-25 01:43:46',NULL),(12,'/page/system/sysUser_sysUserUpdateDone.action',NULL,'/page/system/sysUser_sysUserUpdateDone.action','update','login',10,'2013-11-25 01:43:49',NULL,NULL,'2013-11-25 01:43:49',NULL),(13,'/page/system/sysUser_sysUserDel.action',NULL,'/page/system/sysUser_sysUserDel.action','delete','login',10,'2013-11-25 01:43:53',NULL,NULL,'2013-11-25 01:43:53',NULL),(14,'/login.jsp',NULL,'/login.jsp','unknow','login',10,'2013-11-25 01:43:57',NULL,NULL,'2013-11-25 01:43:57',NULL),(15,'/common/validatecode.action',NULL,'/common/validatecode.action','unknow','login',10,'2013-11-25 01:43:57',NULL,NULL,'2013-11-25 01:43:57',NULL),(16,'/bootstrap/js/jquery.js',NULL,'/bootstrap/js/jquery.js','resource','login',10,'2013-11-25 01:43:57',NULL,NULL,'2013-11-25 01:43:57',NULL),(17,'/bootstrap/js/bootstrap-collapse.js',NULL,'/bootstrap/js/bootstrap-collapse.js','resource','login',10,'2013-11-25 01:43:57',NULL,NULL,'2013-11-25 01:43:57',NULL),(18,'/bootstrap/js/bootstrap-alert.js',NULL,'/bootstrap/js/bootstrap-alert.js','resource','login',10,'2013-11-25 01:43:57',NULL,NULL,'2013-11-25 01:43:57',NULL),(19,'/bootstrap/js/bootstrap-scrollspy.js',NULL,'/bootstrap/js/bootstrap-scrollspy.js','resource','login',10,'2013-11-25 01:43:57',NULL,NULL,'2013-11-25 01:43:57',NULL),(20,'/bootstrap/js/bootstrap-tooltip.js',NULL,'/bootstrap/js/bootstrap-tooltip.js','resource','login',10,'2013-11-25 01:43:57',NULL,NULL,'2013-11-25 01:43:57',NULL),(21,'/bootstrap/js/bootstrap-carousel.js',NULL,'/bootstrap/js/bootstrap-carousel.js','resource','login',10,'2013-11-25 01:43:57',NULL,NULL,'2013-11-25 01:43:57',NULL),(22,'/bootstrap/js/bootstrap-modal.js',NULL,'/bootstrap/js/bootstrap-modal.js','resource','login',10,'2013-11-25 01:43:57',NULL,NULL,'2013-11-25 01:43:57',NULL),(23,'/bootstrap/js/bootstrap-popover.js',NULL,'/bootstrap/js/bootstrap-popover.js','resource','login',10,'2013-11-25 01:43:57',NULL,NULL,'2013-11-25 01:43:57',NULL),(24,'/bootstrap/js/bootstrap-tab.js',NULL,'/bootstrap/js/bootstrap-tab.js','resource','login',10,'2013-11-25 01:43:57',NULL,NULL,'2013-11-25 01:43:57',NULL),(25,'/bootstrap/js/bootstrap-transition.js',NULL,'/bootstrap/js/bootstrap-transition.js','resource','login',10,'2013-11-25 01:43:57',NULL,NULL,'2013-11-25 01:43:57',NULL),(26,'/bootstrap/js/bootstrap-typeahead.js',NULL,'/bootstrap/js/bootstrap-typeahead.js','resource','login',10,'2013-11-25 01:43:57',NULL,NULL,'2013-11-25 01:43:57',NULL),(27,'/bootstrap/js/bootstrap-button.js',NULL,'/bootstrap/js/bootstrap-button.js','resource','login',10,'2013-11-25 01:43:57',NULL,NULL,'2013-11-25 01:43:57',NULL),(28,'/bootstrap/js/bootstrap-dropdown.js',NULL,'/bootstrap/js/bootstrap-dropdown.js','resource','login',10,'2013-11-25 01:43:57',NULL,NULL,'2013-11-25 01:43:57',NULL),(29,'/j_spring_security_check',NULL,'/j_spring_security_check','unknow','login',10,'2013-11-25 01:43:59',NULL,NULL,'2013-11-25 01:43:59',NULL),(30,'/page/system/sysUserList.jsp',NULL,'/page/system/sysUserList.jsp','read','login',10,'2013-11-25 01:44:05',NULL,NULL,'2013-11-25 01:44:05',NULL),(31,'/page/system/sysUser_sysUserList.jsp.action',NULL,'/page/system/sysUser_sysUserList.jsp.action','read','login',10,'2013-11-28 03:46:46',0,NULL,'2013-11-28 03:46:46',NULL),(32,'/page/system/jquery/jquery-2.0.2.js',NULL,'/page/system/jquery/jquery-2.0.2.js','resource','login',10,'2013-11-28 03:46:48',0,NULL,'2013-11-28 03:46:48',NULL),(33,'/page/user_listUser.action',NULL,'/page/user_listUser.action','read','login',10,'2013-11-28 22:25:14',0,NULL,'2013-11-28 22:25:14',NULL),(34,'/anno.action',NULL,'/anno.action','unknow','login',10,'2013-11-30 00:42:27',0,NULL,'2013-11-30 00:42:27',NULL),(35,'/dispatcher.action',NULL,'/dispatcher.action','unknow','login',10,'2013-11-30 00:42:53',0,NULL,'2013-11-30 00:42:53',NULL),(36,'/redirect.action',NULL,'/redirect.action','unknow','login',10,'2013-11-30 00:42:58',0,NULL,'2013-11-30 00:42:58',NULL),(37,'/index.jsp',NULL,'/index.jsp','unknow','login',10,'2013-11-30 00:42:58',0,NULL,'2013-11-30 00:42:58',NULL),(38,'/plainText.action',NULL,'/plainText.action','unknow','login',10,'2013-11-30 00:43:00',0,NULL,'2013-11-30 00:43:00',NULL),(39,'/redirectAction.action',NULL,'/redirectAction.action','unknow','login',10,'2013-11-30 00:43:05',0,NULL,'2013-11-30 00:43:05',NULL),(40,'/directAction.action',NULL,'/directAction.action','unknow','login',10,'2013-11-30 00:43:16',0,NULL,'2013-11-30 00:43:16',NULL),(41,'/duplicate.action',NULL,'/duplicate.action','unknow','login',10,'2013-11-30 00:43:19',0,NULL,'2013-11-30 00:43:19',NULL),(42,'/json_getJson.action',NULL,'/json_getJson.action','read','login',10,'2013-11-30 00:43:24',0,NULL,'2013-11-30 00:43:24',NULL),(43,'/page/user_listUser1.action',NULL,'/page/user_listUser1.action','read','login',10,'2013-11-30 00:43:34',0,NULL,'2013-11-30 00:43:34',NULL),(44,'/page/self-query.html',NULL,'/page/self-query.html','read','login',10,'2013-11-30 00:43:39',0,NULL,'2013-11-30 00:43:39',NULL),(45,'/OlapQueryServlet',NULL,'/OlapQueryServlet','unknow','login',10,'2013-11-30 00:43:40',0,NULL,'2013-11-30 00:43:40',NULL),(46,'/page/test_sendMsg.action',NULL,'/page/test_sendMsg.action','unknow','login',10,'2013-11-30 00:44:03',0,NULL,'2013-11-30 00:44:03',NULL),(47,'/page/test_sendMsg2.action',NULL,'/page/test_sendMsg2.action','unknow','login',10,'2013-11-30 00:44:09',0,NULL,'2013-11-30 00:44:09',NULL),(48,'/page/test_sendMsg3.action',NULL,'/page/test_sendMsg3.action','unknow','login',10,'2013-11-30 00:44:16',0,NULL,'2013-11-30 00:44:16',NULL),(49,'/page/test_sendMsg4.action',NULL,'/page/test_sendMsg4.action','unknow','login',10,'2013-11-30 00:44:21',0,NULL,'2013-11-30 00:44:21',NULL),(50,'/page/jquery/jquery-2.0.2.js',NULL,'/page/jquery/jquery-2.0.2.js','resource','login',10,'2013-11-30 00:44:22',0,NULL,'2013-11-30 00:44:22',NULL),(51,'/page/system/bootstrap/img/favicon.png',NULL,'/page/system/bootstrap/img/favicon.png','resource','login',10,'2014-01-06 01:01:00',0,NULL,'2014-01-06 01:01:00',NULL),(52,'/bootstrap/img/favicon.png',NULL,'/bootstrap/img/favicon.png','resource','login',10,'2014-01-09 03:14:56',0,NULL,'2014-01-09 03:14:56',NULL),(53,'/page/system/sysUser_sysUserDetail.action',NULL,'/page/system/sysUser_sysUserDetail.action','unknow','login',10,'2013-11-25 01:43:45',NULL,NULL,'2013-11-25 01:43:45',NULL),(54,'/page/system/sysUser_sysUserDetail.action',NULL,'/page/system/sysUser_sysUserDetail.action','unknow','login',10,'2013-11-25 01:43:45',NULL,NULL,'2013-11-25 01:43:45',NULL),(55,'/page/system/sysUser_sysUserList1.action',NULL,'/page/system/sysUser_sysUserList1.action','read','login',10,'2014-01-26 06:55:21',0,NULL,'2014-01-26 06:55:21',NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

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

/*Data for the table `sys_role` */

insert  into `sys_role`(`SYS_ROLE_ID`,`SYS_ROLE_NAME`,`SYS_ROLE_DESC`,`MENU_LEVEL_1`,`MENU_LEVEL_2`,`MENU_LEVEL_3`,`MENU_LEVEL_4`,`SYS_AUTHORITY_ID`,`MENU_URL`,`MENU_PARAMS`,`MENU_SORT`,`STATUS_CODE`,`STATUS_CHANGE_TIME`,`DEL_FLAG`,`DEL_TIME`,`CREATE_TIME`,`UPDATE_TIME`) values (22,'anno.action',NULL,'ActionResultType','ActionResultType','anno.action',NULL,34,'/anno.action',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 04:40:58'),(23,'dispatcher.action',NULL,'ActionResultType','ActionResultType','dispatcher.action',NULL,35,'/dispatcher.action',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 04:40:58'),(24,'redirect.action',NULL,'ActionResultType','ActionResultType','redirect.action',NULL,36,'/redirect.action',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 04:40:58'),(25,'plainText.action',NULL,'ActionResultType','ActionResultType','plainText.action',NULL,38,'/plainText.action',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 04:40:58'),(26,'redirectAction.action',NULL,'ActionResultType','ActionResultType','redirectAction.action',NULL,39,'/redirectAction.action',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 04:40:58'),(27,'directAction.action(NG)',NULL,'ActionResultType','ActionResultType','directAction.action(NG)',NULL,40,'/directAction.action',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 04:40:58'),(28,'duplicate.action',NULL,'ActionResultType','ActionResultType','duplicate.action',NULL,41,'/duplicate.action',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 04:40:58'),(29,'getJson()',NULL,'ActionResultType','ActionResultType','getJson()',NULL,42,'javascript:getJson();',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 04:40:58'),(30,'user_listUser.action',NULL,'SSH2Func','SSH2Func','user_listUser.action',NULL,30,'/page/user_listUser.action',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 04:40:58'),(31,'user_listUser1.action',NULL,'SSH2Func','SSH2Func','user_listUser1.action',NULL,43,'/page/user_listUser1.action',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 04:40:58'),(32,'self_query_a1_c',NULL,'SSH2Func','SSH2Func','self_query_a1_c',NULL,45,'/page/self-query.html?table=v3_orders',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 04:40:58'),(33,'sample_queue',NULL,'Queue','Queue','sample_queue',NULL,46,'/page/test_sendMsg.action?msg=abc',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 04:40:58'),(34,'sample2_queue',NULL,'Queue','Queue','sample2_queue',NULL,47,'/page/test_sendMsg2.action?msg=abc',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 04:40:58'),(35,'sample3_queue',NULL,'Queue','Queue','sample3_queue',NULL,48,'/page/test_sendMsg3.action?msg=abc',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 04:40:58'),(36,'sample4_queue',NULL,'Queue','Queue','sample4_queue',NULL,49,'/page/test_sendMsg4.action?msg=abc',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 04:40:58'),(37,'sys_user_list',NULL,'SysUser','SysUser','sys_user_list',NULL,1,'/page/system/sysUser_sysUserList.action',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 05:12:38'),(38,'sys_user_list1',NULL,'SysUser','SysUser1','sys_user_list1',NULL,1,'/page/system/sysUser_sysUserList1.action',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-22 05:13:16'),(39,'sys_user_list2',NULL,'SysUser','SysUser','sys_user_list2',NULL,1,'/page/system/sysUser_sysUserList2.action',NULL,NULL,10,NULL,0,NULL,'2013-11-28 22:34:40','2014-01-26 06:07:56');

/*Table structure for table `sys_role_authority_mapping` */

DROP TABLE IF EXISTS `sys_role_authority_mapping`;

CREATE TABLE `sys_role_authority_mapping` (
  `sys_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_authority_id` int(11) NOT NULL,
  PRIMARY KEY (`sys_role_id`,`sys_authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_authority_mapping` */

insert  into `sys_role_authority_mapping`(`sys_role_id`,`sys_authority_id`) values (22,2),(22,4),(22,6),(22,8),(22,34),(23,2),(23,4),(23,6),(23,8),(23,35),(24,8),(24,36),(24,37),(25,8),(25,38),(30,33),(30,46),(37,1),(37,2),(37,3),(37,4),(37,5),(37,6),(37,7),(37,8),(37,9),(37,10),(37,11),(37,12),(37,13),(37,14),(37,15),(37,16),(37,17),(37,18),(37,19),(37,20),(37,21),(37,22),(37,23),(37,24),(37,25),(37,26),(37,27),(37,28),(37,32),(37,46),(38,32),(38,55);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`sys_user_ID`,`sys_USER_NAME`,`USER_LOGON_NAME`,`USER_PASSWORD`,`USER_TYPE_CODE`,`PERSON_INFO_ID`,`USER_DESCS`,`STATUS_CODE`,`STATUS_CHANGE_TIME`,`DEL_FLAG`,`DEL_TIME`,`CREATE_TIME`,`UPDATE_TIME`) values (1,'AAAA7','A','A',10,NULL,'',10,NULL,0,NULL,NULL,'2013-11-10 07:27:45'),(2,'AA','A','A',10,NULL,'',10,NULL,0,NULL,NULL,'2013-11-10 07:27:45'),(3,'B','A','A',10,NULL,'',10,NULL,0,NULL,NULL,'2013-11-10 07:27:45'),(5,'DDD2','D1','D1',10,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL),(6,'1','1','1',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `t1_a` */

DROP TABLE IF EXISTS `t1_a`;

CREATE TABLE `t1_a` (
  `x1` int(11) NOT NULL AUTO_INCREMENT,
  `c` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`x1`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t1_a` */

insert  into `t1_a`(`x1`,`c`) values (1,'1'),(2,'2'),(3,'3');

/*Table structure for table `t1_b` */

DROP TABLE IF EXISTS `t1_b`;

CREATE TABLE `t1_b` (
  `x2` int(11) NOT NULL AUTO_INCREMENT,
  `x1` int(11) NOT NULL,
  `a` varchar(45) DEFAULT NULL,
  `c` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`x2`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t1_b` */

insert  into `t1_b`(`x2`,`x1`,`a`,`c`) values (1,1,'1','1'),(2,1,'2','2'),(3,2,'1','2'),(4,3,'1','3'),(5,4,'2','1'),(6,4,'3','2'),(7,5,'4','1');

/*Table structure for table `tbfile` */

DROP TABLE IF EXISTS `tbfile`;

CREATE TABLE `tbfile` (
  `fileid` int(11) NOT NULL AUTO_INCREMENT,
  `filename` varchar(200) DEFAULT NULL,
  `filesize` int(11) DEFAULT NULL,
  `filebody` longblob,
  `createuserid` int(11) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  PRIMARY KEY (`fileid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tbfile` */

/*Table structure for table `test_tx` */

DROP TABLE IF EXISTS `test_tx`;

CREATE TABLE `test_tx` (
  `count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `test_tx` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(12) NOT NULL DEFAULT '3' COMMENT '用户ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户名',
  `last_login_time` date DEFAULT NULL COMMENT '最后登录时间',
  `user_info_id` int(11) DEFAULT NULL COMMENT '个人信息扩展id',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`last_login_time`,`user_info_id`) values (1,'\'kelv;e\'m\'','2012-06-19',1),(2,'kelvem','2012-06-19',1),(3,'kelvem','2012-06-19',1),(4,'kelvem','2012-06-19',1),(5,'kelvem','2012-06-19',1),(6,'kelvem','2012-06-19',1),(8,'kelvem','2012-06-19',1),(9,'kelvem','2012-06-19',1),(10,'kelvem','2012-06-19',1),(11,'kelvem','2012-06-19',1),(12,'kelvem','2012-06-19',1),(13,'kelvem','2012-06-19',1),(14,'kelvem','2012-06-19',1),(15,'kelvem','2012-06-19',1),(16,'kelvem','2012-06-19',1),(17,'kelvem','2012-06-19',1),(18,'kelvem','2012-06-19',1),(44,'kelvem','2012-06-28',NULL),(45,'kelvem','2012-06-28',NULL),(46,'kelvem','2012-06-28',NULL),(47,'kelvem','2012-06-29',NULL),(49,'kelvem','2012-06-29',NULL),(50,'kelvem','2012-07-04',NULL),(51,'kelvem','2012-07-04',NULL),(52,'kelvem','2012-07-04',NULL),(53,'kelvem','2012-07-04',NULL),(54,'kelvem','2012-07-04',NULL),(55,'kelvem','2012-07-04',NULL),(56,'kelvem','2012-07-04',NULL),(57,'kelvem','2012-07-04',NULL),(58,'kelvem','2012-07-04',NULL),(59,'kelvem','2012-07-04',NULL),(60,'kelvem','2012-07-04',NULL),(61,'kelvem','2012-07-04',NULL),(62,'kelvem','2012-07-04',NULL),(63,'kelvem','2012-07-04',NULL),(64,'kelvem','2012-07-04',NULL),(65,'kelvem','2012-07-04',NULL),(66,'kelvem','2012-07-04',NULL),(67,'kelvem','2012-07-04',NULL),(68,'kelvem','2012-07-04',NULL),(69,'kelvem','2012-07-04',NULL),(70,'kelvem','2012-07-04',NULL),(71,'kelvem','2012-07-04',NULL),(72,'kelvem','2012-07-04',NULL),(73,'kelvem','2012-07-04',NULL),(74,'kelvem','2012-07-04',NULL),(75,'kelvem','2012-07-04',NULL),(76,'kelvem','2012-07-04',NULL),(77,'kelvem','2012-07-04',NULL),(78,'kelvem','2012-07-04',NULL),(79,'kelvem','2012-07-04',NULL),(80,'kelvem','2012-07-04',NULL),(81,'kelvem','2012-07-04',NULL),(82,'kelvem','2012-07-04',NULL),(83,'kelvem','2012-07-04',NULL),(84,'kelvem','2012-07-04',NULL),(85,'kelvem','2012-07-04',NULL),(86,'kelvem','2012-07-04',NULL),(87,'kelvem','2012-07-04',NULL),(88,'kelvem','2012-07-04',NULL),(89,'kelvem','2012-07-04',NULL),(90,'kelvem','2012-07-04',NULL),(91,'kelvem','2012-07-04',NULL),(92,'kelvem','2012-07-04',1),(93,'kelvem','2012-07-04',1),(94,'kelvem','2012-07-04',1),(95,'kelvem','2012-07-04',1),(96,'kelvem','2012-07-04',1),(97,'kelvem','2012-07-04',1),(98,'kelvem','2012-07-04',1),(99,'kelvem','2012-07-04',1),(100,'kelvem','2012-07-04',1),(101,'kelvem','2012-07-05',NULL),(102,'kelvem','2012-07-05',NULL),(103,'kelvem','2012-07-05',NULL),(104,'kelvem','2012-07-05',1),(105,'kelvem','2012-07-05',1),(106,'kelvem','2012-07-05',1),(107,'kelvem','2012-07-05',1),(108,'kelvem','2012-07-05',1),(109,'kelvem','2012-07-05',1),(110,'kelvem','2012-07-05',1),(111,'kelvem','2012-07-05',1),(112,'kelvem','2012-07-05',1),(113,'kelvem','2012-07-05',1),(114,'kelvem','2012-07-05',1),(115,'kelvem','2012-07-05',1),(116,'kelvem','2012-07-05',1),(117,'kelvem','2012-07-05',1),(118,'kelvem','2012-07-05',1),(119,'kelvem','2012-07-05',1),(120,'kelvem','2012-07-05',1),(121,'kelvem','2012-07-05',1),(122,'kelvem','2012-07-05',1),(123,'kelvem','2012-07-05',1),(124,'kelvem','2012-07-05',1),(125,'kelvem','2012-07-05',1),(126,'kelvem','2012-07-05',1),(127,'kelvem','2012-07-05',1),(128,'kelvem','2012-07-05',1),(129,'kelvem','2012-07-05',1),(130,'kelvem','2012-07-05',1),(131,'kelvem','2012-07-05',1),(132,'kelvem','2012-07-05',1),(133,'kelvem','2012-07-05',1),(134,'kelvem','2012-07-05',1),(135,'kelvem','2012-07-05',1),(136,'kelvem','2012-07-05',1),(137,'kelvem','2012-07-05',1),(138,'kelvem','2012-07-05',1),(139,'kelvem','2012-07-05',1),(140,'kelvem','2012-07-05',1),(141,'kelvem','2012-07-05',1),(142,'kelvem','2012-07-05',1),(143,'kelvem','2012-07-05',1),(144,'kelvem','2012-07-05',1),(145,'kelvem','2012-07-05',1),(146,'kelvem','2012-07-05',1),(147,'kelvem','2012-07-05',1),(148,'kelvem','2012-07-05',1),(149,'kelvem','2012-07-05',1),(150,'kelvem','2012-07-05',1),(151,'kelvem','2012-07-05',1),(152,'kelvem','2012-07-05',1),(153,'kelvem','2012-07-05',1),(154,'kelvem','2012-07-05',1),(155,'kelvem','2012-07-05',1),(156,'kelvem','2012-07-05',1),(157,'kelvem','2012-07-05',1),(158,'kelvem','2012-07-05',1),(159,'kelvem','2012-07-05',1),(160,'kelvem','2012-07-05',1),(161,'kelvem','2012-07-05',1),(162,'kelvem','2012-07-05',1),(163,'kelvem','2012-07-05',1),(164,'kelvem','2012-07-05',1),(165,'kelvem','2012-07-05',1),(166,'kelvem','2012-07-05',1),(167,'kelvem','2012-07-05',1),(168,'kelvem','2012-07-05',1),(169,'kelvem','2012-07-05',1),(170,'kelvem','2012-07-05',1),(171,'kelvem','2012-07-05',1),(172,'kelvem','2012-07-05',1),(173,'kelvem','2012-07-05',1),(174,'kelvem','2012-07-05',1),(175,'kelvem','2012-07-05',1),(176,'kelvem','2012-07-05',1),(177,'kelvem','2012-07-05',1),(178,'kelvem','2012-07-05',1),(179,'kelvem','2012-07-05',1),(180,'kelvem','2012-07-05',1),(181,'kelvem','2012-07-05',1),(182,'kelvem','2012-07-05',1),(183,'kelvem','2012-07-05',1),(184,'kelvem','2012-07-05',1),(185,'kelvem','2012-07-05',1),(186,'kelvem','2012-07-05',1),(187,'kelvem','2012-07-14',1),(188,'kelvem','2012-07-14',1),(189,'kelvem','2012-07-14',1),(190,'kelvem','2012-07-14',1),(191,'kelvem','2012-07-14',1),(192,'kelvem','2012-07-14',1),(193,'kelvem','2012-07-14',1),(194,'kelvem','2012-07-14',1),(195,'kelvem','2012-07-14',1),(196,'kelvem','2012-07-14',1),(197,'kelvem','2012-07-14',1),(198,'kelvem','2012-07-14',1),(199,'kelvem','2012-07-14',1),(200,'kelvem','2012-07-17',1),(201,'kelvem','2012-07-17',1),(202,'kelvem','2012-07-17',1),(2222,'\'kelv\'em\'','2012-06-19',1);

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_info_id` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user_info` */

insert  into `user_info`(`user_id`,`user_info_id`,`age`) values (1,1,30),(2,2,30),(3,3,30),(4,4,30);

/*Table structure for table `user_visit_log` */

DROP TABLE IF EXISTS `user_visit_log`;

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
) ENGINE=InnoDB AUTO_INCREMENT=2254 DEFAULT CHARSET=utf8;

/*Data for the table `user_visit_log` */


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

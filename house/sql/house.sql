# ------------------------------------------------------------
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '姓名',
  `phone` char(13) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(90) NOT NULL DEFAULT '' COMMENT '电子邮件',
  `aboutme` varchar(250) NOT NULL DEFAULT '' COMMENT '自我介绍',
  `passwd` varchar(512) NOT NULL DEFAULT '' COMMENT '经过MD5加密的密码',
  `avatar` varchar(512) NOT NULL DEFAULT '' COMMENT '头像图片',
  `type` tinyint(1) NOT NULL COMMENT '1:普通用户，2:房产经纪人',
  `create_time` date NOT NULL COMMENT '创建时间',
  `enable` tinyint(1) NOT NULL COMMENT '是否启用,1启用，0停用',
  `agency_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属经纪机构',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;

INSERT INTO `user` (`id`, `name`, `phone`, `email`, `aboutme`, `passwd`, `avatar`, `type`, `create_time`, `enable`, `agency_id`)
VALUES
	(7,'hello99','hello','spring_boot@163.com','个人','3bf8013c27e39f2bb7060368bf5f6e49','/1493438523/4.jpg',2,'2017-01-31',1,1),
	(8,'hello1','hello','spring_boot2@163.com','2255','75fb23b165249cedeb60544c4095ec99','/1493438523/1.jpg',2,'2017-01-31',1,1),
	(9,'hello2','hello','spring_boot3@163.com','2255','75fb23b165249cedeb60544c4095ec99','/1493438523/2.jpg',2,'2017-01-31',1,1),
	(10,'hello3','hello','spring_boot4@163.com','2255','75fb23b165249cedeb60544c4095ec99','/1493438523/3.jpg',2,'2017-01-31',1,1),
	(11,'hello4','hello','spring_boot5@163.com','2255','75fb23b165249cedeb60544c4095ec99','/1493438523/4.jpg',2,'2017-01-31',1,1),
	(12,'hello5','hello','spring_boot6@163.com','2255','75fb23b165249cedeb60544c4095ec99','/1493438523/5.jpg',2,'2017-01-31',1,1),
	(13,'hello6','hello','spring_boot7@163.com','2255','75fb23b165249cedeb60544c4095ec99','/1493438523/6.jpg',2,'2017-01-31',1,1),
	(14,'张晶','18909090909','sinewy1@163.com','张晶','75fb23b165249cedeb60544c4095ec99','/1493438523/7.jpg',2,'2017-04-29',1,0),
	(15,'刘志成','18909090909','mooc_hello1@163.com','刘志成','75fb23b165249cedeb60544c4095ec99','/1493439911/client-01.jpg',2,'2017-04-29',1,0),
	(27,'王一','18909090909','spring_boot8@163.com','111111','75fb23b165249cedeb60544c4095ec99','/1515220849/agent-01.jpg',2,'2018-01-06',1,0),
	(29,'1111','12090909090','mooc_hello@163.com','111111','75fb23b165249cedeb60544c4095ec99','/1515227400/member-01.jpg',1,'2018-01-06',1,0);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
# ------------------------------------------------------------
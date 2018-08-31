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

# ------------------------------------------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '房产名称',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1:销售，2:出租',
  `price` int(11) NOT NULL COMMENT '单位元',
  `images` varchar(1024) NOT NULL DEFAULT '' COMMENT '图片地址',
  `area` int(11) NOT NULL DEFAULT '0' COMMENT '面积',
  `beds` int(11) NOT NULL DEFAULT '0' COMMENT '卧室数量',
  `baths` int(11) NOT NULL DEFAULT '0' COMMENT '卫生间数量',
  `rating` double NOT NULL DEFAULT '0' COMMENT '评级',
  `remarks` varchar(512) NOT NULL DEFAULT '' COMMENT '房产描述',
  `properties` varchar(512) NOT NULL DEFAULT '' COMMENT '属性',
  `floor_plan` varchar(250) NOT NULL DEFAULT '' COMMENT '户型图',
  `tags` varchar(20) NOT NULL DEFAULT '' COMMENT '标签',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `city_id` int(11) NOT NULL DEFAULT '0' COMMENT '城市名称',
  `community_id` int(11) NOT NULL DEFAULT '0' COMMENT '小区名称',
  `address` varchar(20) NOT NULL DEFAULT '' COMMENT '房产地址',
  `state` tinyint(1) DEFAULT '1' COMMENT '1-上架，2-下架',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `house` WRITE;
/*!40000 ALTER TABLE `house` DISABLE KEYS */;

INSERT INTO `house` (`id`, `name`, `type`, `price`, `images`, `area`, `beds`, `baths`, `rating`, `remarks`, `properties`, `floor_plan`, `tags`, `create_time`, `city_id`, `community_id`, `address`, `state`)
VALUES
	(22,'西山华府 120平',1,600,'/1493370993/property-07.jpg,/1493370999/property-08.jpg',120,2,12,5,'西山华府 120平西山华府 120平西山华府 120平西山华府 120平西山华府 120平','得房率高,户型好,落地窗','','','2017-04-28 00:00:00',1,1,'西山华府',1),
	(23,'万柳书苑 180平 南北通透',1,800,'/1493381459/property-detail-01.jpg,/1493381460/property-detail-02.jpg,/1493381462/property-detail-03.jpg',120,2,2,4.5,'万柳书苑 180平 南北通透','满五年,采光好,价格合理,税少,学区房','','','2017-04-28 00:00:00',1,2,'清河中街',1),
	(24,'阳光丽景 三面采光 高楼层',1,140,'/1493432771/property-11.jpg,/1493432771/property-12.jpg,/1493432771/property-13.jpg',140,2,2,2.5,'阳光丽景 三面采光 高楼层','南北通透,环境好,带阳台','/1493432771/floor-plan-01.jpg,/1493432771/floor-plan-02.jpg','','2017-04-29 00:00:00',1,5,'西城区',1),
	(25,'阳光丽景 全南 高楼层',1,140,'/1493432771/property-11.jpg,/1493432771/property-12.jpg,/1493432771/property-13.jpg',140,2,2,0,'阳光丽景 三面采光 高楼层','南北通透,环境好,带阳台','/1493432771/floor-plan-01.jpg,/1493432771/floor-plan-02.jpg','','2017-04-29 00:00:00',1,5,'西城区',1),
	(26,'北街嘉园 全南向 南北通透',1,800,'/1493381459/property-detail-01.jpg,/1493381460/property-detail-02.jpg,/1493381462/property-detail-03.jpg',120,2,2,0,'万柳书苑 180平 南北通透','满五年,采光好,价格合理,税少,学区房','','','2017-04-28 00:00:00',1,2,'清河中街',1),
	(27,'橡树湾 全南向 南北通透',2,1,'/1493381459/property-detail-01.jpg,/1493381460/property-detail-02.jpg,/1493381462/property-detail-03.jpg',120,2,2,4,'生活方便','采光好','','','2017-04-28 00:00:00',1,2,'清河中街',1),
	(30,'南北通透好三居',1,200,'/1500796444/property-12.jpg,/1500796444/property-13.jpg',2,3,2,0,'南北通透好三居','环境好,带阳台,临地铁','/1500796444/floor-plan-02.jpg','','2017-07-23 00:00:00',1,6,'紫苑华府',1),
	(31,'好三居采光充足',1,200,'/1500800727/property-12.jpg,/1500800727/property-13.jpg',100,3,2,0,'好三居采光充足','','/1500800727/floor-plan-02.jpg','','2017-07-23 00:00:00',1,7,'清河中街',1),
	(32,'好三居采光充足',1,200,'/1500800766/property-04.jpg,/1500800766/property-05.jpg,/1500800766/property-06.jpg',100,3,2,0,'好三居采光充足','','/1500800766/floor-plan-01.jpg','','2017-07-23 00:00:00',1,7,'清河中街',1),
	(33,'好三居采光充足',1,200,'/1500800883/property-09.jpg,/1500800883/property-10.jpg',100,3,2,0,'好三居采光充足','','/1500800883/floor-plan-02.jpg','','2017-07-23 00:00:00',1,7,'清河中街',1),
	(34,'南北通透好三居',1,200,'/1500800967/property-10.jpg,/1500800967/property-11.jpg',100,3,2,0,'南北通透好三居','','/1500800967/floor-plan-02.jpg','','2017-07-23 00:00:00',1,6,'清河中街',1),
	(35,'南北通透好三居',1,200,'/1500801115/property-09.jpg,/1500801115/property-10.jpg,/1500801115/property-11.jpg',100,3,2,0,'南北通透好三居','','/1500801115/floor-plan-01.jpg','','2017-07-23 00:00:00',1,6,'清河中街',1),
	(36,'南北通透好三居',1,200,'/1500801204/property-10.jpg,/1500801204/property-11.jpg',100,3,2,0,'111','','/1500801204/floor-plan-01.jpg','','2017-07-23 00:00:00',1,7,'22',1),
	(37,'南北通透好三居',1,300,'/1500801346/property-10.jpg',100,3,2,0,'撒的发达','','/1500801346/floor-plan-02.jpg','','2017-07-23 00:00:00',1,6,'撒发达',1),
	(38,'新增房产 阳光充足',1,200,'/1500801594/property-06.jpg,/1500801594/property-07.jpg',100,3,2,0,'新增房产新增房产新增房产','','/1500801594/floor-plan-02.jpg','','2017-07-23 00:00:00',1,4,'清河中街',1),
	(39,'南北通透好三居 采光充足',1,300,'/1500803086/property-13.jpg',100,3,2,5,'1111','','/1500803086/floor-plan-02.jpg','','2017-07-23 17:44:47',1,4,'清河中街',1),
	(40,'中央花园大三居',1,200,'/1514722627/property-09.jpg,/1514722627/property-08.jpg,/1514722627/property-07.jpg',200,3,3,0,'中央花园大三居，阳光好房','满两年,采光好,价格合理,楼龄新,税少,户型好','/1514722627/floor-plan-02.jpg','','2017-12-31 20:17:07',1,6,'抚顺路北大街',1),
	(41,'阳光花园大四居',1,300,'/1514727258/property-06.jpg,/1514727258/property-05.jpg,/1514727258/property-04.jpg',200,4,4,3,'阳光花园大四居阳光花园大四居阳光花园大四居','带阳台,临地铁,没有遮挡,精装修','/1514727258/floor-plan-01.jpg','','2017-12-31 21:34:18',1,7,'北新家园101',1),
	(42,'阳光花园大四居',1,300,'/1514727307/property-06.jpg,/1514727307/property-05.jpg,/1514727307/property-04.jpg',200,4,4,4,'阳光花园大四居阳光花园大四居阳光花园大四居','带阳台,临地铁,没有遮挡,精装修','/1514727307/floor-plan-01.jpg','','2017-12-31 21:35:08',1,7,'北新家园101',1),
	(43,'阳光花园大四居',1,300,'/1514727520/property-10.jpg,/1514727520/property-09.jpg,/1514727520/property-08.jpg',200,4,3,0,'阳光花园大四居阳光花园大四居阳光花园大四居','满五年,楼龄新,税少,落地窗','/1514727520/floor-plan-01.jpg','','2017-12-31 21:38:41',1,7,'北新家园101',1),
	(44,'枫丹丽舍大三居',1,300,'/1515216506/property-08.jpg,/1515216506/property-07.jpg,/1515216506/property-06.jpg',200,3,3,0,'枫丹丽舍大三居枫丹丽舍大三居','满五年,满两年,采光好,高楼层,价格合理,楼龄新,税少,得房率高','/1515216506/floor-plan-02.jpg','','2018-01-06 13:28:27',1,1,'西门大街58号',1),
	(45,'万柳书院一楼',1,400,'/1515217056/property-03.jpg,/1515217056/property-02.jpg,/1515217056/property-01.jpg',200,4,4,0,'万柳书院一楼','户型好,没有遮挡,落地窗,精装修','/1515217057/floor-plan-big.jpg','','2018-01-06 13:37:39',1,0,'万柳书院一楼',1);

/*!40000 ALTER TABLE `house` ENABLE KEYS */;
UNLOCK TABLES;

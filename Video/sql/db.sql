CREATE TABLE `v_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(63) NOT NULL COMMENT '用户名称',
  `password` VARCHAR(63) NOT NULL DEFAULT '' COMMENT '用户密码',
  `gender` TINYINT(3) NOT NULL DEFAULT '0' COMMENT '性别：0 未知， 1男， 1 女',
  `last_login_time` DATETIME DEFAULT NULL COMMENT '最近一次登录时间',
  `last_login_ip` VARCHAR(63) NOT NULL DEFAULT '' COMMENT '最近一次登录IP地址',
  `nickname` VARCHAR(63) NOT NULL DEFAULT '' COMMENT '用户昵称或网络名称',
  `avatar` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '用户头像图片',
  `country` VARCHAR(32) NOT NULL DEFAULT '' COMMENT 'country',
  `province` VARCHAR(32) NOT NULL DEFAULT '' COMMENT 'province',
  `city` VARCHAR(32) NOT NULL DEFAULT '' COMMENT 'city',
  `weixin_openid` VARCHAR(63) NOT NULL DEFAULT '' COMMENT '微信登录openid',
  `add_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`username`)
) ENGINE=INNODB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `bgm` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) NOT NULL COMMENT '歌手',
  `name` varchar(255) NOT NULL COMMENT '歌名',
  `path` varchar(255) NOT NULL COMMENT '播放地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4  COMMENT='背景音乐表';

INSERT INTO `bgm` VALUES (NULL, '黑崎子', '123我爱你', '\\bgm\\123我爱你.mp3');
INSERT INTO `bgm` VALUES (NULL, '李袁杰', '离人愁', '\\bgm\\离人愁.mp3');
INSERT INTO `bgm` VALUES (NULL, '文文小婧', '七月上', '\\bgm\\七月上.mp3');
INSERT INTO `bgm` VALUES (NULL, '徐佳莹', '身骑白马', '\\bgm\\身骑白马.mp3');
INSERT INTO `bgm` VALUES (NULL, '林俊杰', '我很想爱他', '\\bgm\\我很想爱他.mp3');
INSERT INTO `bgm` VALUES (NULL, '汪苏泷', '一笑倾城', '\\bgm\\一笑倾城.mp3');


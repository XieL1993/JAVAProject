CREATE DATABASE mobile;
USE mobile;
-- 用户表
CREATE TABLE `user` (
  `uid` VARCHAR(32) NOT NULL,  #用户编号
  `username` VARCHAR(20) DEFAULT NULL,		#用户名
  `password` VARCHAR(20) DEFAULT NULL,		#密码
  `time` DATETIME DEFAULT NULL,			#创建时间
  PRIMARY KEY (`uid`)
) ;
-- token表
CREATE TABLE `token` (
  `tid` VARCHAR(32) NOT NULL,  #用户编号
  `username` VARCHAR(20) DEFAULT NULL,		#用户名
  `time` DATETIME DEFAULT NULL,			#创建时间
  PRIMARY KEY (`tid`)
) ;
-- 分类表
CREATE TABLE `category` (
  `cid` VARCHAR(32) NOT NULL,
  `cname` VARCHAR(20) DEFAULT NULL,	#分类名称
  PRIMARY KEY (`cid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT INTO category VALUES ('0','首页'),('1','手机数码'),('2','运动户外'),('3','电脑办公'),('4','家具家居'),('5','鞋靴箱包'),('6','图书音像'),('7','母婴孕婴'),('8','汽车用品');

-- 商品表
CREATE TABLE `product` (
  `pid` VARCHAR(32) NOT NULL,
  `pname` VARCHAR(50) DEFAULT NULL,		#商品名称
  `market_price` DOUBLE DEFAULT NULL,	#市场价
  `shop_price` DOUBLE DEFAULT NULL,		#商城价
  `pimage` VARCHAR(200) DEFAULT NULL,	#商品图片路径
  `pdate` DATE DEFAULT NULL,			#上架时间
  `is_hot` INT(11) DEFAULT NULL,		#是否热门：0=不热门,1=热门
  `pdesc` VARCHAR(255) DEFAULT NULL,	#商品描述
  `pflag` INT(11) DEFAULT 0,			#商品标记：0=未下架(默认值),1=已经下架
  `cid` VARCHAR(32) DEFAULT NULL,		#分类id
  PRIMARY KEY (`pid`),
  KEY `product_fk` (`cid`),
  CONSTRAINT `product_fk` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT INTO `product` VALUES
('1','适用小米note m4小米4c小米3手机屏幕总成寄修维修单独换外屏触摸',1399,1299,'products/1/cs10001.jpg','2015-11-02',1,'小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待',0,'1'),
('2','中兴 AXON',2899,2699,'products/1/cs10002.jpg','2015-11-05',1,'中兴 AXON 天机 mini 压力屏版 B2015 华尔金 移动联通电信4G 双卡双待',0,'1'),
('3','华为荣耀6',1599,1499,'products/1/cs10003.jpg','2015-11-02',0,'荣耀 6 (H60-L01) 3GB内存标准版 黑色 移动4G手机',0,'1'),
('4','联想 P1',2199,1999,'products/1/cs10004.jpg','2015-11-02',0,'联想 P1 16G 伯爵金 移动联通4G手机充电5分钟，通话3小时！科技源于超越！品质源于沉淀！5000mAh大电池！高端商务佳配！',0,'1'),
('5','摩托罗拉 moto x（x+1）',1799,1699,'products/1/cs10005.jpg','2015-11-01',0,'摩托罗拉 moto x（x+1）(XT1085) 32GB 天然竹 全网通4G手机11月11天！MOTO X震撼特惠来袭！1699元！带你玩转黑科技！天然材质，原生流畅系统！',0,'1'),
('6','魅族 MX5 16GB 银黑色',1899,1799,'products/1/cs10006.jpg','2015-11-02',0,'魅族 MX5 16GB 银黑色 移动联通双4G手机 双卡双待送原厂钢化膜+保护壳+耳机！5.5英寸大屏幕，3G运行内存，2070万+500万像素摄像头！长期省才是真的省！',0,'1'),
('7','三星 Galaxy On7',1499,1398,'products/1/cs10007.jpg','2015-11-14',0,'三星 Galaxy On7（G6000）昂小七 金色 全网通4G手机 双卡双待新品火爆抢购中！京东尊享千元良机！5.5英寸高清大屏！1300+500W像素！评价赢30元话费券！',0,'1'),
('8','NUU NU5',1288,1190,'products/1/cs10008.jpg','2015-11-02',0,'NUU NU5 16GB 移动联通双4G智能手机 双卡双待 晒单有礼 晨光金香港品牌 2.5D弧度前后钢化玻璃 随机附赠手机套+钢化贴膜 晒单送移动电源+蓝牙耳机',0,'1'),
('9','乐视（Letv）乐1pro（X800）',2399,2299,'products/1/cs10009.jpg','2015-11-02',0,'乐视（Letv）乐1pro（X800）64GB 金色 移动联通4G手机 双卡双待乐视生态UI+5.5英寸2K屏+高通8核处理器+4GB运行内存+64GB存储+1300万摄像头！',0,'1'),
('10','华为 Ascend Mate7',2699,2599,'products/1/cs10010.jpg','2015-11-02',1,'华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！',0,'1'),
('11','vivo X5Pro',2399,2298,'products/1/cs20001.jpg','2015-11-02',1,'移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术',0,'1'),
('12','努比亚（nubia）My 布拉格',1899,1799,'products/1/cs20002.jpg','2015-11-02',0,'努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！',0,'1'),
('13','华为 麦芒4',2599,2499,'products/1/cs20003.jpg','2015-11-02',1,'华为 麦芒4 晨曦金 全网通版4G手机 双卡双待金属机身 2.5D弧面屏 指纹解锁 光学防抖',0,'1'),
('14','vivo X5M',1899,1799,'products/1/cs20004.jpg','2015-11-02',0,'vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV',0,'1'),
('15','Apple iPhone 6 (A1586)',4399,4288,'products/1/cs20005.jpg','2015-11-02',1,'Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！',0,'1'),
('16','华为 HUAWEI Mate S 臻享版',4200,4087,'products/1/cs20006.jpg','2015-11-03',0,'华为 HUAWEI Mate S 臻享版 手机 极昼金 移动联通双4G(高配)满星评价即返30元话费啦；买就送电源+清水套+创意手机支架；优雅弧屏，mate7升级版',0,'1'),
('17','索尼(SONY) E6533 Z3+',4099,3999,'products/1/cs20007.jpg','2015-11-02',0,'索尼(SONY) E6533 Z3+ 双卡双4G手机 防水防尘 涧湖绿索尼z3专业防水 2070万像素 移动联通双4G',0,'1'),
('18','HTC One M9+',3599,3499,'products/1/cs20008.jpg','2015-11-02',0,'HTC One M9+（M9pw） 金银汇 移动联通双4G手机5.2英寸，8核CPU，指纹识别，UltraPixel超像素前置相机+2000万/200万后置双镜头相机！降价特卖，惊喜不断！',0,'1'),
('19','HTC Desire 826d 32G 臻珠白',1599,1469,'products/1/cs20009.jpg','2015-11-02',1,'后置1300万+UltraPixel超像素前置摄像头+【双】前置扬声器+5.5英寸【1080p】大屏！',0,'1'),
('20','小米 红米2A 增强版 白色',649,549,'products/1/cs20010.jpg','2015-11-02',0,'新增至2GB 内存+16GB容量！4G双卡双待，联芯 4 核 1.5GHz 处理器！',0,'1'),
('21','魅族 魅蓝note2 16GB 白色',1099,999,'products/1/cs30001.png','2015-11-02',0,'现货速抢，抢完即止！5.5英寸1080P分辨率屏幕，64位八核1.3GHz处理器，1300万像素摄像头，双色温双闪光灯！',0,'1'),('22','三星 Galaxy S5 (G9008W) 闪耀白',2099,1999,'products/1/cs30002.png','2015-11-02',1,'5.1英寸全高清炫丽屏，2.5GHz四核处理器，1600万像素',0,'1'),
('23','sonim XP7700 4G手机',1799,1699,'products/1/cs30003.png','2015-11-09',1,'三防智能手机 移动/联通双4G 安全 黑黄色 双4G美国军工IP69 30天长待机 3米防水防摔 北斗',0,'1'),
('24','努比亚（nubia）Z9精英版 金色',3988,3888,'products/1/cs30004.png','2015-11-02',1,'移动联通电信4G手机 双卡双待真正的无边框！金色尊贵版！4GB+64GB大内存！',0,'1'),
('25','Apple iPhone 6 Plus (A1524) 16GB 金色',5188,4988,'products/1/cs30005.png','2015-11-02',0,'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力',0,'1'),
('26','Apple iPhone 6s (A1700) 64G 玫瑰金色',6388,6088,'products/1/cs30006.png','2015-11-02',0,'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力',0,'1'),
('27','三星 Galaxy Note5（N9200）32G版',5588,5388,'products/1/cs30007.png','2015-11-02',0,'旗舰机型！5.7英寸大屏，4+32G内存！不一样的SPen更优化的浮窗指令！赠无线充电板！',0,'1'),
('28','三星 Galaxy S6 Edge+（G9280）32G版 铂光金',5999,5888,'products/1/cs30008.png','2015-11-02',0,'赠移动电源+自拍杆+三星OTG金属U盘+无线充电器+透明保护壳',0,'1'),
('29','LG G4（H818）陶瓷白 国际版',3018,2978,'products/1/cs30009.png','2015-11-02',0,'李敏镐代言，F1.8大光圈1600万后置摄像头，5.5英寸2K屏，3G+32G内存，LG年度旗舰机！',0,'1'),
('30','微软(Microsoft) Lumia 640 LTE DS (RM-1113)',1099,999,'products/1/cs30010.png','2015-11-02',0,'微软首款双网双卡双4G手机，5.0英寸高清大屏，双网双卡双4G！',0,'1'),
('31','宏碁（acer）ATC705-N50 台式电脑',2399,2299,'products/1/cs40001.png','2015-11-02',0,'爆款直降，满千减百，品质宏碁，特惠来袭，何必苦等11.11，早买早便宜！',0,'2'),
('32','Apple MacBook Air MJVE2CH/A 13.3英寸',6788,6688,'products/1/cs40002.png','2015-11-02',0,'宽屏笔记本电脑 128GB 闪存',0,'2'),
('33','联想（ThinkPad） 轻薄系列E450C(20EH0001CD)',4399,4199,'products/1/cs40003.png','2015-11-02',0,'联想（ThinkPad） 轻薄系列E450C(20EH0001CD)14英寸笔记本电脑(i5-4210U 4G 500G 2G独显 Win8.1)',0,'2'),
('34','联想（Lenovo）小新V3000经典版',4599,4499,'products/1/cs40004.png','2015-11-02',0,'14英寸超薄笔记本电脑（i7-5500U 4G 500G+8G SSHD 2G独显 全高清屏）黑色满1000減100，狂减！火力全开，横扫3天！',0,'2'),
('35','华硕（ASUS）经典系列R557LI',3799,3699,'products/1/cs40005.png','2015-11-02',0,'15.6英寸笔记本电脑（i5-5200U 4G 7200转500G 2G独显 D刻 蓝牙 Win8.1 黑色）',0,'2'),
('36','华硕（ASUS）X450J',4599,4399,'products/1/cs40006.png','2015-11-02',0,'14英寸笔记本电脑 （i5-4200H 4G 1TB GT940M 2G独显 蓝牙4.0 D刻 Win8.1 黑色）',0,'2'),
('37','戴尔（DELL）灵越 飞匣3000系列',3399,3299,'products/1/cs40007.png','2015-11-03',0,' Ins14C-4528B 14英寸笔记本（i5-5200U 4G 500G GT820M 2G独显 Win8）黑',0,'2'),
('38','惠普(HP)WASD 暗影精灵',5699,5499,'products/1/cs40008.png','2015-11-02',0,'15.6英寸游戏笔记本电脑(i5-6300HQ 4G 1TB+128G SSD GTX950M 4G独显 Win10)',0,'2'),
('39','Apple 配备 Retina 显示屏的 MacBook',11299,10288,'products/1/cs40009.png','2015-11-02',0,'Pro MF840CH/A 13.3英寸宽屏笔记本电脑 256GB 闪存',0,'2'),
('40','机械革命（MECHREVO）MR X6S-M',6799,6599,'products/1/cs40011.png','2015-11-02',0,'15.6英寸游戏本(I7-4710MQ 8G 64GSSD+1T GTX960M 2G独显 IPS屏 WIN7)黑色',0,'2'),
('41','神舟（HASEE） 战神K660D-i7D2',5699,5499,'products/1/cs50001.png','2015-11-02',0,'15.6英寸游戏本(i7-4710MQ 8G 1TB GTX960M 2G独显 1080P)黑色',0,'2'),
('42','微星（MSI）GE62 2QC-264XCN',6199,5999,'products/1/cs50002.png','2015-11-02',0,'15.6英寸游戏笔记本电脑（i5-4210H 8G 1T GTX960MG DDR5 2G 背光键盘）黑色',0,'2'),
('43','雷神（ThundeRobot）G150S',5699,5499,'products/1/cs50003.png','2015-11-02',0,'15.6英寸游戏本 ( i7-4710MQ 4G 500G GTX950M 2G独显 包无亮点全高清屏) 金',0,'2'),
('44','惠普（HP）轻薄系列 HP',3199,3099,'products/1/cs50004.png','2015-11-02',0,'15-r239TX 15.6英寸笔记本电脑（i5-5200U 4G 500G GT820M 2G独显 win8.1）金属灰',0,'2'),
('45','未来人类（Terrans Force）T5',10999,9899,'products/1/cs50005.png','2015-11-02',0,'15.6英寸游戏本（i7-5700HQ 16G 120G固态+1TB GTX970M 3G GDDR5独显）黑',0,'2'),
('46','戴尔（DELL）Vostro 3800-R6308 台式电脑',3299,3199,'products/1/cs50006.png','2015-11-02',0,'（i3-4170 4G 500G DVD 三年上门服务 Win7）黑',0,'2'),
('47','联想（Lenovo）H3050 台式电脑',5099,4899,'products/1/cs50007.png','2015-11-11',0,'（i5-4460 4G 500G GT720 1G独显 DVD 千兆网卡 Win10）23英寸',0,'2'),
('48','Apple iPad mini 2 ME279CH/A',2088,1888,'products/1/cs50008.png','2015-11-02',0,'（配备 Retina 显示屏 7.9英寸 16G WLAN 机型 银色）',0,'2'),
('49','小米（MI）7.9英寸平板',1399,1299,'products/1/cs50009.png','2015-11-02',0,'WIFI 64GB（NVIDIA Tegra K1 2.2GHz 2G 64G 2048*1536视网膜屏 800W）白色',0,'2'),
('50','Apple iPad Air 2 MGLW2CH/A',2399,2299,'products/1/cs50010.png','2015-11-12',0,'（9.7英寸 16G WLAN 机型 银色）',0,'2');
-- 4 创建订单表
CREATE TABLE `orders` (
  `oid` VARCHAR(32) NOT NULL,
  `ordertime` DATETIME DEFAULT NULL,		#下单时间
  `total` DOUBLE DEFAULT NULL,			#总价
  `state` INT(11) DEFAULT NULL,			#订单状态：1=未付款;2=已付款,未发货;3=已发货,没收货;4=收货,订单结束
  `address` VARCHAR(30) DEFAULT NULL,		#收货地址
  `name` VARCHAR(20) DEFAULT NULL,		#收货人
  `telephone` VARCHAR(20) DEFAULT NULL,		#收货人电话
  `uid` VARCHAR(32) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `order_fk` (`uid`),
  CONSTRAINT `order_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ;
-- 5 创建订单项表
CREATE TABLE `orderitem` (
  `itemid` VARCHAR(32) NOT NULL,
  `quantity` INT(11) DEFAULT NULL,		#购买数量
  `total` DOUBLE DEFAULT NULL,			#小计
  `pid` VARCHAR(32) DEFAULT NULL,		#购买商品的id
  `oid` VARCHAR(32) DEFAULT NULL,		#订单项所在订单id
  PRIMARY KEY (`itemid`),
  KEY `order_item_fk_0001` (`pid`),
  KEY `order_item_fk_0002` (`oid`),
  CONSTRAINT `order_item_fk_0001` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
  CONSTRAINT `order_item_fk_0002` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`)
) ;
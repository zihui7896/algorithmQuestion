CREATE TABLE `stuinfo` (
  `stu_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `major` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `classname` varchar(200) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL,
  `bedid` int DEFAULT NULL,
  `parentphone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


insert into stuinfo(stu_id,name,sex,major,phone,address,age,classname,room,bedid,parentphone)
values(1,'AAA','女','物联网工程','111111','fff',20,'ff','101',1,'122222'),
(22,'BB','男','软件工程','222222','廊坊',20,'软件工程一班','112',2,'233333'),
(111,'Alva','女','大数据','333333','保定',19,'bbb','315',5,'344444'),
(1112,'ddd','女','大数据','555','ff',19,'bb','315',5,'344444');

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
insert into user(id,username,password,remarks)
values(1,'aa','111','?'),
(2,'bb','222','this is a remark');
values(3,'cc','111');

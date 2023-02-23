首先需要在 root 用户下 创建 stu 数据库，然后建两张表 user, stuinfo.

```mysql
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
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


insert into stuinfo(stu_id,name,sex,major,phone,address,age,classname,room,bedid,parentphone)
values(1,'AAA','女','物联网工程','111111','fff',20,'ff','101',1,'122222'),
(22,'BB','男','软件工程','222222','廊坊',20,'软件工程一班','112',2,'233333'),
(111,'Alva','女','大数据','333333','保定',19,'bbb','315',5,'344444'),
(1112,'ddd','女','大数据','555','ff',19,'bb','315',5,'344444');

insert into user(id,username,password,remarks)
values(1,'aa','111','?'),
(2,'bb','222','this is a remark');
```

需要修改一下root 密码   可以 ctrl + shrift + f 搜 pwd 和 password 把密码修改一下，或搜yezihui5201678

还有需要注意的是，idea 要导入server 

![](C:\Users\33359\Pictures\images\jsp\微信截图_20221216122505.png)

下面是我俩遇到的问题以及解决url

主要原因是我用的idea, 王瑶用的 eclipse MySQL版本也不一样

There is no configured/running web-servers found! Please, run any web-configuration and hit the Refresh butto

https://blog.csdn.net/YouMing_Li/article/details/117120300

Warning: No artifacts configured

https://blog.csdn.net/weixin_44949135/article/details/113786217

其他的小问题就不写了。
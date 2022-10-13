DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`name` varchar(30) DEFAULT NULL COMMENT '姓名',
`age` int(11) DEFAULT NULL COMMENT '年龄',
`email` varchar(50) DEFAULT NULL COMMENT '邮箱',
`version` int(10) DEFAULT '1' COMMENT '乐观锁',
`deleted` int(1) DEFAULT '0' COMMENT '逻辑删除',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`update_time` datetime DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1431480286016811011 DEFAULT CHARSET=utf8;


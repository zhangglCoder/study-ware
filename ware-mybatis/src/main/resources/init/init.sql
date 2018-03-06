CREATE TABLE `test1` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tiny` tinyint(4) unsigned zerofill DEFAULT NULL,
  `id2` int(10) unsigned zerofill DEFAULT NULL,
  `tiny2` tinyint(4) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别(0:男,1:女)',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `hx_cicerone_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `real_name` varchar(30) NOT NULL COMMENT '真实姓名',
  `sex` int(2) DEFAULT '0' COMMENT '用户性别 0-未知 1-男 2-女',
  `birthday` bigint(15) DEFAULT NULL COMMENT '生日',
  `cover` varchar(200) DEFAULT NULL COMMENT '封面',
  `about` text COMMENT '他的故事',
  `identity_no` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `identity_positive` varchar(200) DEFAULT NULL COMMENT '身份证正面',
  `identity_negative` varchar(200) DEFAULT NULL COMMENT '身份证反面',
  `identity_hold` varchar(200) DEFAULT NULL COMMENT '手持证件照',
  `service_area_city` varchar(32) DEFAULT NULL COMMENT '服务区域',
  `service_area_city_name` varchar(32) DEFAULT NULL COMMENT '服务区域名称',
  `work_status` tinyint(2) DEFAULT '0' COMMENT '工作状态；默认0 空闲，1工作中',
  `work_time` varchar(1000) DEFAULT NULL COMMENT '可被预约时间',
  `autograph` varchar(100) DEFAULT NULL COMMENT '个性签名',
  `phone` varchar(13) DEFAULT NULL COMMENT '电话',
  `tag` varchar(500) DEFAULT NULL COMMENT '标签',
  `service_count` bigint(11) DEFAULT '0' COMMENT '服务次数(接单次数)',
  `score` bigint(11) DEFAULT '0' COMMENT '点评分数 每次订单完成的评价分数的总和除于次数',
  `collect` bigint(11) DEFAULT '0' COMMENT '收藏数',
  `confirm` decimal(3,2) DEFAULT '0.00' COMMENT '确认率',
  `cicerone_type` varchar(500) DEFAULT NULL COMMENT '导游类型(摄影、城市行者、文化旅人、美食客、户外玩咖、历史通)',
  `status` int(2) DEFAULT '1' COMMENT '审核状态 默认1 未审核 2-通过 3-未通过',
  `reason` varchar(200) DEFAULT NULL COMMENT '未通过原因',
  `create_time` bigint(11) DEFAULT NULL COMMENT '申请时间',
  `examine_time` bigint(11) DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='导游表';



CREATE TABLE `hx_cicerone_relevance` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID自增',
  `cic_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '导游ID',
  `type_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '导游类型ID',
  `create_time` bigint(15) DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='导游类型关联表';



CREATE TABLE `hx_cicerone_type` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type_name` varchar(30) DEFAULT NULL COMMENT '类型名称',
  `type_value` varchar(50) DEFAULT NULL COMMENT '类型值',
  `type_description` varchar(100) DEFAULT NULL COMMENT '描述',
  `create_id` bigint(11) DEFAULT '0' COMMENT '创建人ID',
  `create_time` bigint(15) DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint(15) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='导游类型';


# 用户DDL
 DROP TABLE IF EXISTS `user`;
   CREATE TABLE `user`  (
     `id` bigint(20) UNSIGNED ZEROFILL NOT NULL DEFAULT 00000000000000000000,
     `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
     `salt` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '盐',
     `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密码',
     `nick` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '昵称',
     `gender` tinyint(4) NOT NULL DEFAULT 0 COMMENT '性别（0 未知，1 男，2 女）',
     `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
     `birthday` date NULL DEFAULT NULL COMMENT '生日',
     `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像',
     `status` int(8) NOT NULL DEFAULT 1 COMMENT '状态（0 停用， 1启用）',
     `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
     `create_time` datetime NOT NULL COMMENT '创建时间',
     `creator_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
     PRIMARY KEY (`id`) USING BTREE
   ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

# 用户地址表
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户id',
  `province` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '省',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '市',
  `country` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '区',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '详细地址',
  `is_defualt` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否为默认地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `modifier_id` bigint(20) UNSIGNED ZEROFILL NOT NULL DEFAULT 00000000000000000000 COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户地址表' ROW_FORMAT = Compact;

#商品表
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品图片',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '商品价格',
  `status` int(8) NOT NULL DEFAULT 0 COMMENT '商品状态（0 下架，1 上架）',
  `count` int(8) NOT NULL DEFAULT 0 COMMENT '库存数量',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `modifier_id` bigint(20) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Compact;

#订单表
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` bigint(20) NOT NULL DEFAULT 0,
  `order_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '订单编号',
  `total_money` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '订单总金额',
  `paid_money` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '实付金额',
  `discount_amount` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '优惠金额',
  `recived_money` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '实收金额',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `status` int(8) NOT NULL DEFAULT 0 COMMENT '状态（0 待接单，1 已接单，2 已出库， 3 运输中， 4 已签收， 5 已评价， 6 已完结， 7 已关闭）',
  `receivable_money` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '应收金额',
  `paid_time` datetime NOT NULL COMMENT '支付时间',
  `user_address_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户收货地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `modifier_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Compact;

#订单明细表
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` bigint(20) NOT NULL DEFAULT 0,
  `order_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '订单id',
  `order_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '订单编号',
  `goods_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '商品id',
  `original_price` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '商品原价',
  `price` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '商品现价',
  `count` int(8) NOT NULL DEFAULT 0 COMMENT '购买数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单明细表' ROW_FORMAT = Compact;

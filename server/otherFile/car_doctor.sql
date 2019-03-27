/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.111_3306
Source Server Version : 50722
Source Host           : 192.168.10.111:3306
Source Database       : car_doctor

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-08-13 11:22:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for car_accurate_guests
-- ----------------------------
DROP TABLE IF EXISTS `car_accurate_guests`;
CREATE TABLE `car_accurate_guests` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `browse_num` int(11) DEFAULT NULL COMMENT '浏览次数',
  `browse_time` int(11) DEFAULT NULL COMMENT '浏览时长',
  `accurate_customers` int(4) DEFAULT NULL COMMENT '是否精准客户:1是;0否;',
  `whether_forwarding` int(4) DEFAULT NULL COMMENT '是否转发:1是;0否;',
  `lately_see_time` datetime DEFAULT NULL COMMENT '最近查看时间',
  `follow_content` varchar(50) DEFAULT NULL COMMENT '关注内容',
  `forwarding_record` varchar(50) DEFAULT NULL COMMENT '转发记录',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='精准获客';

-- ----------------------------
-- Table structure for car_activity
-- ----------------------------
DROP TABLE IF EXISTS `car_activity`;
CREATE TABLE `car_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `column_type` int(4) DEFAULT NULL COMMENT '所属栏目',
  `activity_title` varchar(100) DEFAULT NULL COMMENT '活动标题',
  `picture` varchar(300) DEFAULT NULL COMMENT '图片',
  `home_page_promotion` int(4) DEFAULT NULL COMMENT '首页是否推广',
  `enroll_fee` decimal(20,2) DEFAULT NULL COMMENT '报名费',
  `partake_compulsory_payment` int(4) DEFAULT NULL COMMENT '参与时强制支付1是；0否',
  `start_date` datetime DEFAULT NULL COMMENT '活动开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '活动结束时间',
  `coupon_id` int(4) DEFAULT NULL COMMENT '活动奖励卡券id',
  `initial_enroll_num` int(11) DEFAULT NULL COMMENT '初始报名数',
  `whether_allow_enroll` int(4) DEFAULT NULL COMMENT '是否允许报名:1是；0否',
  `enroll_num` int(11) DEFAULT NULL COMMENT '报名数',
  `visit_num` int(11) DEFAULT NULL COMMENT '访问数',
  `release_date` datetime DEFAULT NULL COMMENT '发布日期',
  `status` int(4) DEFAULT NULL COMMENT '状态:1进行中;0已下架',
  `view_count` int(11) DEFAULT NULL COMMENT '精准用户设浏览次数',
  `min_time` int(11) DEFAULT NULL COMMENT '精准用户设浏览最小时间(s)',
  `max_time` int(11) DEFAULT NULL COMMENT '精准用户设浏览最大时间(s)',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动表';

-- ----------------------------
-- Table structure for car_activity_apply
-- ----------------------------
DROP TABLE IF EXISTS `car_activity_apply`;
CREATE TABLE `car_activity_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `interested_models` varchar(50) DEFAULT NULL COMMENT '意向车型',
  `is_pay` int(4) DEFAULT NULL COMMENT '是否支付',
  `time` datetime DEFAULT NULL COMMENT '报名日期',
  `referrer_user_id` int(11) DEFAULT NULL COMMENT '推荐用户id',
  `status` int(4) DEFAULT NULL COMMENT '状态：1已确认；0未确认;',
  `is_valid` int(4) DEFAULT NULL COMMENT '是否有效：1是；0否；',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户活动报名表';

-- ----------------------------
-- Table structure for car_activity_lottery
-- ----------------------------
DROP TABLE IF EXISTS `car_activity_lottery`;
CREATE TABLE `car_activity_lottery` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) DEFAULT NULL COMMENT '活动标题',
  `picture` varchar(255) DEFAULT NULL COMMENT '图片',
  `is_push` int(4) DEFAULT NULL COMMENT '首页推广：1是；0否',
  `enroll_fee` decimal(20,2) DEFAULT NULL COMMENT '报名费',
  `is_pay` int(4) DEFAULT NULL COMMENT '参与时强制支付：1是；0否',
  `time` datetime DEFAULT NULL COMMENT '活动日期',
  `type` int(4) DEFAULT NULL COMMENT '活动参与类型 ：0次；1全程；',
  `number` int(11) DEFAULT NULL COMMENT '活动参与次数',
  `lottery_template_id` int(11) DEFAULT NULL COMMENT '奖励设置(抽奖模板id)',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='抽奖活动表';

-- ----------------------------
-- Table structure for car_answer_table
-- ----------------------------
DROP TABLE IF EXISTS `car_answer_table`;
CREATE TABLE `car_answer_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `question_id` int(11) DEFAULT NULL COMMENT '问题id',
  `answer_option` varchar(50) DEFAULT NULL COMMENT '选项',
  `time` datetime DEFAULT NULL COMMENT '参与时间',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回答表';

-- ----------------------------
-- Table structure for car_authority
-- ----------------------------
DROP TABLE IF EXISTS `car_authority`;
CREATE TABLE `car_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `desc_info` varchar(150) DEFAULT NULL COMMENT '相关描述',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Table structure for car_auto_response
-- ----------------------------
DROP TABLE IF EXISTS `car_auto_response`;
CREATE TABLE `car_auto_response` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(100) DEFAULT NULL COMMENT '关键词',
  `type` int(4) DEFAULT NULL COMMENT '类型',
  `picture` varchar(500) DEFAULT NULL COMMENT '图片',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自动回复表';

-- ----------------------------
-- Table structure for car_booking
-- ----------------------------
DROP TABLE IF EXISTS `car_booking`;
CREATE TABLE `car_booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `booking_set_id` int(11) DEFAULT NULL COMMENT '预约类型',
  `reservation_num` bigint(20) DEFAULT NULL COMMENT '预约编号',
  `arrival_time` datetime DEFAULT NULL COMMENT '到店时间',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `employee_id` int(11) DEFAULT NULL COMMENT '接单人',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  `state` int(4) DEFAULT NULL COMMENT '状态',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约管理表';

-- ----------------------------
-- Table structure for car_booking_set
-- ----------------------------
DROP TABLE IF EXISTS `car_booking_set`;
CREATE TABLE `car_booking_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` int(4) DEFAULT NULL COMMENT '预约类型',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `number` int(11) DEFAULT NULL COMMENT '人数',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约设置表';

-- ----------------------------
-- Table structure for car_coupon
-- ----------------------------
DROP TABLE IF EXISTS `car_coupon`;
CREATE TABLE `car_coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `card_id` varchar(50) DEFAULT NULL COMMENT '微信卡券id',
  `coupon_type` int(4) DEFAULT NULL COMMENT '卡券类型:0实物奖品；1电子代金券；',
  `coupon_name` varchar(50) DEFAULT NULL COMMENT '卡券名称',
  `coupon_value` decimal(20,2) DEFAULT NULL COMMENT '卡券面值',
  `number` int(11) DEFAULT NULL COMMENT '数量',
  `consumption_full` decimal(20,2) DEFAULT NULL COMMENT '消费满',
  `valid_till` datetime DEFAULT NULL COMMENT '有效期至',
  `receive_type` int(4) DEFAULT NULL COMMENT '领取类型:0允许重复领取；1首次分享领取',
  `role_id` int(11) DEFAULT NULL COMMENT '员工推荐权限(角色id)',
  `instructions` varchar(255) DEFAULT NULL COMMENT '使用说明',
  `launch` int(11) DEFAULT NULL COMMENT '已送出',
  `successful_transfer` int(11) DEFAULT NULL COMMENT '成功转赠',
  `already_used` int(11) DEFAULT NULL COMMENT '已使用',
  `receive_num` int(11) DEFAULT NULL COMMENT '已领取数量',
  `add_date` datetime DEFAULT NULL COMMENT '添加日期',
  `employee_id` int(11) DEFAULT NULL COMMENT '添加人（员工id）',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卡券表';

-- ----------------------------
-- Table structure for car_department
-- ----------------------------
DROP TABLE IF EXISTS `car_department`;
CREATE TABLE `car_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dep_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `state` int(4) DEFAULT NULL COMMENT '状态',
  `desc_info` varchar(500) DEFAULT NULL COMMENT '描述',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级id',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Table structure for car_detection
-- ----------------------------
DROP TABLE IF EXISTS `car_detection`;
CREATE TABLE `car_detection` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '专项检测名称',
  `state` int(4) DEFAULT NULL COMMENT '状态',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专项检测配置表';

-- ----------------------------
-- Table structure for car_detection_project
-- ----------------------------
DROP TABLE IF EXISTS `car_detection_project`;
CREATE TABLE `car_detection_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `picture` varchar(500) DEFAULT NULL COMMENT '正常状态图',
  `desc_info` varchar(1000) DEFAULT NULL COMMENT '说明',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检测项目列表';

-- ----------------------------
-- Table structure for car_employee
-- ----------------------------
DROP TABLE IF EXISTS `car_employee`;
CREATE TABLE `car_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `department_id` int(11) DEFAULT NULL COMMENT '部门id',
  `real_name` varchar(50) DEFAULT NULL COMMENT '员工姓名',
  `sex` varbinary(20) DEFAULT NULL COMMENT '员工性别',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `username` varchar(50) DEFAULT NULL COMMENT '账号',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `id_num` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `reservation` int(11) DEFAULT NULL COMMENT '每日预约数量',
  `status` int(4) DEFAULT NULL COMMENT '账号状态0:禁用;1正常;',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工信息';

-- ----------------------------
-- Table structure for car_expense_record
-- ----------------------------
DROP TABLE IF EXISTS `car_expense_record`;
CREATE TABLE `car_expense_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `expense_money` decimal(20,2) DEFAULT NULL COMMENT '消费金额',
  `time` datetime DEFAULT NULL COMMENT '消费时间',
  `pay_money` decimal(20,2) DEFAULT NULL COMMENT '支付金额',
  `pay_type` int(4) DEFAULT NULL COMMENT '支付方式',
  `pay_brokerage` decimal(20,2) DEFAULT NULL COMMENT '佣金扣除',
  `pay_member` decimal(20,2) DEFAULT NULL COMMENT '会员卡扣除',
  `meal_id` int(11) DEFAULT NULL COMMENT '消费项目(套餐id)',
  `status` int(4) DEFAULT NULL COMMENT '扣除状态',
  `employee_id` int(11) DEFAULT NULL COMMENT '操作人id',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消费记录表';

-- ----------------------------
-- Table structure for car_fast_reply
-- ----------------------------
DROP TABLE IF EXISTS `car_fast_reply`;
CREATE TABLE `car_fast_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `serial_num` int(11) DEFAULT NULL COMMENT '序列号',
  `message_content` varchar(255) DEFAULT NULL COMMENT '消息内容',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快捷回复表';

-- ----------------------------
-- Table structure for car_feedback_feedback
-- ----------------------------
DROP TABLE IF EXISTS `car_feedback_feedback`;
CREATE TABLE `car_feedback_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `feedback_type` int(4) DEFAULT NULL COMMENT '反馈类型:0保养；1保险；2续保；3其他',
  `feedback_content` varchar(500) DEFAULT NULL COMMENT '反馈内容',
  `feedback_time` datetime DEFAULT NULL COMMENT '反馈时间',
  `reply_content` varchar(500) DEFAULT NULL COMMENT '回复内容',
  `recovery_time` datetime DEFAULT NULL COMMENT '回复时间',
  `processing_state` int(4) DEFAULT NULL COMMENT '处理状态',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='要与用户和角色连接';

-- ----------------------------
-- Table structure for car_insurance
-- ----------------------------
DROP TABLE IF EXISTS `car_insurance`;
CREATE TABLE `car_insurance` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `car_type` varchar(50) DEFAULT NULL COMMENT '车型',
  `car_age` varchar(255) DEFAULT NULL COMMENT '车辆年限',
  `car_price` decimal(20,2) DEFAULT NULL COMMENT '裸车价格',
  `insurance_company_id` int(11) DEFAULT NULL COMMENT '保险公司',
  `loss_insurance` int(4) DEFAULT NULL COMMENT '损失险',
  `theft_insurance` int(4) DEFAULT NULL COMMENT '盗抢险',
  `autoignition_insurance` int(4) DEFAULT NULL COMMENT '自燃险',
  `car_scratch` int(4) DEFAULT NULL COMMENT '车身划痕',
  `third_liability_insurance` int(4) DEFAULT NULL COMMENT '第三方责任险',
  `strong_insurance` int(4) DEFAULT NULL COMMENT '出强险',
  `risks_num` int(4) DEFAULT NULL COMMENT '出险次数',
  `no_deductibles` int(4) DEFAULT NULL COMMENT '不计免赔',
  `glass_insurance` int(4) DEFAULT NULL COMMENT '玻璃险',
  `wading_insurance` int(4) DEFAULT NULL COMMENT '涉水险',
  `seat_insurance` int(4) DEFAULT NULL COMMENT '司机座位责任险',
  `offer` decimal(20,2) DEFAULT NULL COMMENT '报价',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注',
  `employee_id` int(11) DEFAULT NULL COMMENT '员工id',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保险服务表';

-- ----------------------------
-- Table structure for car_insurance_company
-- ----------------------------
DROP TABLE IF EXISTS `car_insurance_company`;
CREATE TABLE `car_insurance_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `discount_rate` decimal(5,2) DEFAULT NULL COMMENT '折扣率',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保险公司表';

-- ----------------------------
-- Table structure for car_integral
-- ----------------------------
DROP TABLE IF EXISTS `car_integral`;
CREATE TABLE `car_integral` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `integral` decimal(20,1) DEFAULT NULL COMMENT '获得积分',
  `type` int(4) DEFAULT NULL COMMENT '类型:1增加；0减少；',
  `rule_id` int(11) DEFAULT NULL COMMENT '积分规则id',
  `business_type` varchar(50) DEFAULT NULL COMMENT '业务类型',
  `business_details` varchar(50) DEFAULT NULL COMMENT '业务详情',
  `operation_time` datetime DEFAULT NULL COMMENT '操作时间',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员积分表';

-- ----------------------------
-- Table structure for car_integral_rule
-- ----------------------------
DROP TABLE IF EXISTS `car_integral_rule`;
CREATE TABLE `car_integral_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organization_id` int(11) DEFAULT NULL COMMENT '门店机构id',
  `rule_name` varchar(255) DEFAULT NULL COMMENT '规则名称',
  `conversion_rule` decimal(20,2) DEFAULT NULL COMMENT '换算规则1积分(元)',
  `first_concern` decimal(5,1) DEFAULT NULL COMMENT '换算积分',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分规则表';

-- ----------------------------
-- Table structure for car_knowledge
-- ----------------------------
DROP TABLE IF EXISTS `car_knowledge`;
CREATE TABLE `car_knowledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) DEFAULT NULL COMMENT '文章标题',
  `type_id` int(11) DEFAULT NULL COMMENT '文章分类',
  `browse_num` int(11) DEFAULT NULL COMMENT '浏览数',
  `state` int(4) DEFAULT NULL COMMENT '状态',
  `picture` varchar(500) DEFAULT NULL COMMENT '图片',
  `content` text COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for car_knowledge_type
-- ----------------------------
DROP TABLE IF EXISTS `car_knowledge_type`;
CREATE TABLE `car_knowledge_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '类别名称',
  `ranl` int(11) DEFAULT NULL COMMENT '排序',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='养车知识类别表';

-- ----------------------------
-- Table structure for car_lottery_info
-- ----------------------------
DROP TABLE IF EXISTS `car_lottery_info`;
CREATE TABLE `car_lottery_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lottery_template_id` int(11) DEFAULT NULL COMMENT '奖励模板id',
  `rank` varchar(50) DEFAULT NULL COMMENT '奖项等级',
  `name` varchar(50) DEFAULT NULL COMMENT '奖项名称',
  `total` int(11) DEFAULT NULL COMMENT '奖品总数量',
  `quantity` int(11) DEFAULT NULL COMMENT '奖品数量',
  `rate` decimal(5,2) DEFAULT NULL COMMENT '中将概率',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='抽奖奖励详情表';

-- ----------------------------
-- Table structure for car_lottery_template
-- ----------------------------
DROP TABLE IF EXISTS `car_lottery_template`;
CREATE TABLE `car_lottery_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '模板名称',
  `decription` varchar(500) DEFAULT NULL COMMENT '备注',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='抽奖模板表';

-- ----------------------------
-- Table structure for car_maintain_remind
-- ----------------------------
DROP TABLE IF EXISTS `car_maintain_remind`;
CREATE TABLE `car_maintain_remind` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `days` int(11) DEFAULT NULL COMMENT '提醒天数',
  `content` varchar(255) DEFAULT NULL COMMENT '提醒内容',
  `type` int(4) DEFAULT NULL COMMENT '类型0未到店；1已到店；',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保养提醒表';

-- ----------------------------
-- Table structure for car_maintenance
-- ----------------------------
DROP TABLE IF EXISTS `car_maintenance`;
CREATE TABLE `car_maintenance` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `vehicle_id` int(11) DEFAULT NULL COMMENT '车辆id',
  `current_mileage` int(11) DEFAULT NULL COMMENT '当前里程',
  `detection_num` int(11) DEFAULT NULL COMMENT '检测总数',
  `serious` int(11) DEFAULT NULL COMMENT '严重',
  `slight` int(11) DEFAULT NULL COMMENT '轻微',
  `good` int(11) DEFAULT NULL COMMENT '良好',
  `detection_time` datetime DEFAULT NULL COMMENT '检测日期',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检修记录表';

-- ----------------------------
-- Table structure for car_maintenance_info
-- ----------------------------
DROP TABLE IF EXISTS `car_maintenance_info`;
CREATE TABLE `car_maintenance_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `maintenance_id` int(11) DEFAULT NULL COMMENT '检修记录id',
  `vehicle_id` int(11) DEFAULT NULL COMMENT '车辆id',
  `detection_id` int(11) DEFAULT NULL COMMENT '检测项目类型',
  `detection_status` int(4) DEFAULT NULL COMMENT '项目状态',
  `advice` varchar(255) DEFAULT NULL COMMENT '建议操作',
  `days` int(11) DEFAULT NULL COMMENT '下次提醒天数',
  `price` decimal(20,2) DEFAULT NULL COMMENT '维修预估费用',
  `employee_id` int(11) DEFAULT NULL COMMENT '检修技师',
  `picture` varchar(500) DEFAULT NULL COMMENT '检测图片',
  `is_service` int(4) DEFAULT NULL COMMENT '用户是否确认维修',
  `service_status` int(4) DEFAULT NULL COMMENT '维修状态',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆检修详情表';

-- ----------------------------
-- Table structure for car_member
-- ----------------------------
DROP TABLE IF EXISTS `car_member`;
CREATE TABLE `car_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `organization_id` int(11) DEFAULT NULL COMMENT '组织机构id',
  `grade` int(11) DEFAULT NULL COMMENT '等级',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `discount_ratio` decimal(5,2) DEFAULT NULL COMMENT '折扣比例',
  `accumulative_money` decimal(20,2) DEFAULT NULL COMMENT '累计金额',
  `achieve_give_integral` int(11) DEFAULT NULL COMMENT '累计积分',
  `amount` decimal(20,2) DEFAULT NULL COMMENT '充值金额',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员卡等级表';

-- ----------------------------
-- Table structure for car_message_push
-- ----------------------------
DROP TABLE IF EXISTS `car_message_push`;
CREATE TABLE `car_message_push` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `push_type` int(4) DEFAULT NULL COMMENT '推送类型',
  `push_content` varchar(500) DEFAULT NULL COMMENT '推送内容',
  `meal_id` int(11) DEFAULT NULL COMMENT '推送套餐',
  `activity_id` int(11) DEFAULT NULL COMMENT '推送活动',
  `coupon_id` int(11) DEFAULT NULL COMMENT '推送卡券',
  `whether_push` int(4) DEFAULT NULL COMMENT '是否推送',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注消息推送';

-- ----------------------------
-- Table structure for car_operation_info
-- ----------------------------
DROP TABLE IF EXISTS `car_operation_info`;
CREATE TABLE `car_operation_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `detection_id` int(11) DEFAULT NULL COMMENT '检修项目id',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `suggest` varchar(50) DEFAULT NULL COMMENT '建议操作',
  `days` int(11) DEFAULT NULL COMMENT '下次提醒天数',
  `price` decimal(20,2) DEFAULT NULL COMMENT '维修预估费用',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检测项目操作详情表';

-- ----------------------------
-- Table structure for car_order
-- ----------------------------
DROP TABLE IF EXISTS `car_order`;
CREATE TABLE `car_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quality_id` int(11) DEFAULT NULL COMMENT '精品id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `order_num` bigint(20) DEFAULT NULL COMMENT '订单号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `telephone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `commodity_name` varchar(20) DEFAULT NULL COMMENT '商品名称',
  `commodity_price` decimal(20,20) DEFAULT NULL COMMENT '商品单价',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  `payment_money` decimal(20,20) DEFAULT NULL COMMENT '支付金额',
  `deduction_integral` decimal(20,20) DEFAULT NULL COMMENT '扣除积分',
  `order_date` datetime DEFAULT NULL COMMENT '下单日期',
  `delivery_date` datetime DEFAULT NULL COMMENT '提货日期',
  `employee_id` int(11) DEFAULT NULL COMMENT '操作人',
  `state` int(4) DEFAULT NULL COMMENT '状态0未支付；1待提货；2已提货；',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='精品订单表';

-- ----------------------------
-- Table structure for car_organization
-- ----------------------------
DROP TABLE IF EXISTS `car_organization`;
CREATE TABLE `car_organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门店组织机构表';

-- ----------------------------
-- Table structure for car_parts
-- ----------------------------
DROP TABLE IF EXISTS `car_parts`;
CREATE TABLE `car_parts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(50) DEFAULT NULL COMMENT '大类名称',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配件大类表';

-- ----------------------------
-- Table structure for car_push
-- ----------------------------
DROP TABLE IF EXISTS `car_push`;
CREATE TABLE `car_push` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` int(4) DEFAULT NULL COMMENT '推送类型',
  `content` varchar(255) DEFAULT NULL COMMENT '推送文本内容',
  `quality_id` int(11) DEFAULT NULL COMMENT '推送精品套餐',
  `coupon_id` int(11) DEFAULT NULL COMMENT '推送卡券',
  `activity_id` int(11) DEFAULT NULL COMMENT '推送活动',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注推送表';

-- ----------------------------
-- Table structure for car_quality
-- ----------------------------
DROP TABLE IF EXISTS `car_quality`;
CREATE TABLE `car_quality` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `first_picture` varchar(500) DEFAULT NULL COMMENT '首图',
  `category_id` int(11) DEFAULT NULL COMMENT '类别id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `original_price` decimal(20,2) DEFAULT NULL COMMENT '原价',
  `price` decimal(20,2) DEFAULT NULL COMMENT '售价',
  `applicable_model` varchar(50) DEFAULT NULL COMMENT '适用车型',
  `recommend` int(4) DEFAULT NULL COMMENT '是否推荐1是，0否',
  `stock` int(11) DEFAULT NULL COMMENT '库存数量',
  `support_integral_change` int(4) DEFAULT NULL COMMENT '是否支持积分换购',
  `purchase_integral` decimal(20,1) DEFAULT NULL COMMENT '换购所需积分',
  `commodity_state` int(4) DEFAULT NULL COMMENT '商品状态1在售；0停售；',
  `obtain_integral` decimal(20,1) DEFAULT NULL COMMENT '获得积分',
  `whether_drop` int(4) DEFAULT NULL COMMENT '是否直降1是；0否；',
  `desc_info` text COMMENT '描述',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='汽车精品表';

-- ----------------------------
-- Table structure for car_questionnaire_survey
-- ----------------------------
DROP TABLE IF EXISTS `car_questionnaire_survey`;
CREATE TABLE `car_questionnaire_survey` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `questionnaire_title` varchar(255) DEFAULT NULL COMMENT '问卷标题',
  `column_type` int(4) DEFAULT NULL COMMENT '所属栏目',
  `title_picture` varchar(500) DEFAULT NULL COMMENT '标题图片',
  `release_date` datetime DEFAULT NULL COMMENT '发布日期',
  `end_date` datetime DEFAULT NULL COMMENT '结束日期',
  `main_object` int(4) DEFAULT NULL COMMENT '主要对象',
  `consumption_push` int(4) DEFAULT NULL COMMENT '消费推送',
  `active_state` int(4) DEFAULT NULL COMMENT '活动状态',
  `coupon_id` int(11) DEFAULT NULL COMMENT '参与奖品卡券id',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问卷调查';

-- ----------------------------
-- Table structure for car_recharge
-- ----------------------------
DROP TABLE IF EXISTS `car_recharge`;
CREATE TABLE `car_recharge` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `organization_id` int(11) DEFAULT NULL COMMENT '机构id',
  `recharge_amount` decimal(20,2) DEFAULT NULL COMMENT '充值金额',
  `donation_amount` decimal(20,2) DEFAULT NULL COMMENT '赠送金额',
  `gift_integral` decimal(20,1) DEFAULT NULL COMMENT '赠送积分',
  `coupon_id` int(11) DEFAULT NULL COMMENT '赠送卡券id',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员充值表';

-- ----------------------------
-- Table structure for car_recharge_record
-- ----------------------------
DROP TABLE IF EXISTS `car_recharge_record`;
CREATE TABLE `car_recharge_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_num` bigint(20) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `car_type` varchar(50) DEFAULT NULL COMMENT '车型',
  `recharge_amount` decimal(20,2) DEFAULT NULL COMMENT '充值金额',
  `given_amount` decimal(20,2) DEFAULT NULL COMMENT '赠送金额',
  `given_coupon` varchar(50) DEFAULT NULL COMMENT '赠送卡券',
  `create_time` datetime DEFAULT NULL COMMENT '交易时间',
  `recharge_type` int(4) DEFAULT NULL COMMENT '充值方式0线上；1线下；2分红转入；',
  `status` int(4) DEFAULT NULL COMMENT '状态：1已支付；0未支付',
  `employee_id` int(11) DEFAULT NULL COMMENT '操作用户id',
  `recharge_id` int(11) DEFAULT NULL COMMENT '充值规则id',
  `coupon_id` int(11) DEFAULT NULL COMMENT '卡券id',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员充值记录';

-- ----------------------------
-- Table structure for car_recommending_gift
-- ----------------------------
DROP TABLE IF EXISTS `car_recommending_gift`;
CREATE TABLE `car_recommending_gift` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` int(4) DEFAULT NULL COMMENT '推荐类型',
  `referrer_user_id` int(11) DEFAULT NULL COMMENT '推荐用户id',
  `referrer_coupon_id` int(11) DEFAULT NULL COMMENT '推荐人奖品id',
  `user_id` int(11) DEFAULT NULL COMMENT '被推荐人id',
  `user_coupon_id` int(11) DEFAULT NULL COMMENT '被推荐人奖品id',
  `recommended_time` datetime DEFAULT NULL COMMENT '推荐时间',
  `participation_time` datetime DEFAULT NULL COMMENT '参与时间',
  `confirmation_code` varchar(255) DEFAULT NULL COMMENT '确认码',
  `confirmation_time` datetime DEFAULT NULL COMMENT '确认时间',
  `current_state` int(4) DEFAULT NULL COMMENT '当前状态：0待确认；1已确认；2已领取',
  `reserved1` varchar(255) DEFAULT NULL,
  `reserved2` varchar(255) DEFAULT NULL,
  `reserved3` varchar(255) DEFAULT NULL,
  `reserved4` varchar(255) DEFAULT NULL,
  `reserved5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推荐有礼表';

-- ----------------------------
-- Table structure for car_rescue
-- ----------------------------
DROP TABLE IF EXISTS `car_rescue`;
CREATE TABLE `car_rescue` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `longitude` decimal(10,7) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10,7) DEFAULT NULL COMMENT '纬度',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `submit_time` datetime DEFAULT NULL COMMENT '提交时间',
  `employee_id` int(11) DEFAULT NULL COMMENT '救援人',
  `verify_time` datetime DEFAULT NULL COMMENT '确认时间',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='救援服务表';

-- ----------------------------
-- Table structure for car_rocking_activity
-- ----------------------------
DROP TABLE IF EXISTS `car_rocking_activity`;
CREATE TABLE `car_rocking_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `field_name` varchar(255) DEFAULT NULL COMMENT '活动名称',
  `partake_num` int(11) DEFAULT NULL COMMENT '参与人数',
  `display_num` int(11) DEFAULT NULL COMMENT '显示人数',
  `activity_date` datetime DEFAULT NULL COMMENT '活动日期',
  `release_date` datetime DEFAULT NULL COMMENT '发布日期',
  `active_state` int(4) DEFAULT NULL COMMENT '活动状态：1进行中；0已结束；',
  `winner_score` int(11) DEFAULT NULL COMMENT '获胜分数',
  `background_picture` varchar(500) DEFAULT NULL COMMENT '背景图片',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='摇一摇活动';

-- ----------------------------
-- Table structure for car_rocking_awards
-- ----------------------------
DROP TABLE IF EXISTS `car_rocking_awards`;
CREATE TABLE `car_rocking_awards` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `awards` varchar(50) DEFAULT NULL COMMENT '奖项',
  `coupon_id` int(11) DEFAULT NULL COMMENT '奖品卡券id',
  `start_place` int(11) DEFAULT NULL COMMENT '开始名次',
  `end_place` int(11) DEFAULT NULL COMMENT '结束名次',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='摇一摇奖项表';

-- ----------------------------
-- Table structure for car_role
-- ----------------------------
DROP TABLE IF EXISTS `car_role`;
CREATE TABLE `car_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_title` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `type` int(4) DEFAULT NULL COMMENT '类型:0普通用户;1保养顾问;2续保顾问;3:保险顾问;',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for car_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `car_role_authority`;
CREATE TABLE `car_role_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '角色id',
  `authority_id` int(11) DEFAULT NULL COMMENT '权限id',
  `append` int(4) DEFAULT NULL COMMENT '新增:1是;0否',
  `del` int(4) DEFAULT NULL COMMENT '删除:1是;0否',
  `alteration` int(4) DEFAULT NULL COMMENT '修改:1是;0否',
  `view` int(4) DEFAULT NULL COMMENT '查看:1是;0否',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Table structure for car_store
-- ----------------------------
DROP TABLE IF EXISTS `car_store`;
CREATE TABLE `car_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_name` varchar(50) DEFAULT NULL,
  `organization_id` int(11) DEFAULT NULL COMMENT '组织机构id',
  `brevity_code` varchar(100) DEFAULT NULL COMMENT '简码',
  `store_type` int(4) DEFAULT NULL COMMENT '门店类型0集团；1 4s店；2维修店；',
  `contacts` varchar(50) DEFAULT NULL COMMENT '联系人',
  `telephone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `state` int(4) DEFAULT NULL COMMENT '状态',
  `price` decimal(20,2) DEFAULT NULL COMMENT '价格',
  `referee` varchar(50) DEFAULT NULL COMMENT '推荐人',
  `service_start` datetime DEFAULT NULL COMMENT '服务开始时间',
  `service_end` datetime DEFAULT NULL COMMENT '服务到期',
  `org_describe` varchar(500) DEFAULT NULL COMMENT '组织描述',
  `longitude` decimal(10,7) DEFAULT NULL COMMENT '地理经度',
  `latitude` decimal(10,7) DEFAULT NULL COMMENT '地理纬度',
  `sales_hotline` varchar(50) DEFAULT NULL COMMENT '销售热线',
  `renew_hotline` varchar(50) DEFAULT NULL COMMENT '续保热线',
  `rescue_hotline` varchar(50) DEFAULT NULL COMMENT '救援热线',
  `lntegral_proportion` decimal(5,2) DEFAULT NULL COMMENT '积分比例',
  `wifi_name` varchar(100) DEFAULT NULL COMMENT 'wifi名称',
  `wifi_password` varchar(100) DEFAULT NULL COMMENT 'wifi密码',
  `logourl` varchar(300) DEFAULT NULL COMMENT 'logourl',
  `qr_code` varchar(300) DEFAULT NULL COMMENT '二维码url',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for car_survey_options
-- ----------------------------
DROP TABLE IF EXISTS `car_survey_options`;
CREATE TABLE `car_survey_options` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `question_id` int(11) DEFAULT NULL COMMENT '问题id',
  `survey_option` varchar(50) DEFAULT NULL COMMENT '选项',
  `option_content` varchar(255) DEFAULT NULL COMMENT '选项内容',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问卷选项表';

-- ----------------------------
-- Table structure for car_survey_question
-- ----------------------------
DROP TABLE IF EXISTS `car_survey_question`;
CREATE TABLE `car_survey_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `survey_id` int(11) DEFAULT NULL COMMENT '问卷id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `desc_info` varchar(255) DEFAULT NULL COMMENT '说明',
  `whether_answer` int(4) DEFAULT NULL COMMENT '是否必答1是；0否',
  `type` int(4) DEFAULT NULL COMMENT '题型：0单选，1多选；2填空',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问卷问题表';

-- ----------------------------
-- Table structure for car_user
-- ----------------------------
DROP TABLE IF EXISTS `car_user`;
CREATE TABLE `car_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '账号',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(50) DEFAULT NULL COMMENT '性别',
  `birthday` varchar(50) DEFAULT NULL COMMENT '生日',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `head_pic` varchar(255) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(50) DEFAULT NULL COMMENT '微信昵称',
  `employee_id` int(11) DEFAULT NULL COMMENT '默认为0,当用户为员工时存id',
  `upkeep_employee_id` int(11) DEFAULT NULL COMMENT '保养顾问',
  `safe_employee_id` int(11) DEFAULT NULL COMMENT '保险顾问员工id',
  `renew_employee_id` int(11) DEFAULT NULL COMMENT '续保顾问员工id',
  `member_id` int(11) DEFAULT NULL COMMENT '会员卡等级id',
  `integral` decimal(20,1) DEFAULT NULL COMMENT '会员积分',
  `money` decimal(20,2) DEFAULT NULL COMMENT '会员余额',
  `brokerage` decimal(20,2) DEFAULT NULL COMMENT '佣金余额',
  `maintain_remind` int(11) DEFAULT NULL COMMENT '保养提醒（天）',
  `renewal_remind` int(11) DEFAULT NULL COMMENT '续保提醒（天）',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Table structure for car_user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `car_user_coupon`;
CREATE TABLE `car_user_coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code_num` varchar(255) DEFAULT NULL COMMENT '卡券Code码',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `coupon_id` int(11) DEFAULT NULL COMMENT '卡券id',
  `status` int(4) DEFAULT NULL COMMENT '卡券状态:0未使用;1已使用；',
  `get_time` datetime DEFAULT NULL COMMENT '领取时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '核销时间',
  `employee_id` int(11) DEFAULT NULL COMMENT '操作用户',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户卡券记录表';

-- ----------------------------
-- Table structure for car_user_log
-- ----------------------------
DROP TABLE IF EXISTS `car_user_log`;
CREATE TABLE `car_user_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `modular_name` varchar(100) DEFAULT NULL COMMENT '模块名称',
  `operation_name` varchar(100) DEFAULT NULL COMMENT '操作名称',
  `operation_time` datetime DEFAULT NULL COMMENT '操作时间',
  `class_name` varchar(100) DEFAULT NULL COMMENT '类名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名称',
  `afferent_parameter` varchar(100) DEFAULT NULL COMMENT '传入参数',
  `operation_ip` varchar(100) DEFAULT NULL COMMENT '操作ip',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Table structure for car_user_lottery
-- ----------------------------
DROP TABLE IF EXISTS `car_user_lottery`;
CREATE TABLE `car_user_lottery` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `lottery_info_id` int(11) DEFAULT NULL COMMENT '奖品表id',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `type` int(4) DEFAULT NULL COMMENT '类型',
  `time` datetime DEFAULT NULL COMMENT '抽奖时间',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户抽奖详情表';

-- ----------------------------
-- Table structure for car_user_rocking
-- ----------------------------
DROP TABLE IF EXISTS `car_user_rocking`;
CREATE TABLE `car_user_rocking` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `place` int(11) DEFAULT NULL COMMENT '名次',
  `rocking_awards_jd` int(11) DEFAULT NULL COMMENT '摇一摇奖项表id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `integral` int(11) DEFAULT NULL COMMENT '摇一摇积分',
  `participate_time` datetime DEFAULT NULL COMMENT '参与时间',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户摇一摇详情表';

-- ----------------------------
-- Table structure for car_user_role
-- ----------------------------
DROP TABLE IF EXISTS `car_user_role`;
CREATE TABLE `car_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色中间表';

-- ----------------------------
-- Table structure for car_vehicle
-- ----------------------------
DROP TABLE IF EXISTS `car_vehicle`;
CREATE TABLE `car_vehicle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `telephone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `plate_num` varchar(255) DEFAULT NULL COMMENT '车牌',
  `car_model` varchar(255) DEFAULT NULL COMMENT '车型',
  `frame_num` varchar(255) DEFAULT NULL COMMENT '车架号',
  `engine_num` varchar(255) DEFAULT NULL COMMENT '发动机号',
  `Insurance_start` datetime DEFAULT NULL COMMENT '保险购买时间',
  `Insurance_state` int(4) DEFAULT NULL COMMENT '保险到期状态1是；0否',
  `last_maintain_km` int(11) DEFAULT NULL COMMENT '上次保养公里数',
  `maintain_interval_km` int(11) DEFAULT NULL COMMENT '保养间隔公里数',
  `last_maintain_time` datetime DEFAULT NULL COMMENT '上次保养日期',
  `maintain_end` datetime DEFAULT NULL COMMENT '保养到期时间',
  `maintain_state` int(4) DEFAULT NULL COMMENT '保养到期状态1是；0否',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户车辆表';

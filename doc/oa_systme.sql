/*
 Navicat Premium Data Transfer

 Source Server         : 123.207.237.59_3306
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 123.207.237.59:3306
 Source Schema         : oa_systme

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 20/01/2020 10:59:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_role
-- ----------------------------
DROP TABLE IF EXISTS `blog_role`;
CREATE TABLE `blog_role`  (
  `role_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键',
  `role_type_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色类型名',
  `role_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_role
-- ----------------------------
INSERT INTO `blog_role` VALUES ('1', '超级管理员', 'SM');
INSERT INTO `blog_role` VALUES ('2', '普通用户', 'N');

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user`  (
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `userName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住址',
  `web_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人主页',
  `callself` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别(M:男,W:女)',
  `nick_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `image_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `user_email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人邮件地址',
  `token` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人安全令牌',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_user
-- ----------------------------
INSERT INTO `blog_user` VALUES ('20200112110339TYPO6S', 'zhangsan', 'f51703256a38e6bab3d9410a070c32ea', '', NULL, NULL, 'M', '张三', NULL, 'zhangsan@gmail.com', NULL);

-- ----------------------------
-- Table structure for dept_info
-- ----------------------------
DROP TABLE IF EXISTS `dept_info`;
CREATE TABLE `dept_info`  (
  `dept_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门id',
  `dept_no` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门号',
  `dept_name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名',
  `dept_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门描述',
  `dept_order` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept_info
-- ----------------------------
INSERT INTO `dept_info` VALUES ('20200118163125K2ZKKM', 'BM20200118163125', '技术部门', '公司最核心的部门,也是公司团队的核心!', '1');
INSERT INTO `dept_info` VALUES ('20200118163146NX5WWU', 'BM20200118163146', '销售部门', '公司对外推销的主要手段!', '2');
INSERT INTO `dept_info` VALUES ('20200118163232OTFS4N', 'BM20200118163232', '产品部门', '和客户对接,了解搞懂客户的需求。', '3');
INSERT INTO `dept_info` VALUES ('20200118163320UGFEMC', 'BM20200118163320', '人事部门', '是公司的\"网关\",进入公司的最低门槛,也是公司娱乐后勤的主要策划部门', '5');
INSERT INTO `dept_info` VALUES ('20200118163516YEHGYS', 'BM20200118163516', '测试部门', '项目上线前,对每一个功能进行检查,看是否有功能残缺,是否有重要bug。是否有性能问题,是公司产品品控的重要部门.', '4');
INSERT INTO `dept_info` VALUES ('20200118163558GLYMWE', 'BM20200118163558', '运维部门', '   公司所有产品测试,git,maven私有仓库,elk等配置都是这个部门负责,公司开发\n的根基，也是jekins以及docker以及k8s的部署人，项目上线都靠他们.', '6');
INSERT INTO `dept_info` VALUES ('20200118225516EW9TCY', 'BM20200118225516', '营销部门', '主要进行营销策略！！！', '7');
INSERT INTO `dept_info` VALUES ('20200120102235O0JWXC', 'BM20200120102235', '添加', '大萨达撒多', '21312');

-- ----------------------------
-- Table structure for duty_info
-- ----------------------------
DROP TABLE IF EXISTS `duty_info`;
CREATE TABLE `duty_info`  (
  `duty_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职务id',
  `duty_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务名',
  `duty_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`duty_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of duty_info
-- ----------------------------
INSERT INTO `duty_info` VALUES ('20200112210230NG03QH', '项目经理', '我牛逼111');
INSERT INTO `duty_info` VALUES ('20200119164732SKWTHV', '主管', '我是主管我怕谁');
INSERT INTO `duty_info` VALUES ('202001192120525RT6K7', 'Java研发', '研发人员');
INSERT INTO `duty_info` VALUES ('20200119212104CQIQEV', 'Java研发', '研发部门');
INSERT INTO `duty_info` VALUES ('20200119220916MSYP9E', '产品经理', '我是经理');

-- ----------------------------
-- Table structure for emp_info
-- ----------------------------
DROP TABLE IF EXISTS `emp_info`;
CREATE TABLE `emp_info`  (
  `emp_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工id',
  `emp_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  PRIMARY KEY (`emp_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp_info
-- ----------------------------
INSERT INTO `emp_info` VALUES ('20200112110339TYPO6T', 'EMP20200112210230');

-- ----------------------------
-- Table structure for link_duty_dept
-- ----------------------------
DROP TABLE IF EXISTS `link_duty_dept`;
CREATE TABLE `link_duty_dept`  (
  `link_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联id',
  `duty_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务id',
  `dept_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门id',
  `emp_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '雇员id',
  PRIMARY KEY (`link_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of link_duty_dept
-- ----------------------------
INSERT INTO `link_duty_dept` VALUES ('20200112210230NG03QM', '20200112210230NG03QH', '20200118163125K2ZKKM', '20200112110339TYPO6T');
INSERT INTO `link_duty_dept` VALUES ('20200119164732A5YC9C', '20200119164732SKWTHV', '20200118163125K2ZKKM', NULL);
INSERT INTO `link_duty_dept` VALUES ('20200119203204S99QMA', '20200119203204EXB6TV', '20200118163125K2ZKKM', NULL);
INSERT INTO `link_duty_dept` VALUES ('20200119212052YEAELQ', '202001192120525RT6K7', '20200118163125K2ZKKM', NULL);
INSERT INTO `link_duty_dept` VALUES ('2020011921210425FFM8', '20200119212104CQIQEV', '20200118163125K2ZKKM', NULL);
INSERT INTO `link_duty_dept` VALUES ('20200119220916QYUJP4', '20200119220916MSYP9E', '20200118163232OTFS4N', NULL);

-- ----------------------------
-- Table structure for link_emp_dept
-- ----------------------------
DROP TABLE IF EXISTS `link_emp_dept`;
CREATE TABLE `link_emp_dept`  (
  `link_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联id',
  `emp_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '雇员id',
  `dept_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`link_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of link_emp_dept
-- ----------------------------
INSERT INTO `link_emp_dept` VALUES ('20200112210230NG03QT', '20200112110339TYPO6T', '20200118163125K2ZKKM');

-- ----------------------------
-- Table structure for link_emp_user
-- ----------------------------
DROP TABLE IF EXISTS `link_emp_user`;
CREATE TABLE `link_emp_user`  (
  `link_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联id',
  `emp_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工id',
  `user_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`link_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of link_emp_user
-- ----------------------------
INSERT INTO `link_emp_user` VALUES ('20200112110339TYPO8T', '20200112110339TYPO6T', '20200112110339TYPO6S');

-- ----------------------------
-- Table structure for link_role_user
-- ----------------------------
DROP TABLE IF EXISTS `link_role_user`;
CREATE TABLE `link_role_user`  (
  `link_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联主键',
  `role_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`link_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of link_role_user
-- ----------------------------
INSERT INTO `link_role_user` VALUES ('20200112110339TYPO6T', '1', '20200112110339TYPO6S');

-- ----------------------------
-- Table structure for opt_log
-- ----------------------------
DROP TABLE IF EXISTS `opt_log`;
CREATE TABLE `opt_log`  (
  `log_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志id',
  `account_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户id',
  `operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作信息',
  `oper_time` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作时间',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '在哪个方法下',
  `ip` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作的ip',
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名',
  `opt_result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作结果',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of opt_log
-- ----------------------------
INSERT INTO `opt_log` VALUES ('20200120103725XYVGOI', '20200112110339TYPO6S', '4', '2020-01-20 10:37:25', 'com.qtatelier.OASystem.web.DutyInfoController.findAll', '0:0:0:0:0:0:0:1', '职位模块', '查出每个部门所有的职位成功', '查出每个部门所有的职位');
INSERT INTO `opt_log` VALUES ('20200120103733C3LZYF', '20200112110339TYPO6S', '4', '2020-01-20 10:37:33', 'com.qtatelier.OASystem.web.DeptInfoController.findAll', '0:0:0:0:0:0:0:1', '部门模块', '查出所有的部门成功', '查出所有的部门');
INSERT INTO `opt_log` VALUES ('202001201037383P8GTV', '20200112110339TYPO6S', '4', '2020-01-20 10:37:38', 'com.qtatelier.OASystem.web.DutyInfoController.findAll', '0:0:0:0:0:0:0:1', '职位模块', '查出每个部门所有的职位成功', '查出每个部门所有的职位');
INSERT INTO `opt_log` VALUES ('20200120103742AJ6I4J', '20200112110339TYPO6S', '4', '2020-01-20 10:37:42', 'com.qtatelier.OASystem.web.DeptInfoController.findAll', '0:0:0:0:0:0:0:1', '部门模块', '查出所有的部门成功', '查出所有的部门');
INSERT INTO `opt_log` VALUES ('20200120103742YMSXUN', '20200112110339TYPO6S', '4', '2020-01-20 10:37:42', 'com.qtatelier.OASystem.web.DutyInfoController.findInfo', '0:0:0:0:0:0:0:1', '职位模块', '查询单条信息成功', '查询单条信息');
INSERT INTO `opt_log` VALUES ('2020012010374648OTGF', '20200112110339TYPO6S', '3', '2020-01-20 10:37:46', 'com.qtatelier.OASystem.web.DutyInfoController.updateDutyInfo', '0:0:0:0:0:0:0:1', '职位模块', '按主键修改职务信息成功', '按主键修改职务信息');
INSERT INTO `opt_log` VALUES ('20200120103748FEQPAT', '20200112110339TYPO6S', '4', '2020-01-20 10:37:48', 'com.qtatelier.OASystem.web.DeptInfoController.findAll', '0:0:0:0:0:0:0:1', '部门模块', '查出所有的部门成功', '查出所有的部门');
INSERT INTO `opt_log` VALUES ('20200120103748LI1QQV', '20200112110339TYPO6S', '4', '2020-01-20 10:37:48', 'com.qtatelier.OASystem.web.DutyInfoController.findInfo', '0:0:0:0:0:0:0:1', '职位模块', '查询单条信息成功', '查询单条信息');
INSERT INTO `opt_log` VALUES ('20200120103751SWQGG8', '20200112110339TYPO6S', '4', '2020-01-20 10:37:51', 'com.qtatelier.OASystem.web.DeptInfoController.findAll', '0:0:0:0:0:0:0:1', '部门模块', '查出所有的部门成功', '查出所有的部门');
INSERT INTO `opt_log` VALUES ('20200120103753MKIKP3', '20200112110339TYPO6S', '4', '2020-01-20 10:37:53', 'com.qtatelier.OASystem.web.DeptInfoController.findInfo', '0:0:0:0:0:0:0:1', '部门模块', '按主键查询部门信息成功', '按主键查询部门信息');
INSERT INTO `opt_log` VALUES ('20200120103755BIG1AL', '20200112110339TYPO6S', '3', '2020-01-20 10:37:55', 'com.qtatelier.OASystem.web.DeptInfoController.updateDeptInfo', '0:0:0:0:0:0:0:1', '部门模块', '按主键修改部门信息成功', '按主键修改部门信息');
INSERT INTO `opt_log` VALUES ('20200120103757YT2SLX', '20200112110339TYPO6S', '4', '2020-01-20 10:37:57', 'com.qtatelier.OASystem.web.DeptInfoController.findInfo', '0:0:0:0:0:0:0:1', '部门模块', '按主键查询部门信息成功', '按主键查询部门信息');
INSERT INTO `opt_log` VALUES ('20200120104940F0XBVR', '20200112110339TYPO6S', '4', '2020-01-20 10:49:40', 'com.qtatelier.OASystem.web.BlogUserController.getUserInfo', '0:0:0:0:0:0:0:1', '用户模块', '登录并展示当前信息成功', '登录并展示当前信息');

-- ----------------------------
-- Table structure for param
-- ----------------------------
DROP TABLE IF EXISTS `param`;
CREATE TABLE `param`  (
  `DEFAULT_PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '默认密码'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of param
-- ----------------------------
INSERT INTO `param` VALUES ('AC(FDS54)*844');

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : favorite

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 18/12/2019 22:10:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dict_article
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_article`;
CREATE TABLE `t_dict_article` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `chapter_id` bigint(20) DEFAULT NULL COMMENT '章节ID',
  `name_en` varchar(255) NOT NULL COMMENT '文章名-英文',
  `name_ch` varchar(255) DEFAULT NULL COMMENT '文章名-中文',
  `content_en` text COMMENT '文章内容-英文',
  `content_ch` text COMMENT '文章内容-中文',
  `descp` text COMMENT '描述/备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_chapter_id` (`chapter_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='词典-章节-文章';

-- ----------------------------
-- Table structure for t_dict_book
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_book`;
CREATE TABLE `t_dict_book` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name_en` varchar(255) NOT NULL COMMENT '书名-英文',
  `name_ch` varchar(255) DEFAULT NULL COMMENT '书名-中文',
  `descp` text COMMENT '描述/备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='词典-书';

-- ----------------------------
-- Table structure for t_dict_chapter
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_chapter`;
CREATE TABLE `t_dict_chapter` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `book_id` bigint(20) DEFAULT NULL COMMENT '书ID',
  `name_en` varchar(255) NOT NULL COMMENT '章节名-英文',
  `name_ch` varchar(255) NOT NULL COMMENT '章节名-中文',
  `descp` text COMMENT '描述/备注',
  `sn` int(4) DEFAULT NULL COMMENT '顺序号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_book_id` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='词典-书-章节';

-- ----------------------------
-- Table structure for t_dict_etyma
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_etyma`;
CREATE TABLE `t_dict_etyma` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(255) NOT NULL COMMENT '词源名称',
  `descp` text COMMENT '词源描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='词典-词源表';

-- ----------------------------
-- Table structure for t_dict_word
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_word`;
CREATE TABLE `t_dict_word` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(255) NOT NULL COMMENT '单词名称',
  `soundmark` varchar(255) DEFAULT NULL COMMENT '音标',
  `degree` smallint(4) DEFAULT NULL COMMENT '熟悉程度，详见枚举',
  `skill_desc` text COMMENT '记忆技巧描述',
  `descp` text COMMENT '单词描述/备注',
  `query_count` int(8) DEFAULT NULL COMMENT '查询次数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_unq_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='词典-单词表';

-- ----------------------------
-- Table structure for t_dict_word_article_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_word_article_rel`;
CREATE TABLE `t_dict_word_article_rel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `word_id` bigint(20) DEFAULT NULL COMMENT '单词表ID',
  `article_id` bigint(20) DEFAULT NULL COMMENT '文章ID',
  `sn` int(8) DEFAULT NULL COMMENT '顺序号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_word_id` (`word_id`) USING BTREE,
  KEY `idx_article_id` (`article_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典-单词-文章关联表';

-- ----------------------------
-- Table structure for t_dict_word_def
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_word_def`;
CREATE TABLE `t_dict_word_def` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `word_id` bigint(20) NOT NULL COMMENT '单词表ID',
  `name` varchar(255) NOT NULL COMMENT '中文释义',
  `type` smallint(4) DEFAULT NULL COMMENT '释义单词类型',
  `explain_en` text COMMENT '英语解释',
  `explain_ch` text COMMENT '中文解释',
  `descp` text COMMENT '描述/备注',
  `sn` int(4) DEFAULT NULL COMMENT '顺序号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='词典-单词释义表';

-- ----------------------------
-- Table structure for t_dict_word_def_example
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_word_def_example`;
CREATE TABLE `t_dict_word_def_example` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `def_id` bigint(20) DEFAULT NULL COMMENT '释义表ID',
  `example_en` text COMMENT '英语解释',
  `example_ch` text COMMENT '中文解释',
  `descp` text COMMENT '描述/备注',
  `sn` int(4) DEFAULT NULL COMMENT '顺序号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='词典-单词释义例子表';

-- ----------------------------
-- Table structure for t_dict_word_etyma_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_word_etyma_rel`;
CREATE TABLE `t_dict_word_etyma_rel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `word_id` bigint(20) DEFAULT NULL COMMENT '单词表ID',
  `etyma_id` bigint(20) DEFAULT NULL COMMENT '词源表ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典-单词词源关联表';

SET FOREIGN_KEY_CHECKS = 1;

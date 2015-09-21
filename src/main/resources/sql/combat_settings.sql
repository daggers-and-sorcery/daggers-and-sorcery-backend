/*
MySQL Data Transfer
Source Host: localhost
Source Database: swords
Target Host: localhost
Target Database: swords
Date: 2015.09.21. 7:37:58
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for combat_settings
-- ----------------------------
DROP TABLE IF EXISTS `combat_settings`;
CREATE TABLE `combat_settings` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `type` varchar(255) NOT NULL,
  `settings_id` mediumint(8) unsigned NOT NULL,
  `trigger_type` varchar(16) NOT NULL,
  `target` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------

/*
MySQL Data Transfer
Source Host: localhost
Source Database: swords
Target Host: localhost
Target Database: swords
Date: 2015.09.16. 7:25:38
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for combat_settings
-- ----------------------------
DROP TABLE IF EXISTS `combat_settings`;
CREATE TABLE `combat_settings` (
  `user_id` int(10) unsigned NOT NULL,
  `type` varchar(255) NOT NULL,
  `settings_id` mediumint(8) unsigned NOT NULL,
  `trigger` smallint(5) unsigned NOT NULL,
  `target` tinyint(3) unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------

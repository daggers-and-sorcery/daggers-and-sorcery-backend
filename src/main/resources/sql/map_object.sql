/*
MySQL Data Transfer
Source Host: localhost
Source Database: swords
Target Host: localhost
Target Database: swords
Date: 2015.08.03. 22:26:19
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for map_object
-- ----------------------------
DROP TABLE IF EXISTS `map_object`;
CREATE TABLE `map_object` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `object` int(11) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  `map_id` int(11) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------

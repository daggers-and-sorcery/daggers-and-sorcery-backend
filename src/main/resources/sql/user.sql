USE swords;

CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(256) NOT NULL,
  `username` varchar(16) NOT NULL,
  `password` varchar(64) NOT NULL,
  `race` varchar(16) DEFAULT NULL,
  `registration_date` datetime DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `x` smallint(6) DEFAULT NULL,
  `y` smallint(6) DEFAULT NULL,
  `map` int(11) DEFAULT NULL,
  `health` int(10) unsigned NOT NULL,
  `mana` int(10) unsigned NOT NULL,
  `movement` int(10) unsigned NOT NULL,
  `lastRegenerationDate` datetime NOT NULL DEFAULT '1970-00-00 00:00:00',
  PRIMARY KEY (`id`,`email`,`username`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

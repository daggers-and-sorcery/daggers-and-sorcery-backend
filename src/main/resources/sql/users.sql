USE swords;

CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(256) CHARACTER SET latin1 NOT NULL,
  `username` varchar(16) CHARACTER SET latin1 NOT NULL,
  `password` varchar(64) CHARACTER SET latin1 NOT NULL,
  `race` varchar(16) CHARACTER SET latin1 NOT NULL,
  `registration_date` datetime NOT NULL,
  `last_login_date` datetime NOT NULL,
  `x` smallint(6) unsigned NOT NULL,
  `y` smallint(6) unsigned NOT NULL,
  `map` int(11) unsigned NOT NULL,
  `health` int(10) unsigned NOT NULL,
  `mana` int(10) unsigned NOT NULL,
  `movement` int(10) unsigned NOT NULL,
  `lastRegenerationDate` datetime NOT NULL DEFAULT '1970-00-00 00:00:00',
  PRIMARY KEY (`id`,`email`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


insert into `users`(`id`,`email`,`username`,`password`,`race`,`registration_date`,`last_login_date`,`x`,`y`,`map`,`health`,`mana`,`movement`,`lastRegenerationDate`) values (1,'test@example.com','testuser','53336a676c64c1396553b2b7c92f38126768827c93b64d9142069c10eda7a721','HUMAN','2015-08-27 15:59:53','2015-08-27 15:59:53',0,0,1,15,15,30,null);

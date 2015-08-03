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
  PRIMARY KEY (`id`,`email`,`username`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


insert into `user`(`id`,`email`,`username`,`password`,`race`,`registration_date`,`last_login_date`,`x`,`y`,`map`) values (2,'asdasd2@asd','asdasd2','5fd924625f6ab16a19cc9807c7c506ae1813490e4ba675f843d5a10e0baacdb8','HUMAN','2015-07-28 12:14:53','2015-08-03 15:53:28',null,null,null);

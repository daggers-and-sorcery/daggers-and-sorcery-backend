USE swords;

CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(256) NOT NULL,
  `username` varchar(16) NOT NULL,
  `password` varchar(64) NOT NULL,
  `race` varchar(16) DEFAULT NULL,
  `registration_date` datetime DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`email`,`username`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


insert into `user`(`id`,`email`,`username`,`password`,`race`,`registration_date`,`last_login_date`) values (1,'asd@asd','asdasd','5fd924625f6ab16a19cc9807c7c506ae1813490e4ba675f843d5a10e0baacdb8','HUMAN','2015-07-28 12:00:39','2015-07-28 12:00:43');

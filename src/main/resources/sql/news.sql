USE swords;

CREATE TABLE `news` (
  `id` smallint(6) NOT NULL DEFAULT '0',
  `date` date NOT NULL,
  `title` varchar(256) NOT NULL,
  `message` varchar(2048) NOT NULL,
  `icon` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


insert into `news`(`id`,`date`,`title`,`message`,`icon`) values (0,'2015-07-02','Test title','some message here just for testing','');

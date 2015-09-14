USE swords;

CREATE TABLE `journal` (
  `user_id` mediumint(8) unsigned NOT NULL,
  `journal_type` varchar(20) NOT NULL,
  `journal_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`journal_type`,`journal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



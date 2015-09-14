USE swords;

CREATE TABLE `journal` (
  `user_id` mediumint(8) unsigned NOT NULL,
  `type` varchar(20) NOT NULL,
  `journal_id` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



USE swords;

CREATE TABLE `inventory` (
  `user_id` int(10) unsigned NOT NULL,
  `item_id` int(10) unsigned NOT NULL,
  `amount` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



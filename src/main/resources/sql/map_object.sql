USE swords;

CREATE TABLE `map_object` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `object` int(11) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  `map_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



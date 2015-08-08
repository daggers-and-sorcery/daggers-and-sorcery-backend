USE swords;

CREATE TABLE `equipment` (
  `userId` bigint(20) unsigned NOT NULL,
  `weapon` int(10) unsigned NOT NULL,
  `offhand` int(10) unsigned NOT NULL,
  `helm` int(10) unsigned NOT NULL,
  `gloves` int(10) unsigned NOT NULL,
  `ring` int(10) unsigned NOT NULL,
  `amulet` int(10) unsigned NOT NULL,
  `boots` int(10) unsigned NOT NULL,
  `bracers` int(10) unsigned NOT NULL,
  `chest` int(10) unsigned NOT NULL,
  `legs` int(10) unsigned NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



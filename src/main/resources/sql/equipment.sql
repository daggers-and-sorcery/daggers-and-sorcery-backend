USE swords;

CREATE TABLE `equipment` (
  `userId` bigint(20) unsigned NOT NULL,
  `weapon` int(10) unsigned NOT NULL DEFAULT '0',
  `offhand` int(10) unsigned NOT NULL DEFAULT '0',
  `helm` int(10) unsigned NOT NULL DEFAULT '0',
  `gloves` int(10) unsigned NOT NULL DEFAULT '0',
  `ring` int(10) unsigned NOT NULL DEFAULT '0',
  `amulet` int(10) unsigned NOT NULL DEFAULT '0',
  `boots` int(10) unsigned NOT NULL DEFAULT '0',
  `bracers` int(10) unsigned NOT NULL DEFAULT '0',
  `chest` int(10) unsigned NOT NULL DEFAULT '0',
  `legs` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



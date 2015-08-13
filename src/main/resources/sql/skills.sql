USE swords;

CREATE TABLE `skills` (
  `userId` bigint(20) unsigned NOT NULL,
  `twoHandedCrushingWeaponsXp` bigint(20) unsigned NOT NULL,
  `oneHandedCrushingWeaponsXp` bigint(20) unsigned NOT NULL,
  `twoHandedAxesXp` bigint(20) unsigned NOT NULL,
  `oneHandedAxesXp` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

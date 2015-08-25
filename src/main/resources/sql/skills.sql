USE swords;

CREATE TABLE `skills` (
  `userId` bigint(20) unsigned NOT NULL,
  `twoHandedCrushingWeaponsXp` bigint(20) unsigned NOT NULL,
  `oneHandedCrushingWeaponsXp` bigint(20) unsigned NOT NULL,
  `twoHandedAxesXp` bigint(20) unsigned NOT NULL,
  `oneHandedAxesXp` bigint(20) unsigned NOT NULL,
  `throwingWeaponsXp` bigint(20) unsigned NOT NULL,
  ` fistfightXp` bigint(20) unsigned NOT NULL,
  `longswordsXp` bigint(20) unsigned NOT NULL,
  `shortswordsXp` bigint(20) unsigned NOT NULL,
  `polearmsXp` bigint(20) unsigned NOT NULL,
  `daggersXp` bigint(20) unsigned NOT NULL,
  `longbowsXp` bigint(20) unsigned NOT NULL,
  `shortbowsXp` bigint(20) unsigned NOT NULL,
  `crossbowsXp` bigint(20) unsigned NOT NULL,
  `lightArmorXp` bigint(20) unsigned NOT NULL,
  `heavyArmorXp` bigint(20) unsigned NOT NULL,
  `robeArmorXp` bigint(20) unsigned NOT NULL,
  `armorlessDefenseXp` bigint(20) unsigned NOT NULL,
  `shieldDefenseXp` bigint(20) unsigned NOT NULL,
  `staffsXp` bigint(20) unsigned NOT NULL,
  `wandsXp` bigint(20) unsigned NOT NULL,
  `spectresXp` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

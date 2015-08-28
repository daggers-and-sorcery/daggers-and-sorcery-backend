USE swords;

CREATE TABLE `skills` (
  `userId` bigint(20) unsigned NOT NULL,
  `twoHandedCrushingWeaponsXp` bigint(20) unsigned NOT NULL DEFAULT '0',
  `oneHandedCrushingWeaponsXp` bigint(20) unsigned NOT NULL DEFAULT '0',
  `twoHandedAxesXp` bigint(20) unsigned NOT NULL DEFAULT '0',
  `oneHandedAxesXp` bigint(20) unsigned NOT NULL DEFAULT '0',
  `throwingWeaponsXp` bigint(20) unsigned NOT NULL DEFAULT '0',
  ` fistfightXp` bigint(20) unsigned NOT NULL DEFAULT '0',
  `longswordsXp` bigint(20) unsigned NOT NULL DEFAULT '0',
  `shortswordsXp` bigint(20) unsigned NOT NULL DEFAULT '0',
  `polearmsXp` bigint(20) unsigned NOT NULL DEFAULT '0',
  `daggersXp` bigint(20) unsigned NOT NULL DEFAULT '0',
  `longbowsXp` bigint(20) unsigned NOT NULL DEFAULT '0',
  `shortbowsXp` bigint(20) unsigned NOT NULL DEFAULT '0',
  `crossbowsXp` bigint(20) unsigned NOT NULL DEFAULT '0',
  `lightArmorXp` bigint(20) unsigned NOT NULL DEFAULT '0',
  `heavyArmorXp` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '0',
  `robeArmorXp` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '0',
  `armorlessDefenseXp` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '0',
  `shieldDefenseXp` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '0',
  `staffsXp` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '0',
  `wandsXp` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '0',
  `spectresXp` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '0',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


insert into `skills`(`userId`,`twoHandedCrushingWeaponsXp`,`oneHandedCrushingWeaponsXp`,`twoHandedAxesXp`,`oneHandedAxesXp`,`throwingWeaponsXp`,` fistfightXp`,`longswordsXp`,`shortswordsXp`,`polearmsXp`,`daggersXp`,`longbowsXp`,`shortbowsXp`,`crossbowsXp`,`lightArmorXp`,`heavyArmorXp`,`robeArmorXp`,`armorlessDefenseXp`,`shieldDefenseXp`,`staffsXp`,`wandsXp`,`spectresXp`) values (1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);

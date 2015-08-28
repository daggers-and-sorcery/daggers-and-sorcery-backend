SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE swords.equipment;
TRUNCATE TABLE swords.inventory;
TRUNCATE TABLE swords.map_entity;
TRUNCATE TABLE swords.map_object;
TRUNCATE TABLE swords.news;
TRUNCATE TABLE swords.users;
TRUNCATE TABLE swords.skills;

#adding basic news
INSERT INTO news
SET id = 1, date = '1977-01-16 21:21:40', title = 'This is some test title', message = 'Lorem ipsum dolor sit ameth....', icon = 'bubble';
INSERT INTO news
SET id = 2, date = '2005-03-18 17:21:31', title = 'Some more tests', message = 'ZZzz hello world...', icon = 'bubble';

#Adding dbuser
INSERT INTO swords.users(`id`,`email`,`username`,`password`,`race`,`registration_date`,`last_login_date`,`x`,`y`,`map`,`health`,`mana`,`movement`,`lastRegenerationDate`) values (1,'dbuser@example.com','dbuser','53336a676c64c1396553b2b7c92f38126768827c93b64d9142069c10eda7a721','HUMAN','2015-08-27 15:59:53','2015-08-27 15:59:53',0,0,1,15,15,30,'2015-08-27 15:59:53');
INSERT INTO swords.equipment (userId, weapon, offhand, helm, gloves, ring, amulet, boots, bracers, chest, legs) VALUES(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO swords.skills (userId, twoHandedCrushingWeaponsXp, oneHandedCrushingWeaponsXp, twoHandedAxesXp, oneHandedAxesXp, throwingWeaponsXp,  longswordsXp, shortswordsXp, polearmsXp, daggersXp, longbowsXp, shortbowsXp, crossbowsXp, lightArmorXp, heavyArmorXp, robeArmorXp, armorlessDefenseXp, shieldDefenseXp, staffsXp, wandsXp, spectresXp) VALUES(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0, 0, 0, 0, 0, 0, 0, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
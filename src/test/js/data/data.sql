SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE swords.equipment;
TRUNCATE TABLE swords.inventory;
TRUNCATE TABLE swords.map_entity;
TRUNCATE TABLE swords.map_object;
TRUNCATE TABLE swords.news;
TRUNCATE TABLE swords.user;

INSERT INTO news
SET id = 1, date = '1977-01-16 21:21:40', title = 'This is some test title', message = 'Lorem ipsum dolor sit ameth....', icon = 'bubble';
INSERT INTO news
SET id = 2, date = '2005-03-18 17:21:31', title = 'Some more tests', message = 'ZZzz hello world...', icon = 'bubble';


SET FOREIGN_KEY_CHECKS = 1;
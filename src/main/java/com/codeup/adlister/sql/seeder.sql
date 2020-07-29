USE tunnel_snakes_db;

TRUNCATE users;

INSERT INTO users (username, email, password, is_admin) VALUES
('LoneWanderer', 'lonewanderer@vault.tec', '$2a$12$5PwrbufAfyRV4SesW1yb/OLlg3Elxifp41ZpJR27lGEH6rNXvhFhi', 1),
('ThreeDog', 'threedog@vault.tec', '$2a$12$5PwrbufAfyRV4SesW1yb/OLlg3Elxifp41ZpJR27lGEH6rNXvhFhi', null),
('Wadsworth', 'wadsworth@vault.tec', '$2a$12$5PwrbufAfyRV4SesW1yb/OLlg3Elxifp41ZpJR27lGEH6rNXvhFhi', null),
('AbrahamWashington', 'abrahamwashington@vault.tec', '$2a$12$5PwrbufAfyRV4SesW1yb/OLlg3Elxifp41ZpJR27lGEH6rNXvhFhi', null),
('AllistairTenpenny', 'allistairtenpenny@vault.tec', '$2a$12$5PwrbufAfyRV4SesW1yb/OLlg3Elxifp41ZpJR27lGEH6rNXvhFhi', null),
('Overseer', 'overseer@vault.tec', '$2a$12$5PwrbufAfyRV4SesW1yb/OLlg3Elxifp41ZpJR27lGEH6rNXvhFhi', null);

TRUNCATE ads;

INSERT INTO ads (user_id, title, description) VALUES
(1, 'Garden of Eden Creation Kit', 'This kit contains all the seeds, fertilizer, and other equipment (including a cold fusion power generator and a basic replicator) necessary to start a new settlement in a post-nuclear world after emerging from a vault shelter.'),
(1, 'Bobblehead - Strength', 'One of twenty bobbleheads that can be found in the Capital Wasteland.'),
(2, 'Info About the Lone Wanderer''s Father', 'Critical information that will lead the Lone Wanderer to his next quest.'),
(2, 'Mini Nuke', 'A football-sized tactical nuclear warhead designed for use in a nuclear catapult weapon.'),
(3, 'Stealth Boy', 'A personal stealth device worn on one''s wrist.'),
(3, 'Purified Water', 'Water that is completely free of radiation.'),
(4, 'Schematics - Railway Rifle', 'The schematics needed to make the railway rifle.'),
(4, 'Declaration of Independence', 'Found in the ruins of D.C''s National Archives.'),
(5, 'Tenpenny Tower Suite Key', 'Key to suite at the top of Tenpenny Tower.'),
(5, 'Bobblehead Collector''s Stand', 'Stand to neatly display all the bobbleheads you''ve collected while out in the Wasteland.'),
(6, 'Pip-Boy 3000', 'The Pip-Boy 3000 is a pre-War electronic Personal Information Processor (PIP).'),
(6, 'Tunnel Snake Outfit', 'A leather jacket with a snake emblem on the back, worn on top of a Vault 101 jumpsuit.');

TRUNCATE categories;

INSERT INTO categories (name) VALUES
('Weapons'),
('Bobbleheads'),
('Electronics'),
('Food'),
('Clothes'),
('Quest Items');

TRUNCATE ad_category;

INSERT INTO ad_category (ad_id, category_id) VALUES
(1, 3),
(2, 2),
(3, 6),
(4, 1),
(5, 3),
(6, 4),
(7, 1),
(8, 6),
(9, 6),
(10, 2),
(11, 3),
(12, 5);
USE tunnel_snakes_db;

TRUNCATE ads;

INSERT INTO ads (id,user_id, title, description) VALUES
(1,1,'used car','Its a used car. What more do you want?'),
(2,1,'house','you live inside'),
(3,2,'shoes','you walk in them'),
(4,1,'hat','for the sun');

INSERT INTO categories(id,name) VALUES
(1,'auto'),
(2,'housing'),
(3,'pizza');

INSERT INTO ad_category(ad_id, category_id) VALUES
(1,1),
(2,1),
(3,2);
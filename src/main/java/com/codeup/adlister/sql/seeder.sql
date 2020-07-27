USE tunnel_snakes_db;

TRUNCATE ads;

INSERT INTO ads (id,user_id, title, description) VALUES
(1,1,'used car','Its a used car. What more do you want?'),
(2,1,'house','you live inside'),
(3,2,'shoes','you walk in them'),
(4,1,'hat','for the sun');
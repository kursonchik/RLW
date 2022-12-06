INSERT INTO roles (id, name) VALUES ('1', 'ROLE_ADMIN'), ('2', 'ROLE_EMPLOYEE'), ('3', 'ROLE_USER');

INSERT INTO users (id, username, password, email) VALUES (1,'adminadmin','adminadmin','admin@gmail.com'),(2,'employee','employee','employee@gmail.com'),(3,'passenger','passenger','passenger@gmail.com'),(4,'kursonchik','$2a$10$a11D2DS5.isgmVD9wJHxDud0XLbOx31zH.XU5RYqmcWinDUoAcWh6','olgakurson@mail.ru'),(5,'kursonchik1','kursonchik','olgakurson1@mail.ru'),(6,'qwerty','$2a$10$YjECQweX/4EBYteqQ2J8quo5OPR9KDijNGBr2R6ceRUlGHit/.isW','qwerty@gmail.com'),(7,'admin1','$2a$10$gswV/7CP.CbRgFSyDoVER.ovHMlNGsqLwzQgtdIsy/EeOw894MBKe','admin1@gmail.com');

INSERT INTO user_roles (user_id, role_id) VALUES ('1', '1'), ('2', '2'), ('3', '3');

INSERT INTO passengers (id, first_name, last_name, birth_date, passport_number, user_id) VALUES ('1', 'Ivan', 'Ivanov', '1975-01-01', '999999999', '3');

INSERT INTO tracks (id) VALUES ('1'), ('2'),('3'),('4'),('5'),('6'),('7'),('8'),('9'),('10');

INSERT INTO stations (id,name,is_endpoint,is_breakpoint) VALUES (1,'Viciebsk',1,0), (2,'Hrodna',1,0),(3,'Orsha',1,0),(4,'Brest',0,1),(5,'Pinsk',0,1),(6,'Maladzechna',1,0),(7,'Mahiliow',1,0),(8,'Baranavichi',1,0),(9,'Lida',1,0),(10,'Homiel',1,0),(11,'Minsk',1,0),(12,'Babrujsk',1,0);

INSERT INTO trains (id, name, seats, track_id) VALUES (1,'Minsk - Brest',50,1),(2,'Orsha - Brest',50,2),(3,'Minsk - Hrodna',50,3),(4,'Viciebsk - Homiel',50,4),(5,'Minsk- Homiel - Minsk',50,5),(6,'Brest - Minsk',50,6),(7,'Hrodna - Minsk',50,7),(8,'Minsk - Viciebsk',50,8),(9,'Viciebsk - Pinsk',50,9);

INSERT INTO mappings (id,station_id,track_id,station_order) VALUES (1,11,1,1), (2,8,1,2),(3,4,1,3),(4,3,2,1),(5,11,2,2),(6,4,2,3),(7,11,3,1), (8,6,3,2),(9,9,3,3),(10,2,3,4),(11,1,4,1),(12,3,4,2),(13,7,4,3), (14,10,4,4),(15,11,5,1);
INSERT INTO mappings (id,station_id,track_id,station_order) VALUES (16,12,5,2),(17,10,5,3),(18,11,6,3),(19,8,6,2),(20,4,6,1),(21,2,7,1),(23,6,7,2),(24,11,7,3),(25,11,8,1),(26,3,8,2),(27,1,8,3),(28,1,9,1),(29,11,9,2),(30,5,9,3),(31,12,5,4),(32,11,5,5);

INSERT INTO areas (id,station_from_id,station_to_id,distance,track_id,direction) VALUES (1,11,8,0.146,1,1),(2,8,4,0.195,1,1),(3,11,4,0.341,1,1),(4,3,11,0.221,2,1),(5,11,4,0.341,2,1),(6,3,4,0.519,2,1),(7,11,6,0.077,3,1),(8,6,9,0.128,3,1),(9,9,2,0.132,3,1),(10,11,2,0.337,3,1),(11,1,3,0.074,4,1),(12,3,7,0.074,4,1),(13,7,10,0.213,4,1),(14,1,10,0.337,4,1),(15,11,12,0.147,5,1),(16,12,10,0.158,5,1),(17,11,10,0.305,5,1),(18,4,8,0.146,6,1),(19,8,11,0.195,6,1),(20,4,11,0.341,6,1),(21,2,6,0.2,7,1),(22,6,11,0.132,7,1),(23,2,11,0.337,7,1),(24,11,3,0.221,8,1),(25,3,1,0.07,8,1),(26,11,1,0.29,8,1),(27,1,11,0.29,9,1),(28,11,5,0.274,9,1),(29,1,5,0.582,9,1),(30,10,12,0.158,5,1),(35,12,11,0.147,5,1);

INSERT INTO schedules (id,arrival_time,departure_time,direction,train_status,station_id,train_id) VALUES (1,'07:10:00','07:10:00',1,'Delay',11,1),(2,'08:56:00','08:56:00',1,'Delay',8,1),(3,'09:10:00','09:10:00',0,'Delay',4,1),(7,'12:56:00','12:56:00',0,'Delay',2,3),(10,'11:56:00','11:56:00',1,'Delay',9,3),(9,'10:56:00','10:56:00',1,'Delay',6,3),(4,'12:10:00','12:10:00',1,'Delay',3,2),(5,'13:10:00','13:10:00',1,'Delay',11,2),(8,'09:56:00','09:56:00',1,'Delay',11,3),(6,'14:10:00','14:10:00',0,'Delay',4,2),(11,'09:56:00','09:56:00',1,'Delay',1,4),(12,'10:56:00','10:56:00',1,'Delay',3,4);
INSERT INTO schedules (id,arrival_time,departure_time,direction,train_status,station_id,train_id) VALUES (13,'11:56:00','11:56:00',1,'Delay',7,4),(14,'12:56:00','12:56:00',0,'Delay',10,4),(15,'13:10:00','13:10:00',1,'Delay',11,5),(16,'15:11:00','15:11:00',1,'Delay',12,5),(17,'15:25:00','15:25:00',1,'Delay',10,5),(23,'17:56:00','17:56:00',0,'Delay',11,7),(22,'16:56:00','16:56:00',1,'Delay',6,7),(20,'09:10:00','09:10:00',0,'Delay',11,6),(19,'08:56:00','08:56:00',1,'Delay',8,6),(18,'07:10:00','07:10:00',1,'Delay',4,6),(24,'09:10:00','09:10:00',1,'Delay',11,8),(21,'14:56:00','14:56:00',1,'Delay',2,7);
INSERT INTO schedules (id,arrival_time,departure_time,direction,train_status,station_id,train_id) VALUES (25,'10:10:00','10:10:00',0,'Delay',3,8),(26,'11:10:00','11:10:00',0,'Delay',1,8),(29,'12:56:00','12:56:00',0,'Delay',5,9),(28,'09:56:00','09:56:00',1,'Delay',11,9),(27,'08:56:00','08:56:00',1,'Delay',1,9),(32,'12:56:00','12:56:00',1,'Canceled',11,5),(31,'12:56:00','12:56:00',1,'Canceled',12,5),(30,'12:56:00','12:56:00',1,'Cancelled',10,5);


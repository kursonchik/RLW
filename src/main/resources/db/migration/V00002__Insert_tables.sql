INSERT INTO roles (id, name) VALUES ('1', 'admin'), ('2', 'employee'), ('3', 'passenger');

INSERT INTO users (id, username, password, email) VALUES ('1', 'admin', 'admin', 'admin@gmail.com'), ('2', 'employee', 'employee', 'employee@gmail.com'), ('3', 'passenger', 'passenger', 'passenger@gmail.com');

INSERT INTO user_roles (user_id, role_id) VALUES ('1', '1'), ('2', '2'), ('3', '3');

INSERT INTO passengers (id, first_name, last_name, birth_date, passport_number, user_id) VALUES ('1', 'Ivan', 'Ivanov', '1975-01-01', '999999999', '3');

INSERT INTO tracks (id) VALUES ('1'), ('2'),('3'),('4'),('5');

INSERT INTO trains (id, name, seats, track_id) VALUES ('1', '687Б', '50', '1'),('2', '742Б', '60', '2'),('3', '712Б', '70', '3'),('4', '003Б', '80', '4'),('5', '632Б', '90', '5');

INSERT INTO trains(id, name, seats, track_id) VALUES ('2', 'Minsk — Viciebsk', '100', '2'), ('3', 'Homiel — Polack', '100', '3'), ('4', 'Minsk — Brest', '50', '4'), ('5', 'Minsk — Mahiliou', '50', '5');
UPDATE trains SET name = 'Minsk — Hrodna' WHERE (`id` = '1');
INSERT INTO trains (id, name, seats, track_id) VALUES ('6', 'Minsk — Homiel', '100', '6');
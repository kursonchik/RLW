drop database if exists railwaybooking;
create SCHEMA railwaybooking;
use railwaybooking;

create TABLE IF NOT EXISTS users
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(145) NOT NULL,
  email VARCHAR(50) UNIQUE NOT NULL,
  PRIMARY KEY (`id`),
  );

create TABLE IF NOT EXISTS user_role
    user_id INT NOT NULL PRIMARY KEY,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
    ON delete CASCADE
    ON update CASCADE,
    ADD CONSTRAINT role_id
    FOREIGN KEY (role_id)
    REFERENCES roles (id)
    ON delete CASCADE
    ON update CASCADE;

create TABLE IF NOT EXISTS passengers (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  birth_date DATE NOT NULL,
  passport_number INT NULL,
  user_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX user_id_idx (user_id ASC) VISIBLE,
  CONSTRAINT userID
    FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON delete CASCADE
    ON update CASCADE);

create TABLE IF NOT EXISTS tracks (
  id INT NOT NULL,
  PRIMARY KEY (id));


create TABLE IF NOT EXISTS trains (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  seats INT NULL,
  track_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX track_id_idx (track_id ASC) VISIBLE,
  CONSTRAINT track_id
    FOREIGN KEY (track_id)
    REFERENCES tracks (id)
    ON delete CASCADE
    ON update CASCADE);

create TABLE IF NOT EXISTS tickets (
  id INT NOT NULL AUTO_INCREMENT,
  number VARCHAR(20) NOT NULL,
  passenger_id INT NOT NULL,
  departure_station VARCHAR(45) NOT NULL,
  arrival_station VARCHAR(45) NOT NULL,
  departure_time TIME NOT NULL,
  arrival_time TIME NOT NULL,
  date DATE NOT NULL,
  price DOUBLE NOT NULL,
  PRIMARY KEY (id),
  INDEX passenger_id_idx (passenger_id ASC) VISIBLE,
  CONSTRAINT passenger_id
    FOREIGN KEY (passenger_id)
    REFERENCES passengers (id)
    ON delete CASCADE
    ON update CASCADE);


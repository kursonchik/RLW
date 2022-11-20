drop database if exists railwaybooking;
create SCHEMA IF NOT EXISTS railwaybooking;
use railwaybooking;

create TABLE IF NOT EXISTS users (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(145) NOT NULL,
  email VARCHAR(50) UNIQUE NOT NULL);

create TABLE IF NOT EXISTS roles (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));

create TABLE IF NOT EXISTS user_roles(
    user_id INT NOT NULL PRIMARY KEY,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
    ON delete CASCADE
    ON update CASCADE,
    CONSTRAINT role_id
    FOREIGN KEY (role_id)
    REFERENCES roles (id)
    ON delete CASCADE
    ON update CASCADE
);

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
    
    create TABLE IF NOT EXISTS stations (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  is_endpoint TINYINT(1) NULL,
  is_breakpoint TINYINT(1) NULL,
  PRIMARY KEY (id));

create TABLE IF NOT EXISTS schedules (
  id INT NOT NULL AUTO_INCREMENT,
  station_id INT NOT NULL,
  train_id INT NOT NULL,
  train_status VARCHAR(45) NULL,
  arrival_time TIME NOT NULL,
  departure_time TIME NOT NULL,
  direction TINYINT(1) NULL,
  PRIMARY KEY (id),
  CONSTRAINT station_id
    FOREIGN KEY (station_id)
    REFERENCES stations (id)
     ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT train_id
    FOREIGN KEY (train_id)
    REFERENCES trains (id)
     ON DELETE CASCADE
    ON UPDATE CASCADE);

    create TABLE IF NOT EXISTS tracks (
  id INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id));

create TABLE IF NOT EXISTS mappings (
  id INT NOT NULL AUTO_INCREMENT,
  station_id INT NOT NULL,
  track_id INT NOT NULL,
  station_order INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT stationID
    FOREIGN KEY (station_id)
    REFERENCES stations (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT trackID
    FOREIGN KEY (track_id)
    REFERENCES tracks (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

    create TABLE IF NOT EXISTS areas (
  id INT NOT NULL AUTO_INCREMENT,
  station_from_id INT NOT NULL,
  station_to_id INT NOT NULL,
  distance DOUBLE NOT NULL,
  track_id INT NOT NULL,
  direction TINYINT(1) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT station_from_id
    FOREIGN KEY (station_from_id)
    REFERENCES stations (id)
    ON DELETE CASCADE
  ON UPDATE CASCADE,
  CONSTRAINT tracks_ID
  FOREIGN KEY (track_id)
  REFERENCES tracks (id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  CONSTRAINT station_to_id
    FOREIGN KEY (station_to_id)
    REFERENCES stations (id)
    ON DELETE CASCADE
  ON UPDATE CASCADE);

create TABLE IF NOT EXISTS train_tickets(
   train_id INT NOT NULL PRIMARY KEY,
    ticket_id INT NOT NULL,
    FOREIGN KEY (train_id) REFERENCES trains (id)
    ON delete CASCADE
    ON update CASCADE,
    CONSTRAINT ticket_id
    FOREIGN KEY (ticket_id)
    REFERENCES tickets (id)
    ON delete CASCADE
    ON update CASCADE
);
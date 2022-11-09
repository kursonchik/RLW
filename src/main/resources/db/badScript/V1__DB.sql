drop database if exists railwaybooking;
create SCHEMA railwaybooking;
use railwaybooking;

create TABLE users (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(45) UNIQUE NOT NULL,
  password VARCHAR(45) NOT NULL DEFAULT '{noop}111111',
  email VARCHAR(45) UNIQUE NOT NULL,
  firstName VARCHAR(15) NOT NULL,
  lastName VARCHAR(15) NOT NULL,
  birthdate DATE NOT NULL,
  passportNumber INT NULL,
  roleId INT NULL,
  FOREIGN KEY (roleId) REFERENCES role (id)
  ON update CASCADE ON delete SET DEFAULT
  );

  CREATE TABLE roles (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));

  ALTER TABLE users
CHANGE COLUMN `role_id` `role_id` INT NOT NULL ,
ADD INDEX `role_id_idx` (`role_id` ASC) VISIBLE;
;
ALTER TABLE `railwaybooking`.`users`
ADD CONSTRAINT `role_id`
  FOREIGN KEY (`role_id`)
  REFERENCES `railwaybooking`.`roles` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  ALTER TABLE `railwaybooking`.`users`
DROP FOREIGN KEY `role_id`;
ALTER TABLE `railwaybooking`.`users`
ADD CONSTRAINT `role_id`
  FOREIGN KEY (`role_id`)
  REFERENCES `railwaybooking`.`roles` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

  ALTER TABLE `railwaybooking`.`users`
DROP FOREIGN KEY `role_id`;
ALTER TABLE `railwaybooking`.`users`
DROP COLUMN `role_id`,
DROP COLUMN `passport_number`,
DROP COLUMN `birth_date`,
DROP COLUMN `last_name`,
DROP COLUMN `first_name`,
DROP INDEX `role_id_idx` ;
;

CREATE TABLE `railwaybooking`.`user_role` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `userID`
    FOREIGN KEY (`user_id`)
    REFERENCES `railwaybooking`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `role_ID`
    REFERENCES `railwaybooking`.`roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `railwaybooking`.`passengers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birth_date` DATE NOT NULL,
  `passport_number` INT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `railwaybooking`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


  CREATE TABLE `railwaybooking`.`tickets` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(45) NULL,
  `passenger_id` INT NOT NULL,
  `departure_station` VARCHAR(45) NOT NULL,
  `arrival_station` VARCHAR(45) NOT NULL,
  `departure_time` TIME NULL,
  `arrival_time` TIME NULL,
  `date` DATE NULL,
  `price` DOUBLE NULL,
  PRIMARY KEY (`id`),
  INDEX `passenger_id_idx` (`passenger_id` ASC) VISIBLE,
  CONSTRAINT `passenger_id`
    FOREIGN KEY (`passenger_id`)
    REFERENCES `railwaybooking`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `railwaybooking`.`trains` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `seats` INT NULL,
  `root_id` INT NULL,
  PRIMARY KEY (`id`));

  ALTER TABLE `railwaybooking`.`trains`
ADD INDEX `roots_id_idx` (`root_id` ASC) VISIBLE;
;
ALTER TABLE `railwaybooking`.`trains`
ADD CONSTRAINT `roots_id`
  FOREIGN KEY (`root_id`)
  REFERENCES `railwaybooking`.`roots` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

CREATE TABLE `railwaybooking`.`stations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `is_endpoint` TINYINT(1) NULL,
  `is_breakpoint` TINYINT(1) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `railwaybooking`.`shedules` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `station_id` INT NOT NULL,
  `train_id` INT NOT NULL,
  `train_status` VARCHAR(45) NULL,
  `arrival_time` TIME NOT NULL,
  `departure_time` TIME NOT NULL,
  `direction` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `station_id_idx` (`station_id` ASC) VISIBLE,
  INDEX `train_id_idx` (`train_id` ASC) VISIBLE,
  CONSTRAINT `station_id`
    FOREIGN KEY (`station_id`)
    REFERENCES `railwaybooking`.`stations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `train_id`
    FOREIGN KEY (`train_id`)
    REFERENCES `railwaybooking`.`trains` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    CREATE TABLE `railwaybooking`.`roots` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`));

CREATE TABLE `railwaybooking`.`mappings` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `station_id` INT NOT NULL,
  `root_id` INT NOT NULL,
  `station_order` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `station_id_idx` (`station_id` ASC) VISIBLE,
  INDEX `root_id_idx` (`root_id` ASC) VISIBLE,
  CONSTRAINT `station_id`
    FOREIGN KEY (`station_id`)
    REFERENCES `railwaybooking`.`stations` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `root_id`
    FOREIGN KEY (`root_id`)
    REFERENCES `railwaybooking`.`roots` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

    CREATE TABLE `railwaybooking`.`areas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `station_from_id` INT NOT NULL,
  `station_to_id` INT NOT NULL,
  `distance` DOUBLE NOT NULL,
  `root_id` INT NOT NULL,
  `direction` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `station_from_id_idx` (`station_from_id` ASC) VISIBLE,
  INDEX `station_to_id_idx` (`station_to_id` ASC) VISIBLE,
  CONSTRAINT `station_from_id`
    FOREIGN KEY (`station_from_id`)
    REFERENCES `railwaybooking`.`stations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `station_to_id`
    FOREIGN KEY (`station_to_id`)
    REFERENCES `railwaybooking`.`stations` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

    ALTER TABLE `railwaybooking`.`areas`
ADD CONSTRAINT `rootID`
  FOREIGN KEY (`root_id`)
  REFERENCES `railwaybooking`.`roots` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

CREATE TABLE `railwaybooking`.`train_tickets` (
  `train_id` INT NULL,
  `ticket_id` INT NULL,
  INDEX `train_id_idx` (`train_id` ASC) VISIBLE,
  INDEX `ticket_id_idx` (`ticket_id` ASC) VISIBLE,
  CONSTRAINT `trainID`
    FOREIGN KEY (`train_id`)
    REFERENCES `railwaybooking`.`trains` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `ticketID`
    FOREIGN KEY (`ticket_id`)
    REFERENCES `railwaybooking`.`tickets` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);




drop database if exists railwaybooking;
create SCHEMA railwaybooking;
use railwaybooking;

create TABLE IF NOT EXISTS user (
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

  CREATE TABLE `railwaybooking`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

  ALTER TABLE `railwaybooking`.`users`
CHANGE COLUMN `role_id` `role_id` INT NOT NULL ,
ADD INDEX `role_id_idx` (`role_id` ASC) VISIBLE;
;
ALTER TABLE `railwaybooking`.`users`
ADD CONSTRAINT `role_id`
  FOREIGN KEY (`role_id`)
  REFERENCES `railwaybooking`.`roles` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

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

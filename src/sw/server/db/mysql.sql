SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `larskrid_sw` ;
CREATE SCHEMA IF NOT EXISTS `larskrid_sw` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `larskrid_sw` ;

-- -----------------------------------------------------
-- Table `larskrid_sw`.`Producer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `larskrid_sw`.`Producer` ;

CREATE  TABLE IF NOT EXISTS `larskrid_sw`.`Producer` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `producerId` INT NOT NULL ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `timeCreated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `isAdmin` TINYINT(1) NOT NULL DEFAULT FALSE ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `username_UNIQUE` ON `larskrid_sw`.`Producer` (`username` ASC) ;

CREATE UNIQUE INDEX `producerId_UNIQUE` ON `larskrid_sw`.`Producer` (`producerId` ASC) ;


-- -----------------------------------------------------
-- Table `larskrid_sw`.`Contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `larskrid_sw`.`Contact` ;

CREATE  TABLE IF NOT EXISTS `larskrid_sw`.`Contact` (
  `id` INT NOT NULL ,
  `producerId` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  `phoneSms` VARCHAR(45) NOT NULL ,
  `phoneCall` VARCHAR(45) NOT NULL ,
  `emailAlert` TINYINT(1) NOT NULL ,
  `callAlert` TINYINT(1) NOT NULL ,
  `smsAlert` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_Contact_Producer1`
    FOREIGN KEY (`producerId` )
    REFERENCES `larskrid_sw`.`Producer` (`producerId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `producerId_IDX` ON `larskrid_sw`.`Contact` (`producerId` ASC) ;


-- -----------------------------------------------------
-- Table `larskrid_sw`.`Sheep`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `larskrid_sw`.`Sheep` ;

CREATE  TABLE IF NOT EXISTS `larskrid_sw`.`Sheep` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `producerId` INT NOT NULL ,
  `sheepId` INT NOT NULL ,
  `rfid` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `dateOfBirth` TIMESTAMP NULL ,
  `timeOfDeath` TIMESTAMP NULL ,
  `weight` INT NOT NULL ,
  `notes` TEXT NOT NULL ,
  `attacked` TINYINT(1) NOT NULL DEFAULT FALSE ,
  `timeAdded` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_Sheep_User1`
    FOREIGN KEY (`producerId` )
    REFERENCES `larskrid_sw`.`Producer` (`producerId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sheep_Contact1`
    FOREIGN KEY (`producerId` )
    REFERENCES `larskrid_sw`.`Contact` (`producerId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `rfid_UNIQUE` ON `larskrid_sw`.`Sheep` (`rfid` ASC) ;

CREATE INDEX `fk_Sheep_User_IDX` ON `larskrid_sw`.`Sheep` (`producerId` ASC) ;


-- -----------------------------------------------------
-- Table `larskrid_sw`.`Event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `larskrid_sw`.`Event` ;

CREATE  TABLE IF NOT EXISTS `larskrid_sw`.`Event` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `rfid` INT NOT NULL ,
  `messageType` INT NOT NULL ,
  `timeSent` TIMESTAMP NULL ,
  `timeReceived` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `longitude` DOUBLE NOT NULL ,
  `latitude` DOUBLE NOT NULL ,
  `pulse` INT NOT NULL ,
  `temperature` DOUBLE NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_Message_Sheep`
    FOREIGN KEY (`rfid` )
    REFERENCES `larskrid_sw`.`Sheep` (`rfid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Message_Sheep_IDX` ON `larskrid_sw`.`Event` (`rfid` ASC) ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
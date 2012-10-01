SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `sheepwatch` ;
CREATE SCHEMA IF NOT EXISTS `sheepwatch` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `sheepwatch` ;

-- -----------------------------------------------------
-- Table `sheepwatch`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sheepwatch`.`User` ;

CREATE  TABLE IF NOT EXISTS `sheepwatch`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `producerId` INT NOT NULL ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `timeCreated` TIMESTAMP NOT NULL ,
  `isAdmin` TINYINT(1) NULL DEFAULT FALSE ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `username_UNIQUE` ON `sheepwatch`.`User` (`username` ASC) ;

CREATE UNIQUE INDEX `producerId_UNIQUE` ON `sheepwatch`.`User` (`producerId` ASC) ;


-- -----------------------------------------------------
-- Table `sheepwatch`.`Contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sheepwatch`.`Contact` ;

CREATE  TABLE IF NOT EXISTS `sheepwatch`.`Contact` (
  `id` INT NOT NULL ,
  `producerId` INT NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `email` VARCHAR(45) NULL ,
  `phoneSms` VARCHAR(45) NULL ,
  `phoneCall` VARCHAR(45) NULL ,
  `emailAlert` TINYINT(1) NULL ,
  `callAlert` TINYINT(1) NULL ,
  `smsAlert` TINYINT(1) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

CREATE INDEX `producerId_IDX` ON `sheepwatch`.`Contact` (`producerId` ASC) ;


-- -----------------------------------------------------
-- Table `sheepwatch`.`Sheep`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sheepwatch`.`Sheep` ;

CREATE  TABLE IF NOT EXISTS `sheepwatch`.`Sheep` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `producerId` INT NOT NULL ,
  `sheepId` INT NOT NULL ,
  `rfid` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `dateOfBirth` TIMESTAMP NULL ,
  `weight` INT NOT NULL ,
  `notes` TEXT NOT NULL ,
  `attacked` TINYINT(1) NOT NULL DEFAULT FALSE ,
  `timeOfDeath` TIMESTAMP NULL ,
  `timeAdded` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_Sheep_User1`
    FOREIGN KEY (`producerId` )
    REFERENCES `sheepwatch`.`User` (`producerId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sheep_Contact1`
    FOREIGN KEY (`producerId` )
    REFERENCES `sheepwatch`.`Contact` (`producerId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `rfid_UNIQUE` ON `sheepwatch`.`Sheep` (`rfid` ASC) ;

CREATE INDEX `fk_Sheep_User_IDX` ON `sheepwatch`.`Sheep` (`producerId` ASC) ;


-- -----------------------------------------------------
-- Table `sheepwatch`.`Message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sheepwatch`.`Message` ;

CREATE  TABLE IF NOT EXISTS `sheepwatch`.`Message` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `rfid` INT NOT NULL ,
  `messageType` INT NOT NULL ,
  `timeSent` TIMESTAMP NULL ,
  `timeReceived` TIMESTAMP NULL ,
  `longitude` DOUBLE NOT NULL ,
  `latitude` DOUBLE NOT NULL ,
  `pulse` INT NOT NULL ,
  `temperature` DOUBLE NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_Message_Sheep`
    FOREIGN KEY (`rfid` )
    REFERENCES `sheepwatch`.`Sheep` (`rfid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Message_Sheep_IDX` ON `sheepwatch`.`Message` (`rfid` ASC) ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

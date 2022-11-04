-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema orders
-- -----------------------------------------------------

DROP SCHEMA IF EXISTS `orders`;

-- -----------------------------------------------------
-- Schema orders
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `orders` DEFAULT CHARACTER SET utf8 ;
USE `orders` ;

-- -----------------------------------------------------
-- Table `orders`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orders`.`user` (
                                             `id` INT NOT NULL AUTO_INCREMENT,
                                             `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(60) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `name` VARCHAR(45) NULL,
    `lastname` VARCHAR(60) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `alias_UNIQUE` (`username` ASC) VISIBLE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orders`.`order_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orders`.`order_status` (
                                                     `id` INT NOT NULL AUTO_INCREMENT,
                                                     `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orders`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orders`.`order` (
                                              `id` INT NOT NULL AUTO_INCREMENT,
                                              `user_id` INT NOT NULL,
                                              `order_status_id` INT NOT NULL,
                                              PRIMARY KEY (`id`),
    INDEX `fk_orders_user_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_order_order_status1_idx` (`order_status_id` ASC) VISIBLE,
    CONSTRAINT `fk_orders_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `orders`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_order_order_status1`
    FOREIGN KEY (`order_status_id`)
    REFERENCES `orders`.`order_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `orders`.`order_tracking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `orders`.`order_tracking` (
                                                       `id` INT NOT NULL AUTO_INCREMENT,
                                                       `description` VARCHAR(250) NOT NULL,
    `date` DATETIME NOT NULL,
    `order_id` INT NOT NULL,
    `order_status_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_order_tracking_orders1_idx` (`order_id` ASC) VISIBLE,
    INDEX `fk_order_tracking_order_status1_idx` (`order_status_id` ASC) VISIBLE,
    CONSTRAINT `fk_order_tracking_orders1`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders`.`order` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_order_tracking_order_status1`
    FOREIGN KEY (`order_status_id`)
    REFERENCES `orders`.`order_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

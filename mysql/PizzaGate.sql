-- MySQL Script generated by MySQL Workbench
-- Mon Jun 22 23:04:36 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema eif209_2001_p02
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `eif209_2001_p02` ;

-- -----------------------------------------------------
-- Schema eif209_2001_p02
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `eif209_2001_p02` DEFAULT CHARACTER SET utf8 ;
USE `eif209_2001_p02` ;

-- -----------------------------------------------------
-- Table `eif209_2001_p02`.`Products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p02`.`Products` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p02`.`Products` (
  `idProduct` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(7) NOT NULL,
  `size` VARCHAR(45) NOT NULL,
  `description` VARCHAR(60) NOT NULL,
  `price` DOUBLE NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `imgpath` VARCHAR(45) NULL,
  PRIMARY KEY (`idProduct`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p02`.`Clients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p02`.`Clients` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p02`.`Clients` (
  `idClient` INT NOT NULL AUTO_INCREMENT,
  `idCard` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(8) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idClient`),
  UNIQUE INDEX `idCard_UNIQUE` (`idCard` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p02`.`Admins`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p02`.`Admins` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p02`.`Admins` (
  `idAdmin` INT NOT NULL AUTO_INCREMENT,
  `idCard` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`idAdmin`),
  UNIQUE INDEX `idCard_UNIQUE` (`idCard` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p02`.`Clients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p02`.`Clients` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p02`.`Clients` (
  `idClient` INT NOT NULL AUTO_INCREMENT,
  `idCard` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(8) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idClient`),
  UNIQUE INDEX `idCard_UNIQUE` (`idCard` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p02`.`Status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p02`.`Status` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p02`.`Status` (
  `idStatus` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`idStatus`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p02`.`Invoices`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p02`.`Invoices` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p02`.`Invoices` (
  `idInvoice` INT NOT NULL AUTO_INCREMENT,
  `idClient` INT NOT NULL,
  `idStatus` INT NOT NULL,
  `date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idInvoice`),
  INDEX `fk_Invoices_Client1_idx` (`idClient` ASC) VISIBLE,
  INDEX `fk_Invoices_Status1_idx` (`idStatus` ASC) VISIBLE,
  CONSTRAINT `fk_Invoices_Client1`
    FOREIGN KEY (`idClient`)
    REFERENCES `eif209_2001_p02`.`Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Invoices_Status1`
    FOREIGN KEY (`idStatus`)
    REFERENCES `eif209_2001_p02`.`Status` (`idStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p02`.`Details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p02`.`Details` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p02`.`Details` (
  `idInvoice` INT NOT NULL,
  `idProduct` INT NOT NULL,
  `stock` INT NULL,
  PRIMARY KEY (`idInvoice`, `idProduct`),
  INDEX `fk_Details_Invoices1_idx` (`idInvoice` ASC) VISIBLE,
  INDEX `fk_Details_Products1_idx` (`idProduct` ASC) VISIBLE,
  CONSTRAINT `fk_Details_Invoices1`
    FOREIGN KEY (`idInvoice`)
    REFERENCES `eif209_2001_p02`.`Invoices` (`idInvoice`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Details_Products1`
    FOREIGN KEY (`idProduct`)
    REFERENCES `eif209_2001_p02`.`Products` (`idProduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p02`.`Ingredients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p02`.`Ingredients` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p02`.`Ingredients` (
  `idIngredient` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(3) NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idIngredient`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p02`.`Feedbacks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p02`.`Feedbacks` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p02`.`Feedbacks` (
  `idFeedback` INT NOT NULL AUTO_INCREMENT,
  `idClient` INT NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`idFeedback`),
  INDEX `fk_Feedbacks_Client1_idx` (`idClient` ASC) VISIBLE,
  CONSTRAINT `fk_Feedbacks_Client1`
    FOREIGN KEY (`idClient`)
    REFERENCES `eif209_2001_p02`.`Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `eif209_2001_p02`.`Recipe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `eif209_2001_p02`.`Recipe` ;

CREATE TABLE IF NOT EXISTS `eif209_2001_p02`.`Recipe` (
  `idProduct` INT NOT NULL,
  `idIngredient` INT NOT NULL,
  PRIMARY KEY (`idProduct`, `idIngredient`),
  INDEX `fk_Products_has_Ingredients_Ingredients1_idx` (`idIngredient` ASC) VISIBLE,
  INDEX `fk_Products_has_Ingredients_Products1_idx` (`idProduct` ASC) VISIBLE,
  CONSTRAINT `fk_Products_has_Ingredients_Products1`
    FOREIGN KEY (`idProduct`)
    REFERENCES `eif209_2001_p02`.`Products` (`idProduct`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Products_has_Ingredients_Ingredients1`
    FOREIGN KEY (`idIngredient`)
    REFERENCES `eif209_2001_p02`.`Ingredients` (`idIngredient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `eif209_2001_p02`.`Products`
-- -----------------------------------------------------
START TRANSACTION;
USE `eif209_2001_p02`;
INSERT INTO `eif209_2001_p02`.`Products` (`idProduct`, `code`, `size`, `description`, `price`, `type`, `imgpath`) VALUES (1, 'PEP8', 'Pequeño', 'Pizza de Peperronie', 4000, 'Pizza', 'products-images/Pizza/Peperronie.jpg');
INSERT INTO `eif209_2001_p02`.`Products` (`idProduct`, `code`, `size`, `description`, `price`, `type`, `imgpath`) VALUES (2, 'JAQP16', 'Grande', 'Pizza de Jamón y Queso', 7500.5, 'Pizza', 'products-images/Pizza/Ham&Cheesse.jpg');
INSERT INTO `eif209_2001_p02`.`Products` (`idProduct`, `code`, `size`, `description`, `price`, `type`, `imgpath`) VALUES (3, 'SUPP16', 'Grande', 'Pizza Suprema', 9000.5, 'Pizza', 'products-images/Pizza/Supreme.jpg');
INSERT INTO `eif209_2001_p02`.`Products` (`idProduct`, `code`, `size`, `description`, `price`, `type`, `imgpath`) VALUES (4, 'PES1', 'Pequeño', 'Pesi', 2000, 'Bebida', 'products-images/Beverages/pepsi.jpg');
INSERT INTO `eif209_2001_p02`.`Products` (`idProduct`, `code`, `size`, `description`, `price`, `type`, `imgpath`) VALUES (5, 'FAN2', 'Grande', 'Fanta Naranja', 1000, 'Bebida', 'products-images/Beverages/fanta.jpg');
INSERT INTO `eif209_2001_p02`.`Products` (`idProduct`, `code`, `size`, `description`, `price`, `type`, `imgpath`) VALUES (6, 'PIEAPEQ', 'Pequeño', 'Pie de Arandanos', 3500, 'Postre', 'products-images/Desserts/BlueBerryPie.jpg');
INSERT INTO `eif209_2001_p02`.`Products` (`idProduct`, `code`, `size`, `description`, `price`, `type`, `imgpath`) VALUES (7, 'TRELPEQ', 'Pequeño', 'Tres Leches', 5000, 'Postre', 'products-images/Desserts/ThreeMilks.jpg');
INSERT INTO `eif209_2001_p02`.`Products` (`idProduct`, `code`, `size`, `description`, `price`, `type`, `imgpath`) VALUES (8, 'PEP16', 'Grande', 'Pizza de Peperronie', 8000.5, 'Pizza', 'products-images/Pizza/Peperronie.jpg');
INSERT INTO `eif209_2001_p02`.`Products` (`idProduct`, `code`, `size`, `description`, `price`, `type`, `imgpath`) VALUES (9, 'JAQP8', 'Pequeño', 'Pizza de Jamón y Queso', 3500, 'Pizza', 'products-images/Pizza/Ham&Cheesse.jpg');
INSERT INTO `eif209_2001_p02`.`Products` (`idProduct`, `code`, `size`, `description`, `price`, `type`, `imgpath`) VALUES (10, 'PEP12', 'Mediano', 'Pizza de Peperronie', 4800.5, 'Pizza', 'products-images/Pizza/Peperronie.jpg');
INSERT INTO `eif209_2001_p02`.`Products` (`idProduct`, `code`, `size`, `description`, `price`, `type`, `imgpath`) VALUES (11, 'SUPP8', 'Pequeño', 'Pizza Suprema', 4000, 'Pizza', 'products-images/Pizza/Supreme.jpg');
INSERT INTO `eif209_2001_p02`.`Products` (`idProduct`, `code`, `size`, `description`, `price`, `type`, `imgpath`) VALUES (12, 'SUPP12', 'Mediano', 'Pizza Suprema', 6500.5, 'Pizza', 'products-images/Pizza/Supreme.jpg');
INSERT INTO `eif209_2001_p02`.`Products` (`idProduct`, `code`, `size`, `description`, `price`, `type`, `imgpath`) VALUES (13, 'JAQP12', 'Mediano', 'Pizza de Jamón y Queso', 5500, 'Pizza', 'products-images/Pizza/Ham&Cheesse.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eif209_2001_p02`.`Admins`
-- -----------------------------------------------------
START TRANSACTION;
USE `eif209_2001_p02`;
INSERT INTO `eif209_2001_p02`.`Admins` (`idAdmin`, `idCard`, `name`, `lastname`, `email`, `password`) VALUES (DEFAULT, '504250570', 'Erick ', 'Badilla Gonzalez', 'erickbadilla99@gmail.com', '1234567b');
INSERT INTO `eif209_2001_p02`.`Admins` (`idAdmin`, `idCard`, `name`, `lastname`, `email`, `password`) VALUES (DEFAULT, '504250413', 'Carlos', 'Zhou Zheng', 'carloszhou0@gmail.com', '1234567b');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eif209_2001_p02`.`Clients`
-- -----------------------------------------------------
START TRANSACTION;
USE `eif209_2001_p02`;
INSERT INTO `eif209_2001_p02`.`Clients` (`idClient`, `idCard`, `name`, `lastname`, `address`, `phone`, `email`, `password`) VALUES (DEFAULT, '185045679', 'Ignacio', 'Monge Salazar', 'Montes de Oca Uruca', '60405010', 'nacho@gmail.com', 'qwer');
INSERT INTO `eif209_2001_p02`.`Clients` (`idClient`, `idCard`, `name`, `lastname`, `address`, `phone`, `email`, `password`) VALUES (DEFAULT, '150305402', 'Nicole ', 'Cannet Porras ', 'San Antonio de Belen ', '50501012', 'nicole@email.com', 'qwer');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eif209_2001_p02`.`Status`
-- -----------------------------------------------------
START TRANSACTION;
USE `eif209_2001_p02`;
INSERT INTO `eif209_2001_p02`.`Status` (`idStatus`, `description`) VALUES (DEFAULT, 'Se esta preparando la orden');
INSERT INTO `eif209_2001_p02`.`Status` (`idStatus`, `description`) VALUES (DEFAULT, 'La orden esta llegando a su destino');
INSERT INTO `eif209_2001_p02`.`Status` (`idStatus`, `description`) VALUES (DEFAULT, 'Se finalizo la orden');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eif209_2001_p02`.`Invoices`
-- -----------------------------------------------------
START TRANSACTION;
USE `eif209_2001_p02`;
INSERT INTO `eif209_2001_p02`.`Invoices` (`idClient`, `idStatus`) VALUES (1, 1);
INSERT INTO `eif209_2001_p02`.`Invoices` (`idClient`, `idStatus`) VALUES (2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `eif209_2001_p02`.`Details`
-- -----------------------------------------------------
START TRANSACTION;
USE `eif209_2001_p02`;
INSERT INTO `eif209_2001_p02`.`Details` (`idInvoice`, `idProduct`, `stock`) VALUES (1, 1, 5);
INSERT INTO `eif209_2001_p02`.`Details` (`idInvoice`, `idProduct`, `stock`) VALUES (1, 2, 4);
INSERT INTO `eif209_2001_p02`.`Details` (`idInvoice`, `idProduct`, `stock`) VALUES (1, 3, 3);
INSERT INTO `eif209_2001_p02`.`Details` (`idInvoice`, `idProduct`, `stock`) VALUES (2, 1, 2);
INSERT INTO `eif209_2001_p02`.`Details` (`idInvoice`, `idProduct`, `stock`) VALUES (2, 2, 1);
INSERT INTO `eif209_2001_p02`.`Details` (`idInvoice`, `idProduct`, `stock`) VALUES (2, 3, 5);
INSERT INTO `eif209_2001_p02`.`Details` (`idInvoice`, `idProduct`, `stock`) VALUES (2, 4, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `eif209_2001_p02`.`Ingredients`
-- -----------------------------------------------------
START TRANSACTION;
USE `eif209_2001_p02`;
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (1, 'PEP', 'Pepperoni');
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (2, 'JAP', 'Jamón de Pollo');
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (3, 'QUM', 'Queso Mozzarella');
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (4, 'HAR', 'Harina');
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (5, 'ALB', 'Albahaca');
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (6, 'TOM', 'Salsa de tomate');
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (7, 'ORE', 'Orégano');
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (8, 'LEV', 'Levadura');
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (9, 'AGU', 'Agua');
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (10, 'SAL', 'Sal');
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (11, 'HON', 'Hongos');
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (12, 'SCP', 'Salchichas de Pollo');
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (13, 'SCC', 'Salchichas de Carne');
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (14, 'JAC', 'Jamón de Carne');
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (15, 'ACE', 'Aceitunas');
INSERT INTO `eif209_2001_p02`.`Ingredients` (`idIngredient`, `code`, `name`) VALUES (16, 'BAC', 'Bacon');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eif209_2001_p02`.`Feedbacks`
-- -----------------------------------------------------
START TRANSACTION;
USE `eif209_2001_p02`;
INSERT INTO `eif209_2001_p02`.`Feedbacks` (`idFeedback`, `idClient`, `description`) VALUES (DEFAULT, 1, 'Meh');
INSERT INTO `eif209_2001_p02`.`Feedbacks` (`idFeedback`, `idClient`, `description`) VALUES (DEFAULT, 1, 'Casi');
INSERT INTO `eif209_2001_p02`.`Feedbacks` (`idFeedback`, `idClient`, `description`) VALUES (DEFAULT, 1, 'Bad');
INSERT INTO `eif209_2001_p02`.`Feedbacks` (`idFeedback`, `idClient`, `description`) VALUES (DEFAULT, 2, 'Not good');
INSERT INTO `eif209_2001_p02`.`Feedbacks` (`idFeedback`, `idClient`, `description`) VALUES (DEFAULT, 2, 'Too bad');

COMMIT;


-- -----------------------------------------------------
-- Data for table `eif209_2001_p02`.`Recipe`
-- -----------------------------------------------------
START TRANSACTION;
USE `eif209_2001_p02`;
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (1, 1);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (1, 3);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (1, 5);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (1, 6);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (2, 14);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (2, 3);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (2, 5);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (2, 6);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (2, 7);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (3, 1);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (3, 2);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (3, 3);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (3, 5);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (3, 6);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (3, 7);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (3, 11);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (3, 12);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (3, 15);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (3, 16);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (8, 1);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (8, 3);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (8, 5);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (8, 6);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (9, 14);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (9, 3);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (9, 5);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (9, 6);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (9, 7);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (10, 1);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (10, 3);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (10, 5);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (10, 6);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (11, 1);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (11, 2);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (11, 3);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (11, 5);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (11, 6);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (11, 7);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (11, 11);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (11, 12);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (11, 15);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (11, 16);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (12, 1);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (12, 2);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (12, 3);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (12, 5);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (12, 6);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (12, 7);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (12, 11);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (12, 12);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (12, 15);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (12, 16);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (13, 14);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (13, 3);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (13, 5);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (13, 6);
INSERT INTO `eif209_2001_p02`.`Recipe` (`idProduct`, `idIngredient`) VALUES (13, 7);

COMMIT;


-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Proyectosdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Proyectosdb` ;

-- -----------------------------------------------------
-- Schema Proyectosdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Proyectosdb` DEFAULT CHARACTER SET utf8mb3 ;
SHOW WARNINGS;
USE `Proyectosdb` ;

-- -----------------------------------------------------
-- Table `Proyectosdb`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Proyectosdb`.`status` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Proyectosdb`.`status` (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `status_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;
CREATE UNIQUE INDEX `category_name_UNIQUE` ON `Proyectosdb`.`status` (`status_name` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Proyectosdb`.`technologies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Proyectosdb`.`technologies` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Proyectosdb`.`technologies` (
  `tech_id` INT NOT NULL AUTO_INCREMENT,
  `tech_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tech_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;
CREATE UNIQUE INDEX `category_name_UNIQUE` ON `Proyectosdb`.`technologies` (`tech_name` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Proyectosdb`.`developers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Proyectosdb`.`developers` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Proyectosdb`.`developers` (
  `dev_id` INT NOT NULL AUTO_INCREMENT,
  `devname` VARCHAR(20) NOT NULL,
  `dev_surname` CHAR(60) NOT NULL,
  `email` VARCHAR(90) NOT NULL,
  `linkedin_url` VARCHAR(90) NOT NULL,
  `github_url` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`dev_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;
CREATE UNIQUE INDEX `Username_UNIQUE` ON `Proyectosdb`.`developers` (`devname` ASC) VISIBLE;


SHOW WARNINGS;
CREATE UNIQUE INDEX `email_UNIQUE` ON `Proyectosdb`.`developers` (`email` ASC) VISIBLE;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table `Proyectosdb`.`projects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Proyectosdb`.`projects`;

SHOW WARNINGS;

CREATE TABLE IF NOT EXISTS `Proyectosdb`.`projects` (
  `project_id` INT NOT NULL AUTO_INCREMENT,
  `project_name` VARCHAR(150) NOT NULL,
  `description` VARCHAR(150) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `demo_url` VARCHAR(150) NOT NULL,
  `picture` VARCHAR(150) NOT NULL,
  `status_status_id` INT NOT NULL,
  PRIMARY KEY (`project_id`),
  CONSTRAINT `fk_projects_status`
    FOREIGN KEY (`status_status_id`)
    REFERENCES `Proyectosdb`.`status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3;

SHOW WARNINGS;

CREATE UNIQUE INDEX `project_name_UNIQUE`
  ON `Proyectosdb`.`projects` (`project_name` ASC) VISIBLE;

SHOW WARNINGS;


-- -----------------------------------------------------
-- Table `Proyectosdb`.`technologies_used_in_projects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Proyectosdb`.`technologies_used_in_projects` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Proyectosdb`.`technologies_used_in_projects` (
  `tecnologia_tecnologia_id` INT NOT NULL, 
  `projects_project_id` INT NOT NULL,
  PRIMARY KEY (`tecnologia_tecnologia_id`, `projects_project_id`),
  CONSTRAINT `fk_technologies_used_in_projects_technology`
    FOREIGN KEY (`tecnologia_tecnologia_id`)
    REFERENCES `Proyectosdb`.`technologies` (`tech_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_technologies_used_in_projects_project`
    FOREIGN KEY (`projects_project_id`)
    REFERENCES `Proyectosdb`.`projects` (`project_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;
CREATE INDEX `fk_Users_has_productos_productos1_idx` ON `Proyectosdb`.`technologies_used_in_projects` (`tecnologia_tecnologia_id` ASC) VISIBLE;

SHOW WARNINGS;
CREATE INDEX `fk_Users_has_productos_Users1_idx` ON `Proyectosdb`.`technologies_used_in_projects` (`projects_project_id` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Proyectosdb`.`developers_worked_on_projects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Proyectosdb`.`developers_worked_on_projects` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Proyectosdb`.`developers_worked_on_projects` (
  `developer_dev_id` INT NOT NULL, 
  `projects_project_id` INT NOT NULL,
  PRIMARY KEY (`developer_dev_id`, `projects_project_id`),
  CONSTRAINT `fk_developers_worked_in_projects_developer`
    FOREIGN KEY (`developer_dev_id`)
    REFERENCES `Proyectosdb`.`developers` (`dev_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_developers_worked_in_projects_project`
    FOREIGN KEY (`projects_project_id`)
    REFERENCES `Proyectosdb`.`projects` (`project_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;
CREATE INDEX `fk_Users_has_productos_productos1_idx` ON `Proyectosdb`.`developers_worked_on_projects` (`developer_dev_id` ASC) VISIBLE;

SHOW WARNINGS;
CREATE INDEX `fk_Users_has_productos_Users1_idx` ON `Proyectosdb`.`developers_worked_on_projects` (`projects_project_id` ASC) VISIBLE;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema university_se
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `university_se` ;

-- -----------------------------------------------------
-- Schema university_se
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `university_se` DEFAULT CHARACTER SET latin1 ;
USE `university_se` ;

-- -----------------------------------------------------
-- Table `university_se`.`academic_year`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`academic_year` ;

CREATE TABLE IF NOT EXISTS `university_se`.`academic_year` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `start_year` INT(11) NOT NULL,
  `end_year` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`chat_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`chat_group` ;

CREATE TABLE IF NOT EXISTS `university_se`.`chat_group` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`course_of_study`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`course_of_study` ;

CREATE TABLE IF NOT EXISTS `university_se`.`course_of_study` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `academic_year` INT(11) NOT NULL,
  INDEX `FK_course_year_idx` (`academic_year` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_course_year`
    FOREIGN KEY (`academic_year`)
    REFERENCES `university_se`.`academic_year` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`user_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`user_type` ;

CREATE TABLE IF NOT EXISTS `university_se`.`user_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`user` ;

CREATE TABLE IF NOT EXISTS `university_se`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `user_type` INT(11) NOT NULL,
  `course_of_study` INT(11) NULL DEFAULT NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `FK_user_type_idx` (`user_type` ASC),
  INDEX `FK_course_of_study_idx` (`course_of_study` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_course_of_study`
    FOREIGN KEY (`course_of_study`)
    REFERENCES `university_se`.`course_of_study` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_user_type`
    FOREIGN KEY (`user_type`)
    REFERENCES `university_se`.`user_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`chat_group_has_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`chat_group_has_user` ;

CREATE TABLE IF NOT EXISTS `university_se`.`chat_group_has_user` (
  `chat_group` INT(11) NOT NULL,
  `user` INT(11) NOT NULL,
  PRIMARY KEY (`chat_group`, `user`),
  INDEX `fk_chat_group_has_user_user1_idx` (`user` ASC),
  INDEX `fk_chat_group_has_user_chat_group1_idx` (`chat_group` ASC),
  CONSTRAINT `fk_chat_group_has_user_chat_group1`
    FOREIGN KEY (`chat_group`)
    REFERENCES `university_se`.`chat_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_group_has_user_user1`
    FOREIGN KEY (`user`)
    REFERENCES `university_se`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`classroom`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`classroom` ;

CREATE TABLE IF NOT EXISTS `university_se`.`classroom` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `latitude` DOUBLE NULL DEFAULT NULL,
  `longitude` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`subject` ;

CREATE TABLE IF NOT EXISTS `university_se`.`subject` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `cfu` INT(11) NOT NULL,
  `professor` INT(11) NOT NULL,
  `course_of_study` INT(11) NOT NULL,
  `year` INT(11) NOT NULL,
  INDEX `FK_professor_idx` (`professor` ASC),
  INDEX `FK_subject_course_of_study_idx` (`course_of_study` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_subject_course_of_study`
    FOREIGN KEY (`course_of_study`)
    REFERENCES `university_se`.`course_of_study` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_subject_professor`
    FOREIGN KEY (`professor`)
    REFERENCES `university_se`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`timeslot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`timeslot` ;

CREATE TABLE IF NOT EXISTS `university_se`.`timeslot` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`lesson`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`lesson` ;

CREATE TABLE IF NOT EXISTS `university_se`.`lesson` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `classroom` INT(11) NOT NULL,
  `timeslot` INT(11) NOT NULL,
  `subject` INT(11) NOT NULL,
  INDEX `FK_LESSON_CLASSROOM_idx` (`classroom` ASC),
  INDEX `FK_lesson_timeslot_idx` (`timeslot` ASC),
  INDEX `FK_lesson_subject_idx` (`subject` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_lesson_classroom`
    FOREIGN KEY (`classroom`)
    REFERENCES `university_se`.`classroom` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_lesson_subject`
    FOREIGN KEY (`subject`)
    REFERENCES `university_se`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_lesson_timeslot`
    FOREIGN KEY (`timeslot`)
    REFERENCES `university_se`.`timeslot` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`document`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`document` ;

CREATE TABLE IF NOT EXISTS `university_se`.`document` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `link` VARCHAR(100) NOT NULL,
  `note` VARCHAR(45) NULL DEFAULT NULL,
  `publish_date` DATETIME NOT NULL,
  `lesson` INT(11) NOT NULL,
  INDEX `FK_document_lesson_idx` (`lesson` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_document_lesson`
    FOREIGN KEY (`lesson`)
    REFERENCES `university_se`.`lesson` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`evaluation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`evaluation` ;

CREATE TABLE IF NOT EXISTS `university_se`.`evaluation` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `score` INT(11) NOT NULL,
  `note` VARCHAR(150) NULL DEFAULT NULL,
  `sender` INT(11) NOT NULL,
  `recipient` INT(11) NOT NULL,
  INDEX `FK_evaluation_sender_idx` (`sender` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_evaluation_sender`
    FOREIGN KEY (`sender`)
    REFERENCES `university_se`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`exam`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`exam` ;

CREATE TABLE IF NOT EXISTS `university_se`.`exam` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `subject` INT(11) NOT NULL,
  `classroom` INT(11) NOT NULL,
  `timeslot` INT(11) NOT NULL,
  INDEX `FK_exam_subject_idx` (`subject` ASC),
  INDEX `FK_exam_classroom_idx` (`classroom` ASC),
  INDEX `FK_exam__idx` (`timeslot` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_exam_`
    FOREIGN KEY (`timeslot`)
    REFERENCES `university_se`.`timeslot` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_exam_classroom`
    FOREIGN KEY (`classroom`)
    REFERENCES `university_se`.`classroom` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_exam_subject`
    FOREIGN KEY (`subject`)
    REFERENCES `university_se`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`exam_results`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`exam_results` ;

CREATE TABLE IF NOT EXISTS `university_se`.`exam_results` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `vote` INT(11) NOT NULL,
  `student` INT(11) NOT NULL,
  `date` DATETIME NOT NULL,
  `exam` INT(11) NOT NULL,
  INDEX `id_exam_student_idx` (`student` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `id_exam_student`
    FOREIGN KEY (`student`)
    REFERENCES `university_se`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`message` ;

CREATE TABLE IF NOT EXISTS `university_se`.`message` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `body` VARCHAR(45) NOT NULL,
  `send_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sender` INT(11) NOT NULL,
  `chat_group` INT(11) NOT NULL,
  INDEX `FK_message_user_idx` (`sender` ASC),
  INDEX `FK_message_chat_group_idx` (`chat_group` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_message_chat_group`
    FOREIGN KEY (`chat_group`)
    REFERENCES `university_se`.`chat_group` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_message_user`
    FOREIGN KEY (`sender`)
    REFERENCES `university_se`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`reporting_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`reporting_status` ;

CREATE TABLE IF NOT EXISTS `university_se`.`reporting_status` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`reporting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`reporting` ;

CREATE TABLE IF NOT EXISTS `university_se`.`reporting` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `item` INT(11) NOT NULL,
  `note` VARCHAR(150) NULL DEFAULT NULL,
  `last_modified` DATETIME NOT NULL,
  `done_by` INT(11) NOT NULL,
  `status` INT(11) NOT NULL,
  `classroom` INT(11) NOT NULL,
  INDEX `FK_reporting_user_idx` (`done_by` ASC),
  INDEX `FK_reporing_status_idx` (`status` ASC),
  INDEX `FK_reporing_classroom_idx` (`classroom` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_reporing_classroom`
    FOREIGN KEY (`classroom`)
    REFERENCES `university_se`.`classroom` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_reporing_status`
    FOREIGN KEY (`status`)
    REFERENCES `university_se`.`reporting_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_reporting_user`
    FOREIGN KEY (`done_by`)
    REFERENCES `university_se`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`support_device`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`support_device` ;

CREATE TABLE IF NOT EXISTS `university_se`.`support_device` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `university_se`.`support_device_has_classroom`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `university_se`.`support_device_has_classroom` ;

CREATE TABLE IF NOT EXISTS `university_se`.`support_device_has_classroom` (
  `support_device` INT(11) NOT NULL,
  `classroom` INT(11) NOT NULL,
  PRIMARY KEY (`support_device`, `classroom`),
  INDEX `fk_support_device_has_classroom_classroom1_idx` (`classroom` ASC),
  INDEX `fk_support_device_has_classroom_support_device1_idx` (`support_device` ASC),
  CONSTRAINT `fk_support_device_has_classroom_classroom1`
    FOREIGN KEY (`classroom`)
    REFERENCES `university_se`.`classroom` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_support_device_has_classroom_support_device1`
    FOREIGN KEY (`support_device`)
    REFERENCES `university_se`.`support_device` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
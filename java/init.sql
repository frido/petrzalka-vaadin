CREATE TABLE `world`.`budget` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL,
  `year` INT NULL,
  `program` INT NULL,
  `amount_original` DECIMAL NULL DEFAULT 0,
  `amount_updated` DECIMAL NULL DEFAULT 0,
  `amount_real` DECIMAL NULL DEFAULT 0,
  `comment` VARCHAR(255) NULL,
  `status` VARCHAR(45) NULL,
  `previous` INT NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `world`.`budget`
ADD INDEX `fk_idx` (`previous` ASC) VISIBLE;
;
ALTER TABLE `world`.`budget`
ADD CONSTRAINT `fk`
FOREIGN KEY (`previous`)
REFERENCES `world`.`budget` (`id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

INSERT INTO `world`.`budget` (`title`, `year`, `program`, `amount_original`, `amount_updated`, `amount_real`, `comment`, `status`)
VALUES ('Moderná prezentácia pamiatok a pamätihodností MČ', '2020', '1', '0', '0', '0', 'niCE life projekt. Náhradné čerpanie za neschválený pôvodný projekt. Pre potreby niCE life projektu sú potrebné iba bežné výdavky. Kapitálové výdavky v roku 2020 nebudú čerpané', 'error');

CREATE TABLE `world`.`grant_subject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `world`.`grant_subject`
    ADD COLUMN `category` VARCHAR(45) NOT NULL DEFAULT 'OZ' AFTER `title`;

CREATE TABLE `world`.`grant_item` (
`id` INT NOT NULL AUTO_INCREMENT,
`subject` INT NOT NULL,
`year` INT NOT NULL,
`amount` DECIMAL(10,2) NOT NULL,
`detail` VARCHAR(255) NULL,
PRIMARY KEY (`id`),
INDEX `fk_idx` (`subject` ASC) VISIBLE,
CONSTRAINT `fk_grant_grant_subject`
  FOREIGN KEY (`subject`)
  REFERENCES `world`.`grant_subject` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

  CREATE TABLE `world`.`playground` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `district` INT NOT NULL,
  `category` VARCHAR(45) NULL,
  `year` INT NULL,
  `flag` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `world`.`project` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `date` DATE NOT NULL,
  `description` LONGTEXT NULL,
  `icon` INT NULL,
  `budget` INT NULL,
  `url` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

  ALTER TABLE `world`.`project` 
ADD COLUMN `phase` VARCHAR(45) NULL AFTER `url`,
ADD COLUMN `status` VARCHAR(45) NULL AFTER `phase`;

ALTER TABLE `world`.`project` 
CHANGE COLUMN `status` `status` VARCHAR(45) NOT NULL ;


CREATE TABLE `world`.`image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `source` VARCHAR(1023) NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `world`.`statement` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `source` VARCHAR(255) NOT NULL,
  `date` DATE NULL,
  `status` VARCHAR(45) NOT NULL,
  `status_description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `world`.`project` 
CHANGE COLUMN `status` `status` VARCHAR(45) NOT NULL DEFAULT 'INWORK' ;

ALTER TABLE `world`.`image` 
ADD COLUMN `project_id` INT NULL AFTER `source`,
ADD INDEX `image_project_idx` (`project_id` ASC) VISIBLE;
;
ALTER TABLE `world`.`image` 
ADD CONSTRAINT `image_project`
  FOREIGN KEY (`project_id`)
  REFERENCES `world`.`project` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  ALTER TABLE `world`.`statement` 
ADD COLUMN `project_id` INT NULL AFTER `status_description`,
ADD INDEX `statement_project_idx` (`project_id` ASC) VISIBLE;
;
ALTER TABLE `world`.`statement` 
ADD CONSTRAINT `statement_project`
  FOREIGN KEY (`project_id`)
  REFERENCES `world`.`project` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;






CREATE TABLE `usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `senha` VARCHAR(100) NOT NULL,
  `data_Atualizacao` DATETIME NOT NULL,
  `ativo` INT (1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE `reuniao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `data` DATETIME NOT NULL,
  `finalizado` INT (1) NOT NULL,
  `ativo` INT (1) NOT NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reuniao_usuario_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_reuniao_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE `presenca` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `presente` INT (1) NOT NULL,
  `usuario_id` INT NOT NULL,
  `reuniao_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_presenca_usuario_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_presenca_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `usuario` (`id`),
   INDEX `fk_presenca_reuniao_idx` (`reuniao_id` ASC),
   CONSTRAINT `fk_presenca_reuniao`
   FOREIGN KEY (`reuniao_id`)
	REFERENCES `usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

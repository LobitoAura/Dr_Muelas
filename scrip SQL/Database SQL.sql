CREATE DATABASE dr_muelas;

USE dr_muelas;

CREATE TABLE `Persona`
(
 `dni`       int NOT NULL ,
 `nombre`    varchar(45) NOT NULL ,
 `apellido`  varchar(45) NOT NULL ,
 `edad`      int NOT NULL ,
 `domicilio` varchar(100) NOT NULL ,
 `telefono`  varchar(45) NOT NULL ,
 `genero`    char NOT NULL ,
 `email`     varchar(100) NOT NULL ,
 `usuario`   varchar(45) NOT NULL ,
 `clave`     varchar(45) NOT NULL ,

PRIMARY KEY (`dni`)
) ENGINE=INNODB;

CREATE TABLE `turno` (
  `id` int NOT NULL AUTO_INCREMENT,
  `categoria` tinyint(1) NOT NULL,
  `fecha` varchar(45) NOT NULL,
  `disponibilidad` tinyint(1) NOT NULL,
  `id_paciente` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `paciente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_dni` int NOT NULL,
  `emergencia` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Persona` (`id_dni`),
  CONSTRAINT `FK_Persona` FOREIGN KEY (`id_dni`) REFERENCES `persona` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `Historia_Clinica`
(
 `id`          int NOT NULL AUTO_INCREMENT ,
 `id_paciente` int NOT NULL ,
 `tratamiento` varchar(100) NOT NULL ,

PRIMARY KEY (`id`),
KEY `FK_Paciente` (`id_paciente`),
CONSTRAINT `FK_Paciente` FOREIGN KEY `FK_Paciente` (`id_paciente`) REFERENCES `Paciente` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=INNODB;
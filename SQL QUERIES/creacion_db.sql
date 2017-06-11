CREATE DATABASE sistemaBares;

USE sistemaBares;

CREATE TABLE `usuario_tb`(
  `id_usuario` varchar(10) NOT NULL,
  `nombre_usu` varchar(50) NOT NULL,
  `apellido_usu` varchar(50) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` varchar(50) NOT NULL,
  PRIMARY KEY (`id_usuario`)
  );

  
CREATE TABLE `platillo_tb`(
  `id_platillo` varchar(10) NOT NULL,
  `nombre_pla` varchar(50) NOT NULL,
  `descrp_pla` varchar(250) NOT NULL,
  `ingrediente_pla` varchar(250) NOT NULL,
  `categoria` varchar(50) NOT NULL,
  `temperatura_pla` int(4) NOT NULL,
  `imagen` varchar(50) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `servido` varchar(10) NOT NULL,	
  PRIMARY KEY (`id_platillo`)
  );
  
  CREATE TABLE `restaurante_tb`(
  `id_restaurante` varchar(10) NOT NULL,
  `nombre_rest` varchar(50) NOT NULL,
  `direccion_rest` varchar(50) DEFAULT NULL,
  `telf_rest` varchar(50) DEFAULT NULL,
  `dueno_rest` varchar(50) NOT NULL,
  `asist_rest` varchar(10) NOT NULL,
  PRIMARY KEY (`id_restaurante`),
  CONSTRAINT `restaurante_FK1` FOREIGN KEY (`asist_rest`) REFERENCES `usuario_tb` (`id_usuario`)
  );  
  
CREATE TABLE `menu_tb`(
  `id_restaurante` varchar(10) NOT NULL,
  `id_platillo` varchar(10) NOT NULL,
  PRIMARY KEY (`id_restaurante`,`id_platillo`),
  CONSTRAINT `menu_FK1` FOREIGN KEY (`id_platillo`) REFERENCES `platillo_tb` (`id_platillo`),
  CONSTRAINT `menu_FK2` FOREIGN KEY (`id_restaurante`) REFERENCES `restaurante_tb` (`id_restaurante`)
  );
  

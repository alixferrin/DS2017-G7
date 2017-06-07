
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
  
CREATE TABLE `categoria_tb`(
  `id_categoria` varchar(10) NOT NULL,
  `nombre_cat` varchar(50) NOT NULL,
  PRIMARY KEY (`id_categoria`)
  );
  
  CREATE TABLE `tipo_tb`(
  `id_tipo` varchar(10) NOT NULL,
  `nombre_tipo` varchar(50) NOT NULL,
  PRIMARY KEY (`id_tipo`)
  );
  
CREATE TABLE `platillo_tb`(
  `id_platillo` varchar(10) NOT NULL,
  `nombre_pla` varchar(50) NOT NULL,
  `descrp_pla` varchar(50) NOT NULL,
  `id_categoria` varchar(10) NOT NULL,
  `temperatura_pla` int(4) NOT NULL,
  `imagen` mediumblob NOT NULL,
  `id_tipo` varchar(10) NOT NULL,
  PRIMARY KEY (`id_platillo`),
  CONSTRAINT `platillo_FK1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria_tb` (`id_categoria`),
  CONSTRAINT `platillo_FK2` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_tb` (`id_tipo`)
  );
  
CREATE TABLE `menu_tb`(
  `id_menu` varchar(10) NOT NULL,
  `id_platillo` varchar(10) NOT NULL,
  PRIMARY KEY (`id_menu`),
  CONSTRAINT `menu_FK1` FOREIGN KEY (`id_platillo`) REFERENCES `platillo_tb` (`id_platillo`)
  );
  
  CREATE TABLE `restaurante_tb`(
  `id_restaurante` varchar(10) NOT NULL,
  `nombre_rest` varchar(50) NOT NULL,
  `direccion_rest` varchar(50) DEFAULT NULL,
  `telf_rest` varchar(50) DEFAULT NULL,
  `dueno_rest` varchar(10) NOT NULL,
  `asist_rest` varchar(10) NOT NULL,
  `id_menu` varchar(10) NOT NULL,
  PRIMARY KEY (`id_restaurante`),
  CONSTRAINT `restaurante_FK1` FOREIGN KEY (`dueno_rest`) REFERENCES `usuario_tb` (`id_usuario`),
  CONSTRAINT `restaurante_FK2` FOREIGN KEY (`id_menu`) REFERENCES `menu_tb` (`id_menu`),
  CONSTRAINT `restaurante_FK3` FOREIGN KEY (`asist_rest`) REFERENCES `usuario_tb` (`id_usuario`)
  );
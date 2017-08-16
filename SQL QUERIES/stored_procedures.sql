
use sistemabares;

/*PARA menu_tb*/
DELIMITER $$
CREATE PROCEDURE `nuevoMenu` (IN newIdRest varchar(10), IN newIdPlatillo varchar(10))
BEGIN
	insert into menu_tb values (newIdRest, newIdPlatillo);
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `modificarMenu` (IN Idrest varchar(10), IN newIdPlatillo varchar(10))
BEGIN
	update menu_tb
    set id_platillo = newIdPlatillo
    where id_restaurante = Idrest;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `buscarMenu` (IN Idrest varchar(10))
BEGIN
	SELECT * from menu_tb where id_restaurante = Idrest;
END $$
DELIMITER ;



/*PARA platillo_tb*/
DELIMITER $$
CREATE PROCEDURE `nuevoPlatillo` (IN newNombre varchar(50), IN newDescrip varchar(250), IN newIngre VARCHAR(250), IN newCategoria varchar(50), IN newTemp int(4), IN newImage varchar(50), IN newTipo varchar(50), IN newServido varchar(10))
BEGIN
	insert into platillo_tb values (default, newNombre, newDescrip, newIngre, newCategoria, newTemp, newImage, newTipo, newServido);
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `modificarPlatillo` (IN ID varchar(10), IN newNombre varchar(50), IN newDescrip varchar(150), IN newCategoria varchar(50), IN newImage varchar(50), IN  newIngre varchar(250))
BEGIN
	update platillo_tb
    set nombre_pla = newNombre, descrp_pla = newDescrip, categoria = newCategoria, imagen = newImage, ingrediente_pla = newIngre
    where id_platillo = ID;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `buscarPlatillo` (IN busqueda varchar(250))
BEGIN
	SELECT nombre_pla
    from platillo_tb 
    where nombre_pla like concat('%', busqueda, '%') or descrp_pla like concat('%', busqueda, '%');
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `listCategorias`()
BEGIN
	SELECT DISTINCT categoria FROM platillo_tb;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `mostrarPlatillos` (IN cat VARCHAR(50))
BEGIN
	SELECT nombre_pla FROM platillo_tb WHERE categoria = cat;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `getInfoPla` (IN nom VARCHAR(50))
BEGIN
	SELECT id_platillo, nombre_pla, descrp_pla, categoria, imagen, ingrediente_pla FROM platillo_tb WHERE nombre_pla = nom;
END $$
DELIMITER

DELIMITER $$
CREATE PROCEDURE `getPlatID`(IN nom VARCHAR(50))
BEGIN
	SELECT id_platillo FROM platillo_tb WHERE nombre_pla = nom;
END $$
DELIMITER ;

/*PARA restaurante_tb*/
DELIMITER $$
CREATE PROCEDURE `nuevoRestaurante` (IN newID varchar(10), IN newNombre varchar(50), IN newDireccion varchar(50), IN newTelf varchar(50), IN newDueno varchar(50), IN newAsist varchar(10), IN newCompra varchar(10))
BEGIN
	insert into restaurante_tb values (newID, newNombre, newDireccion, newTelf, newDueno, newAsist, newCompra);
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `modificarRestaurante` (IN ID varchar(10), IN newNombre varchar(50), IN newDireccion varchar(50), IN newTelf varchar(50), IN newDueno varchar(50), IN newAsist varchar(10), IN newCompra varchar(10))
BEGIN
	update restaurante_tb
    set nombre_rest = newNombre, direccion_rest = newDireccion, telf_rest = newTelf, dueno_rest = newDueno, asist_rest = newAsist, opCompra_rest = newCompra
    where id_restaurante = ID;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `listarRestaurantes` ()
BEGIN
	SELECT *
    from restaurante_tb; 
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `getRest` (IN nom VARCHAR(50))
BEGIN
	SELECT nombre_rest FROM platillo_tb AS p JOIN menu_tb as m ON p.id_platillo=m.id_platillo JOIN restaurante_tb AS r ON r.id_restaurante=m.id_restaurante WHERE nombre_pla = nom;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `getIDRest` (IN nom VARCHAR(50))
BEGIN
	SELECT id_restaurante FROM restaurante_tb WHERE nombre_rest = nom;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `getHabilitadosCompra` (IN nom VARCHAR(50))
BEGIN
	SELECT id_restaurante FROM restaurante_tb WHERE opCompra_rest = "1";
END $$
DELIMITER ;


/*PARA usuario_tb*/
DELIMITER $$
CREATE PROCEDURE `nuevoUsuario` (IN newID varchar(10), IN newNombre varchar(50), IN newApellido varchar(50), IN newNick varchar(50), IN newPass varchar(50), IN newLevel varchar(10))
BEGIN
	insert into usuario_tb values (newID, newNombre, newApellido, newNick, newPass, newLevel);
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `modificarUsuario` (IN ID varchar(10), IN newNombre varchar(50), IN newApellido varchar(50), IN newNick varchar(50), IN newPass varchar(50), IN newLevel varchar(10))
BEGIN
	update usuario_tb
    set nombre_usu = newNombre, apellido_usu = newApellido, username = newNick, contrasenia = newPass, acceso = newLevel
    where id_usuario = ID;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `login` (IN nick varchar(50), IN pass varchar(50))
BEGIN
	SELECT *
    from usuario_tb
    where username = nick and contrasenia = pass; 
END $$
DELIMITER ;

/*Asistente*/
DELIMITER $$
CREATE PROCEDURE `listCatASIS`(IN asis VARCHAR(50))
BEGIN
	SELECT DISTINCT categoria FROM platillo_tb AS p JOIN menu_tb AS m ON p.id_platillo = m.id_platillo JOIN restaurante_tb AS r ON r.id_restaurante = m.id_restaurante WHERE asist_rest = asis;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `listPlatillos` (IN asis VARCHAR(50))
BEGIN
	SELECT DISTINCT nombre_pla FROM platillo_tb AS p JOIN menu_tb as m ON p.id_platillo=m.id_platillo JOIN restaurante_tb AS r ON r.id_restaurante=m.id_restaurante WHERE asist_rest = asis;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `getRestASIS`(IN asis VARCHAR(50), IN nom VARCHAR(50))
BEGIN
	SELECT nombre_rest FROM platillo_tb AS p JOIN menu_tb as m ON p.id_platillo=m.id_platillo JOIN restaurante_tb AS r ON r.id_restaurante=m.id_restaurante WHERE nombre_pla = nom AND asist_rest = asis;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `mostrarPlatilloASIS`(IN asis VARCHAR(50), IN cat VARCHAR(50))
BEGIN
	SELECT DISTINCT nombre_pla FROM platillo_tb AS p JOIN menu_tb AS m ON p.id_platillo = m.id_platillo JOIN restaurante_tb AS r ON r.id_restaurante = m.id_restaurante WHERE categoria = cat AND asist_rest = asis;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `getRestDeASIS`(IN asis VARCHAR(50))
BEGIN
	SELECT nombre_rest FROM restaurante_tb WHERE asist_rest = asis;
END $$
DELIMITER ;

/*Menu*/
DELIMITER $$
CREATE PROCEDURE `insertMenu`(IN id_pla VARCHAR(50), IN id_rest VARCHAR(50))
BEGIN
	INSERT INTO menu_tb VALUE(id_rest, id_pla);
END $$
DELIMITER ;

/*General*/
DELIMITER $$
CREATE PROCEDURE `getTipos`()
BEGIN
	SELECT DISTINCT tipo FROM platillo_tb;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `getServido`()
BEGIN
	SELECT DISTINCT servido FROM platillo_tb;
END $$
DELIMITER ;

/*Para carnet*/
DELIMITER $$
CREATE PROCEDURE `nuevoCarnet` (IN matricula VARCHAR(50))
BEGIN
	INSERT INTO carnet_tb values (matricula, 0);
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `setSaldo` (IN matricula VARCHAR(50), IN sal int)
BEGIN
	UPDATE carnet_tb SET saldo = saldo + sal WHERE id_carnet = matricula;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `getSaldo` (IN matricula VARCHAR(50))
BEGIN
	SELECT saldo FROM carnet_tb WHERE id_carnet = matricula;
END $$
DELIMITER ;

/*para orden*/
DELIMITER $$
CREATE PROCEDURE `nuevaOrdenCarnet` (IN matricula VARCHAR(50), IN hora varchar(30), IN coste int)
BEGIN
	INSERT INTO carnet_tb values (0, hora, matricula);
    UPDATE carnet_tb SET saldo = saldo - coste WHERE id_carnet = matricula;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `nuevaOrdenTarjeta` (IN hora varchar(30))
BEGIN
	INSERT INTO carnet_tb values (0, hora, NULL);
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `listarOrdenPorMatricula` (IN matricula varchar(50))
BEGIN
	SELECT * FROM orden_tb WHERE cliente = matricula;
END $$
DELIMITER ;



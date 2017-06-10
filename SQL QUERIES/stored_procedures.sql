
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
CREATE PROCEDURE `buscarMenu` (IN Idrest varchar(10), OUT newIdPlatillo varchar(10))
BEGIN
	SELECT id_platillo into newIdPlatillo from menu_tb where id_restaurante = Idrest;
END $$
DELIMITER ;



/*PARA platillo_tb*/
DELIMITER $$
CREATE PROCEDURE `nuevoPlatillo` (IN newID varchar(10), IN newNombre varchar(50), IN newDescrip varchar(50), IN newCategoria varchar(50), IN newTemp int(4), IN newImage varchar(50), IN newTipo varchar(50), IN newServido varchar(10))
BEGIN
	insert into platillo_tb values (newID, newNombre, newDescrip, newCategoria, newTemp, newImage, newTipo, newServido);
END $$
DELIMITER 

DELIMITER $$
CREATE PROCEDURE `modificarPlatillo` (IN ID varchar(10), IN newNombre varchar(50), IN newDescrip varchar(50), IN newCategoria varchar(50), IN newTemp int(4), IN newImage varchar(50), IN newTipo varchar(50), IN newServido varchar(10))
BEGIN
	update platillo_tb
    set nombre_pla = newNombre, descrp_pla = newDescrip, id_categoria = newCategoria, temperatura_pla = newTemp, imagen = newImage, id_tipo = newTipo, servido = newServido
    where id_platillo = ID;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `buscarPlatillo` (IN nombre varchar(50), IN descrp varchar(250), OUT nombrePla varchar(50), OUT descrpPla varchar(250), 
									OUT categoria varchar(50), OUT temperatura_pla int(4), OUT img varchar(50), OUT tipo varchar(50), OUT serv varchar(10))
BEGIN
	SELECT nombre_pla, descrp_pla, id_categoria, temperatura_pla, imagen, id_tipo, servido 
    into nombrePla, descrpPla, categoria, temperatura_pla, img, tipo, serv 
    from menu_tb 
    where nombre_pla like concat('%', nombre, '%') or descrp_pla like concat('%', descrp, '%');
END $$
DELIMITER ;


/*PARA restaurante_tb*/
DELIMITER $$
CREATE PROCEDURE `nuevoRestaurante` (IN newID varchar(10), IN newNombre varchar(50), IN newDireccion varchar(50), IN newTelf varchar(50), IN newDueno varchar(50), IN newAsist varchar(10))
BEGIN
	insert into restaurante_tb values (newID, newNombre, newDireccion, newTelf, newDueno, newAsist);
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `modificarRestaurante` (IN ID varchar(10), IN newNombre varchar(50), IN newDireccion varchar(50), IN newTelf varchar(50), IN newDueno varchar(50), IN newAsist varchar(10))
BEGIN
	update restaurante_tb
    set nombre_rest = newNombre, direccion_rest = newDireccion, telf_rest = newTelf, dueno_rest = newDueno, asist_rest = newAsist
    where id_restaurante = ID;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `buscarRestaurante` (OUT id varchar(10), OUT nombre varchar(50), OUT direccion varchar(50), OUT telf varchar(50), OUT dueno varchar(50), OUT asist varchar(10))
BEGIN
	SELECT id_restaurante, nombre_rest, direccion_rest, telf_rest, dueno_rest, asist_rest
    into id, nombre, direccion, telf, dueno, asist 
    from restaurante_tb; 
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
    set nombre_usu = newNombre, apellido_usu = newApellido, nickname = newNick, password = newPass, level = newLevel
    where id_usuario = ID;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `login` (IN nick varchar(50), IN pass varchar(50), OUT usu_nick varchar(50))
BEGIN
	SELECT nickname
    into usu_nick
    from usuario_tb
    where nickname = nick and password = pass; 
END $$
DELIMITER ;




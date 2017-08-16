USE sistemabares;

/*Usuarios*/
INSERT INTO usuario_tb VALUES ('0931974380','JOE','SAVERIO','jsaverio','saverio95','1');
INSERT INTO usuario_tb VALUES ('1310539760','ALIX','FERRIN', 'apferrin','apferrin','2');
INSERT INTO usuario_tb VALUES ('1310539752','ALEX','FERRIN','aferrin','aferrin','2');
INSERT INTO usuario_tb VALUES ('092785289', 'LUIS GINO', 'CRUZ', 'lgcruz','lgcruz', '1');
INSERT INTO usuario_tb VALUES ('0922481304', 'GEANCARLO', 'MURILLO', 'gcmurillo','gcmurillo', '2');
INSERT INTO usuario_tb VALUES ('1310539757','CARLOS','ARIAS', 'carias','carias','2');
INSERT INTO usuario_tb VALUES ('1310539759','CARLA','ROSERO', 'crosero','crosero','2');
INSERT INTO usuario_tb VALUES ('1310539758','SEBASTIAN','PIGUAVE', 'spiguave','spiguave','2');



/*Platillos*/
INSERT INTO platillo_tb VALUE (default,'CEVICHE DE PESCADO', 'PLATO SERVIDO FRIO QUE CONTIENE PESCADO CURTIDO JUNTO CON TOMATE','ALBACORA, TOMATE, CEBOLLA, PEREJIL, PIMIENTOS,LIMON.', 'EJECUTIVO', 17, 'ceviche_pescado.jpg','PLATO FUERTE','Frio');
INSERT INTO platillo_tb VALUE (default,'CEVICHE DE CAMARON', 'PLATO SERVIDO FRIO QUE CONTIENE CAMARON JUNTO CON TOMATE','CAMARON, TOMATE, CEBOLLA, PEREJIL, PIMIENTOS,LIMON.','EJECUTIVO', 17, 'ceviche_camaron.jpg','PLATO FUERTE','Frio');
INSERT INTO platillo_tb VALUE (default,'CEVICHE DE CONCHA', 'PLATO SERVIDO FRIO QUE CONTIENE CONCHA JUNTO CON TOMATE','CONCHA, TOMATE, CEBOLLA, PEREJIL, PIMIENTOS,LIMON.', 'ESTUDIANTIL', 17, 'ceviche_concha.jpg','PLATO FUERTE','Frio');
INSERT INTO platillo_tb VALUE (default,'ARROZ CON MENESTRA Y CARNE', 'PLATO SERVIDO CON ARROZ CON FREJOLES Y CARNE ASADA CON PATACONES ACOMPAÑADO DE SOPA DE POLLO','ARROZ, FREJOLES, CARNE ASADA, PATACONES.', 'ESTUDIANTIL', 25, 'arroz_menestra_carne.jpg','PLATO FUERTE','Caliente');
INSERT INTO platillo_tb VALUE (default,'PATACONES CON QUESO Y SALPRIETA', 'PLATO SERVIDO CON VERDE FRITO QUESO Y SALPRIETA (MANI MOLIDO) ACOMPAÑADO DE CUAJADA','VERDE, QUESO, SALPRIETA.', 'EJECUTIVO', 23, 'patacones_queso.jpg','APERITIVO','Caliente');
INSERT INTO platillo_tb VALUE (default,'MORO CON POLLO', 'PLATO SERVIDO CON MORO Y POLLO ACOMPAÑADO DE PATACONES ACOMPAÑADO DE CALDO DE GALLINA CRIOLLA','POLLO ASADO, PATACONES, ARROZ, LENTEJA, TOMATE, LECHUGA, CEBOLLA PERLA.', 'EJECUTIVO', 23, 'moro_pollo.jpg','PLATO FUERTE','Caliente');
INSERT INTO platillo_tb VALUE (default,'MORO CON CARNE', 'PLATO SERVIDO CON MORO Y CARNE ACOMPAÑADO DE PATACONES ACOMPAÑADO DE CALDO DE GALLINA CRIOLLA','CARNE ASADO, PATACONES, ARROZ, LENTEJA, TOMATE, LECHUGA, CEBOLLA PERLA.', 'ESTUDIANTIL', 23, 'moro_carne.jpg','PLATO FUERTE','Caliente');
INSERT INTO platillo_tb VALUE (default,'CHURRASCO', 'PLATO SERVIDO CON ARROZ HUEVO Y CARNE ASADA ACOMPAÑADOO DE SOPA DE QUESO', 'ARROZ, HUEVO CARNE.', 'ESTUDIANTIL',23, 'churrasco.jpg','PLATO FUERTE','Caliente');
INSERT INTO platillo_tb VALUE (default,'YAPINGACHO', 'PLATO SERVIDO ARROZ CON HUEVO FRITO Y CHORIZO, ACOMPANNADO DE UNA TORTILLA DE PAPA EN SALSA DE MANI, ENSALADA Y LOCRO','ARROZ, HUEVO FRITO, CHORIZO, TORTILLA DE PAPA, LECHUGA, TOMATE, CEBOLLA PERLA.', 'EJECUTIVO',23, 'yapingacho.jpg', 'PLATO FUERTE', 'caliente');
INSERT INTO platillo_tb VALUE (default,'CAZUELA DE PESCADO', 'MAJADO DE VERDE CON PESCADO','VERDE MAJADO, PIMIENTO, TOMATE, CEBOLLA PERLA, PESACDO, MANI.', 'ESTUDIANTIL',23, 'cazuela_pescado.jpg', 'PLATO FUERTE', 'caliente');
INSERT INTO platillo_tb VALUE (default,'CAZUELA DE CAMARON', 'MAJADO DE VERDE CON CAMARON','VERDE MAJADO, PIMIENTO, TOMATE, CEBOLLA PERLA, PESACDO, MANI.', 'EJECUTIVO',23, 'cazuela_camaron.jpg', 'PLATO FUERTE', 'caliente');
INSERT INTO platillo_tb VALUE (default,'ENCEBOLLADO', 'CALDO DE ALBACORA CEBOLLA, YUCA, PEREJIL, SERVIDO CON PAN O CHIFLE CON LIMON','CEBOLLA, ALBACORA, YUCA, PAPA, LIMON, PEREJIL.', 'EJECUTIVO',23, 'encebollado.jpg', 'PLATO FUERTE', 'caliente');
   

/*Restaurante*/
INSERT INTO restaurante_tb VALUE ('RST01', 'Restaurante Coca Cola', 'CAMPUS ESPOL FIEC', '01222222', 'JORGE DURAN','1310539760', '0');
INSERT INTO restaurante_tb VALUE ('RST02', 'Restaurante Neo', 'CAMPUES ESPOL EDCOM', '01222222', 'DAYANA OCHOA','1310539752', '0');
INSERT INTO restaurante_tb VALUE ('RST03', 'Restaurante CELEX', 'CAMPUS ESPOL CELEX', '042685666', 'JUAN CARLOS MORAN', '1310539760', '0');
INSERT INTO restaurante_tb VALUE ('RST04', 'Restaurante Malicia', 'CAMPUS ESPOL FIMCBOR', '042658978', 'GERMAN GARMENDIA', '1310539752', '0');
INSERT INTO restaurante_tb VALUE ('RST05', 'Restaurante piscina', 'CAMPUS ESPOL PISCINA', '042632578', 'ISABELLA MENDOZA', '1310539760', '0');
INSERT INTO restaurante_tb VALUE ('RST06', 'Restaurante FICT', 'CAMPUS ESPOL FICT', '042658963', 'GABRIELA MENDOZA', '1310539752', '0');
INSERT INTO restaurante_tb VALUE ('RST07', 'Restaurante FCSH 1', 'CAMPUS ESPOL FCSH A LADO DE LA CARRETA DE TONO', '042625852','XIMENA VELEZ','1310539760', '0');
INSERT INTO restaurante_tb VALUE ('RST08', 'Restaurante FCSH 2', 'CAMPUS ESPOL FCSH A LADO DE LA  LIBRERIA CIENTFICA', '042685102','LORENA VELEZ','1310539760', '0');
INSERT INTO restaurante_tb VALUE ('RST09', 'Restaurante MARITIMA', 'CAMPUS ESPOL FIMCBOR', '042687963', 'XAVIER MACIAS', '0922481304', '0');
INSERT INTO restaurante_tb VALUE ('RST10', 'Restaurante NUEVA MALICIA', 'CAMPUS ESPOL FIMCBOR', '1234568', 'PANCHITA', '0922481304', '1');


/*Menu*/
INSERT INTO menu_tb VALUE ('RST01',10);
INSERT INTO menu_tb VALUE ('RST01', 11);
INSERT INTO menu_tb VALUE ('RST01', 4);
INSERT INTO menu_tb VALUE ('RST01', 8);
INSERT INTO menu_tb VALUE ('RST01', 9);
INSERT INTO menu_tb VALUE ('RST01', 12);
INSERT INTO menu_tb VALUE ('RST02', 1);
INSERT INTO menu_tb VALUE ('RST02', 2);
INSERT INTO menu_tb VALUE ('RST02', 3);
INSERT INTO menu_tb VALUE ('RST02', 4);
INSERT INTO menu_tb VALUE ('RST02', 6);
INSERT INTO menu_tb VALUE ('RST02', 7);
INSERT INTO menu_tb VALUE ('RST02', 9);
INSERT INTO menu_tb VALUE ('RST03', 1);
INSERT INTO menu_tb VALUE ('RST03', 2);
INSERT INTO menu_tb VALUE ('RST03', 3);
INSERT INTO menu_tb VALUE ('RST03', 4);
INSERT INTO menu_tb VALUE ('RST03', 6);
INSERT INTO menu_tb VALUE ('RST04', 4);
INSERT INTO menu_tb VALUE ('RST04', 6);
INSERT INTO menu_tb VALUE ('RST04', 7);
INSERT INTO menu_tb VALUE ('RST05', 4);
INSERT INTO menu_tb VALUE ('RST05', 8);
INSERT INTO menu_tb VALUE ('RST05', 9);
INSERT INTO menu_tb VALUE ('RST06', 4);
INSERT INTO menu_tb VALUE ('RST06', 5);
INSERT INTO menu_tb VALUE ('RST06', 8);
INSERT INTO menu_tb VALUE ('RST07', 4);
INSERT INTO menu_tb VALUE ('RST07', 5);
INSERT INTO menu_tb VALUE ('RST07', 10);
INSERT INTO menu_tb VALUE ('RST07', 11);
INSERT INTO menu_tb VALUE ('RST07',12);
INSERT INTO menu_tb VALUE ('RST08', 4);
INSERT INTO menu_tb VALUE ('RST08', 5);
INSERT INTO menu_tb VALUE ('RST08', 10);
INSERT INTO menu_tb VALUE ('RST08', 11);
INSERT INTO menu_tb VALUE ('RST08', 12);
INSERT INTO menu_tb VALUE ('RST09', 4);
INSERT INTO menu_tb VALUE ('RST09', 5);
INSERT INTO menu_tb VALUE ('RST09', 8);
INSERT INTO menu_tb VALUE ('RST10', 4);
INSERT INTO menu_tb VALUE ('RST10', 5);
INSERT INTO menu_tb VALUE ('RST10', 6);
INSERT INTO menu_tb VALUE ('RST10', 8);
INSERT INTO menu_tb VALUE ('RST10', 9);





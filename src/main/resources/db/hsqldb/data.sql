INSERT INTO autoridades(autoridad) VALUES ('admin');
INSERT INTO autoridades(autoridad) VALUES ('organizacion');
INSERT INTO autoridades(autoridad) VALUES ('cliente');

INSERT INTO usuarios(nombre_usuario, password, enabled, autoridades) VALUES ('alebangon', 'alebangon', TRUE, 'admin');
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (1, 'alejandro', 'Baños Gonzalez', 'abagon1999@gmail.com', 'alebangon');

INSERT INTO usuarios(nombre_usuario, password, enabled, autoridades) VALUES ('pabgutceb', 'pabgutceb', TRUE, 'admin');
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (2, 'Pablo', 'Gutiérrez Ceballos', 'pabloguti1006@gmail.com', 'pabgutceb');

INSERT INTO usuarios(nombre_usuario, password, enabled, autoridades) VALUES ('andmecsan', 'andmecsan', TRUE, 'admin');
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (3, 'Andrea', 'Mec San', 'andmecsan@gmail.com', 'andmecsan');

INSERT INTO usuarios(nombre_usuario, password, enabled, autoridades) VALUES ('romflorod', 'romflorod', TRUE, 'admin');
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (4, 'roman', 'flores rodriguez', 'romflorod@gmail.com', 'romflorod');

INSERT INTO usuarios(nombre_usuario, password, enabled, autoridades) VALUES ('ezepersos', 'ezepersos', TRUE, 'admin');
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (5, 'ezequiel', 'per sos', 'ezepersos@gmail.com', 'ezepersos');

INSERT INTO usuarios(nombre_usuario, password, enabled, autoridades) VALUES ('julibamon', 'julibamon', TRUE, 'admin');
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (6, 'julia', 'iba mon', 'julibamon@gmail.com', 'julibamon');

INSERT INTO usuarios(nombre_usuario, password, enabled, autoridades) VALUES ('cliente1', 'cliente1', TRUE, 'cliente');
INSERT INTO clientes(id, nombre, apellidos, email, nombre_usuario, telefono) VALUES (2,'andrea', 'Ceballos', 'AndreaCeballos@gmail.com','cliente1', '666777888');

INSERT INTO usuarios(nombre_usuario, password, enabled, autoridades) VALUES ('organizacion1', 'organizacion1', TRUE, 'organizacion');
INSERT INTO organizaciones(id,cif, email, info, nombre_organizacion, nombre_usuario) VALUES (1,'A1373B', 'organizacion1@gmail.com','empresa de cenas', 'organizacion1', 'organizacion1');


INSERT INTO usuarios(nombre_usuario, password, enabled, autoridades) VALUES ('Test_delete', 'test', TRUE, 'cliente');
INSERT INTO clientes(id, nombre, apellidos, email, nombre_usuario, telefono) VALUES (1,'andrea23', 'Ceballos', 'pepeceba@gmail.com','Test_delete', '666777888');


INSERT INTO usuarios(nombre_usuario, password, enabled, autoridades) VALUES ('Test_delete2', 'test', TRUE, 'organizacion');
INSERT INTO organizaciones(id,cif, email, info, nombre_organizacion, nombre_usuario) VALUES (2,'A1373B', 'cenitaspaco@gmail.com','empresa de cenas', 'Paco el cenas', 'Test_delete2');



INSERT INTO peticion(id,cif,email,info,nombre_organizacion,fecha) VALUES (1,'J76767676','VinosEmpresa@gmailTest.com','Empresa de vinos','Vinos y Empresa','2021-12-03');
INSERT INTO peticion(id,cif,email,info,nombre_organizacion,fecha) VALUES (2,'S8787877','CantadoresDeAndalucia@gmailTest.com','Empresa de cantaores de Andalucía','Canta Andalucia','2021-12-12');
INSERT INTO peticion(id,cif,email,info,nombre_organizacion,fecha) VALUES (3, 'P98989898','MilesSongs@example.com','Conciertos','Miles Songs','2021-11-23');
INSERT INTO peticion(id,cif,email,info,nombre_organizacion,fecha) VALUES (4,'X98989898','ConcertForValor@beef.com','conciertos de todo el mundo','For Valor','2021-11-05');



INSERT INTO lugar_realizacion(id,telefono,aforo,nombre_recinto,direccion,email,caracteristicas,url_foto,precio_dia) VALUES (1,695696847, 3000, 'caixaforum', 'Av reina mercedes', 'ayuda@caixaforum.com','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi maximus molestie ex, vitae cursus sapien elementum sed. Donec dolor sapien, egestas eget turpis in, fringilla bibendum neque. Pellentesque mi erat, aliquet', 'https://www.elindependiente.com/wp-content/uploads/2018/02/caixaforum-barcelona-facana-656x368.jpg', 10.0);
INSERT INTO lugar_realizacion(id,telefono,aforo,nombre_recinto,direccion,email,caracteristicas,url_foto,precio_dia) VALUES (2,690321658, 2010, 'bilindo', 'Nervión Plaza', 'ayuda@bilindo.com','Proin id ante nec sapien facilisis finibus. Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'https://www.diariodesevilla.es/2019/02/06/sevilla/terraza-Bilindo-formada-Plaza-America_1325577931_94584854_667x375.jpg', 19.30);
INSERT INTO lugar_realizacion(id,telefono,aforo,nombre_recinto,direccion,email,caracteristicas,url_foto,precio_dia) VALUES (3,685458962, 3500, 'Nueva plaza', 'Av Nueva Zelanda', 'Nervion@gmail.com','Quisque ac scelerisque lectus. Sed commodo, quam vel luctus interdum, sem dolor pellentesque turpis, ac consectetur nunc lectus eget metus.', 'https://pbs.twimg.com/media/CJ82gS-XAAEKWeB.jpg',12.0);
INSERT INTO lugar_realizacion(id,telefono,aforo,nombre_recinto,direccion,email,caracteristicas,url_foto,precio_dia) VALUES (4,666666666, 600000, 'Estadio La Cartuja', 'La Cartuja', 'Cartuja@gmail.com','Morbi tellus purus, varius non laoreet sit amet, sollicitudin ac nisl. Nam ullamcorper feugiat tortor, non vulputate mauris lacinia in.', 'https://multimedia.andalucia.org/fotos/image_200818.jpeg',16.70);

--INSERT INTO alquiler_espacio(id, precio_total, id_lugar_realizacion) VALUES (1, 50, 1);
--INSERT INTO actividad(id,tematica_actividad,descripcion_actividad,fecha_inicio,fecha_fin,alquiler_espacio_id) VALUES (1,'concierto de estopa', 'Mauris dolor elit, lobortis vel nulla eget, tristique scelerisque orci. Integer feugiat urna at volutpat dapibus. Curabitur pharetra, metus quis egestas scelerisque, mi nunc varius lectus,', '2022-12-12', '2022-12-13', 1);


INSERT INTO factura (id,fecha_factura,precio_total,nombre_usuario) VALUES(1,'2020-03-03',15.6,'alebangon');
INSERT INTO factura (id,fecha_factura,precio_total,nombre_usuario) VALUES(2,'2020-05-03', 1.6,'romflorod');
INSERT INTO factura (id,fecha_factura,precio_total,nombre_usuario) VALUES(3,'2020-09-03', 5.6,'ezepersos');
INSERT INTO factura (id,fecha_factura,precio_total,nombre_usuario) VALUES(4,'2020-02-03', 68.1,'andmecsan');
INSERT INTO factura (id,fecha_factura,precio_total,nombre_usuario) VALUES(5,'2020-02-03', 68.1,'organizacion1');
INSERT INTO factura (id,fecha_factura,precio_total,nombre_usuario) VALUES(6,'2020-02-03', 99.0,'organizacion1');
INSERT INTO factura (id,fecha_factura,precio_total,nombre_usuario) VALUES(7,'2020-02-03', 99.0,'cliente1');
--INSERT INTO linea_factura(id, id_alquiler_espacio, cantidad, id_factura) VALUES (1, 1, 1, 5);

-- EL CONCIERTO DE EMINEN NO SE PUEDE BORRAR QUE LO USAMOS PARA PRUEBAS

INSERT INTO eventos(id,tipo_evento, descripcion, nombre_evento, fecha_inicio, fecha_fin, organizacion_id, es_publico) VALUES (1,'CULTURALES_DE_OCIO', 'Concierto de Eminem', 'Eminem concert for valor', '2021-04-11', '2021-06-11', 1, TRUE);
INSERT INTO eventos(id,tipo_evento, descripcion, nombre_evento, fecha_inicio, fecha_fin, organizacion_id, es_publico) VALUES (2,'CULTURALES_DE_OCIO', 'Fusce vel neque nunc. Mauris nec enim at justo semper lobortis. Sed et pulvinar turpis', 'The Late Morning Event Show', '2023-06-11', '2023-06-12' , 1, TRUE);
INSERT INTO eventos(id,tipo_evento, descripcion, nombre_evento, fecha_inicio, fecha_fin, organizacion_id, es_publico) VALUES (3,'CULTURALES_DE_OCIO', 'FITUR es la primera cita anual para los profesionales del turismo mundial', 'FITUR 2021', '2022-10-11', '2022-10-12', 1, FALSE);
INSERT INTO eventos(id,tipo_evento, descripcion, nombre_evento, fecha_inicio, fecha_fin, organizacion_id, es_publico) VALUES (4,'CULTURALES_DE_OCIO', 'Maecenas eu pellentesque eros, sed pulvinar lacus. Proin tempus laoreet mauris', 'The 2021 Financial Checklist', '2022-04-13', '2022-04-13', 2, TRUE);
INSERT INTO eventos(id,tipo_evento, descripcion, nombre_evento, fecha_inicio, fecha_fin, organizacion_id, es_publico) VALUES (5,'CULTURALES_DE_OCIO', 'Integer volutpat augue a quam eleifend euismod. Proin consequat imperdiet leo.', 'Master The Trade: Master Your Money and Mindset For Investing in 2021', '2022-06-11', '2022-06-15', 2, TRUE);
INSERT INTO eventos(id,tipo_evento, descripcion, nombre_evento, fecha_inicio, fecha_fin, organizacion_id, es_publico) VALUES (6,'CULTURALES_DE_OCIO', 'Fusce condimentum tincidunt turpis vel ultricies.', 'Book Club with Margaret Atwood', '2022-02-11', '2022-05-13', 2, TRUE);
INSERT INTO eventos(id,tipo_evento, descripcion, nombre_evento, fecha_inicio, fecha_fin, organizacion_id, es_publico) VALUES (7,'CULTURALES_DE_OCIO', 'ornare lacus suscipit eget. Pellentesque nec fermentum justo. Donec ultricies justo ac porttitor tempor.', 'Reconstruye tu negocio', '2022-08-11', '2022-08-12', 2, TRUE);
INSERT INTO eventos(id,tipo_evento, descripcion, nombre_evento, fecha_inicio, fecha_fin, organizacion_id, es_publico) VALUES (8,'CULTURALES_DE_OCIO', 'ornare lacus suscipit eget. Pellentesque nec fermentum justo. Donec ultricies justo ac porttitor tempor.', 'Reconstruye tu negocio', '2022-08-11', '2022-08-12', 1, False);
INSERT INTO eventos(id,tipo_evento, descripcion, nombre_evento, fecha_inicio, fecha_fin, organizacion_id, es_publico) VALUES (9,'CULTURALES_DE_OCIO', 'ornare lacus suscipit eget. Pellentesque nec fermentum justo. Donec ultricies justo ac porttitor tempor.', 'Reconstruye tu negocio', '2022-08-11', '2022-08-12', 1, False);

INSERT INTO alquiler_espacio(id, fecha_fin_reserva, fecha_inicio_reserva, precio_total, id_lugar_realizacion) VALUES(1, '2021-04-11 16:00', '2021-04-11 10:00' , '35000.00', 1);
INSERT INTO alquiler_espacio(id, fecha_fin_reserva, fecha_inicio_reserva, precio_total, id_lugar_realizacion) VALUES(2, '2021-04-11 14:00', '2021-04-11 08:00' , '20000.00', 2);
INSERT INTO alquiler_espacio(id, fecha_fin_reserva, fecha_inicio_reserva, precio_total, id_lugar_realizacion) VALUES(3, '2021-04-11 14:00', '2021-04-11 08:00' , '20000.00', 3);

INSERT INTO actividad(id,tematica_actividad,descripcion_actividad,fecha_inicio,fecha_fin, alquiler_espacio_id, evento_id) VALUES (2,'Eminem in live', 'Mauris dolor elit, lobortis vel nulla eget, tristique scelerisque orci. Integer feugiat urna at volutpat dapibus. Curabitur pharetra, metus quis egestas scelerisque, mi nunc varius lectus,', '2021-04-11 10:00', '2021-04-11 13:00',1, 1);
INSERT INTO actividad(id,tematica_actividad,descripcion_actividad,fecha_inicio,fecha_fin, alquiler_espacio_id, evento_id) VALUES (3,'Eminem behind scenes', 'Mauris dolor elit, lobortis vel nulla eget, tristique scelerisque orci. Integer feugiat urna at volutpat dapibus. Curabitur pharetra, metus quis egestas scelerisque, mi nunc varius lectus,', '2021-04-11 13:05', '2021-04-11 13:45',2, 1);
INSERT INTO actividad(id,tematica_actividad,descripcion_actividad,fecha_inicio,fecha_fin, alquiler_espacio_id, evento_id) VALUES (11,'Tupac in live', 'accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias,', '2021-04-11 10:00', '2021-04-11 13:00', 3, 2);

INSERT INTO exponente(id,nombre_exponente,apellidos_exponente,alias) VALUES(1,'Marshall Bruce','Mathers III', 'EMINEM');
INSERT INTO exponente(id,nombre_exponente,apellidos_exponente,alias) VALUES(2,'Jack Berman','Webster II', 'Travis Scott');

INSERT INTO consulta(id, asunto, descripcion, fecha_consulta, id_cliente, ID_EVENTO) VALUES(1, 'test_asunto', 'test_descripcion', '2022-03-03',1,1);
INSERT INTO consulta(id, asunto, descripcion, fecha_consulta, id_cliente, ID_EVENTO) VALUES(2, 'test_asunto_2', 'test_descripcion_2', '2021-03-03',1,2);

INSERT INTO tipoentradas(id, precio, nombre, fecha_inicio, fecha_fin, num_entradas, evento_id) VALUES (1,8, 'DIURNA', '2021-04-11 09:00', '2021-04-11 12:00', 10,1);
INSERT INTO tipoentradas(id,precio, nombre, fecha_inicio, fecha_fin, num_entradas, evento_id) VALUES (2,9, 'NOCTURNA', '2021-04-11 16:00', '2021-04-11 20:00', 10,1);
INSERT INTO tipoentradas(id,precio, nombre, fecha_inicio, fecha_fin, num_entradas, evento_id) VALUES (3,5, 'DIURNA', '2023-06-11 09:00', '2023-06-11 12:00', 10,2);
INSERT INTO tipoentradas(id,precio, nombre, fecha_inicio, fecha_fin, num_entradas, evento_id) VALUES (4,8, 'NOCTURNA', '2023-06-11 16:00', '2023-06-11 20:00', 10,2);
INSERT INTO tipoentradas(id,precio, nombre, fecha_inicio, fecha_fin, num_entradas, evento_id) VALUES (5,6, 'DIURNA', '2022-10-11 09:00', '2022-10-11 12:00', 10,3);
INSERT INTO tipoentradas(id,precio, nombre, fecha_inicio, fecha_fin, num_entradas, evento_id) VALUES (6,7, 'NOCTURNA', '2022-10-11 16:00', '2022-10-11 20:00', 10,3);
INSERT INTO tipoentradas(id,precio, nombre, fecha_inicio, fecha_fin, num_entradas, evento_id) VALUES (7,4, 'DIURNA', '2022-04-13 09:00', '2022-04-13 12:00', 10,4);
INSERT INTO tipoentradas(id,precio, nombre, fecha_inicio, fecha_fin, num_entradas, evento_id) VALUES (8,7, 'NOCTURNA', '2022-04-13 16:00', '2022-04-13 20:00', 10,4);
INSERT INTO tipoentradas(id,precio, nombre, fecha_inicio, fecha_fin, num_entradas, evento_id) VALUES (9,6, 'DIURNA', '2022-06-11 09:00', '2022-06-11 12:00', 10,5);
INSERT INTO tipoentradas(id,precio, nombre, fecha_inicio, fecha_fin, num_entradas, evento_id) VALUES (10,9, 'NOCTURNA', '2022-06-11 16:00', '2022-06-11 20:00', 10,5);
INSERT INTO tipoentradas(id,precio, nombre, fecha_inicio, fecha_fin, num_entradas, evento_id) VALUES (11,7, 'DIURNA', '2022-02-11 09:00', '2022-02-11 12:00', 10,6);
INSERT INTO tipoentradas(id,precio, nombre, fecha_inicio, fecha_fin, num_entradas, evento_id) VALUES (12,9, 'NOCTURNA', '2022-02-11 16:00', '2022-02-11 20:00', 10,6);
INSERT INTO tipoentradas(id,precio, nombre, fecha_inicio, fecha_fin, num_entradas, evento_id) VALUES (13,9, 'NOCTURNA', '2022-08-11 16:00', '2022-08-11 20:00', 10,7);




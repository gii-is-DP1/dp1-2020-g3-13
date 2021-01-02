INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('alebangon', 'alebangon', TRUE);
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (1, 'alejandro', 'Baños Gonzalez', 'abagon1999@gmail.com', 'alebangon');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (1,'alebangon','admin');

INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('pabgutceb', 'pabgutceb', TRUE);
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (2, 'Pablo', 'Gutiérrez Ceballos', 'pabloguti1006@gmail.com', 'pabgutceb');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (2,'pabgutceb','admin');

INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('andmecsan', 'andmecsan', TRUE);
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (3, 'Andrea', 'Mec San', 'andmecsan@gmail.com', 'andmecsan');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (3,'andmecsan','admin');

INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('romflorod', 'romflorod', TRUE);
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (4, 'roman', 'flores rodriguez', 'romflorod@gmail.com', 'romflorod');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (4,'romflorod','admin');

INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('ezepersos', 'ezepersos', TRUE);
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (5, 'ezequiel', 'per sos', 'ezepersos@gmail.com', 'ezepersos');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (5,'ezepersos','admin');

INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('julibamon', 'julibamon', TRUE);
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (6, 'julia', 'iba mon', 'julibamon@gmail.com', 'julibamon');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (6,'julibamon','admin');



INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('cliente1', 'cliente1', TRUE);
INSERT INTO clientes(id, nombre, apellidos, email, nombre_usuario, telefono) VALUES (2,'andrea23', 'Ceballos', 'pepeceba@gmail.com','cliente1', '666777888');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (7,'cliente1','cliente');

INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('organizacion1', 'organizacion1', TRUE);
INSERT INTO organizaciones(id,cif, email, info, nombre_organizacion, nombre_usuario) VALUES (1,'A1373B', 'organizacion1@gmail.com','empresa de cenas', 'organizacion1', 'organizacion1');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (10,'organizacion1','organizacion');

INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('Test_delete', 'test', TRUE);
INSERT INTO clientes(id, nombre, apellidos, email, nombre_usuario, telefono) VALUES (1,'andrea23', 'Ceballos', 'pepeceba@gmail.com','Test_delete', '666777888');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (8,'Test_delete','cliente');

INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('Test_delete2', 'test', TRUE);
INSERT INTO organizaciones(id,cif, email, info, nombre_organizacion, nombre_usuario) VALUES (2,'A1373B', 'cenitaspaco@gmail.com','empresa de cenas', 'Paco el cenas', 'Test_delete2');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (9,'Test_delete2','organizacion');



INSERT INTO peticion(id,cif,email,info,nombre_organizacion,fecha) VALUES (1,'6969696969','CasaPepe@gmailTest.com','EMPRESA DE VINO SIN ALCOHOL','Casa Pepe','2020-12-03');
INSERT INTO peticion(id,cif,email,info,nombre_organizacion,fecha) VALUES (2,'96969696','FrancisquitoCantaor@gmailTest.com','cisquito el cantaor','francisco pepe','2020-12-12');
INSERT INTO peticion(id,cif,email,info,nombre_organizacion,fecha) VALUES (3, '666666666','travis@scott.com','Conciertos de trap','Travis Scott','2020-11-23');
INSERT INTO peticion(id,cif,email,info,nombre_organizacion,fecha) VALUES (4,'9999999','yung@beef.com','conciertos de trap español','Ternera joven','2020-11-05');


INSERT INTO consulta(id, name, asunto, descripcion, fecha_consulta) VALUES(1, 'test', 'test_asunto', 'test_descripcion', '2020-03-03');

INSERT INTO lugar_realizacion(id,telefono,aforo,nombre_recinto,direccion,email,disponibilidad,caracteristicas,url_foto) VALUES (1,695696847, 3000, 'caixaforum', 'Av reina mercedes', 'ayuda@caixaforum.com', TRUE,'muy bonito', 'https://www.elindependiente.com/wp-content/uploads/2018/02/caixaforum-barcelona-facana-656x368.jpg');
INSERT INTO actividad(id,tematica_actividad,descripcion_actividad,fecha_inicio,fecha_fin,lugar_realizacion_id) VALUES (1,'concierto de estopa', 'un buen concierto', '2020-12-12', '2020-12-12', 1);

INSERT INTO factura (id,fecha_factura,precio_total,nombre_usuario) VALUES(1,'2020-03-03', 15.6,'alebangon');
INSERT INTO factura (id,fecha_factura,precio_total,nombre_usuario) VALUES(2,'2020-05-03', 1.6,'romflorod');
INSERT INTO factura (id,fecha_factura,precio_total,nombre_usuario) VALUES(3,'2020-09-03', 5.6,'ezepersos');
INSERT INTO factura (id,fecha_factura,precio_total,nombre_usuario) VALUES(4,'2020-02-03', 68.1,'andmecsan');
INSERT INTO factura (id,fecha_factura,precio_total,nombre_usuario) VALUES(5,'2020-02-03', 68.1,'organizacion1');


INSERT INTO eventos(tipo_evento, descripcion, nombre_evento, fecha_inicio, medidas_sanitarias, categoria, fecha_fin, organizacion_id) VALUES ('Concierto', 'Concierto de Eminem', 'Eminem concert for valor', '2021-06-11', '6M', 'Musica', '2021-06-11', 1);
INSERT INTO eventos(tipo_evento, descripcion, nombre_evento, fecha_inicio, medidas_sanitarias, categoria, fecha_fin, organizacion_id) VALUES ('Concierto', 'Concierto de Eem', 'Game Awards', '2023-06-11', '6M', 'Musica', '2021-06-11' , 1);
INSERT INTO eventos(tipo_evento, descripcion, nombre_evento, fecha_inicio, medidas_sanitarias, categoria, fecha_fin, organizacion_id) VALUES ('Concierto', 'Concito de Eminem', 'Animal crossing', '2022-06-11', '6M', 'Musica', '2021-06-11', 1);

INSERT INTO tipoentradas(precio, nombre, fecha_inicio, descuento, fecha_fin, num_entradas, evento_id) VALUES (8, 'Diurna', '2022-04-11', 15, '2022-05-10', 10,1);
INSERT INTO tipoentradas(precio, nombre, fecha_inicio, descuento, fecha_fin, num_entradas, evento_id) VALUES (9, 'Nocturna', '2022-04-11', 15, '2022-05-10', 10,1);

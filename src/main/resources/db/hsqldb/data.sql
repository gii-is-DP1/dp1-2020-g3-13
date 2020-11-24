-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

INSERT INTO vets VALUES (1, 'James', 'Carter');
INSERT INTO vets VALUES (2, 'Helen', 'Leary');
INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT INTO owners VALUES (11, 'alebangon', '', 'C/ Gertrudis Gomez 123', 'Sevilla', '6085558487', 'owner1');
INSERT INTO owners VALUES (16, 'romflorod', '', 'C/ Gertrudis Gomez 123', 'Sevilla', '6085558487', 'owner1');
INSERT INTO owners VALUES (12, 'andmecsan', '', 'C/ Ingenieria Informatica 2', 'Sevilla', '6085558487', 'owner1');
INSERT INTO owners VALUES (13, 'ezepersos', '', 'C/ Presidente 1', 'Sevilla', '675888212', 'owner1');
INSERT INTO owners VALUES (14, 'pabloguti01', '', 'C/ Betis 10', 'Sevilla', '667836389', 'owner1');
INSERT INTO owners VALUES (15, 'julibamon', '', 'C/ Feria', 'Sevilla', '661515321', 'owner1');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

-- INSERT INTO peticion(id,name,cif,email,info,organizacion) VALUES (2,'antonio','690321145','antonio@elbola.es','EMPRESA DE BALONES CUADRADOS','BALON CUADRADO');
-- INSERT INTO admins(id, usuario, password, nombre, apellidos, email) VALUES (1, 'alebangon', 'alebangon99', 'alejandro', 'Baños Gonzalez', 'awwadw@gmail.com' );


-- INSERT INTO organizaciones(id, cif, email, info, password, usuario, nombre_organizacion,fk_peticion) VALUES (1,'690321145', 'antonio@elbola.es','EMPRESA DE BALONES CUADRADOS','pelotasCuadradas','antBola', 'BALON CUADRADO',1);
INSERT INTO peticion(id,cif,email,info,nombre_organizacion) VALUES (1,'6969696969','PRIMOPEPE@paco.COM','EMPRESA DE VINO SIN ALCOHOL','PRIMO PEPE');
INSERT INTO peticion(id,cif,email,info,nombre_organizacion) VALUES (2,'96969696','francisquito@PEPE.COM','cisquito el cantaor','francisco PEPE');
INSERT INTO peticion(id,cif,email,info,nombre_organizacion) VALUES (3, '666666666','travis@scott.COM','conciertos de trap','travieso scotte');
INSERT INTO peticion(id,cif,email,info,nombre_organizacion) VALUES (4,'9999999','yung@beef.COM','conciertos de trap español','ternera joven');


INSERT INTO consulta(id, name, asunto, descripcion, fecha_consulta) VALUES(1, 'test', 'test_asunto', 'test_descripcion', '2020-03-03');

INSERT INTO factura (id,fecha_factura,precio_total,usuario_asociado) VALUES(1,'2020-03-03', 15.6,'alebangon');
INSERT INTO factura (id,fecha_factura,precio_total,usuario_asociado) VALUES(2,'2020-05-03', 1.6,'romflorod');
INSERT INTO factura (id,fecha_factura,precio_total,usuario_asociado) VALUES(3,'2020-09-03', 5.6,'ezepersos');
INSERT INTO factura (id,fecha_factura,precio_total,usuario_asociado) VALUES(4,'2020-02-03', 68.1,'andmecsan');


INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('pabgutceb', 'pabgutceb', TRUE);
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (2, 'Pablo', 'Gutiérrez Ceballos', 'pabloguti1006@gmail.com', 'pabgutceb');
INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('andmecsan', 'andmecsan', TRUE);
INSERT INTO autoridades(id,usuario,autoridad) VALUES (2,'pabgutceb','admin');
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (3, 'Andrea', 'Mec San', 'andmecsan@gmail.com', 'andmecsan');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (3,'andmecsan','admin');
INSERT INTO eventos(tipo_evento, descripcion, nombre_evento, fecha_inicio, medidas_sanitarias, categoria, fecha_fin) VALUES ('ConciertoEminem', 'Concierto', 'Cantan', '2021-06-11', 'muchas', 'Musica', '2021-06-11' );
INSERT INTO tipoentradas(precio, nombre, fecha_inicio, descuento, fecha_fin, num_entradas) VALUES (8, 'Diurna', '2022-04-11', 15, '2022-05-10', 10);

INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('romflorod', 'romflorod', TRUE);
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (4, 'roman', 'flores rodriguez', 'romflorod@gmail.com', 'romflorod');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (4,'romflorod','admin');
INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('ezepersos', 'ezepersos', TRUE);
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (5, 'ezequiel', 'per sos', 'ezepersos@gmail.com', 'ezepersos');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (5,'ezepersos','admin');
INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('julibamon', 'julibamon', TRUE);
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (6, 'julia', 'iba mon', 'julibamon@gmail.com', 'julibamon');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (6,'julibamon','admin');





INSERT INTO clientes(id, nombre, apellidos, email, nombre_usuario, telefono) VALUES (1,'pepe56', 'Ceballos', 'pepeceba@gmail.com', 'pabgutceb',  666777888);

INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('alebangon', 'alebangon', TRUE);
INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (1, 'alejandro', 'Baños Gonzalez', 'abagon1999@gmail.com', 'alebangon');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (1,'alebangon','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO usuarios(nombre_usuario,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO autoridades(id,usuario,autoridad) VALUES (7,'owner1','owner');
INSERT INTO lugar_realizacion(id,telefono,aforo,nombre_recinto,direccion,email,disponibilidad,caracteristicas,url_foto) VALUES (1,695696847, 3000, 'caixaforum', 'Av reina mercedes', 'ayuda@caixaforum.com', TRUE,'muy bonito', 'https://www.elindependiente.com/wp-content/uploads/2018/02/caixaforum-barcelona-facana-656x368.jpg');
INSERT INTO actividad(id,tematica_actividad,descripcion_actividad,fecha_inicio,fecha_fin,nombre_recinto) VALUES (1,'concierto de estopa', 'un buen concierto', '2020-12-12', '2020-12-12', 'caixaforum');

--INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('andmecsan', 'andmecsan', TRUE);
INSERT INTO clientes(id, nombre, apellidos, email, nombre_usuario, telefono) VALUES (3,'andrea23', 'Ceballos', 'pepeceba@gmail.com', 'andmecsan',  666777888);
INSERT INTO autoridades(id,usuario,autoridad) VALUES (8,'andmecsan','cliente');

--INSERT INTO admins(id, nombre, apellidos, email, nombre_usuario) VALUES (3, 'andrea', 'meca sanchez', 'andmecsan@alum.us.es', 'andmecsan');
--INSERT INTO autoridades(id,usuario,autoridad) VALUES (9,'andmecsan','admin');

INSERT INTO usuarios(nombre_usuario, password, enabled) VALUES ('prueba1', 'prueba1', TRUE);
INSERT INTO clientes(id, nombre, apellidos, email, nombre_usuario, telefono) VALUES (4,'andrea23', 'Ceballos', 'pepeceba@gmail.com','prueba1', 666777888);
INSERT INTO autoridades(id,usuario,autoridad) VALUES (12,'prueba1','cliente');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (10,'prueba1','organizacion');

INSERT INTO organizaciones(id,nombre_usuario, nombre_organizacion) VALUES (1, 'prueba1','PRIMO PEPE');
INSERT INTO autoridades(id,usuario,autoridad) VALUES (11,'pabgutceb','cliente');
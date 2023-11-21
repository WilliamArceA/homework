INSERT INTO roles(id,name) VALUES ('1','Administrador');
INSERT INTO roles(id,name) VALUES ('2','Empleado de turno');
INSERT INTO roles(id,name) VALUES ('3','Jefe de Departamento');

INSERT INTO users(id,username, password, email, created_at) VALUES ('1','vabasto', 'A123456a!', 'vabasto@gmail.com', null);
INSERT INTO users(id,username, password, email, created_at) VALUES ('2','gzurita', 'A123456a!', 'gzurita@gmail.com', null);
INSERT INTO users(id,username, password, email, created_at) VALUES ('3','lcentellas', 'A123456a!', 'lcentellas@gmail.com', null);
INSERT INTO users(id,username, password, email, created_at) VALUES ('4','mzceballos', 'A123456a!', 'mzeballos@gmail.com', null);

INSERT INTO user_role(id, active, created_at, user_id, role_id) VALUES ('1',true,now(), '1', '1');
INSERT INTO user_role(id, active, created_at, user_id, role_id) VALUES ('2',true,now(), '1', '3');
INSERT INTO user_role(id, active, created_at, user_id, role_id) VALUES ('3',true,now(), '2', '2');
INSERT INTO user_role(id, active, created_at, user_id, role_id) VALUES ('4',true,now(), '3', '2');
INSERT INTO user_role(id, active, created_at, user_id, role_id) VALUES ('5',true,now(), '4', '2');

INSERT INTO users_detail(id, first_name, last_name, age, birthday, user_id) VALUES ('1','Victor','Avasto', '23', '1999-09-11','1');
INSERT INTO users_detail(id, first_name, last_name, age, birthday, user_id) VALUES ('2','Gabriel','Zurita', '24', '1999-09-08','2');
INSERT INTO users_detail(id, first_name, last_name, age, birthday, user_id) VALUES ('3','Leonardo','Centellas', '23', '1999-04-06','3');
INSERT INTO users_detail(id, first_name, last_name, age, birthday, user_id) VALUES ('4','Marcelo','Zeballos', '24', '1999-05-10','4');
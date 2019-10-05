CREATE TABLE task_type (
  id int(2) NOT NULL,
  type varchar(20) NOT NULL,
  comment varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE task (
  id int(5) NOT NULL AUTO_INCREMENT,
  user_id int(5) NOT NULL,
  type_id int(2) NOT NULL,
  title varchar(50) NOT NULL,
  detail text,
  deadline datetime NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE user_info (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  email varchar(70) NOT NULL,
  password varchar(60) NOT NULL,
  enabled boolean NOT NULL,
  authority varchar(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE role (
  id int(2) NOT NULL,
  role_key varchar(50),
  role_comment varchar(100),
  PRIMARY KEY (id)
 );
 
 CREATE TABLE permission (
   id int(11) NOT NULL AUTO_INCREMENT,
   user_info_id int(11) NOT NULL,
   role_id int(2) NOT NULL,
   PRIMARY KEY (id)
 );
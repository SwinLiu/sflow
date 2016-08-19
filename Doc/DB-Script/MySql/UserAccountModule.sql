SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS sflow_user_login_logs;
DROP TABLE IF EXISTS sflow_user_password;
DROP TABLE IF EXISTS sflow_user_account;




/* Create Tables */

CREATE TABLE sflow_user_login_logs
(
	id varchar(20) NOT NULL,
	user_id varchar(20) NOT NULL,
	login_ip varchar(20) NOT NULL,
	login_time datetime NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE sflow_user_account
(
	id varchar(20) NOT NULL,
	user_name varchar(20),
	email varchar(40),
	phone varchar(20),
	status int(3),
	PRIMARY KEY (id),
	UNIQUE (user_name),
	UNIQUE (email),
	UNIQUE (phone)
);


CREATE TABLE sflow_user_password
(
	id varchar(20) NOT NULL,
	password varchar(50)
);



/* Create Foreign Keys */

ALTER TABLE sflow_user_login_logs
	ADD CONSTRAINT FK_user_login_logs FOREIGN KEY (user_id)
	REFERENCES sflow_user_account (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;


ALTER TABLE sflow_user_password
	ADD CONSTRAINT FK_user_password FOREIGN KEY (id)
	REFERENCES sflow_user_account (id)
	ON UPDATE RESTRICT
	ON DELETE CASCADE
;




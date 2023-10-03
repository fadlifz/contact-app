CREATE DATABASE contact_app_restful_api;

USER contact_app_restful_api;

CREATE TABLE contact_app_restful_api.users (
	username varchar(100) NOT NULL,
	password varchar(100) NOT NULL,
	name varchar(100) NOT NULL,
	token varchar(100),
	token_expired_at BIGINT,
	primary key (username),
	UNIQUE (token)
)
ENGINE=InnoDB;

SELECT * FROM users;

DESC users;

CREATE TABLE contact_app_restful_api.contacts (
	id varchar(100) NOT NULL,
	username varchar(100) NOT NULL,
	first_name varchar(100) NOT NULL,
	last_name varchar(100),
	phone varchar(100),
	email varchar(100),
	PRIMARY KEY (id),
	FOREIGN KEY fk_users_contacts(username) REFERENCES contact_app_restful_api.users(username)
)
ENGINE=InnoDB;

SELECT * FROM contacts;

DESC contacts;

CREATE TABLE contact_app_restful_api.addresses (
	id varchar(100) NOT NULL,
	contact_id varchar(100) NOT NULL,
	street varchar(200),
	city varchar(100),
	province varchar(100),
	country varchar(100) NOT NULL,
	postal_code varchar(10),
	PRIMARY KEY (id),
	FOREIGN KEY fk_contacts_addresses (contact_id) REFERENCES contact_app_restful_api.contacts(id)
)
ENGINE=InnoDB;
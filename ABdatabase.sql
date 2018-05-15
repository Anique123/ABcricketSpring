CREATE DATABASE IF NOT EXISTS ABdb;

CREATE TABLE member (

member_ID	     INT	        NOT NULL  UNIQUE AUTO_INCREMENT,
member_firstname VARCHAR(50)	NOT NULL,
member_lastname  VARCHAR(50)    NOT NULL,
member_cpr		 INT			NOT NULL  UNIQUE,
member_adress	 VARCHAR(50) 	NOT NULL  UNIQUE,
city 			 VARCHAR(50)    NOT NULL,
zip_code		 VARCHAR(50)    NOT NULL,
member_age		 int			NOT NULL,
phone_number	 int			NOT NULL,
email_adress	 VARCHAR(50)	NOT NULL, UNIQUE,

);

CREATE TABLE match (

match_ID		INT			    NOT NULL, UNIQUE,








/**
 * Author:  oyindamolaakindele
 * Created: 09-Dec-2021
 */

CREATE TABLE IF NOT EXISTS Address(
	id INTEGER NOT NULL AUTO_INCREMENT,
    city VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    post_code VARCHAR(50) NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Users(
	id INTEGER NOT NULL AUTO_INCREMENT,
    email VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATETIME NULL,
    date_joined DATETIME NOT NULL,
    password VARCHAR(255) NOT NULL,
    email_verified BIT NOT NULL,
    address_id INTEGER,
    user_type VARCHAR(15) NOT NULL,
    verification_code VARCHAR(255) NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (address_id) REFERENCES Address(id)
);
/**
 * Author:  oyindamolaakindele
 * Created: 21-Dec-2021
 */

CREATE TABLE IF NOT EXISTS ParcelDelivery(
	id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NULL,
    pickup_address VARCHAR(255) NOT NULL,
    destination_address VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    created_by INTEGER NOT NULL,
    pickedup_by INTEGER NULL,
    
    PRIMARY KEY (id)
);
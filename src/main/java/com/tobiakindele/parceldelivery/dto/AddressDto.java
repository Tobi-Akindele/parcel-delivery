package com.tobiakindele.parceldelivery.dto;

import java.io.Serializable;

/**
 *
 * @author oyindamolaakindele
 */

public class AddressDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String city;
    private String street;
    private String postCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}

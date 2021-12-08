package com.tobiakindele.parceldelivery.managedbean;

import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author oyindamolaakindele
 */
@Named(value = "signUpRequest")
@RequestScoped
public class SignUpRequest implements Serializable {

    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    /**
     * Creates a new instance of SignUpRequest
     */
    public SignUpRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

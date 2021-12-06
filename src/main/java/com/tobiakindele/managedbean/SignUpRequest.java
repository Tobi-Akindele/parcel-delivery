package com.tobiakindele.managedbean;

import javax.inject.Named;
import java.io.Serializable;
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
}

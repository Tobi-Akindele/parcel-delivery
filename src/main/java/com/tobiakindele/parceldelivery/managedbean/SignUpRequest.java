package com.tobiakindele.parceldelivery.managedbean;

import com.tobiakindele.parceldelivery.models.User;
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

    private User user;

    /**
     * Creates a new instance of SignUpRequest
     */
    public SignUpRequest() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

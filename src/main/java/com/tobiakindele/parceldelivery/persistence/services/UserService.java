package com.tobiakindele.parceldelivery.persistence.services;

import com.tobiakindele.parceldelivery.models.User;

import javax.faces.validator.ValidatorException;

/**
 *
 * @author oyindamolaakindele
 */

public interface UserService {
    
    public User createUser(User user) throws ValidatorException;
    
    public User findByEmail(String email);
    
    public User login(String email, String password);
    
    public String logout();
}

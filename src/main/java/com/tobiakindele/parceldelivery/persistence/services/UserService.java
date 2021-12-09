/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tobiakindele.parceldelivery.persistence.services;

import com.tobiakindele.parceldelivery.models.User;

import javax.faces.validator.ValidatorException;

/**
 *
 * @author oyindamolaakindele
 */

public interface UserService {
    
    public User createUser(User user) throws ValidatorException;
}

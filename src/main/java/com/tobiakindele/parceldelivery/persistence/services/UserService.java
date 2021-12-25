package com.tobiakindele.parceldelivery.persistence.services;

import com.tobiakindele.parceldelivery.dto.UserDto;

/**
 *
 * @author oyindamolaakindele
 */

public interface UserService {
    
    public UserDto createUser(UserDto user);
    
    public UserDto findByEmail(String email);
    
    public UserDto findById(Long id);
    
    public UserDto login(String email, String password);
    
    public void logout();
    
    public boolean verifyUser(String verificationCode);
}

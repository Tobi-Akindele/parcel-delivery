package com.tobiakindele.parceldelivery.persistence.dao;

import com.tobiakindele.parceldelivery.dto.UserDto;
import com.tobiakindele.parceldelivery.models.User;

/**
 *
 * @author oyindamolaakindele
 */

public interface UserDao extends AbstractDao<User, UserDto> {
     
    public UserDto findByEmail(String email);
    
    public UserDto findByVerificationCode(String verificationCode);
}

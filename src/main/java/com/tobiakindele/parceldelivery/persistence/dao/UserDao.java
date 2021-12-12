package com.tobiakindele.parceldelivery.persistence.dao;

import com.tobiakindele.parceldelivery.models.User;

/**
 *
 * @author oyindamolaakindele
 */

public interface UserDao extends AbstractDao<User> {
     
    public User findByEmail(String email);
}

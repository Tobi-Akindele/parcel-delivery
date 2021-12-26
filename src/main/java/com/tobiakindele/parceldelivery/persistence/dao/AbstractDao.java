package com.tobiakindele.parceldelivery.persistence.dao;

import com.tobiakindele.parceldelivery.models.AbstractModel;

/**
 *
 * @author oyindamolaakindele
 * @param <T>
 * @param <U>
 */

public interface AbstractDao<T extends AbstractModel, U> {
    
    public U save(T t);
    
    public U read(Long id);
    
    public U update(T t);
    
    public void delete(T t);
}

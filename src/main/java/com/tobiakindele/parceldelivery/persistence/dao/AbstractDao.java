package com.tobiakindele.parceldelivery.persistence.dao;

import com.tobiakindele.parceldelivery.models.AbstractModel;

/**
 *
 * @author oyindamolaakindele
 * @param <T>
 */

public interface AbstractDao<T extends AbstractModel> {
    
    public T save(T t);
    
    public T read(Long id);
    
    public T update(T t);
    
    public void delete(Long id);
}

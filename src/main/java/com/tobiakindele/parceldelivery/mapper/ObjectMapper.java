package com.tobiakindele.parceldelivery.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 *
 * @author oyindamolaakindele
 */

public class ObjectMapper {
    
    private static Mapper instance;
    
    private ObjectMapper(){}
    
    public static Mapper getInstance() {
        if(instance == null){
            synchronized(ObjectMapper.class){
                if(instance == null){
                    instance = new DozerBeanMapper();
                }
            }
        }
        return instance;
    }
}

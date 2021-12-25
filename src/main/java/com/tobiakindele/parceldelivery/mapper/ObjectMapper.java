package com.tobiakindele.parceldelivery.mapper;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 *
 * @author oyindamolaakindele
 */

public class ObjectMapper {
    
    private static Mapper mapper;
    
    public static Mapper getMapper() {
        if(mapper == null){
            mapper = new DozerBeanMapper();
        }
        return mapper;
    }
}

package com.tobiakindele.parceldelivery.persistence.dao;

import com.tobiakindele.parceldelivery.dto.ParcelDeliveryDto;
import com.tobiakindele.parceldelivery.models.ParcelDelivery;
import java.util.List;

/**
 *
 * @author oyindamolaakindele
 */
public interface ParcelDeliveryDao extends AbstractDao<ParcelDelivery, ParcelDeliveryDto> {
    
    public List<ParcelDeliveryDto> 
        getAllParcelDeliveryByStatus(String status, Long userId, int[] range);
        
    public Long getAllParcelDeliveryByStatusCount(String status, Long userId);
    
    public List<ParcelDeliveryDto> 
        getAllParcelDeliveryForDriverByStatus(String status, Long userId, int[] range);
        
    public Long getAllParcelDeliveryForDriverByStatusCount(String status, Long userId);
}

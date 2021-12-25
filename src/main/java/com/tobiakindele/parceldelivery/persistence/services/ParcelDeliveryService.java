package com.tobiakindele.parceldelivery.persistence.services;

import com.tobiakindele.parceldelivery.dto.ParcelDeliveryDto;
import java.util.List;

/**
 *
 * @author oyindamolaakindele
 */
public interface ParcelDeliveryService {
    
    public ParcelDeliveryDto createParcelDelivery(ParcelDeliveryDto parcelDeliveryDto);
    
    public List<ParcelDeliveryDto> getAllParcelDeliveryByStatus(String status, int[] range);
    
    public Long getAllParcelDeliveryByStatusCount(String status);
    
    public ParcelDeliveryDto findById(Long id);
}

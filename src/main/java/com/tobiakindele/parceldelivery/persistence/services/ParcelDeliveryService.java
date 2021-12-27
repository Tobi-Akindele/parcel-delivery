package com.tobiakindele.parceldelivery.persistence.services;

import com.tobiakindele.parceldelivery.dto.ParcelDeliveryDto;
import java.util.List;

/**
 *
 * @author oyindamolaakindele
 */
public interface ParcelDeliveryService {
    
    public ParcelDeliveryDto createParcelDelivery(ParcelDeliveryDto parcelDeliveryDto);
    
    public ParcelDeliveryDto updateParcelDelivery(ParcelDeliveryDto parcelDeliveryDto);
    
    public void deleteParcelDelivery(Long parcelDeliveryId);
    
    public List<ParcelDeliveryDto> getAllParcelDeliveryByStatus(String status, int[] range);
    
    public Long getAllParcelDeliveryByStatusCount(String status);
    
    public ParcelDeliveryDto findById(Long id);
    
    public List<ParcelDeliveryDto> getAllParcelDeliveryForDriverByStatus(String status, int[] range);
    
    public Long getAllParcelDeliveryForDriverByStatusCount(String status);
    
    public void pickupParcelDeliveryRequest(Long parcelDeliveryId);
    
    public void deliverParcel(Long parcelDeliveryId);
}

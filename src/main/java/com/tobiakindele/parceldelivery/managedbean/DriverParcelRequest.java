package com.tobiakindele.parceldelivery.managedbean;

import com.tobiakindele.parceldelivery.dto.ParcelDeliveryDto;
import com.tobiakindele.parceldelivery.persistence.services.ParcelDeliveryService;
import com.tobiakindele.parceldelivery.persistence.services.impl.ParcelDeliveryServiceImpl;
import com.tobiakindele.parceldelivery.utils.ConstantUtils;
import com.tobiakindele.parceldelivery.utils.MessageUtils;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author oyindamolaakindele
 */
@Named(value = "driverParcelRequest")
@RequestScoped
public class DriverParcelRequest {

    private ParcelDeliveryDto parcelDto;
    private final ParcelDeliveryService parcelDeliveryService;
    
    @PostConstruct
    public void init() {
        parcelDto = new ParcelDeliveryDto();
    }
    
    public DriverParcelRequest() {
        parcelDeliveryService = new ParcelDeliveryServiceImpl();
    }

    public ParcelDeliveryDto getParcelDto() {
        return parcelDto;
    }

    public void setParcelDto(ParcelDeliveryDto parcelDto) {
        this.parcelDto = parcelDto;
    }
    
    public void setSelected(Long selectedParcelId) {
        parcelDto = parcelDeliveryService.findById(selectedParcelId);
    }
    
    public String pickupOrDeliverParcelDeliveryRequest(){
        if(parcelDto.getId() != null) {
            if(ConstantUtils.PARCEL_DELIVERY_STATUS[0].equalsIgnoreCase(parcelDto.getStatus())) {
                parcelDeliveryService.pickupParcelDeliveryRequest(parcelDto.getId());
                MessageUtils.addSuccessMessageWithFlash("Parcel picked up for delivery successfully.");
            }
            if(ConstantUtils.PARCEL_DELIVERY_STATUS[3].equalsIgnoreCase(parcelDto.getStatus())) {
                parcelDeliveryService.deliverParcel(parcelDto.getId());
                MessageUtils.addSuccessMessageWithFlash("Parcel delivered successfully.");
            }
            
        }
        return "driver_home?faces-direct=true";
    }
}

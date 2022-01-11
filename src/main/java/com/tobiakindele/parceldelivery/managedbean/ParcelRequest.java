package com.tobiakindele.parceldelivery.managedbean;

import com.tobiakindele.parceldelivery.dto.ParcelDeliveryDto;
import com.tobiakindele.parceldelivery.persistence.services.ParcelDeliveryService;
import com.tobiakindele.parceldelivery.persistence.services.impl.ParcelDeliveryServiceImpl;
import com.tobiakindele.parceldelivery.utils.MessageUtils;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author oyindamolaakindele
 */
@Named(value = "parcelRequest")
@RequestScoped
public class ParcelRequest {

    private ParcelDeliveryDto parcelDto;
    
    private final ParcelDeliveryService parcelDeliveryService = new ParcelDeliveryServiceImpl();

    @PostConstruct
    public void init() {
        parcelDto = new ParcelDeliveryDto();
    }

    public ParcelDeliveryDto getParcelDto() {
        return parcelDto;
    }

    public void setParcelDto(ParcelDeliveryDto parcelDto) {
        this.parcelDto = parcelDto;
    }

    public String saveParcelDeliveryRequest() {
        parcelDeliveryService.createParcelDelivery(parcelDto);
        MessageUtils.addSuccessMessageWithFlash("Parcel delivery has been added successfully.");
        return "user_home?faces-redirect=true";
    }
    
    public String updateParcelDeliveryRequest() {
        parcelDeliveryService.updateParcelDelivery(parcelDto);
        MessageUtils.addSuccessMessageWithFlash("Parcel delivery has been updated successfully.");
        return "user_home?faces-redirect=true";
    }
    
    public String deleteParcelDeliveryRequest() {
        if (parcelDto.getId() != null) {
            parcelDeliveryService.deleteParcelDelivery(parcelDto.getId());
            MessageUtils.addSuccessMessageWithFlash("Parcel delivery has been deleted successfully.");
        }
        return "user_home?faces-redirect=true";
    }
    
    public void setSelected(Long selectedParcelId) {
        parcelDto = parcelDeliveryService.findById(selectedParcelId);
    }
}

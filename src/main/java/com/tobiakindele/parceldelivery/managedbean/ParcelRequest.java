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
    private final ParcelDeliveryService parcelDeliveryService;

    @PostConstruct
    public void init() {
        parcelDto = new ParcelDeliveryDto();
    }

    public ParcelRequest() {
        parcelDeliveryService = new ParcelDeliveryServiceImpl();
    }

    public ParcelDeliveryDto getParcelDto() {
        return parcelDto;
    }

    public void setParcelDto(ParcelDeliveryDto parcelDto) {
        this.parcelDto = parcelDto;
    }

    public String saveParcelDeliveryRequest() {
        parcelDeliveryService.createParcelDelivery(parcelDto);
        MessageUtils.addSuccessMessageWithFlash("Parcel delivery has been added successful.");
        return "user_home?faces-redirect=true";
    }

    public String getParcelDeliveryDetails(Long parcelId) {
        parcelDto = parcelDeliveryService.findById(parcelId);
        return "user_home?faces-redirect=true";
    }

    public void setSelected(Long selectedParcelId) {
        parcelDto = parcelDeliveryService.findById(selectedParcelId);
    }
}

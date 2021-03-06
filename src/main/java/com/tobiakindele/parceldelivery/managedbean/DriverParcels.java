package com.tobiakindele.parceldelivery.managedbean;

import com.tobiakindele.parceldelivery.dto.ParcelDeliveryDto;
import com.tobiakindele.parceldelivery.persistence.services.ParcelDeliveryService;
import com.tobiakindele.parceldelivery.persistence.services.impl.ParcelDeliveryServiceImpl;
import com.tobiakindele.parceldelivery.utils.ConstantUtils;
import com.tobiakindele.parceldelivery.utils.PaginationHelper;
import com.tobiakindele.parceldelivery.utils.Utils;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author oyindamolaakindele
 */
@Named(value = "dps")
@ViewScoped
public class DriverParcels implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private final ParcelDeliveryService parcelDeliveryService = new ParcelDeliveryServiceImpl();
    private DataModel<ParcelDeliveryDto> dataModel;
    private PaginationHelper pagination;
    private String status;
    
    public DataModel<ParcelDeliveryDto> getDataModel() {
        status = Utils.isEmpty(status) ? ConstantUtils.PARCEL_DELIVERY_STATUS[0] : status;
        dataModel = getPagination().createPageDataModel();

        return dataModel;
    }
    
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getCount() {
                    return parcelDeliveryService.getAllParcelDeliveryForDriverByStatusCount(status).intValue();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(parcelDeliveryService
                            .getAllParcelDeliveryForDriverByStatus(status, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }

            };
        }
        return pagination;
    }
    
    public void next() {
        getPagination().nextPage();
        recreateModel();
    }

    public void previous() {
        getPagination().previousPage();
        recreateModel();
    }
    
    private void recreateModel() {
        dataModel = null;
    }
    
    public void refreshData(ValueChangeEvent e){
        status = e.getNewValue().toString();
        getPagination();
        recreateModel();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

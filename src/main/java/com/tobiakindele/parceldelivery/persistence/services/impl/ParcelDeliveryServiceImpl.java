package com.tobiakindele.parceldelivery.persistence.services.impl;

import com.tobiakindele.parceldelivery.dto.ParcelDeliveryDto;
import com.tobiakindele.parceldelivery.dto.UserDto;
import com.tobiakindele.parceldelivery.mapper.ObjectMapper;
import com.tobiakindele.parceldelivery.models.ParcelDelivery;
import com.tobiakindele.parceldelivery.persistence.dao.ParcelDeliveryDao;
import com.tobiakindele.parceldelivery.persistence.dao.impl.ParcelDeliveryDaoImpl;
import com.tobiakindele.parceldelivery.persistence.services.ParcelDeliveryService;
import com.tobiakindele.parceldelivery.persistence.services.UserService;
import com.tobiakindele.parceldelivery.utils.ConstantUtils;
import com.tobiakindele.parceldelivery.utils.EmailUtil;
import com.tobiakindele.parceldelivery.utils.LoggerUtil;
import com.tobiakindele.parceldelivery.utils.SessionUtils;
import com.tobiakindele.parceldelivery.utils.Utils;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.mail.MessagingException;
import org.dozer.Mapper;
import org.dozer.MappingException;

/**
 *
 * @author oyindamolaakindele
 */
@Stateless
public class ParcelDeliveryServiceImpl implements ParcelDeliveryService {
    
    private static final Logger logger = Logger.getLogger(ParcelDeliveryServiceImpl.class.getName());
    
    private final ParcelDeliveryDao parcelDeliveryDao = new ParcelDeliveryDaoImpl();
    private final UserService userService = new UserServiceImpl();
    private static final Mapper mapper = ObjectMapper.getMapper();
    
    @Override
    public List<ParcelDeliveryDto> getAllParcelDeliveryByStatus(String status, int[] range){
        if(ConstantUtils.PARCEL_DELIVERY_STATUS[2].equalsIgnoreCase(status)){
            status = null;
        }
        List<ParcelDeliveryDto> parcelList = parcelDeliveryDao
                .getAllParcelDeliveryByStatus(status, SessionUtils.getUserId(), range);
        if(parcelList != null && !parcelList.isEmpty()){
            parcelList.forEach(p -> {
                if(p.getCreatedBy() != null)
                    p.setCreatedByDto(userService.findById(p.getCreatedBy()));
                if(p.getPickedupBy() != null)
                    p.setPickedByDto(userService.findById(p.getPickedupBy()));
            });
        }
        return parcelList;
    }

    @Override
    public Long getAllParcelDeliveryByStatusCount(String status) {
        if(ConstantUtils.PARCEL_DELIVERY_STATUS[2].equalsIgnoreCase(status)){
            status = null;
        }
        return parcelDeliveryDao.getAllParcelDeliveryByStatusCount(status, SessionUtils.getUserId());
    }

    @Override
    public ParcelDeliveryDto createParcelDelivery(ParcelDeliveryDto parcelDeliveryDto) {
        try {
            UserDto userDto = userService.findById(SessionUtils.getUserId());
            parcelDeliveryDto.setCreatedBy(userDto.getId());
            parcelDeliveryDto.setCreatedAt(new Date());
            parcelDeliveryDto.setStatus(ConstantUtils.PARCEL_DELIVERY_STATUS[0]);
            
            parcelDeliveryDto = parcelDeliveryDao.save(mapper.map(parcelDeliveryDto, ParcelDelivery.class));

            parcelDeliveryDto.setCreatedByDto(userDto);
            sendCreationConfirmation(parcelDeliveryDto, Utils.getServerURL(FacesContext.getCurrentInstance()));
            
            return parcelDeliveryDto;
        } catch (MappingException e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw e;
        }
    }
    
    @Override
    public ParcelDeliveryDto updateParcelDelivery(ParcelDeliveryDto parcelDeliveryDto) {
        try{
            ParcelDeliveryDto dbParcel = parcelDeliveryDao.read(parcelDeliveryDto.getId());
            if(dbParcel == null){
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Parcel does not exists",
                        "Parcel ID validation failed");
                throw new ValidatorException(msg);
            }
            if(!ConstantUtils.PARCEL_DELIVERY_STATUS[0].equalsIgnoreCase(dbParcel.getStatus())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Parcel update request failed because it is " + dbParcel.getStatus(),
                        "Parcel status validation failed");
                throw new ValidatorException(msg);
            }
            dbParcel.setName(parcelDeliveryDto.getName());
            dbParcel.setDescription(parcelDeliveryDto.getDescription());
            dbParcel.setPickupAddress(parcelDeliveryDto.getPickupAddress());
            dbParcel.setDestinationAddress(parcelDeliveryDto.getDestinationAddress());
            parcelDeliveryDto = parcelDeliveryDao.update(mapper.map(dbParcel, ParcelDelivery.class));
            
            return parcelDeliveryDto;
        } catch(MappingException e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw e;
        }
    }
    
    @Override
    public void deleteParcelDelivery(Long parcelDeliveryId) {
        try{
            ParcelDeliveryDto dbParcel = parcelDeliveryDao.read(parcelDeliveryId);
            if(dbParcel == null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Parcel does not exists",
                        "Parcel ID validation failed");
                throw new ValidatorException(msg);
            }
            if(!ConstantUtils.PARCEL_DELIVERY_STATUS[0].equalsIgnoreCase(dbParcel.getStatus())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Parcel deletion request failed because it is " + dbParcel.getStatus(),
                        "Parcel deletion validation failed");
                throw new ValidatorException(msg);
            }
            parcelDeliveryDao.delete(mapper.map(dbParcel, ParcelDelivery.class));
        } catch(ValidatorException | MappingException e){
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw e;
        }
    }
    
    @Override
    public ParcelDeliveryDto findById(Long id) {
        ParcelDeliveryDto parcelDeliveryDto = parcelDeliveryDao.read(id);
        if(parcelDeliveryDto != null){
            if(parcelDeliveryDto.getCreatedBy() != null)
                parcelDeliveryDto.setCreatedByDto(userService.findById(parcelDeliveryDto.getCreatedBy()));
            if(parcelDeliveryDto.getPickedupBy() != null)
                parcelDeliveryDto.setPickedByDto(userService.findById(parcelDeliveryDto.getPickedupBy()));
        }
        return parcelDeliveryDto;
    }

    private void sendCreationConfirmation(ParcelDeliveryDto parcelDeliveryDto, String serverURL) {
        try{
            String subject = "Parcel Delivery Request Confirmation";
            String message = "Dear [[name]],<br><br>"
                    + "This is to notify you that the following request has been created and will be picked up by a Driver shortly;<br>"
                    + "<p> Parcel Name: [[parcelName]] <br>"
                    + "Parcel Description: [[parcelDescription]] <br>"
                    + "Pickup Address: [[pickupAddress]] <br>"
                    + "Destination Address: [[destinationAddress]] <br>"
                    + "Status: [[status]] </p><br>"
                    + "Kindly <a href=\"[[URL]]\" target=\"_self\">visit</a> to view the request <br>"
                    + "or click the link below:<br>"
                    + "[[URL]] <br><br>"
                    + "Thank you,<br>"
                    + "Delis parcel delivery.";
            
            String URL = serverURL + "/user_home.xhtml";
            
            message = message.replace("[[name]]", parcelDeliveryDto.getCreatedByDto().getFirstName());
            message = message.replace("[[parcelName]]", parcelDeliveryDto.getName());
            message = message.replace("[[parcelDescription]]", parcelDeliveryDto.getDescription());
            message = message.replace("[[pickupAddress]]", parcelDeliveryDto.getPickupAddress());
            message = message.replace("[[destinationAddress]]", parcelDeliveryDto.getDestinationAddress());
            message = message.replace("[[status]]", parcelDeliveryDto.getStatus());
            message = message.replace("[[URL]]", URL);
            
            EmailUtil.sendEmail(subject, message, parcelDeliveryDto.getCreatedByDto().getEmail());
        } catch(MessagingException e){
            LoggerUtil.logError(logger, Level.SEVERE, e);
        }
    }
}

package com.tobiakindele.parceldelivery.testmodelutils;

import com.tobiakindele.parceldelivery.dto.ParcelDeliveryDto;
import com.tobiakindele.parceldelivery.dto.UserDto;
import com.tobiakindele.parceldelivery.utils.ConstantUtils;
import java.util.Date;

/**
 *
 * @author oyindamolaakindele
 */
public class TestModelUtils {
    
    public static UserDto getUserDto(){
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setFirstName("JUnit");
        userDto.setLastName("Mockito");
        userDto.setEmail("dummy@test.com");
        userDto.setPassword("12345");
        userDto.setConfirmPassword("12345");
        userDto.setEmailVerified(Boolean.TRUE);
        return userDto;
    }
    
    public static ParcelDeliveryDto getParcelDeliveryDto() {
        ParcelDeliveryDto parcel = new ParcelDeliveryDto();
        parcel.setId(1L);
        parcel.setName("JUnit");
        parcel.setDescription("Mockito");
        parcel.setPickupAddress("Oracle inc");
        parcel.setDestinationAddress("Oracle inc");
        parcel.setStatus(ConstantUtils.PARCEL_DELIVERY_STATUS[0]);
        parcel.setCreatedAt(new Date());
        parcel.setCreatedBy(Long.MIN_VALUE);
        parcel.setPickedupBy(Long.MIN_VALUE);
        parcel.setCreatedByDto(getUserDto());
        parcel.setPickedByDto(getUserDto());
        return parcel;
    }
}

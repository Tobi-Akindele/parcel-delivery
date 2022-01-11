package com.tobiakindele.parceldelivery.persistence.services.impl;

import com.tobiakindele.parceldelivery.dto.ParcelDeliveryDto;
import com.tobiakindele.parceldelivery.dto.UserDto;
import com.tobiakindele.parceldelivery.persistence.dao.ParcelDeliveryDao;
import com.tobiakindele.parceldelivery.persistence.dao.impl.ParcelDeliveryDaoImpl;
import com.tobiakindele.parceldelivery.persistence.services.UserService;
import com.tobiakindele.parceldelivery.testmodelutils.TestModelUtils;
import com.tobiakindele.parceldelivery.utils.ConstantUtils;
import java.util.List;
import javax.faces.validator.ValidatorException;
import org.hamcrest.collection.IsEmptyCollection;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author oyindamolaakindele
 */
public class ParcelDeliveryServiceImplTest {

    private ParcelDeliveryServiceImpl instance;

    private UserService userService;

    private ParcelDeliveryDao parcelDeliveryDao;

    @Before
    public void setUp() {
        instance = new ParcelDeliveryServiceImpl();
        parcelDeliveryDao = mock(ParcelDeliveryDaoImpl.class);
        userService = mock(UserServiceImpl.class);
        instance.setParcelDeliveryDao(parcelDeliveryDao);
        instance.setUserService(userService);
    }

    /**
     * Test of getAllParcelDeliveryByStatus method, of class
     * ParcelDeliveryServiceImpl. Success
     */
    @Test
    public void testGetAllParcelDeliveryByStatusSuccess() {
        ParcelDeliveryDto parcel = TestModelUtils.getParcelDeliveryDto();
        List<ParcelDeliveryDto> parcels = mock(List.class);
        parcels.add(parcel);
        when(parcelDeliveryDao.getAllParcelDeliveryByStatus(any(), any(), any())).thenReturn(parcels);
        List<ParcelDeliveryDto> result = instance.getAllParcelDeliveryByStatus("status", new int[2]);
        assertThat(result, not(IsEmptyCollection.empty()));
    }

    /**
     * Test of getAllParcelDeliveryByStatusCount method, of class
     * ParcelDeliveryServiceImpl. Success
     */
    @Test
    public void testGetAllParcelDeliveryByStatusCountSuccess() {
        Long expResult = 1L;
        when(parcelDeliveryDao.getAllParcelDeliveryByStatusCount(any(), any())).thenReturn(expResult);
        Long result = instance.getAllParcelDeliveryByStatusCount("status");
        assertEquals(expResult, result);
    }

    /**
     * Test of createParcelDelivery method, of class ParcelDeliveryServiceImpl.
     * Success
     */
    @Test
    public void testCreateParcelDeliverySuccess() {
        UserDto userDto = TestModelUtils.getUserDto();
        ParcelDeliveryDto parcel = TestModelUtils.getParcelDeliveryDto();
        parcel.setCreatedByDto(userDto);
        when(userService.findById(any())).thenReturn(userDto);
        when(parcelDeliveryDao.save(any())).thenReturn(parcel);
        ParcelDeliveryDto result = instance.createParcelDelivery(parcel);
        assertEquals(1L, (long) result.getId());
    }

    /**
     * Test of updateParcelDelivery method, of class ParcelDeliveryServiceImpl.
     * Success
     */
    @Test
    public void testUpdateParcelDeliverySuccess() {
        ParcelDeliveryDto parcel = TestModelUtils.getParcelDeliveryDto();
        when(parcelDeliveryDao.read(any())).thenReturn(parcel);
        when(parcelDeliveryDao.update(any())).thenReturn(parcel);
        ParcelDeliveryDto result = instance.updateParcelDelivery(parcel);
        assertEquals(1L, (long) result.getId());
    }

    /**
     * Test of updateParcelDelivery method, of class ParcelDeliveryServiceImpl.
     * Fail with Parcel not found.
     */
    @Test
    public void testUpdateParcelDeliveryFailWithParcelNotFound() {
        try {
            ParcelDeliveryDto parcel = TestModelUtils.getParcelDeliveryDto();
            when(parcelDeliveryDao.read(any())).thenReturn(null);
            instance.updateParcelDelivery(parcel);
        } catch (ValidatorException e) {
            assertEquals("Parcel does not exist", e.getMessage());
        }
    }

    /**
     * Test of updateParcelDelivery method, of class ParcelDeliveryServiceImpl.
     * Fail with Invalid status.
     */
    @Test
    public void testUpdateParcelDeliveryFailWithInvalidStatus() {
        ParcelDeliveryDto parcel = TestModelUtils.getParcelDeliveryDto();
        try {
            parcel.setStatus(ConstantUtils.PARCEL_DELIVERY_STATUS[1]);
            when(parcelDeliveryDao.read(any())).thenReturn(parcel);
            instance.updateParcelDelivery(parcel);
        } catch (ValidatorException e) {
            assertEquals("Parcel update request failed because it is " + parcel.getStatus(), e.getMessage());
        }
    }

    /**
     * Test of deleteParcelDelivery method, of class ParcelDeliveryServiceImpl.
     * Success
     */
    @Test
    public void testDeleteParcelDeliverySuccess() {
        ParcelDeliveryDto parcel = TestModelUtils.getParcelDeliveryDto();
        when(parcelDeliveryDao.read(any())).thenReturn(parcel);
        doNothing().when(parcelDeliveryDao).delete(any());
        instance.deleteParcelDelivery(any());
        assertTrue(true);
    }

    /**
     * Test of findById method, of class ParcelDeliveryServiceImpl. Success
     */
    @Test
    public void testFindByIdSuccess() {
        ParcelDeliveryDto parcel = TestModelUtils.getParcelDeliveryDto();
        UserDto userDto = TestModelUtils.getUserDto();
        when(parcelDeliveryDao.read(any())).thenReturn(parcel);
        when(userService.findById(any())).thenReturn(userDto);
        ParcelDeliveryDto result = instance.findById(any());
        assertNotNull(result);
    }

    /**
     * Test of getAllParcelDeliveryForDriverByStatus method, of class
     * ParcelDeliveryServiceImpl. Success
     */
    @Test
    public void testGetAllParcelDeliveryForDriverByStatusSuccess() {
        ParcelDeliveryDto parcel = TestModelUtils.getParcelDeliveryDto();
        List<ParcelDeliveryDto> parcels = mock(List.class);
        parcels.add(parcel);
        when(parcelDeliveryDao.getAllParcelDeliveryForDriverByStatus(any(), any(), any())).thenReturn(parcels);
        List<ParcelDeliveryDto> result = instance.getAllParcelDeliveryForDriverByStatus("status", new int[2]);
        assertThat(result, not(IsEmptyCollection.empty()));
    }

    /**
     * Test of getAllParcelDeliveryForDriverByStatusCount method, of class
     * ParcelDeliveryServiceImpl. Success
     */
    @Test
    public void testGetAllParcelDeliveryForDriverByStatusCountSuccess() {
        Long expResult = 1L;
        when(parcelDeliveryDao.getAllParcelDeliveryForDriverByStatusCount(any(), any())).thenReturn(1L);
        Long result = instance.getAllParcelDeliveryForDriverByStatusCount("status");
        assertEquals(expResult, result);
    }

    /**
     * Test of pickupParcelDeliveryRequest method, of class
     * ParcelDeliveryServiceImpl. Success
     */
    @Test
    public void testPickupParcelDeliveryRequestSuccess() {
        ParcelDeliveryDto parcel = TestModelUtils.getParcelDeliveryDto();
        UserDto userDto = TestModelUtils.getUserDto();
        when(parcelDeliveryDao.read(any())).thenReturn(parcel);
        when(parcelDeliveryDao.update(any())).thenReturn(parcel);
        when(userService.findById(any())).thenReturn(userDto);
        instance.pickupParcelDeliveryRequest(any());
        assertTrue(true);
    }

    /**
     * Test of deliverParcel method, of class ParcelDeliveryServiceImpl. Success
     */
    @Test
    public void testDeliverParcelSuccess() {
        ParcelDeliveryDto parcel = TestModelUtils.getParcelDeliveryDto();
        parcel.setStatus(ConstantUtils.PARCEL_DELIVERY_STATUS[3]);
        UserDto userDto = TestModelUtils.getUserDto();
        when(parcelDeliveryDao.read(any())).thenReturn(parcel);
        when(parcelDeliveryDao.update(any())).thenReturn(parcel);
        when(userService.findById(any())).thenReturn(userDto);
        instance.deliverParcel(any());
        assertTrue(true);
    }
}

package com.tobiakindele.parceldelivery.persistence.services.impl;

import com.tobiakindele.parceldelivery.dto.UserDto;
import com.tobiakindele.parceldelivery.persistence.dao.UserDao;
import com.tobiakindele.parceldelivery.persistence.dao.impl.UserDaoImpl;
import com.tobiakindele.parceldelivery.testmodelutils.TestModelUtils;
import javax.faces.validator.ValidatorException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author oyindamolaakindele
 */
public class UserServiceImplTest {

    private UserServiceImpl instance;
    private UserDao userDao;

    @Before
    public void setUp() {
        instance = new UserServiceImpl();
        userDao = mock(UserDaoImpl.class);
        instance.setUserDao(userDao);
    }

    /**
     * Test of createUser method, of class UserServiceImpl. Success.
     */
    @Test
    public void testCreateUserSuccess() {
        UserDto userDto = TestModelUtils.getUserDto();
        when(userDao.save(any())).thenReturn(userDto);
        when(userDao.findByEmail(any())).thenReturn(null);
        UserDto result = instance.createUser(userDto);
        assertEquals(1L, (long) result.getId());
    }

    /**
     * Test of createUser method, of class UserServiceImpl. Fail with Password
     * match error.
     */
    @Test
    public void testCreateUserFailWithPasswordMatchError() {
        try {
            UserDto userDto = TestModelUtils.getUserDto();
            userDto.setConfirmPassword("54321");
            instance.createUser(userDto);
        } catch (ValidatorException e) {
            assertEquals("Password does not match.", e.getMessage());
        }
    }

    /**
     * Test of createUser method, of class UserServiceImpl. Fail with Duplicate
     * email error.
     */
    @Test
    public void testCreateUserFailWithDuplicateError() {
        try {
            UserDto userDto = TestModelUtils.getUserDto();
            when(userDao.findByEmail(any())).thenReturn(userDto);
            instance.createUser(userDto);
        } catch (ValidatorException e) {
            assertEquals("Email already exists.", e.getMessage());
        }
    }

    /**
     * Test of findByEmail method, of class UserServiceImpl. Success
     */
    @Test
    public void testFindByEmailSuccess() {
        UserDto expResult = TestModelUtils.getUserDto();
        when(userDao.findByEmail(any())).thenReturn(expResult);
        UserDto result = instance.findByEmail(any());
        assertNotNull(result);
    }

    /**
     * Test of login method, of class UserServiceImpl. Success.
     */
    @Test
    public void testLoginSuccess() {
        UserDto userDto = TestModelUtils.getUserDto();
        userDto.setPassword("$2a$10$fzZ198LXe11y2EVWy.31K.HyUmmn3b6jPSSCOLL7GvQETSaERs47a");
        when(userDao.findByEmail(any())).thenReturn(userDto);
        UserDto result = instance.login(any(), userDto.getConfirmPassword());
        assertEquals(userDto, result);
    }

    /**
     * Test of login method, of class UserServiceImpl. Fail with Email not found
     * error.
     */
    @Test
    public void testLoginFailWithEmailError() {
        try {
            when(userDao.findByEmail(any())).thenReturn(null);
            instance.login(anyString(), TestModelUtils.getUserDto().getPassword());
        } catch (ValidatorException e) {
            assertEquals("Email does not exist.", e.getMessage());
        }
    }

    /**
     * Test of login method, of class UserServiceImpl. Fail with Not verified
     * email.
     */
    @Test
    public void testLoginFailWithNotVerifiedEmail() {
        try {
            UserDto userDto = TestModelUtils.getUserDto();
            userDto.setEmailVerified(Boolean.FALSE);
            when(userDao.findByEmail(any())).thenReturn(userDto);
            instance.login(anyString(), userDto.getPassword());
        } catch (ValidatorException e) {
            assertEquals("Email associated with this account has not been verified.", e.getMessage());
        }
    }

    /**
     * Test of login method, of class UserServiceImpl. Password not match error.
     */
    @Test
    public void testLoginFailWithPasswordError() {
        try {
            UserDto userDto = TestModelUtils.getUserDto();
            userDto.setPassword("$2a$10$fzZ198LXe11y2EVWy.31K.HyUmmn3b6jPSSCOLL7GvQETSaERs47a");
            when(userDao.findByEmail(any())).thenReturn(userDto);
            instance.login(anyString(), "54321");
        } catch (ValidatorException e) {
            assertEquals("Incorrect password.", e.getMessage());
        }
    }

    /**
     * Test of logout method, of class UserServiceImpl. Success
     */
    @Test
    public void testLogout() {
        instance.logout();
        assertTrue(true);
    }

    /**
     * Test of verifyUser method, of class UserServiceImpl. Success
     */
    @Test
    public void testVerifyUser() {
        UserDto userDto = TestModelUtils.getUserDto();
        boolean expResult = true;
        when(userDao.findByVerificationCode(any())).thenReturn(userDto);
        boolean result = instance.verifyUser(any());
        assertEquals(expResult, result);
    }

    /**
     * Test of findById method, of class UserServiceImpl. Success
     */
    @Test
    public void testFindById() {
        UserDto userDto = TestModelUtils.getUserDto();
        when(userDao.read(any())).thenReturn(userDto);
        UserDto result = instance.findById(any());
        assertNotNull(result);
    }
}

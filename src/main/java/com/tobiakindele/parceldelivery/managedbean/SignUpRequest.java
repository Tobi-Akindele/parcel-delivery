package com.tobiakindele.parceldelivery.managedbean;

import com.tobiakindele.parceldelivery.dto.AddressDto;
import com.tobiakindele.parceldelivery.dto.UserDto;
import com.tobiakindele.parceldelivery.enums.UserType;
import com.tobiakindele.parceldelivery.persistence.services.UserService;
import com.tobiakindele.parceldelivery.persistence.services.impl.UserServiceImpl;
import com.tobiakindele.parceldelivery.utils.MessageUtils;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author oyindamolaakindele
 */
@Named(value = "signUpRequest")
@RequestScoped
public class SignUpRequest {

    private UserDto userDto;

    private final UserService userService;
    
    @PostConstruct
    public void init() {
        userDto = new UserDto(new AddressDto());
    }

    /**
     * Creates a new instance of SignUpRequest
     */
    public SignUpRequest() {
        this.userService = new UserServiceImpl();
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
    
    public String registerUser() {
        userDto.setUserType(UserType.USER.name());
        userService.createUser(userDto);
        MessageUtils.addSuccessMessageWithFlash("Account creation successful.");
        return "login?faces-redirect=true";
    }
    
    public String registerDriver() {
        userDto.setUserType(UserType.DRIVER.name());
        userService.createUser(userDto);
        MessageUtils.addSuccessMessageWithFlash("Account creation successful.");
        return "login?faces-redirect=true";
    }
}

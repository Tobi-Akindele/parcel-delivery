package com.tobiakindele.parceldelivery.managedbean;

import com.tobiakindele.parceldelivery.dto.UserDto;
import com.tobiakindele.parceldelivery.persistence.services.UserService;
import com.tobiakindele.parceldelivery.persistence.services.impl.UserServiceImpl;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author oyindamolaakindele
 */

@Named(value = "loginRequest")
@SessionScoped
public class LoginRequest implements Serializable {
    
    protected static final long serialVersionUID = 1L;

    private String email;
    private String password;
    private UserDto userDto;

    private final UserService userService = new UserServiceImpl();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
    
    public String requestLogin(){
        userDto = userService.login(email, password);
        String page = null;
        switch(userDto.getUserType()){
            case "USER":
                page = "user_home?faces-redirect=true";
            break;
            case "DRIVER":
                page = "driver_home?faces-redirect=true";
            break;
        }
        return page;
    }
    
    public String requestLogout() {
        userService.logout();
        return "login?faces-redirect=true";
    }
}

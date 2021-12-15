package com.tobiakindele.parceldelivery.managedbean;

import com.tobiakindele.parceldelivery.models.User;
import com.tobiakindele.parceldelivery.persistence.services.UserService;
import com.tobiakindele.parceldelivery.persistence.services.impl.UserServiceImpl;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author oyindamolaakindele
 */

@Named(value = "loginRequest")
@RequestScoped
public class LoginRequest {

    private String email;
    private String password;
    private User user;
    
    private final UserService userService = new UserServiceImpl();
    
    /**
     * Creates a new instance of LoginRequest
     */
    public LoginRequest() {
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String requestLogin(){
        user = userService.login(email, password);
        
        String page = null;
        switch(user.getUserType()){
            case "USER":
                page = "user_home";
            break;
            case "DRIVER":
                page = "driver_home";
            break;
        }
        return page;
    }
    
    public String requestLogout(){
        return userService.logout();
    }
}

package com.tobiakindele.parceldelivery.managedbean;

import com.tobiakindele.parceldelivery.enums.UserType;
import com.tobiakindele.parceldelivery.models.Address;
import com.tobiakindele.parceldelivery.models.User;
import com.tobiakindele.parceldelivery.persistence.services.UserService;
import com.tobiakindele.parceldelivery.persistence.services.impl.UserServiceImpl;
import com.tobiakindele.parceldelivery.utils.ConstantUtils;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author oyindamolaakindele
 */
@Named(value = "signUpRequest")
@RequestScoped
public class SignUpRequest {

    private User user;

    private UserService userService = new UserServiceImpl();
    
    @PostConstruct
    public void init() {
        user = new User(new Address());
    }

    /**
     * Creates a new instance of SignUpRequest
     */
    public SignUpRequest() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String registerUser(){
        user.setUserType(UserType.USER.name());
        userService.createUser(user);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(ConstantUtils.SIGN_UP_SUCCESS, new FacesMessage("User signup", "Account creation successful."));
        context.getExternalContext().getFlash().setKeepMessages(true);
        return "login";
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

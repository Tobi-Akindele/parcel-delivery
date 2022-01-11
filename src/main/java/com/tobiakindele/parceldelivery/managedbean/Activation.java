package com.tobiakindele.parceldelivery.managedbean;

import com.tobiakindele.parceldelivery.persistence.services.UserService;
import com.tobiakindele.parceldelivery.persistence.services.impl.UserServiceImpl;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author oyindamolaakindele
 */

@Named(value = "activation")
@RequestScoped
public class Activation {

    private boolean valid;

    private final UserService userService = new UserServiceImpl();

    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String value = request.getParameter("key");
        valid = userService.verifyUser(value);
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}

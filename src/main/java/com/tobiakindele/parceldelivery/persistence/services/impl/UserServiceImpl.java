package com.tobiakindele.parceldelivery.persistence.services.impl;

import com.tobiakindele.parceldelivery.models.User;
import com.tobiakindele.parceldelivery.persistence.dao.UserDao;
import com.tobiakindele.parceldelivery.persistence.services.UserService;
import com.tobiakindele.parceldelivery.utils.ConstantUtils;
import com.tobiakindele.parceldelivery.utils.Utils;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author oyindamolaakindele
 */

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public User createUser(User user) throws ValidatorException {

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            FacesMessage msg = new FacesMessage("E-mail validation failed.", "Email is invalid.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(ConstantUtils.PASSWORD_FORM_UI_ID, msg);
            throw new ValidatorException(msg);
        }
        user.setDateJoined(new Date());
        user.setPassword(Utils.encryptPassword(user.getPassword()));

        return userDao.save(user);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}

package com.tobiakindele.parceldelivery.persistence.services.impl;

import com.tobiakindele.parceldelivery.models.User;
import com.tobiakindele.parceldelivery.persistence.dao.UserDao;
import com.tobiakindele.parceldelivery.persistence.dao.impl.UserDaoImpl;
import com.tobiakindele.parceldelivery.persistence.services.UserService;
import com.tobiakindele.parceldelivery.utils.ConstantUtils;
import com.tobiakindele.parceldelivery.utils.Utils;
import java.util.Date;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author oyindamolaakindele
 */

@Stateless
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User createUser(User user) throws ValidatorException {

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            FacesMessage msg = new FacesMessage("Password validation failed.", "Password does not match.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(ConstantUtils.PASSWORD_FORM_UI_ID, msg);
            throw new ValidatorException(msg);
        }
        if (userDao.findByEmail(user.getEmail()) != null) {
            FacesMessage msg = new FacesMessage("Unique Email validation failed.", "Email already exists.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(ConstantUtils.EMAIL_FORM_UI_ID, msg);
            throw new ValidatorException(msg);
        }
        user.setDateJoined(new Date());
        user.setPassword(Utils.encryptPassword(user.getPassword()));
        user.setEmailVerified(Boolean.FALSE);

        return userDao.save(user);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}

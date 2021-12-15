package com.tobiakindele.parceldelivery.persistence.services.impl;

import com.tobiakindele.parceldelivery.models.User;
import com.tobiakindele.parceldelivery.persistence.dao.UserDao;
import com.tobiakindele.parceldelivery.persistence.dao.impl.UserDaoImpl;
import com.tobiakindele.parceldelivery.persistence.services.UserService;
import com.tobiakindele.parceldelivery.utils.SessionUtils;
import com.tobiakindele.parceldelivery.utils.Utils;
import java.util.Date;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author oyindamolaakindele
 */

@Stateless
public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public User createUser(User user) throws ValidatorException {

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Password does not match.",
                    "Password match validation failed.");
            throw new ValidatorException(msg);
        }
        if (userDao.findByEmail(user.getEmail()) != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Email already exists.", 
                    "Unique Email validation failed");
            throw new ValidatorException(msg);
        }
        user.setDateJoined(new Date());
        user.setPassword(Utils.encryptPassword(user.getPassword()));
        user.setEmailVerified(Boolean.FALSE);
        user.setConfirmPassword(null);

        return userDao.save(user);
    }
    
    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    
    @Override
    public User login(String email, String password){
        User user = userDao.findByEmail(email);
        if(user == null){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Email does not exist.",
                    "Email validation failed.");
            throw new ValidatorException(msg);
        }
        if(!Utils.passwordMatches(password, user.getPassword())){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Incorrect password.",
                    "Password validation failed.");
            throw new ValidatorException(msg);
        }
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("email", user.getEmail());
        session.setAttribute("userId", user.getId());
        
        return user;
    }
    
    @Override
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login";
    }
}

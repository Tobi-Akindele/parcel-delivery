package com.tobiakindele.parceldelivery.persistence.services.impl;

import com.tobiakindele.parceldelivery.dto.UserDto;
import com.tobiakindele.parceldelivery.mapper.ObjectMapper;
import com.tobiakindele.parceldelivery.models.User;
import com.tobiakindele.parceldelivery.persistence.dao.UserDao;
import com.tobiakindele.parceldelivery.persistence.dao.impl.UserDaoImpl;
import com.tobiakindele.parceldelivery.persistence.services.UserService;
import com.tobiakindele.parceldelivery.utils.EmailUtil;
import com.tobiakindele.parceldelivery.utils.LoggerUtil;
import com.tobiakindele.parceldelivery.utils.SessionUtils;
import com.tobiakindele.parceldelivery.utils.Utils;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import org.dozer.Mapper;
import org.dozer.MappingException;

/**
 *
 * @author oyindamolaakindele
 */

@Stateless
public class UserServiceImpl implements UserService {
    
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    private final UserDao userDao = new UserDaoImpl();
    private static final Mapper mapper = ObjectMapper.getMapper();

    @Override
    public UserDto createUser(UserDto userDto) {

        try {
            if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Password does not match.",
                        "Password match validation failed.");
                throw new ValidatorException(msg);
            }
            if (userDao.findByEmail(userDto.getEmail()) != null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Email already exists.",
                        "Unique Email validation failed");
                throw new ValidatorException(msg);
            }
            userDto.setDateJoined(new Date());
            userDto.setPassword(Utils.encryptPassword(userDto.getPassword()));
            userDto.setEmailVerified(Boolean.FALSE);
            userDto.setConfirmPassword(null);
            userDto.setVerificationCode(Utils.generateVerificationCode());

            userDto = userDao.save(mapper.map(userDto, User.class));
            
            sendRegistrationVerification(userDto, Utils.getServerURL(FacesContext.getCurrentInstance()));
            
            return userDto;
        } catch (ValidatorException | MappingException e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw e;
        }
    }
    
    @Override
    public UserDto findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    
    @Override
    public UserDto login(String email, String password){
        UserDto userDto = userDao.findByEmail(email);
        if(userDto == null){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Email does not exist.",
                    "Email validation failed.");
            throw new ValidatorException(msg);
        }
        if(!userDto.getEmailVerified()){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Email associated with this account has not been verified.",
                    "Account is not active.");
            throw new ValidatorException(msg);
        }
        if(!Utils.passwordMatches(password, userDto.getPassword())){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Incorrect password.",
                    "Password validation failed.");
            throw new ValidatorException(msg);
        }
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("email", userDto.getEmail());
        session.setAttribute("userId", userDto.getId());
        
        return userDto;
    }
    
    @Override
    public void logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
    }
    
    @Override
    public boolean verifyUser(String verificationCode){
        boolean verified = false;
        UserDto userDto = userDao.findByVerificationCode(verificationCode);
        if(userDto != null){
            userDto.setVerificationCode(null);
            userDto.setEmailVerified(true);
            userDao.update(mapper.map(userDto, User.class));
            verified = true;
        }
        return verified;
    }

    private void sendRegistrationVerification(UserDto userDto, String serverURL) {
        try {
            String subject = "Account Activation";
            String message = "Dear [[name]],<br>"
                    + "Please click the link below to verify your registration:<br>"
                    + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3><br> "
                    + "or visit the link below on your browser <br>"
                    + "[[URL]]<br><br>"
                    + "Thank you,<br>"
                    + "Delis parcel delivery.";
            message = message.replace("[[name]]", userDto.getFirstName());
            String verifyLink = serverURL + "/activate.xhtml?key=" + userDto.getVerificationCode();
            message = message.replace("[[URL]]", verifyLink);
            EmailUtil.sendEmail(subject, message, userDto.getEmail());
        } catch (MessagingException e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
        }
    }

    @Override
    public UserDto findById(Long id) {
        return userDao.read(id);
    }
}

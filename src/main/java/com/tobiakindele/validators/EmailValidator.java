/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tobiakindele.validators;

import com.tobiakindele.utils.ConstantUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author oyindamolaakindele
 */
@FacesValidator("com.tobiakindele.validators.EmailValidator")
public class EmailValidator implements Validator{

    private final Pattern pattern;
    private Matcher matcher;
    
    public EmailValidator(){
        pattern = Pattern.compile(ConstantUtils.EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
    }
    
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {
        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()) {
            FacesMessage msg
                    = new FacesMessage("E-mail validation failed.", "Email is invalid.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}

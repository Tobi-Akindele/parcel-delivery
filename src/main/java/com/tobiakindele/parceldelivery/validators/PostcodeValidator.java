package com.tobiakindele.parceldelivery.validators;

import com.tobiakindele.parceldelivery.utils.ConstantUtils;
import com.tobiakindele.parceldelivery.utils.Utils;
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

@FacesValidator("com.tobiakindele.parceldelivery.validators.PostcodeValidator")
public class PostcodeValidator implements Validator {

    private final Pattern pattern;
    private Matcher matcher;
    
    public PostcodeValidator(){
        pattern = Utils.compilePattern(ConstantUtils.POSTCODE_REGEX);
    }
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {
        matcher = pattern.matcher(value.toString());
        if (!matcher.matches()) {
            FacesMessage msg
                    = new FacesMessage("Postcode validation failed.", "Postcode is invalid.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}

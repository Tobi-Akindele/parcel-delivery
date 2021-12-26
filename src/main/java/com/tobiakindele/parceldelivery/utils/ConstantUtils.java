package com.tobiakindele.parceldelivery.utils;

/**
 *
 * @author oyindamolaakindele
 */

public class ConstantUtils {
    
    private ConstantUtils(){};
    
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
            + "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*"
            + "(\\.[A-Za-z]{2,})$";
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String PASSWORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$";
    public static final String POSTCODE_REGEX = "^[A-Z]{1,2}[0-9]{1,2}[A-Z]?\\s?[0-9][A-Z]{2}$";
    public static final String PERSIST_UNIT = "delis-parcel-delivery";
    public static final String PASSWORD_FORM_UI_ID = "userSignUpForm:password";
    public static final String EMAIL_FORM_UI_ID = "userSignUpForm:email";
    public static final String SIGN_UP_SUCCESS = "loginForm:successSignUpMessage";
    public static final String SYS_ADMIN_EMAIL = "system_support@delisparceldelivery.co.uk";
    public static final String[] PARCEL_DELIVERY_STATUS = { "Pending", "Completed", "All", "In Transit" };
}

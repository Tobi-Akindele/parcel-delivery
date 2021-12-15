package com.tobiakindele.parceldelivery.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author oyindamolaakindele
 */

public class Utils {

    private static final Logger logger = Logger.getLogger(Utils.class.getName());

    public static boolean isEmpty(Object obj) {
        return Objects.isNull(obj) || obj.toString().trim().isEmpty() 
                || obj.toString().trim().equalsIgnoreCase("null");
    }

    public static Date getDateByFormat(String format, String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = (Date) dateFormat.parse(value);
        } catch (ParseException e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
        }
        return date;
    }

    public static String getDateStringByFormat(String format, Date value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String date = dateFormat.format(value);

        return date;
    }
    
    public static Pattern compilePattern(String regex, int option){
        return Pattern.compile(regex, option);
    }
    
    public static Pattern compilePattern(String regex){
        return Pattern.compile(regex);
    }
    
    public static String encryptPassword(String src){
        return new BCryptPasswordEncoder().encode(src);
    }
    
    public static String extractExceptionMessage(String src){
        if(isEmpty(src))
            return src;
        return src.substring(src.lastIndexOf(":") + 1).trim();
    }
    
    public static boolean passwordMatches(String rawPassword, String encodedPassword){
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }
}

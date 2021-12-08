package com.tobiakindele.parceldelivery.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oyindamolaakindele
 */
public class Utils {

    private static final Logger logger = Logger.getLogger(Utils.class.getName());

    public static boolean isEmpty(Object obj) {
        return Objects.isNull(obj) || obj.toString().trim().isEmpty();
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
}

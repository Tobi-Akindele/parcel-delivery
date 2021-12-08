package com.tobiakindele.parceldelivery.utils;
import java.util.logging.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;

/**
 *
 * @author oyindamolaakindele
 */
public class LoggerUtil {
    
    public static void logError(Logger logger, Level level, Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        logger.log(level, sw.toString());
    }
}

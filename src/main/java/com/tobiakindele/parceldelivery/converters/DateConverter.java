package com.tobiakindele.parceldelivery.converters;

import com.tobiakindele.parceldelivery.utils.ConstantUtils;
import com.tobiakindele.parceldelivery.utils.LoggerUtil;
import com.tobiakindele.parceldelivery.utils.Utils;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.Date;
import java.util.logging.Level;
import javax.faces.convert.ConverterException;
import java.util.logging.Logger;

/**
 *
 * @author oyindamolaakindele
 */

@FacesConverter(value = "dateConverter")
public class DateConverter implements Converter {
    
    private static final Logger logger = Logger.getLogger(DateConverter.class.getName());

    @Override
    public Date getAsObject(FacesContext fc, UIComponent uic, String value) {
        try {
            return Utils.isEmpty(value) ? null : Utils.getDateByFormat(ConstantUtils.DATE_FORMAT, value);
        } catch (Exception e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw new ConverterException("Date failed to convert");
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if(!(value instanceof Date)){
            throw new ConverterException("Value not a Date object");
        }
        return value != null ? Utils.getDateStringByFormat(ConstantUtils.DATE_FORMAT, (Date) value) : null;
    }
}

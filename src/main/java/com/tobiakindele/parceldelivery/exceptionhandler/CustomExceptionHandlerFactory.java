package com.tobiakindele.parceldelivery.exceptionhandler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 *
 * @author oyindamolaakindele
 */

public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {

    private ExceptionHandlerFactory parent;
    
    public CustomExceptionHandlerFactory(ExceptionHandlerFactory parent){
        this.parent = parent;
    }
    
    @Override
    public ExceptionHandler getExceptionHandler() {
        ExceptionHandler result = new CustomExceptionHandler(parent.getExceptionHandler());
        return result;
    }
    
}

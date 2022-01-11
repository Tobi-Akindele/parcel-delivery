package com.tobiakindele.parceldelivery.exceptionhandler;

import com.tobiakindele.parceldelivery.utils.ConstantUtils;
import com.tobiakindele.parceldelivery.utils.MessageUtils;
import com.tobiakindele.parceldelivery.utils.Utils;
import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExceptionHandler;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/**
 *
 * @author oyindamolaakindele
 */

public class CustomExceptionHandler extends ExceptionHandlerWrapper {
    
    private ExceptionHandler wrappedExceptionHandler;
    
    public CustomExceptionHandler (ExceptionHandler wrappedExceptionHandler){
        this.wrappedExceptionHandler = wrappedExceptionHandler;
    }
    
    @Override
    public ExceptionHandler getWrapped() {
        return wrappedExceptionHandler;
    }

    @Override
    public void handle() throws FacesException {
        Iterator<ExceptionQueuedEvent> eventIterator = getUnhandledExceptionQueuedEvents().iterator();
        while (eventIterator.hasNext()) {
            ExceptionQueuedEvent event = eventIterator.next();
            ExceptionQueuedEventContext eventContext = (ExceptionQueuedEventContext) event.getSource();
            Throwable throwable = eventContext.getException();
            try {
                String genericMessage = "An Internal Server Error occurred, kindly contact system administrator at: " + ConstantUtils.SYS_ADMIN_EMAIL;
                String message = throwable.getClass() == FacesException.class 
                        ? Utils.extractExceptionMessage(throwable.getCause().getLocalizedMessage()) 
                        : genericMessage;
                
                MessageUtils.addErrorMessage(message);
            } finally {
                eventIterator.remove();
            }
        }
        getWrapped().handle();
    }
}

package com.tobiakindele.parceldelivery.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author oyindamolaakindele
 */

public class SessionUtils {
    
    public static HttpSession getSession(){
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getEmail() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return String.valueOf(session.getAttribute("email"));
    }

    public static Long getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (Long) session.getAttribute("userId");
        } else {
            return null;
        }
    }
}

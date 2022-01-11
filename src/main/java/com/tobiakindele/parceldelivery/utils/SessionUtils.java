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
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc != null ? (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false) : null;
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getEmail() {
        HttpSession session = getSession();
        if (session != null) {
            return String.valueOf(session.getAttribute("email"));
        } else {
            return null;
        }
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

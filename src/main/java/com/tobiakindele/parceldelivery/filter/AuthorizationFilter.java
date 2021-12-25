package com.tobiakindele.parceldelivery.filter;

import com.tobiakindele.parceldelivery.utils.LoggerUtil;
import com.tobiakindele.parceldelivery.utils.Utils;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author oyindamolaakindele
 */

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = {"/*"})
public class AuthorizationFilter implements Filter {
    
    private static final Logger logger = Logger.getLogger(AuthorizationFilter.class.getName());

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            HttpSession session = request.getSession(false);
            String requestURI = request.getRequestURI();
            if (requestURI.contains("/login.xhtml")
                    || requestURI.contains("/sign_up.xhtml")
                    || requestURI.contains("/driver_signup.xhtml")
                    || requestURI.contains("/activate.xhtml")
                    || requestURI.contains("/public/")
                    || requestURI.contains("javax.faces.resource")
                    || (session != null && !Utils.isEmpty(session.getAttribute("email")))) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/faces/login.xhtml");
            }
        } catch (IOException | ServletException e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw e;
        } 
    }
}

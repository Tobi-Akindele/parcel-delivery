package com.tobiakindele.parceldelivery.utils;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author oyindamolaakindele
 */
public class EmailUtil {

    private static final Logger logger = Logger.getLogger(EmailUtil.class.getName());

    public static void sendEmail(String subject, String message, String toAddress) throws AddressException, MessagingException {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            ExternalContext context = facesContext.getExternalContext();
            String host = context.getInitParameter("SMTP.Host");
            String port = context.getInitParameter("SMTP.Port");
            String fromAddress = context.getInitParameter("MAIL.From");
            String username = context.getInitParameter("SMTP.Username");
            String password = context.getInitParameter("SMTP.Password");
            String auth = context.getInitParameter("SMTP.Auth");

            //set SMTP server properties
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.auth", auth);
            props.put("mail.smtp.ssl.enable", "false");

            Authenticator authenticator = null;
            if (Boolean.parseBoolean(auth)) {
                authenticator = new Authenticator() {
                    private final PasswordAuthentication pa = new PasswordAuthentication(username, password);

                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        return pa;
                    }
                };
            }

            Session session = Session.getInstance(props, authenticator);
            try {
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(fromAddress));
                InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
                msg.setRecipients(Message.RecipientType.TO, toAddresses);
                msg.setSubject(subject);
                msg.setSentDate(new Date());

                Multipart multipart = new MimeMultipart();
                MimeBodyPart htmlContent = new MimeBodyPart();
                htmlContent.setContent(message, "text/html");
                multipart.addBodyPart(htmlContent);
                msg.setContent(multipart);

                Transport.send(msg);
            } catch (MessagingException e) {
                LoggerUtil.logError(logger, Level.SEVERE, e);
            }
        }
    }
}

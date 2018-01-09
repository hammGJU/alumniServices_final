/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumni.alumniapp.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author hesham
 */
public class SendEmail implements Serializable {

     private final String HOST_KEY = "mail.smtp.host";
    private final String HOST = "127.0.0.1";
    private final String AUTH_KEY = "mail.smtp.auth";
    private final String PORT_KEY = "mail.smtp.port";
    private final int PORT_SMTP = 10025;
    private final int PORT_FACTORY = 465;
    private final String SOCKET_FACTORY_CLASS_KEY = "mail.smtp.socketFactory.class";
    private final String SOCKET_FACTORY_CLASS = "javax.net.ssl.SSLSocketFactory";
    private final String SOCKET_FACTORY_PORT_KEY = "mail.smtp.socketFactory.port";
    private final String SOCKET_FACTORY_FALLBACK_KEY = "mail.smtp.socketFactory.fallback";

    public void sendEmail(String userName, String password, String fromAdrs,
            List<String> toAdrs, String subject, String message) throws AddressException, MessagingException {

        Properties properties = System.getProperties();
        properties.put(HOST_KEY, HOST);
        properties.put(AUTH_KEY, false);
        properties.put(PORT_KEY, PORT_SMTP);
//        properties.put(AUTH_KEY, true);
//        properties.put(SOCKET_FACTORY_CLASS_KEY, SOCKET_FACTORY_CLASS);
//        properties.put(SOCKET_FACTORY_PORT_KEY, 10025);
//        properties.put(SOCKET_FACTORY_FALLBACK_KEY, false);
        Authenticator authenticator = new Authenticator() {
            private PasswordAuthentication pa = new PasswordAuthentication(userName, password);

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return pa;
            }

        };
        Session emailSession = Session.getInstance(properties, null);
        emailSession.setDebug(true);
        Message emailMessage = new MimeMessage(emailSession);
        InternetAddress fromAddress = new InternetAddress(fromAdrs);
        emailMessage.setFrom(fromAddress);
        InternetAddress toAddress;
        List<InternetAddress> toAddresses = new ArrayList<>();
        for (String s : toAdrs) {
            toAddress = new InternetAddress(s);
            toAddresses.add(toAddress);
        }
        Address[] a = new Address[toAddresses.size()];
        emailMessage.setRecipients(Message.RecipientType.TO, toAddresses.toArray(a));
        emailMessage.setSubject(subject);
//        emailMessage.setContent(message, "text/html");
        emailMessage.setText(message);
        Transport trans = emailSession.getTransport("smtp");
        trans.connect(HOST, userName, password);

//        Transport.send(emailMessage);
        trans.sendMessage(emailMessage, emailMessage.getAllRecipients());

    }

}

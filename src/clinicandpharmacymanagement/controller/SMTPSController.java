/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinicandpharmacymanagement.Controller;
import java.util.*;

import javax.mail.*;

import javax.mail.internet.*;

import javax.activation.*;
/**
 *
 * @author lenovo
 */
public class SMTPSController {
    private static final String host = "smtp.gmail.com";

    private static final String port = "587"; 

    private static final String email = "shrijan.mhrz@gmail.com";

    private static String password = "yklw jwxz trdy ywsm";



    // Send Email Method

    public static boolean sendMail(String recipient, String subject, String body) {

        // Set up properties for the email session

        Properties properties = new Properties();

        properties.setProperty("mail.smtp.host", host);

        properties.setProperty("mail.smtp.port", port);  

        properties.setProperty("mail.smtp.auth", "true");

        properties.setProperty("mail.smtp.starttls.enable", "true");  // Enable STARTTLS

        properties.put("mail.smtp.starttls.enable", "true");

        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");  // Forces TLSv1.2

        // Create a session with the properties

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(email, password);

            }

        });



        try {

            // Create the message

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(email));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            message.setSubject(subject);

            message.setText(body);

            // Send the message

            Transport.send(message);

            System.out.println("Mail sent successfully!"+body);

            return true;

        } catch (MessagingException mex) {

            mex.printStackTrace();

            return false;

        }

    }
    
}

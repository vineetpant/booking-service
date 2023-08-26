package com.oli.booking.utility;

import java.util.Properties;
import com.oli.booking.models.Booking;
import com.oli.booking.models.response.CreatedBooking;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailMock {
    public static void sendEmail(Booking booking, CreatedBooking createdBooking) {
        String to = booking.getEmail();
        // Sender's email ID needs to be mentioned
        String from = "test_admin@oli.test.com";
        // TODO: change to email host
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("A booking has been created!");
            String mailMessage = "Congratulation! A booking has been created/updated for you " + "transaction Id "
                    + createdBooking.transactionId() + " transaction hash " + createdBooking.hash();
            message.setText(mailMessage);

            // Send message
            // Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

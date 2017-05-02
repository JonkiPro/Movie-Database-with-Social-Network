package com.jonki.ServiceImpl;

import com.jonki.Service.SendMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class SendMessageServiceImpl implements SendMessageService{

    private String username;
    private String password;

    @PostConstruct
    private void initialize() {
        username = "1kyd6tt@gmail.com";
        password = "dImdCz95";
    }

    @Override
    public boolean send(final String recipientEmail, final String subject, final String textMessage) {
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        try{
            Session session = Session.getDefaultInstance(props,
                    new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }});

            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress("slugocki.jonatan@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail,false));
            msg.setSubject(subject);
            msg.setText(textMessage);
            msg.setSentDate(new Date());
            Transport.send(msg);
            System.out.println("Message sent.");
            return true;
        } catch (MessagingException e){ System.out.println("An error occured, cause: " + e);
            return false;
        }
    }

}

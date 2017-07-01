package com.jonki.ServiceImpl;

import com.jonki.Service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
@PropertySource("classpath:settingsMail.properties")
public class MailServiceImpl implements MailService {

    @Value("${mail.username}") String username;
    @Value("${mail.password}") String password;

    @Value("${mail.smtp.host}") String mailSmtpHost;
    @Value("${mail.smtp.socketFactory.class}") String mailSmptSocketFactoryClass;
    @Value("${mail.smtp.socketFactory.fallback}") String mailSmptSocketFactoryFallback;
    @Value("${mail.smtp.port}") String mailSmptPort;
    @Value("${mail.smtp.socketFactory.port}") String mailSmptSocketFactoryPort;
    @Value("${mail.smtp.auth}") String mailSmptAuth;
    @Value("${mail.debug}") String mailDebug;
    @Value("${mail.store.protocol}") String mailStoreProtocol;
    @Value("${mail.transport.protocol}") String mailTransportProtocol;

    @Value("${mail.internet_address}") String mailInternetAddress;

    @Override
    public boolean send(final String recipientEmail, final String subject, final String textMessage) {
        try{
            Session session = Session.getDefaultInstance(getProperties(),
                    new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }});

            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(mailInternetAddress));
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

    // Get a Properties object
    private Properties getProperties() {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", mailSmtpHost);
        properties.setProperty("mail.smtp.socketFactory.class", mailSmptSocketFactoryClass);
        properties.setProperty("mail.smtp.socketFactory.fallback", mailSmptSocketFactoryFallback);
        properties.setProperty("mail.smtp.port", mailSmptPort);
        properties.setProperty("mail.smtp.socketFactory.port", mailSmptSocketFactoryPort);
        properties.put("mail.smtp.auth", mailSmptAuth);
        properties.put("mail.debug", mailDebug);
        properties.put("mail.store.protocol", mailStoreProtocol);
        properties.put("mail.transport.protocol", mailTransportProtocol);

        return properties;
    }

}

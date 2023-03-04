package main.artfix;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSend {
    public static void send(String who, String mailMessage){
        try {
            String host = "smtp.***.ru";
            String port = "***"; // or ***
            String username = "***@***.ru";
            String password = "***";
            String subject = "Mail from Shop " + Memory.shopName;
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(who));
            message.setSubject(subject);
            message.setText(mailMessage);
            Transport.send(message);
        } catch (Exception e) {
            System.out.println("System: Mail send Error!!!!!!!!!!!!");
            System.out.println("Զանգահարեք Տնօրենին");
            System.out.println("Զանգահարեք Տնօրենին");
            System.out.println("Զանգահարեք Տնօրենին");
            System.out.println("Զանգահարեք Տնօրենին");
            System.exit(0);
        }
    }
}
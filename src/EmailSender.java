import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailSender {

    private static final String FROM_EMAIL = "apuautomotiveservicecentre@gmail.com";
    private static final String APP_PASSWORD = "wlnj bhui xkdt ahgl";

    public static void sendEmail(String toEmail, String subject, String messageText) {

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        });

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(FROM_EMAIL));

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );

            message.setSubject(subject);

            message.setText(messageText);

            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
        }
    }
}
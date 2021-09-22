package ferreteria;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.SocketAddress;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Email {

    public void enviarMail(String destinatario, String claveGenerada) throws MessagingException {

        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(propiedad);
        String correo = "ferreteriagamarra812@gmail.com";
        String pass = "";
        String asunto = "Recupero de contraseña";//mando el mensaje que  se genero

        MimeMessage mail = new MimeMessage(session);
        try {
            mail.setFrom(new InternetAddress(correo));//desde donde se envia
            mail.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(destinatario)});
            mail.setSubject(asunto);
            mail.setText("Su clave de recuperacion es: " + claveGenerada);

            Transport transport = session.getTransport("smtp");
            transport.connect(correo, pass);
            //   transport.sendMessage(mail, mail.getRecipients(javax.mail.Message.RecipientType.TO));
            transport.sendMessage(mail, mail.getAllRecipients());
            transport.close();
        } catch (AddressException e) {
            System.out.println("error en enviar correo: " + e.getMessage());
        } catch (MessagingException e) {
            System.out.println("error en enviar correo: " + e.getMessage());
        }
    }
}

package ferreteria;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class Email {
    static String correo = "ferreteriagamarra812@gmail.com";
    static String pass = "ferreteria812";
    static String asunto = "Recupero de contraseña";//mando el mensaje que  se genero

    public static void enviarCorreo(String destinatario, String claveGenerada) throws MessagingException {
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.auth", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.user", correo);
        propiedad.setProperty("mail.smtp.clave", pass);

        Session session = Session.getDefaultInstance(propiedad);
        session.setDebug(true);
        try {
            MimeMessage msj = new MimeMessage(session);
            msj.setFrom(new InternetAddress(correo));
            msj.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(destinatario)));
            msj.setSubject(asunto);
            msj.setText("Su clave de recuperación es: " + claveGenerada);

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com",correo, pass);
           // transport.sendMessage(msj,msj.getRecipients(javax.mail.Message.RecipientType.TO));
            transport.sendMessage(msj, msj.getAllRecipients());
            transport.close();
            System.out.println("Correo enviado");
        } catch (AddressException e) {
            System.out.println("error en enviar correo: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

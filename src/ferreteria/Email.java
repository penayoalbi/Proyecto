package ferreteria;

import java.net.PasswordAuthentication;
import java.net.SocketAddress;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.SearchTerm;



public class Email {

    public void enviarMail(String destinatario, String claveGenerada) throws MessagingException {
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp gmail com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(propiedad);
        String correo = "albi.penayo@gmail.com";
        String pass = "cr7carplove2911.";
        String destino = destinatario;
        String asunto = "Recupero de contraseña";
        String mensajeClave = claveGenerada; //mando el mensaje que autogenero con mi sistema

        MimeMessage mail = new MimeMessage(session);
        mail.setFrom(new InternetAddress(correo));//desde donde se envia
        mail.addRecipients(javax.mail.Message.RecipientType.TO, String.valueOf(new InternetAddress(destinatario)));
        mail.setSubject(asunto);
        mail.setText(mensajeClave);

        Transport transport = session.getTransport("smtp");
        transport.connect(correo, pass);
        transport.sendMessage(mail, mail.getRecipients(javax.mail.Message.RecipientType.TO));
        transport .close();
    }

}

package ferreteria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    private static final int WITDH = 0;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtClave;
    @FXML
    private Button btnIngresar;
    @FXML
    private Button btnSalir;
    @FXML
    private AnchorPane Home, page2, page3, page4;
    @FXML
    private Hyperlink recuperacion;

    @FXML
    void ingresar(ActionEvent event) throws Exception {
        System.out.println("Click en el boton ingresar");
        bd base = new bd();//instancia de bd

        if (txtUsuario.getText().equals("") || txtClave.getText().equals("")) {
            alert("llene los campos de usuario y clave");
        } else {
            ResultSet rs;
            rs = base.Consultar("SELECT * FROM ferreteria.usuarios");
            int resu = 0;
            while (rs.next()) {
                if (txtUsuario.getText().equals(rs.getString("nombre")) && txtClave.getText().equals(rs.getString("clave"))) {
                    // System.out.println("bienvenidoooo");
                    alert("Bienvenido");
                    resu = 1;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("principal.fxml"));
                        Parent root = loader.load();
                        PrincipalController vistaPrincipal = loader.getController();//instancia  class
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        // Stage myStage = (Stage) this.vistaPrincipal.getScene().getWindow();
                        //myStage.close();
                    } catch (IOException e) {
                        Logger.getLogger(Recuperacion.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
            rs.close();

            if (resu != 1) {
                alert("Error:   usuario no existe");
                txtUsuario.setText(null);
                txtClave.setText(null);
            }
        }
    }

    @FXML
    void recuperacion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("recuperacion.fxml"));
            Parent root = loader.load();
            Recuperacion recuperacionPass = loader.getController();//instan recu class
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> recuperacionPass.controladorClose());// cuando se cierra ejecuta esto
            Stage myStage = (Stage) this.recuperacion.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
           // Logger.getLogger(Recuperacion.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("error en recuperacion.");
        }
    }

/*
    @FXML
    public void enviarCorreo(String destinatario, String claveGenerada) throws MessagingException {
        String correo = "ferreteriagamarra812@gmail.com";
        String pass = "ferreteria812";
        String asunto = "Recupero de contraseña";//mando el mensaje que  se genero
            Properties propiedad = new Properties();
            propiedad.put("mail.smtp.auth", "true");
            propiedad.put("mail.smtp.starttls.enable", "true");
            propiedad.put("mail.smtp.host", "smtp.gmail.com");
            propiedad.put("mail.smtp.port", "587");
            propiedad.put("mail.smtp.clave", pass);

            Session session = Session.getDefaultInstance(propiedad, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return super.getPasswordAuthentication();
                }
            });

            try {
                MimeMessage mail = new MimeMessage(session);
                mail.setFrom(new InternetAddress(correo));
                mail.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(destinatario)));
                mail.setSubject(asunto);
                mail.setText("Su clave de recuperación es: " + claveGenerada);
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", correo, pass);
                transport.sendMessage(mail, mail.getRecipients(javax.mail.Message.RecipientType.TO));
                // transport.sendMessage(mail, mail.getAllRecipients());
                transport.close();
                System.out.println("Correo enviado");
            } catch (AddressException e) {
                System.out.println("error en enviar correo: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }*/

        @FXML
        void cancelar() {
            System.out.println("click en  Cancelar");
            System.exit(WITDH);
        }

        public void alert(String msj) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ALERT");
            alert.setContentText(msj);
            alert.showAndWait();
        }
}

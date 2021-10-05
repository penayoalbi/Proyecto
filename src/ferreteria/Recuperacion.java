package ferreteria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.sql.ResultSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Recuperacion<activation, _JAVA_OPTIONS, modules, add> {
    private static final int WITDH = 0;
    @FXML private AnchorPane vistaRecuperacion;
    @FXML private Pane formRecuperacion;
    @FXML private Pane vistaEnviarCorreo;
    @FXML private TextField usuarioID, nuevaContraseña, repetirContraseña;
    @FXML private Button guardarCambio;
    @FXML private Button cancelarCambio;
    @FXML private Button btnEnviarCorreo;
    public TextField txtCorreoRecupero;

    validacion valida = new validacion();
    Email email = new  Email();
    @FXML
    public void initialize1(){
      //  formRecuperacion.setVisible(false);
    }

    @FXML
    public void guardarCambio(){
        bd base = new bd();
        ResultSet rs;
        if(!nuevaContraseña.getText().equals("")){
            String sql=( "UPDATE `usuarios` SET `clave` = '"+nuevaContraseña.getText()
                    +"' WHERE `usuarios`.`usuarioID` = "+usuarioID.getText());
            base.Guardar(sql);
            limpiarCeldas();
        }else{
            System.out.println("el campo esta vacio");
        }
    }
    @FXML
    public void controladorClose(){
        System.out.println("click en guardar cambio");
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Controller controller = loader.getController();//instancia controller class
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e-> controladorClose());// cuando se cierra debe ejecutar esto
            Stage myStage = (Stage) this.guardarCambio.getScene().getWindow();//para cerrar ventana, pero siempre hay una en pantalla
            myStage.close();
        }catch (IOException e){
            Logger.getLogger(Recuperacion.class.getName()).log(Level.SEVERE, null,e);
        }
    }

    @FXML
    public void cancelarCambio(){
        System.out.println("click en  Cancelar Cambio");
        //System.exit(WITDH);
    }

    @FXML public void enviarCorreo() {
       // formRecuperacion.setVisible(true);
        try{
           // if(!txtCorreoRecupero.getText().equals("") && valida.validarEmail(txtCorreoRecupero.getText())) {
                email.enviarCorreo(txtCorreoRecupero.getText(),retornaAleatrio());
                System.out.println("se envio correctamente. Revise su correo" +retornaAleatrio());
           // }
        }catch (InvalidParameterException | MessagingException  e){
            System.out.println("error al enviar correo de recupero: "+e.getMessage());
        }
    }

    @FXML
    public static String retornaAleatrio(){
        //banco de caracteres
        String bancoCaracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String cadenaAleatorio = "";
        for(int i=0; i<10; i++){
            int itemAleatorio = numAleatorio(0, bancoCaracteres.length()-1);
            char caracterAleatorio = bancoCaracteres.charAt(itemAleatorio);
            cadenaAleatorio+=caracterAleatorio;
        }
        System.out.println("mi aleatorio: "+cadenaAleatorio);
        return  cadenaAleatorio;
    }

    @FXML
    public static Integer numAleatorio(int min, int max){
        return ThreadLocalRandom.current().nextInt(min,max+1 );
    }

    @FXML
    public void limpiarCeldas(){
        //txt_idprofe.setText(null);
        usuarioID.setText(null);
        nuevaContraseña.setText(null);
        repetirContraseña.setText(null);
    }

}

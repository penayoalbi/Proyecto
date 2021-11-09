package ferreteria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Recuperacion<activation, _JAVA_OPTIONS, modules, add> {
    private static final int WITDH = 0;
    @FXML private AnchorPane vistaRecuperacion;
    @FXML private Pane formRecuperacion;
    @FXML private Pane paneRecupero;
    @FXML private Pane vistaEnviarCorreo;
    @FXML private TextField txtValidador, txtNuevaContraseña, txtRepetirContraseña;
    @FXML private Button guardarCambio;
    @FXML private Button cancelarCambio;
    @FXML private Button btnEnviarCorreo;
    public TextField txtCorreoRecupero;

    bd base = new bd();
    validacion valida = new validacion();
    Email email = new  Email();
    Controller alert = new Controller();
    public String claveAleatorio ="";//es donde almaceno lo que mando por email.

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
           // stage.setOnCloseRequest(e-> controladorClose());// cuando se cierra debe ejecutar esto
        }catch (IOException e){
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null,e);
        }
    }

    @FXML public String enviarCorreo() {
        claveAleatorio=retornaAleatrio();
        try{
            if(!txtCorreoRecupero.getText().equals("") && valida.validarEmail(txtCorreoRecupero.getText())) {
                email.enviarCorreo(txtCorreoRecupero.getText(),claveAleatorio);

                System.out.println("Se envio correctamente. Mi clave autogenerada es: " +claveAleatorio);
                Alert alert = new Alert(Alert.AlertType.valueOf(String.valueOf(Alert.AlertType.INFORMATION)));
                alert.setHeaderText(null);
                alert.setTitle("Exito");
                alert.setContentText("Revise su casilla de correo.");
                alert.showAndWait();
            }
            return claveAleatorio;
        }catch ( Exception e){
            System.out.println("Error al enviar correo de recupero: "+e.getMessage());
            return claveAleatorio;
        }
    }

    @FXML public void guardarCambio(){
        System.out.println("click en guardar cambio");
        System.out.println("clave aleatorio viene de enviar correo: "+claveAleatorio);
        ResultSet rs;
        int id = 0;
        String sql = "SELECT usuarioID, correo FROM `usuarios` WHERE correo = '"+txtCorreoRecupero.getText()+"'";
        try{
            rs=base.Consultar(sql);
            while (rs.next()){
                 id=rs.getInt("usuarioID");
            }
           // System.out.println("id: "+id);//prueba
            if(txtValidador.getText().equals(claveAleatorio)){
                if(txtNuevaContraseña.getText().equals(txtRepetirContraseña.getText())){
                    String update="UPDATE usuarios set clave = '"+txtNuevaContraseña.getText()
                            +"' where correo = '"+txtCorreoRecupero.getText()+"'";
                    base.Guardar(update);
                    alert.alert("ÉXITO!");
                  //  System.out.println("nuevo: "+txtNuevaContraseña.getText());
                    limpiarCeldas();
                    //controladorClose();
                }else{
                    alert.alert("ERROR: las claves deben coincidir.");
                    limpiarCeldas();
                }
            }else{
                alert.alert("Error: Código validador invalido.");
            }
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            alert.alert("Error al recuperar contraseña");
        }
    }

    @FXML//devuelve una cadena aleatoria desde un banco de valores
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

    @FXML//devuelve num aleatorio
    public static Integer numAleatorio(int min, int max){
        return ThreadLocalRandom.current().nextInt(min,max+1 );
    }

    @FXML
    public void cancelarCambio(){
        System.out.println("click en  Cancelar Cambio");
        //System.exit(WITDH);
        limpiarCeldas();
    }

    @FXML
    public void limpiarCeldas(){
        txtValidador.setText(null);
        txtNuevaContraseña.setText(null);
        txtRepetirContraseña.setText(null);
    }
}

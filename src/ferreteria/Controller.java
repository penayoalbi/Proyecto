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

    bd base = new bd();//instancia de bd

    @FXML
    void ingresar(ActionEvent event)
    {
        String usuario = "";
        String clave = "";
        String estado = "";
        System.out.println("Click en el boton ingresar");
        try {
            if (txtUsuario.getText().equals("") || txtClave.getText().equals("")) {
                alert("llene los campos de usuario y clave");
            } else {
                ResultSet rs;
                rs = base.Consultar("SELECT usuario, clave, estado FROM ferreteria.usuarios " +
                        "WHERE usuario = '" + txtUsuario.getText() + "'");
                while (rs.next()) {
                    usuario = rs.getString("usuario");
                    clave = rs.getString("clave");
                    estado = rs.getString("estado");
                }
                if (!txtUsuario.getText().equals(usuario)) {
                    alert("USUARIO INCORRECTO");
                } else {
                    if (!txtClave.getText().equals(clave)) {
                        alert("ERROR DE CONTRASEÑA");
                    } else {
                        if (!estado.equals("Habilitado")) {
                            alert("USUARIO NO HABILITADO");
                        } else {
                            alert("Bienvenido");

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("principal.fxml"));
                            Parent root = loader.load();
                            PrincipalController vistaPrincipal = loader.getController();//instancia  class
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.show();
                           // stage.setOnCloseRequest(e -> controladorClose());
                          //  Stage myStage = (Stage) this.btnIngresar.getScene().getWindow();
                            //myStage.close();
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.println("Error. "+e.getMessage());
        }
    }


    @FXML void controladorClose(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("principal.fxml"));
            Parent root = loader.load();
            Controller controllerPrincipal = loader.getController();//instancia controller class
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            //stage.setOnCloseRequest(e -> controladorClose());// cuando se cierra debe ejecutar esto
        } catch (IOException e) {
          //  Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error.");
        }
    }

    @FXML void recuperacion(ActionEvent event) {//llama a la vista de recuperacion
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("recuperacion.fxml"));
            Parent root = loader.load();
            Recuperacion recuperacionPass = loader.getController();//instan recu class
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
           // stage.setOnCloseRequest(e -> recuperacionPass.controladorClose());// cuando se cierra ejecuta esto
        } catch (IOException e) {
           // Logger.getLogger(Recuperacion.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("error en recuperacion. no carga");
        }
    }

    @FXML
    void cancelar() {
            System.out.println("click en  Cancelar");
            System.exit(WITDH);
    }

    @FXML  public void alert(String msj) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Mensaje");
            alert.setContentText(msj);
            alert.showAndWait();
        }
}

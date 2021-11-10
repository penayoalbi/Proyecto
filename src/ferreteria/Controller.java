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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    private Hyperlink recuperacion, linkGenerarClave;
    //generar panel de generar clave si es la primera vez que inicia sesion
    @FXML private Pane paneGenerarClave;
    @FXML private Button btnGenerarClave;
    @FXML private TextField txtGenerarClave,txtClaveGenerada;


    bd base = new bd();//instancia de bd
    Alerts alertas = new Alerts();

    //no es objet, pero sirve
    String usuario = "";
    String clave = "";
    String estado = "";
    int claveGeneradaPor = 0;
    int id = 0;

    @FXML public void initialize(){
       // iconos();
        linkGenerarClave.setDisable(true);
        paneGenerarClave.setVisible(false);
    }

    @FXML
    void ingresar(ActionEvent event)
    {
        linkGenerarClave.setDisable(false);
        System.out.println("Click en el boton ingresar");
        try
        {
            if (txtUsuario.getText().equals("") || txtClave.getText().equals(""))
            {
                alert("llene los campos de usuario y clave");
            } else
            {
                ResultSet rs;
                rs = base.Consultar("SELECT usuarioID,usuario, clave, estado, claveGeneradaPor FROM ferreteria.usuarios " +
                        "WHERE usuario = '" + txtUsuario.getText() + "'");
                while (rs.next())
                {
                    usuario = rs.getString("usuario");
                    clave = rs.getString("clave");
                    estado = rs.getString("estado");
                    claveGeneradaPor =rs.getInt("claveGeneradaPor");
                    id = rs.getInt("usuarioID");

                }
                if (!txtUsuario.getText().equals(usuario))
                {
                    alert("USUARIO INCORRECTO");
                } else
                {
                    if (claveGeneradaPor == 1) {

                        mostrarPane();//llama al panel para generar clave por usuario
                        //txtClave.setDisable(true);
                        if (txtGenerarClave.getText().equals("") || txtClaveGenerada.getText().equals("")) {
                            System.out.println("llene los campos");
                        } else {
                            if (txtClaveGenerada.getText().equals(txtGenerarClave.getText())) {
                                System.out.println("los campos no coinciden");
                            } else {
                                generarClave();
                                alertas.mensajeInfo("se genero la clave. Inicie sesion");
                                //txtClave.setDisable(false);
                                paneGenerarClave.setVisible(false);
                                System.out.println("error en generar clave");
                            }
                        }
                    } else {

                        if (!txtClave.getText().equals(clave)) {
                            alertas.mensajeError("ERROR DE CONTRASEÑA");
                        } else {
                            if (!estado.equals("Habilitado")) {
                                alertas.mensajeError("USUARIO NO HABILITADO");
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
                                stage.setOnCloseRequest(e -> vistaPrincipal.loginClose());
                                Stage myStage = (Stage) this.btnIngresar.getScene().getWindow();
                                myStage.close();
                            }
                        }
                    }
                }
            }
          //  }
        }catch (Exception e){
            System.out.println("Error. "+e.getMessage());
        }
    }

    @FXML public void mostrarPane(){
        System.out.println("mostrar pane");
        paneGenerarClave.setVisible(true);

    }

     @FXML
     public void generarClave(){
        System.out.println("click en generar clave");
        //UPDATE `usuarios` SET `clave` = '1234', `claveGeneradaPor` = '1' WHERE `usuarios`.`usuarioID` = 10
        if(txtGenerarClave.getText().equals(txtClaveGenerada.getText())){
            String sql = "UPDATE `usuarios` SET `clave` = '"+txtGenerarClave.getText()+"',`claveGeneradaPor` = 0" +
                    " WHERE `usuarios`.`usuarioID` = '"+id+"'";
            base.modificardatos(sql);
            System.out.println("se genero clave por usuario en el "+id);
            paneGenerarClave.setVisible(false);//cierro pane
        }else {
            alertas.mensajeError("los campos deben coincidir");
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
            System.out.println("Error."+e.getMessage());
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
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

    @FXML
    public void alert(String msj) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Mensaje");
        alert.setContentText(msj);
        alert.showAndWait();
    }

        //agregar funcionalidad
    @FXML public  void iconos(){
        try{
            URL url = getClass().getResource("/img/login.png");
            Image image = new Image(url.toString(),24,24,false,true);
            btnIngresar.setGraphic(new ImageView(image));
        }catch (Exception e){
            System.out.println("error en iconos"+e.getMessage());
        }
    }

}

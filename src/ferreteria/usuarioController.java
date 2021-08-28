package ferreteria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class usuarioController {
    @FXML private Hyperlink principal;
    @FXML private Button btnListar;
    @FXML private Button btnNuevo;
    @FXML private Button btnEditar;
    @FXML private Button btnBorrar;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;
    @FXML private TableView <usuario> tablaUsuario;
    @FXML private ComboBox cmbDocumento;


    @FXML
    public void controladorClose(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("principal.fxml"));
            Parent root = loader.load();
            PrincipalController vistaPrincipal = loader.getController();//instancia controller class
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e-> controladorClose());// cuando se cierra debe ejecutar esto
            Stage myStage = (Stage) this.principal.getScene().getWindow();//para cerrar ventana, pero siempre hay una en pantalla
            myStage.close();
        }catch (IOException e){
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null,e);
        }
    }
    @FXML
    public void Listar(){
        //

    }
    @FXML
    public void nuevoUsuario(){}

    @FXML
    public void borrarUsuario(){}

    @FXML
    public void guardar(){
        //INSERT INTO `usuarios` (`id`, `nombre`, `correo`, `clave`, `cargo`, `Estado`, `tipoDocumento`, `documento`, `apellido`, `telefono`)
        // VALUES (NULL, 'ema', 'ema@gmail.cm','ema123', 'admin', 'Habilitado', 'dni', '40562147', 'Florentin', '1145565462')
    }

    @FXML
    public void cancelar (){}

}

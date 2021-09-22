package ferreteria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class clientesController {
    @FXML private TableView <clientes> tablaCliente;
    @FXML private TableColumn <clientes,Integer> colClienteID;
    @FXML private TableColumn <clientes, String> colTipoDocumento;
    @FXML private TableColumn <clientes, String> colDocumento;
    @FXML private TableColumn <clientes, String> colNombre;
    @FXML private TableColumn <clientes, String> colApellido;
    @FXML private TableColumn <clientes, String> colCorreo;
    @FXML private TableColumn <clientes, String> colTelefono;
    @FXML private TableColumn <clientes, String> colDireccion;
    @FXML private TableColumn <clientes, String> colProvincia;
    @FXML private TableColumn <clientes, String> colLocalidad;

    @FXML private TextField txtClienteID, txtTipoDocumento, txtDocumento,
                            txtNombre, txtApellido, txtCorreo, txtTelefono,
                            txtDireccion, txtProvincia, txtLocalidad;

    @FXML private Button btnListar, btnModificar, btnNuevo, btnGuardar, btnBuscar, btnBorrar;


    @FXML
    public void controladorClose() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("principal.fxml"));
            Parent root = loader.load();
            PrincipalController vistaPrincipal = loader.getController();//instancia controller class
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controladorClose());// cuando se cierra debe ejecutar esto
        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }




}

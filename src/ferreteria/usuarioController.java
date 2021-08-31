package ferreteria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
    @FXML private TableColumn <usuario, Integer>colUsuarioID;
    @FXML private TableColumn <usuario,String>colTipoDocumento;
    @FXML private TableColumn <usuario,String>colDocumento;
    @FXML private TableColumn <usuario,String>colNombre;
    @FXML private TableColumn <usuario,String>colApellido;
    @FXML private TableColumn <usuario,String>colCorreo;
    @FXML private TableColumn <usuario,String>colClave;
    @FXML private TableColumn <usuario,String>colCargo;
    @FXML private TableColumn <usuario,String>colTelefono;
    @FXML private TableColumn <usuario,String>colEstado;
    @FXML private TableColumn <usuario,String>colDomicilio;


    @FXML private ComboBox cmbDocumento;

    ObservableList<usuario> oblist = FXCollections.observableArrayList();//lista observable

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
            Stage myStage = (Stage) this.principal.getScene().getWindow();//para cerrar ventana, volviendo a pantalla principal
            myStage.close();
        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    public void initialize() {
        List<String> TipoDocumento = new ArrayList<>();
        TipoDocumento.add("Documento Nacional de Identidad");
        TipoDocumento.add("Cédula de Identidad");
        TipoDocumento.add("Pasaporte");
        ObservableList tipoD = FXCollections.observableArrayList(TipoDocumento);
        cmbDocumento.getItems().clear();
        cmbDocumento.setItems(tipoD);
        cmbDocumento.setValue("Seleccionar...");
    }

    @FXML
    public void Listar() {
        System.out.println("Click en listar");
        try {
          //  tablaUsuario.getItems().clear();
            bd base = new bd();
            ResultSet rs;
            rs = base.Consultar("SELECT * FROM usuarios");
            while (rs.next()) {
                oblist.add(new usuario(rs.getInt("usuarioID"), rs.getString("tipoDocumento"), rs.getString("documento"),
                        rs.getString("nombre"), rs.getString("apellido"), rs.getString("correo"),
                        rs.getString("clave"), rs.getString("cargo"),
                        rs.getString("telefono"), rs.getString("estado"), rs.getString("domicilio")));
            }
            colUsuarioID.setCellValueFactory(new PropertyValueFactory<>("usuarioID"));
            colTipoDocumento.setCellValueFactory(new PropertyValueFactory<>("tipoDocumento"));
            colDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
            colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
            colClave.setCellValueFactory(new PropertyValueFactory<>("clave"));
            colCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
            colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
            colDomicilio.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
            tablaUsuario.setItems(oblist);//agregar a grillla
            tablaUsuario.refresh();
            //btnListar.setDisable(true);
        } catch (Exception e) {
            System.out.println("Error: de nulo " + e.getMessage());
        }
}
        /*
            col_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
            grid.setItems(oblist);   //agregar a grid
            btnalta.setDisable(true);
            grid.refresh();
            btnlistar.setDisable(true);

        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
        *

    }*/
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

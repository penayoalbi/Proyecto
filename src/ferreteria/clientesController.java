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

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class clientesController {
    @FXML private TableView <clientes> tablaCliente;
    @FXML private TableColumn <clientes, Integer> colClienteID;
    @FXML private TableColumn <clientes, String> colTipoDocumento;
    @FXML private TableColumn <clientes, String> colDocumento;
    @FXML private TableColumn <clientes, String> colNombre;
    @FXML private TableColumn <clientes, String> colApellido;
    @FXML private TableColumn <clientes, String> colCorreo;
    @FXML private TableColumn <clientes, String> colTelefono;
    @FXML private TableColumn <clientes, String> colDireccion;
    @FXML private TableColumn <clientes, String> colProvincia;
    @FXML private TableColumn <clientes, String> colLocalidad;
    @FXML private ComboBox cmbTipoDocumento;

    @FXML private TextField txtClienteID, txtDocumento, txtNombre,
                            txtApellido, txtCorreo, txtTelefono,
                            txtDireccion, txtProvincia, txtLocalidad;

    @FXML private Button btnListar, btnModificar, btnNuevo,
                          btnGuardar, btnBuscar, btnBorrar;

    ObservableList<clientes> lista = FXCollections.observableArrayList();

    Controller alert = new Controller();
    bd base= new bd();//llamo a la clase de conexión a base de datos

    @FXML
    public void initialize() {
      //  eventoClick();
        List<String> TipoDocumento = new ArrayList<>();
        TipoDocumento.add("Documento Nacional de Identidad");
        TipoDocumento.add("Cédula de Identidad");
        TipoDocumento.add("Pasaporte");
        ObservableList tipoD = FXCollections.observableArrayList(TipoDocumento);
        cmbTipoDocumento.getItems().clear();
        cmbTipoDocumento.setItems(tipoD);
        cmbTipoDocumento.setValue("Seleccionar...");
    }

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

    @FXML public void ListarCliente(){
        ResultSet rs;
        try{
            rs = base.Consultar("SELECT * FROM clientes");
            while (rs.next()){
                lista.add( new clientes(rs.getInt("clienteID"),rs.getString("tipoDocumento"),
                        rs.getInt("Documento"),rs.getString("Nombre"),
                        rs.getString("Apellido"),rs.getInt("Telefono"),
                        rs.getString("Correo"),rs.getString("direccion"),
                        rs.getString("provincia"),rs.getString("localidad")));
            }
            colClienteID.setCellValueFactory(new PropertyValueFactory<>("clienteID"));
            colTipoDocumento.setCellValueFactory(new PropertyValueFactory<>("tipoDocumento"));
            colDocumento.setCellValueFactory(new PropertyValueFactory<>("Documento"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            colApellido.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
            colTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
            colCorreo.setCellValueFactory(new PropertyValueFactory<>("Correo"));
            colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
            colProvincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));
            colLocalidad.setCellValueFactory(new PropertyValueFactory<>("localidad"));
            tablaCliente.setItems(lista);
        }catch (Exception e){
            System.out.println("ERROR en listar "+e.getMessage());
        }
    }
    @FXML
    public void eventoClick(){
        tablaCliente.getSelectionModel().selectedItemProperty().addListener((observableValue, valorAnterior, valorActual)->{

        });
        /*
        //valorActual apunta a valor seleccionado en la grilla
        tablaUsuario.getSelectionModel().selectedItemProperty().addListener((observableValue, valorAnterior, valorActual) -> {
            txtUsuarioID.setText(String.valueOf(valorActual.getUsuarioID()));
            cmbDocumento.setValue(valorActual.getTipoDocumento());
            txtDocumento.setText(String.valueOf(valorActual.getDocumento()));
            txtNombre.setText(String.valueOf(valorActual.getNombre()));
            txtApellido.setText(String.valueOf(valorActual.getApellido()));
            txtTelefono.setText(String.valueOf(valorActual.getTelefono()));
            txtCorreo.setText(String.valueOf(valorActual.getCorreo()));
            txtDomicilio.setText(String.valueOf(valorActual.getDomicilio()));
            txtClave.setText(String.valueOf(valorActual.getClave()));
            txtEstado.setText(String.valueOf(valorActual.getEstado()));
            txtCargo.setText(String.valueOf(valorActual.getCargo()));

            btnListar.setDisable(true);
            txtUsuarioID.setDisable(true);
            btnEditar.setDisable(false);
            tablaUsuario.refresh();
        });
    }
         */

    }

    @FXML public void Modificar(){
        System.out.println("click en modificar");

    }
    @FXML public void Nuevo(){
        System.out.println("click en nuevo");
    }
    @FXML public void Guardar(){
        System.out.println("click en guardar");
    }
    @FXML public void Borrar(){
        System.out.println("click en borrar");
    }
    @FXML public void Buscar(){
        System.out.println("click en borrar");
    }


}

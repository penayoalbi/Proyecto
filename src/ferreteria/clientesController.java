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
import java.util.Optional;
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
                            txtDireccion, txtProvincia, txtLocalidad, txtBuscar;

    @FXML private Button btnListar, btnModificar, btnNuevo,
                          btnGuardar, btnBuscar, btnBorrar;

    ObservableList<clientes> lista = FXCollections.observableArrayList();
    //instanciar a clases
    Controller alert = new Controller();
    bd base= new bd();//llamo a la clase de conexión a base de datos
    validacion validar = new validacion();

    @FXML
    public void initialize() {
        eventoClick();
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
    public void eventoClick() {
        tablaCliente.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, valorAnterior, valorActual) -> {
            txtClienteID.setText(String.valueOf(valorActual.getClienteID()));
            cmbTipoDocumento.setValue((valorActual.getTipoDocumento()));
            txtDocumento.setText(String.valueOf(valorActual.getDocumento()));
            txtNombre.setText(String.valueOf(valorActual.getNombre()));
            txtApellido.setText(String.valueOf(valorActual.getApellido()));
            txtTelefono.setText(String.valueOf(valorActual.getTelefono()));
            txtCorreo.setText(String.valueOf(valorActual.getCorreo()));
            txtDireccion.setText(String.valueOf(valorActual.getDireccion()));
            txtProvincia.setText(String.valueOf(valorActual.getProvincia()));
            txtLocalidad.setText(String.valueOf(valorActual.getLocalidad()));
            btnListar.setDisable(true);
            txtClienteID.setDisable(true);
        });
    }

    @FXML public void Modificar(){
        txtClienteID.setDisable(true);
        tablaCliente.refresh();
        try{
            if(!cmbTipoDocumento.getItems().equals("") | !txtDocumento.getText().equals("") | !txtNombre.getText().equals("")
               | !txtApellido.getText().equals("") | !txtTelefono.getText().equals("") | !txtCorreo.getText().equals("")
               | !txtDireccion.getText().equals("") | !txtProvincia.getText().equals("") | !txtLocalidad.getText().equals("")){
                if(validar.validarNumero(txtDocumento.getText()) && validar.validarNumero(txtTelefono.getText())
                        && validar.validarEmail(txtCorreo.getText())){
                    //alert.alert("datos ok");
                    String modificar = "UPDATE clientes SET clienteID ='"+txtClienteID.getText()+"',TipoDocumento ='"
                            +cmbTipoDocumento.getValue()+"',Documento ='"+txtDocumento.getText()+"',Nombre ='"
                            +txtNombre.getText()+"',Apellido ='"+txtApellido.getText()+"', Telefono ='"
                            +txtTelefono.getText()+"',Correo ='"+txtCorreo.getText()+"', direccion ='"
                            +txtDireccion.getText() +"',provincia ='"+txtProvincia.getText()+"',localidad ='"
                            +txtLocalidad.getText()+"'"+ "WHERE clienteID ='" +txtClienteID.getText()+"'";
                    base.modificardatos(modificar);
                    //tablaCliente.refresh();
                }else{
                    alert.alert("datos no ok");
                }
            }else{
                alert.alert("Error: Completar todos los campos");
            }
        }catch (Exception e){
            System.out.println("Error al modificar: "+e.getMessage());
        }
    }
    @FXML public void Nuevo(){
        limpiar();
    }
    @FXML public void Guardar(){
        txtClienteID.setDisable(true);
        try{
            if(!cmbTipoDocumento.getItems().equals("") | !txtDocumento.getText().equals("") | !txtNombre.getText().equals("")
                    | !txtApellido.getText().equals("") | !txtTelefono.getText().equals("") | !txtCorreo.getText().equals("")
                    | !txtDireccion.getText().equals("") | !txtProvincia.getText().equals("") | !txtLocalidad.getText().equals("")) {
                if (validar.validarNumero(txtDocumento.getText()) && validar.validarNumero(txtTelefono.getText())
                        && validar.validarEmail(txtCorreo.getText())){
                    if(txtDocumento.getText().length()==8){
                        if(txtNombre.getText().length()>=3 && txtNombre.getText().length()<=30 && txtApellido.getText().length()>=3
                        && txtApellido.getText().length()<=30){
                            if(confirmar()){
                                String insert = "INSERT INTO clientes (TipoDocumento, Documento, Nombre, Apellido,Telefono," +
                                        "Correo, direccion, provincia, localidad) VALUES ('"+cmbTipoDocumento.getValue()
                                        +"','"+txtDocumento.getText()+"','"+txtNombre.getText()+"','"+txtApellido.getText()
                                        +"','"+txtTelefono.getText()+"','"+txtCorreo.getText()+"','"+txtDireccion.getText()
                                        +"','"+txtProvincia.getText()+"','"+txtLocalidad.getText()+"')";
                                base.Guardar(insert);
                                System.out.println("se inserto con exito");
                                tablaCliente.refresh();
                            }else{
                                System.out.println("Operacion cancelada");
                                limpiar();
                            }
                        }else{
                            System.out.println("ERROR: el campo de Nombre/Apellido no es valido");
                        }
                    }else{
                        System.out.println("ERROR en el campo Documento");
                    }
                }else{
                    System.out.println("CARACTER NO VALIDO (númericos, textos, correos)");
                }
            }else{
                System.out.println("Completar todos los campos");
            }
        }catch (Exception e) {
            System.out.println("ERROR: no se pudo guargar nuevo usuario");
        }
    }

    @FXML public void Borrar(){
        try{
            int item = tablaCliente.getSelectionModel().getSelectedItem().getClienteID();
            String eliminar = "DELETE FROM clientes WHERE clienteID ='"+item+"'";
            if(confirmar()){
                if(base.buscarIdex(eliminar)){
                    System.out.println("Se elimino con exito: "+ item);
                    tablaCliente.refresh();
                }
            }
        }catch (Exception e){
            System.out.println("ERROR al eliminar "+e.getMessage());
        }
    }
    @FXML public void Buscar(){
        ResultSet rs;
        try{//SELECT * FROM `clientes` WHERE Nombre LIKE 'an%'
            String buscar = "SELECT * FROM clientes WHERE Nombre LIKE '"+txtBuscar+"'% ORDER BY Nombre";
            rs=base.Consultar(buscar);
            while (rs.next()){

            }

        }catch (Exception e){
            System.out.println("Error al buscar "+e.getMessage());
        }
        System.out.println("click en borrar");
    }

    @FXML public void limpiar(){
        txtClienteID.setText(null);
        txtDocumento.setText(null);
        txtNombre.setText(null);
        txtApellido.setText(null);
        txtCorreo.setText(null);
        txtTelefono.setText(null);
        txtDireccion.setText(null);
        txtLocalidad.setText(null);
        txtProvincia.setText(null);
    }

    @FXML
    public  Boolean confirmar (){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Confirmar acción?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
}

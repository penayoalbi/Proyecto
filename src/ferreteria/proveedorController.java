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

public class proveedorController {
    @FXML private ComboBox cmbTipoDocumento;
    @FXML private TableView <proveedor>tablaProveedor;

    @FXML private TextField txtProveedorID, txtDocumento, txtNombre,
            txtApellido, txtCorreo, txtTelefono,
            txtDireccion;
    @FXML private Button btnListar, btnModificar, btnNuevo,
            btnGuardar, btnBuscar, btnBorrar;
    @FXML private TableColumn <proveedor,Integer>colProveedorID;
    @FXML private TableColumn <proveedor,String>colTipoDocumento;
    @FXML private TableColumn <proveedor,Integer>colDocumento;
    @FXML private TableColumn <proveedor,String>colNombre;
    @FXML private TableColumn <proveedor,String>colApellido;
    @FXML private TableColumn <proveedor,String>colTelefono;
    @FXML private TableColumn <proveedor,String>colCorreo;
    @FXML private TableColumn <proveedor,String>colDireccion;

    bd base= new bd();
    validacion validar = new validacion();
    clientesController alert= new clientesController();
    Controller msj = new Controller();
    Alerts alertas = new Alerts();

    ObservableList<proveedor> oblist = FXCollections.observableArrayList();//lista observable

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

    @FXML
    public void initialize() {
        eventoClick();
        txtProveedorID.setDisable(true);
        Listar();
        List<String> TipoDocumento = new ArrayList<>();
        TipoDocumento.add("Documento Nacional de Identidad");
        TipoDocumento.add("Cédula de Identidad");
        TipoDocumento.add("Pasaporte");
        ObservableList tipoD = FXCollections.observableArrayList(TipoDocumento);
        cmbTipoDocumento.getItems().clear();
        cmbTipoDocumento.setItems(tipoD);
        cmbTipoDocumento.setValue("Seleccionar...");
    }

    @FXML public void Listar(){
        ResultSet rs;
        try{
            rs = base.Consultar("SELECT * FROM proveedor");
            while(rs.next()) {
                oblist.add(new proveedor(rs.getInt("proveedorID"),
                        rs.getString("tipoDocumento"),
                        rs.getInt("documento"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("direccion"))
                );
            }
            colProveedorID.setCellValueFactory(new PropertyValueFactory<>("proveedorID"));
            colTipoDocumento.setCellValueFactory(new PropertyValueFactory<>("tipoDocumento"));
            colDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
            colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
            colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
            tablaProveedor.setItems(oblist);
        }catch (Exception e){
            System.out.println("Error en listar Proveedor: "+e.getMessage());
        }
    }

    @FXML public void eventoClick(){
        tablaProveedor.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue, valorAnterior, valorActual )->{
                    txtProveedorID.setText(String.valueOf(valorActual.getProveedorID()));
                    cmbTipoDocumento.setValue(String.valueOf(valorActual.getTipoDocumento()));
                    txtDocumento.setText(String.valueOf(valorActual.getDocumento()));
                    txtNombre.setText(String.valueOf(valorActual.getNombre()));
                    txtApellido.setText(String.valueOf(valorActual.getApellido()));
                    txtTelefono.setText(String.valueOf(valorActual.getTelefono()));
                    txtCorreo.setText(String.valueOf(valorActual.getCorreo()));
                    txtDireccion.setText(String.valueOf(valorActual.getDireccion()));
                    txtProveedorID.setDisable(true);
                }
        );
    }

    @FXML public void Modificar(){
        txtProveedorID.setDisable(true);
        try{
            if(!cmbTipoDocumento.equals("") | !txtDocumento.equals("") |
                    !txtNombre.equals("") |   !txtApellido.equals("") |
                    !txtTelefono.equals("") | !txtCorreo.equals("")|
                    !txtDireccion.equals(""))
            {
                if (!(validar.validarNumero(txtDocumento.getText())
                        && validar.validarNumero(txtTelefono.getText())))
                {
                    System.out.println("Error: los campos documento y telefono deben ser númericos.");
                } else {
                    if(!validar.validarEmail(txtCorreo.getText())){
                        System.out.println("Error en el campo correo");
                    }else{
                        if(txtDocumento.getText().length()==8){
                            if(alert.confirmar()){
                                String update = "UPDATE proveedor SET proveedorID = '"
                                        +txtProveedorID.getText()+"',tipoDocumento='"
                                        +cmbTipoDocumento.getValue()+"',documento ='"
                                        +txtDocumento.getText()+"',nombre='"
                                        +txtNombre.getText()+"',apellido ='"
                                        +txtApellido.getText()+"',telefono='"
                                        +txtTelefono.getText()+"',correo='"
                                        +txtCorreo.getText()+"', direccion ='"
                                        +txtDireccion.getText()+"'"
                                        +"WHERE proveedorID='"+txtProveedorID.getText()+"'";
                                base.modificardatos(update);
                                limpiarGrilla();
                                tablaProveedor.refresh();
                            }else{
                                System.out.println("Operacion cancelada");
                                limpiarCeldas();
                            }
                        }else{
                            System.out.println("ERROR en el campo documento.");
                        }
                    }
                }
            }else{
                System.out.println("hay campos vacios");
            }
        }catch (Exception e){
            System.out.println("Error al modificar: "+e.getMessage());
        }
    }

    @FXML public void Nuevo(){
        limpiarCeldas();
        txtProveedorID.setDisable(true);

    }

    @FXML public void Borrar(){
        System.out.println("click en borrar");
          try{
            int item = tablaProveedor.getSelectionModel().getSelectedItem().getProveedorID();
           // String deshFk = "SET GLOBAL FOREIGN_KEY_CHECKS=0 ";//deshabilitar FK
           // base.Consultar(deshFk);//deshabilitar el Fk
            String eliminar = "Update proveedor set estado = 0 WHERE proveedorID ='"+item+"'";
            if(alert.confirmar()){
                if(base.buscarIdex(eliminar)){
                    alertas.mensajeInfo("Se dió de baja con exito: id eliminado:  "+ item);
                 //   String habFk="SET GLOBAL FOREIGN_KEY_CHECKS=1";//Habilitar FK
                    //base.Consultar(habFk);
                    tablaProveedor.refresh();
                }
            }
        }catch (Exception e){
            System.out.println("ERROR al eliminar "+e.getMessage());
        }
    }

    @FXML public void Guardar(){
        //falta validar documento
        txtProveedorID.setDisable(true);
        int estado =0;
        try{
            if(!cmbTipoDocumento.equals("") | !txtDocumento.equals("")
                    | !txtNombre.equals("") | !txtApellido.equals("")
                    | !txtTelefono.equals("") | !txtCorreo.equals("")
                    | !txtDireccion.equals(""))
            {
                if (!(validar.validarNumero(txtDocumento.getText())
                        && validar.validarNumero(txtTelefono.getText())))
                {
                    alertas.mensajeError("los campos documento y telefono deben ser númericos.");
                    System.out.println("Error: los campos documento y telefono deben ser númericos.");
                } else {
                    if(!validar.validarEmail(txtCorreo.getText()))
                    {
                        alertas.mensajeError("Error en el campo correo.");
                        System.out.println("Error en el campo correo.");
                    }else{
                        if(txtDocumento.getText().length()>=5){
                            if(alert.confirmar()){
                                estado =1;
                                String update = "INSERT INTO proveedor (tipoDocumento, documento, nombre, apellido, " +
                                        "telefono, correo, direccion, estado) VALUES ('"
                                        +cmbTipoDocumento.getValue()+ "','"
                                        +txtDocumento.getText()+"','"
                                        +txtNombre.getText()+"','"
                                        +txtApellido.getText()+"','"
                                        +txtTelefono.getText()+"','"
                                        +txtCorreo.getText()+"','"
                                        +txtDireccion.getText()+"','"
                                        +estado+"')";
                                base.Guardar(update);
                                limpiarGrilla();
                                tablaProveedor.refresh();
                                alertas.mensajeInfo("Se registro con exito!");
                            }else{
                                alertas.mensajeInfo("Operación cancelada");
                                System.out.println("Operacion cancelada");
                                limpiarCeldas();
                            }
                        }else{
                            alertas.mensajeError("Documento no válido.");
                            System.out.println("ERROR en el campo documento.");
                        }
                    }
                }
            }else{
                alertas.mensajeError("Completar todos los campos");
                System.out.println("Hay campos vacios");
            }
        }catch (Exception e){
            alertas.mensajeInfo("Debe completar todos los compos");
            System.out.println("Error al registrar nuevo proveedor: "+e.getMessage());
        }
    }

    @FXML public void limpiarGrilla(){
        tablaProveedor.refresh();
    }
    @FXML public void limpiarCeldas(){
        txtProveedorID.setText(null);
        txtDocumento.setText(null);
        cmbTipoDocumento.setValue(null);
        txtNombre.setText(null);
        txtApellido.setText(null);
        txtTelefono.setText(null);
        txtCorreo.setText(null);
        txtDireccion.setText(null);
    }
}

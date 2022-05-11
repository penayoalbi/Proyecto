package ferreteria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.logging.Level;
import java.util.logging.Logger;


public class ventasController {
    @FXML private Button btnEmitirFactura;
    @FXML private Button btnCancelar;
    @FXML private Button btnBuscarCliente;
    @FXML private Button btnBuscarProducto;
    @FXML private Button btnAddCliente;
    @FXML private Button btnAddProducto;
    @FXML private Button btnNuevaVenta;
    @FXML private Button btnGenerarVenta;
    @FXML private Button btnNuevoCliente;
    @FXML private Button btnAddGrilla;

    @FXML private TextField txtDocumentoCliente;
    @FXML private TextField txtProducto;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtMontoTotal;

    @FXML private ComboBox <clientes> cmbCliente;
    @FXML private ComboBox <Producto>cmbProducto;
    @FXML private ComboBox <usuario> cmbVendedor;

    @FXML private TableView <ModelVentas>tablaVenta;
    @FXML private TableColumn <ModelVentas,Integer>colCodVenta;
    @FXML private TableColumn <ModelVentas,Integer>colCantidad;
    @FXML private TableColumn <ModelVentas,DatePicker>colFecha;

    //llamadas
    bd base = new bd();
    Alerts alertas = new Alerts();


    ObservableList<ModelVentas> venta = FXCollections.observableArrayList();
    ObservableList<Producto> obproductos = FXCollections.observableArrayList();
    ObservableList<clientes> cbcliente = FXCollections.observableArrayList();
    ObservableList<usuario> obUsuario = FXCollections.observableArrayList();

    ArrayList lista =new ArrayList();

    public  void  initialize(){
        iniciarComboBox();
        btnBuscarCliente.setDisable(true);
        txtDocumentoCliente.setDisable(true);
    }

    @FXML public void setBtnAddGrilla(ActionEvent event){
        Object ventas []=null;
        ventas [0] = txtProducto.getText();


        colCantidad.setCellValueFactory(new PropertyValueFactory<>(txtCantidad.getText()));

    }

    public void GenerarVenta(){
        System.out.println("click en generar venta");
        try{

        }catch (Exception e){
            System.out.println("error en crear venta"+e.getMessage());
        }

    }

    @FXML public void buscarCliente(){
        String cliente;
        ResultSet rs ;
        String buscarCliente="Select clienteID, nombre, estado from clientes where documento = '"+txtDocumentoCliente.getText()+"'";
        try{
            rs=base.Consultar(buscarCliente);
            if(rs.next()){
                cliente=rs.getString("nombre");
            }else{
                alertas.mensajeInfo("Cliente no existe. Click en nuevo para agregar");
            }
        }catch (Exception e){
            System.out.println("error en buscar" + e.getMessage());
        }

    }

    public void iniciarComboBox(){
        clientes Cli = new clientes();
        cbcliente = Cli.getCliente();//viene del modelo
        this.cmbCliente.setItems(cbcliente);

        usuario cmbUser = new usuario();
        obUsuario = cmbUser.getUsuarios();//viene del modelo
        this.cmbVendedor.setItems(obUsuario);

        Producto cmbProd = new Producto();
        obproductos = cmbProd.getProducto();
        this.cmbProducto.setItems(obproductos);

    }
    @FXML public void buscarProducto(){

        try{
            Integer codProd;
            String nombre;
            Integer stock;
            String descProd;
            String filtrarProd;
            ResultSet rs;
            filtrarProd= "Select productoID, nombre, stock , descripcion from productos where nombre = '"+ txtProducto.getText()+"'";
            rs = base.Consultar(filtrarProd);
            while(rs.next()){

            }

        }catch (Exception e){
            System.out.println("error en buscar" + e.getMessage());
        }
    }
    @FXML public void addCliente(){}
    @FXML public void addProducto(){}
    @FXML public void addVendedor(){}
    @FXML public void EmitirBoleta(){
        try{

        }catch (Exception e){
            System.out.println("error en buscar" + e.getMessage());
        }
    }

    @FXML
    public void cerrarVentana() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("principal.fxml"));
            Parent root = loader.load();
            PrincipalController vistaPrincipal = loader.getController();//instancia controller class
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> vistaPrincipal.loginClose());// cuando se cierra debe ejecutar esto
            //Stage myStage = (Stage) this.principal.getScene().getWindow();//para cerrar ventana, volviendo a pantalla principal
            //  myStage.close();
        } catch (IOException e) {
            Logger.getLogger(Recuperacion.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    @FXML public void generarFacturar () {
        GenerarReportes newReportes = new GenerarReportes();
        newReportes.CrearFactura();
    }


}

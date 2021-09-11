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

public class productoController {
    @FXML private TableView <Producto>tablaProducto;
    @FXML private TextField txtProductoID;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtPrecioLista;
    @FXML private TextField txtPrecioVenta;
    @FXML private TextField txtStock;
    @FXML private TextField txtCategoria;
    @FXML private ComboBox cmbCategoria;
    @FXML private ComboBox cmbProveedor;

    @FXML private TableColumn <Producto,Integer> colProductoID;
    @FXML private TableColumn <Producto,String> colDescripcion;
    @FXML private TableColumn <Producto,Float> colPrecioLista;
    @FXML private TableColumn <Producto,Float> colPrecioVenta;
    @FXML private TableColumn <Producto,Integer> colStock;
    @FXML private TableColumn <Producto,String> colCategoria;
    @FXML private TableColumn <Producto,String> colProveedor;
    @FXML private Button btnListar, btnGuadar, btnModificar, btnBorrar;
    @FXML private TextField txtPrueba;

    ObservableList<Producto> oblist = FXCollections.observableArrayList();//lista observable

    bd base = new bd();
    Controller alert=new Controller();

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
           // Stage myStage = (Stage) this.vistaPrincipal.getScene().getWindow();//para cerrar ventana, volviendo a pantalla principal
         //   myStage.close();
        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    @FXML
    public void initialize(){
        eventoClick();

    }

    @FXML
    public void listar(){
        System.out.println("click en lista");
        ResultSet rs;
        try{
            rs=base.Consultar("SELECT `ProductoID`,`Descripcion`,`PrecioLista`,`PrecioVenta`,`Stock`,`Categoria`,`Nombre` " +
                    "FROM `productos` INNER JOIN `proveedor` WHERE `proveedorID`=`IDproveedor`");
            while(rs.next()){
                oblist.add(new Producto(rs.getInt("ProductoID"),rs.getString("Descripcion"),
                        rs.getFloat("PrecioLista"), rs.getFloat("PrecioVenta"),
                        rs.getInt("Stock"),rs.getString("Categoria"),
                        rs.getString("Nombre")));
            }
            colProductoID.setCellValueFactory(new PropertyValueFactory<>("ProductoID"));
            colDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
            colPrecioLista.setCellValueFactory(new PropertyValueFactory<>("PrecioLista"));
            colPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("PrecioVenta"));
            colStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
            colCategoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
            colProveedor.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            tablaProducto.setItems(oblist);
            tablaProducto.refresh();

        }catch (Exception e){
            System.out.println("Error en listar"+e.getMessage());
        }
    }

    @FXML
    public void eventoClick(){
        tablaProducto.getSelectionModel().selectedItemProperty().addListener((observableValue, valorAnterior, valorActual) ->{
            txtProductoID.setText(String.valueOf(valorActual.getProductoID()));
            txtNombre.setText(String.valueOf(valorActual.getNombre()));
            txtDescripcion.setText(String.valueOf(valorActual.getDescripcion()));
            txtPrecioLista.setText(String.valueOf(valorActual.getPrecioLista()));
            txtPrecioVenta.setText(String.valueOf(valorActual.getPrecioVenta()));
            txtCategoria.setText(String.valueOf(valorActual.getCategoria()));
            txtStock.setText(String.valueOf(valorActual.getStock()));
            tablaProducto.refresh();

        } );
    }

    @FXML
    public void modificar(){
        System.out.println("click en modificar");
    }
    @FXML
    public void eliminar(){
        System.out.println("click en eliminar");
    }
    /*
    * INSERT INTO `productos` (`ProductoID`, `Descripcion`, `PrecioLista`, `PrecioVenta`, `Stock`, `Categoria`, `IDProveedor`)
    *  VALUES ()
    * */
    @FXML
    public void guardar(){
        System.out.println("click en guardar");
        btnListar.setDisable(true);
        try{
            if(txtDescripcion.getText().equals("")|| txtPrecioLista.getText().equals("")||txtPrecioVenta.getText().equals("")
                    ||txtStock.getText().equals("")||txtCategoria.getText().equals("")||cmbProveedor.getItems().equals("")){
                alert.alert("ERROR: campos vacios ");
            }else{
                String insert="INSERT INTO `productos` (`Descripcion`,`PrecioLista`,`PrecioVenta`,`Stock`,`Categoria`,`proveedor`)" +
                        " VALUES('"+txtDescripcion.getText()+"','"+txtPrecioLista.getText()+"','"+txtPrecioVenta.getText() +
                        "','"+txtStock.getText()+"','"+txtCategoria.getText()+"','"+cmbProveedor.getItems()+"')";
                base.Guardar(insert);
                alert.alert("Se Guardo Correctamente!");
            }
        }catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
    @FXML
    public void nuevo(){
        System.out.println("click en nuevo");
        SelectProveedor();
        limpiarCeldas();
        txtProductoID.setDisable(true);
        txtNombre.setDisable(true);
        cmbProveedor.setDisable(false);
    }

    //comboBox nombre proveedor
    @FXML
    public void SelectProveedor(){
        ResultSet resu;
        List<String> nombreProveedor = new ArrayList<String>();
        try{
            String select="SELECT `nombre` FROM `proveedor`";
            resu=base.Consultar(select);
            while(resu.next()){
                nombreProveedor.add((resu.getString("nombre")));
            }
            ObservableList<String> proveedor = FXCollections.observableArrayList(nombreProveedor);
            cmbProveedor.setItems(proveedor);
            cmbProveedor.setValue("Selecionar");
        }catch (Exception e){
            System.out.println("Error al buscar proveedor: "+e.getMessage());
        }
    }

    //prueba
    Seguridad seg= new Seguridad();
    @FXML public void prueba(){
        if(!txtPrueba.getText().equals("")){
            String enc;
            enc=seg.encriptar(txtPrueba.getText());
            System.out.println("esto es prueba: "+enc);
        }else{
            System.out.println("error en prueba");
        }
    }

    @FXML
    public void limpiarCeldas(){
        txtProductoID.setText(null);
        txtNombre.setText(null);
        txtDescripcion.setText(null);
        txtPrecioLista.setText(null);
        txtPrecioVenta.setText(null);
        txtStock.setText(null);
        cmbCategoria.setValue(null);
    }
}

package ferreteria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
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
    @FXML private ComboBox cmbCategoria;
    @FXML private ComboBox cmbProveedor;

    @FXML private TableColumn <Producto,Integer> colProductoID ;
    @FXML private TableColumn <Producto,String> colNombre ;
    @FXML private TableColumn <Producto,String> colDescripcion;
    @FXML private TableColumn <Producto,Float> colPrecioLista;
    @FXML private TableColumn <Producto,Float> colPrecioVenta;
    @FXML private TableColumn <Producto,Integer> colStock;
    @FXML private TableColumn <Producto,String> colCategoria;
    @FXML private TableColumn <Producto,String> colProveedor;

    ObservableList<Producto> oblist = FXCollections.observableArrayList();//lista observable

    bd base = new bd();

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
            //SELECT `ProductoID`, `Nombre`, `Descripcion`, `PrecioLista`, `PrecioVenta`,
            // `Stock`, `Categoria`, `proveedorID` FROM `productos` WHERE 1
            rs=base.Consultar("SELECT `ProductoID`, `Nombre`, `Descripcion`, `PrecioLista`, `PrecioVenta`,`Stock`, `Categoria`, `proveedorID` FROM `productos` WHERE 1");
            while(rs.next()){
                oblist.add(new Producto(rs.getInt("ProductoID"),rs.getString("Nombre"),
                        rs.getString("Descripcion"),rs.getFloat("PrecioLista"),
                        rs.getFloat("PrecioVenta"),rs.getInt("Stock"),rs.getString("Categoria"),
                        rs.getInt("proveedorID")));
            }
            colProductoID.setCellValueFactory(new PropertyValueFactory<>("ProductoID"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            colDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
            colPrecioLista.setCellValueFactory(new PropertyValueFactory<>("PrecioLista"));
            colPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("PrecioVenta"));
            colStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
            colCategoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
            colProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedorID"));
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
            txtStock.setText(String.valueOf(valorActual.getStock()));
            tablaProducto.refresh();

        } );
    }

    /*
    * @FXML
    *
    * */
    @FXML
    public void modificar(){
        System.out.println("click en modificar");
    }
    @FXML
    public void eliminar(){
        System.out.println("click en eliminar");
    }
    @FXML
    public void guardar(){
        System.out.println("click en guardar");
    }
    @FXML
    public void nuevo(){
        System.out.println("click en nuevo");
        limpiarCeldas();
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
        cmbProveedor.setValue(null);
    }
}

package ferreteria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class buscarProductoController {

    @FXML private TableView <Producto> tablaResu;
    @FXML private TextField txtProducto;
    @FXML private Button btnBuscar;
    @FXML private Button btnLimpiar;
    @FXML private TableColumn <Producto, Integer> colCodigo;
    @FXML private TableColumn <Producto, String> colDescripcion;
    @FXML private TableColumn <Producto, Integer> colStock;
    @FXML private TableColumn <Producto, Float> colPrecioUnitario;
    @FXML private TableColumn <Producto, String> colUbicacion;

    ObservableList<Producto> oblist = FXCollections.observableArrayList();//lista observable
    bd base = new bd(); //instancia de la base de datos
    Controller alert = new Controller();// intancia de Alertas


    public void buscarProducto(){
        System.out.println("click en buscar");
        ResultSet resu;
        try{
           resu = base.Consultar("SELECT `ProductoID`, `Descripcion`, `Stock`, `PrecioVenta` FROM `productos`" +
                    " WHERE Descripcion LIKE '%"+txtProducto.getText()+"%'");
            while (resu.next()) {
                oblist.add( new Producto(resu.getInt("ProductoID"),resu.getString("Descripcion"),
                        resu.getFloat("PrecioVenta"),resu.getInt("Stock")));
            }
            colCodigo.setCellValueFactory(new PropertyValueFactory<>("ProductoID"));
            colDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
            colPrecioUnitario.setCellValueFactory( new PropertyValueFactory<>("PrecioVenta"));
            colStock.setCellValueFactory( new PropertyValueFactory<>("Stock"));
            tablaResu.setItems(oblist);
            tablaResu.refresh();

        }catch (Exception e){
            System.out.println("error en... "+e.getMessage());
        }
    }

    @FXML
    public  void limpiarTabla(){
        txtProducto.setText(null);
        tablaResu.setItems(null);
    }

    public void cerrarVentana() {//lanza nuevamente la pantalla principal
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
            System.out.println("error");
            // Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}

package ferreteria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrincipalController {
    private static final int WITDH  = 0;
    @FXML private Button btnUsuario;
    @FXML private Button btnSalir;
    @FXML private Button btnCliente;
    @FXML private Button btnProveedor;
    @FXML private Button btnVentas;
    @FXML private Button btnCompras;

    @FXML
    public void loginClose()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Controller login = loader.getController();//instan  class
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e-> login.controladorClose());// cuando se cierra ejecuta esto
            //Stage myStage = (Stage) this.btnUsuario.getScene().getWindow();
           // myStage.close();
        }catch (IOException e){
            System.out.println("error loginClose "+e.getMessage());
           // Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null,e);
        }
    }

    @FXML
    private void vistaUsuario(ActionEvent event){
        System.out.println("click en usuario");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("usuario.fxml"));
            Parent root = loader.load();
            usuarioController usuarios = loader.getController();//instan  class
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e-> usuarios.controladorClose());// cuando se cierra ejecuta esto
            Stage myStage = (Stage) this.btnUsuario.getScene().getWindow();
            myStage.close();

        }catch (IOException e){
            Logger.getLogger(Recuperacion.class.getName()).log(Level.SEVERE, null,e);
        }
    }
    @FXML
    private void vistaProducto(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("producto.fxml"));
            Parent root = loader.load();
            productoController productos = loader.getController();//instan  class
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e-> productos.controladorClose());// cuando se cierra ejecuta esto
            Stage myStage = (Stage) this.btnUsuario.getScene().getWindow();
            myStage.close();

        }catch (IOException e){
            Logger.getLogger(Recuperacion.class.getName()).log(Level.SEVERE, null,e);
        }
    }

    @FXML
    public void vistaProveedor(){
        System.out.println("click en vista proveedor");
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("proveedor.fxml"));
            Parent root = loader.load();
            proveedorController proveedor = loader.getController();
            Scene scene = new Scene (root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e-> proveedor.controladorClose());

            Stage myStage = (Stage) this.btnProveedor.getScene().getWindow();
            myStage.close();
        }catch (Exception e){
            System.out.println("ERROR: "+e.getMessage());
        }
    }

    @FXML
   public void vistaCliente(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("clientes.fxml"));
            Parent root = loader.load();
            clientesController cliente = loader.getController();
            Scene scene = new Scene (root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e-> cliente.controladorClose());

            Stage myStage = (Stage) this.btnCliente.getScene().getWindow();
            myStage.close();
        }catch (Exception e){
            System.out.println("ERROR: "+e.getMessage());
        }
    }

    @FXML public void Ventas(){
       // GenerarReportes repo = new GenerarReportes();
       // repo.CrearFactura();
        System.out.println("click en ventas");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ventas.fxml"));
            Parent root = loader.load();
            ventasController ventas = loader.getController();//instan  class
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e-> ventas.cerrarVentana());// cuando se cierra ejecuta esto
            Stage myStage = (Stage) this.btnVentas.getScene().getWindow();
            myStage.close();
        }catch (IOException e){
            Logger.getLogger(Recuperacion.class.getName()).log(Level.SEVERE, null,e);
        }


    }
    @FXML public void Compras(){
        System.out.println("click en Compras");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modulocompra.fxml"));
            Parent root = loader.load();
            compraController compra = loader.getController();//instan  class
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e-> compra.cerrarVentaCompra());// cuando se cierra ejecuta esto
           // Stage myStage = (Stage) this.btnCompras.getScene().getWindow();
           // myStage.close();
        }catch (IOException e){
            System.out.println("Error en compra");
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null,e);

        }
    }
    @FXML public void Factura(){
        System.out.println("click en Facrtura");
    }

    @FXML public void MediosDePagos(){
        System.out.println("click en MediosDePagos");
    }

    @FXML
    private void salir(ActionEvent event){
            System.out.println("click en  Cancelar");
            System.exit(WITDH);
    }
}

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
    }

    private void controladorClose() {
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

        }catch (Exception e){
            System.out.println("ERROR: "+e.getMessage());
        }
    }

    @FXML
    private void salir(ActionEvent event){
            System.out.println("click en  Cancelar");
            System.exit(WITDH);
        }
}

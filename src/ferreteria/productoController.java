package ferreteria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class productoController {


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
    }


    @FXML
    public void listar(){
        System.out.println("click en lista");
    }
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
    }

    @FXML
    public void limpiarCeldas(){
        System.out.println("click en limpiarCeldas");
    }
}

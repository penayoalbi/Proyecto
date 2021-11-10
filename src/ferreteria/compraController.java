package ferreteria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class compraController {


    @FXML public void cerrarVentaCompra() {

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

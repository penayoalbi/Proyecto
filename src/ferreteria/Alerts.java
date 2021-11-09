package ferreteria;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class Alerts {
    //retorna alerta para info
    @FXML public void mensajeInfo(String string){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information");
        alert.setTitle("Mensaje");
        alert.setContentText(string);
        alert.showAndWait();

    }
    @FXML public void mensajeError(String string){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setTitle("Mensaje");
        alert.setContentText(string);
        alert.showAndWait();

    }
    @FXML public void mensajeConfirmacion(String string){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmation");
        alert.setTitle("Mensaje");
        alert.setContentText(string);
        alert.showAndWait();
    }

}

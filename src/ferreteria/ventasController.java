package ferreteria;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ventasController {
    @FXML private Button btnGenerarFactura;


    @FXML public void cerrarVentana(){

    }


    @FXML public void generarFacturar(){
        GenerarReportes newReportes = new GenerarReportes();
        newReportes.CrearFactura();
    }
}

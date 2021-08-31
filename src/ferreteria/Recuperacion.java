package ferreteria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Recuperacion {
    private static final int WITDH = 0;
    @FXML private AnchorPane vistaRecuperacion;
    @FXML private Pane formRecuperacion;
    @FXML private TextField idUsuario, nuevaContraseña, repetirContraseña;
    @FXML private Button guardarCambio;
    @FXML private Button cancelarCambio;

    @FXML
    public void guardarCambio(){
        bd base = new bd();
        ResultSet rs;//txt_nombre.getText().equals("")|
        if(!nuevaContraseña.getText().equals("")){
            String sql=( "UPDATE `usuarios` SET `clave` = '"+nuevaContraseña.getText()+"' WHERE `usuarios`.`usuarioID` = "+idUsuario.getText());
            base.Guardar(sql);
            limpiarCeldas();
        }else{
            System.out.println("el campo esta vacio");
        }
    }
    @FXML
    public void controladorClose(){
        System.out.println("click en guardar cambio");
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Controller controller = loader.getController();//instancia controller class
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e-> controladorClose());// cuando se cierra debe ejecutar esto
            Stage myStage = (Stage) this.guardarCambio.getScene().getWindow();//para cerrar ventana, pero siempre hay una en pantalla
            myStage.close();
        }catch (IOException e){
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null,e);
        }
    }

    @FXML
    public void cancelarCambio(){
        System.out.println("click en  Cancelar Cambio");
        //System.exit(WITDH);
    }

    @FXML
    public void limpiarCeldas(){
        //txt_idprofe.setText(null);
        idUsuario.setText(null);
        nuevaContraseña.setText(null);
        repetirContraseña.setText(null);
    }

}

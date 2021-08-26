package sample;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static final int WITDH =0 ;
    @FXML private Pane home;
    @FXML private Pane login;
    @FXML private TextField txtUsuario;
    @FXML private TextField txtClave;
    @FXML private Button btnIngresar;
    @FXML private Button btnSalir;
    @FXML private AnchorPane Home, page2, page3, page4;



@FXML
    void ingresar() throws Exception{
    System.out.println("Click en el boton ingresar");
    bd base = new bd();//instancia de bd

    if(txtUsuario.getText().equals("") || txtClave.getText().equals("")){
        alert("llene los campos de usuario y clave");
    }else{
        ResultSet rs;//
        rs = base.Consultar( "SELECT * FROM ferreteria.usuarios");
        int  resu = 0;
        while (rs.next()){
            if(txtUsuario.getText().equals(rs.getString("nombre")) && txtClave.getText().equals(rs.getString("clave"))){
               System.out.println("bienvenidoooo");
                resu = 1;
                //alert("Bienvenido");
                break;
            }else {
                resu =0;
                System.out.println("usuario no existe");
                //alert("Error:   usuario no existe");
            }
            //System.out.println(nombre + "\t"+clave+"\t"); //mostrar en forma de tabla
        }
        rs.close();

        if(resu==1){
            alert("Bienvenido!");
        }else{
            alert("Error:   usuario no existe");
        }
        }
    }

    @FXML void cancelar(){
        System.out.println("click en  Cancelar");
        System.exit(WITDH);
    }
    //page 2
   /* @FXML
    private void paginaPrincipal(){
        Stage principal = new Stage();
        AnchorPane paginaHome = new AnchorPane();
        Scene homee = new Scene(paginaHome);
        principal.setScene(homee);
        principal.setTitle("nueva ventana");
        principal.show();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("principal.fxml"));
            principal.setScene(homee);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }*/

    //mensaje de alerta
    public void alert(String msj){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("ALERT");
        alert.setContentText(msj);
        alert.showAndWait();
    }
}
package ferreteria;

import javafx.fxml.FXML;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validacion {

    @FXML
    public boolean validarNulo(String string){
        return true;
    }
    @FXML
    public boolean validarTexto(String datos){
        return datos.matches("[a-zA-Z ]*$");
    }
    @FXML
    public boolean validarNumero(String datos){
        return datos.matches("[0-9]*$");
    }
    @FXML
    public boolean validarEmail(String datos){
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(datos);
        return matcher.find();
    }
}


package ferreteria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void init(){

    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Inicio de sesión");
        primaryStage.setScene(new Scene(root, 400, 275));
        primaryStage.show();
    }
    @Override
    public void stop(){

    }

    public static void main(String[] args) {
        launch(args);
    }

}

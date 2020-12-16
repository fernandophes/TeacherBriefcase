package src.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaInicial extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("telaInicial.fxml"));
        Scene cena = new Scene(root);
        stage.setTitle("Teacher Briefcase");
        stage.setScene(cena);
        stage.show();
    }
    
}

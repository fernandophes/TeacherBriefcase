package src.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.controller.FrontController;

public class Telas extends Application {

    private static Stage primaryStage;
    

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        Telas.primaryStage = primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        primaryStage.setTitle("Teacher Briefcase");
        primaryStage.show();
        telaLogin();
    }

    public static void telaLogin() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("telaLogin.fxml"));
        Scene cena = new Scene(root);
        primaryStage.setTitle("Teacher Briefcase");
        primaryStage.setScene(cena);
    }

    public static void telaInicial() throws Exception {
        telaProvas();
    }

    public static void barraPrincipal() {
        
    }

    public static void telaProvas() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("telaProvas.fxml"));
        Scene cena = new Scene(root);
        primaryStage.setTitle("Provas | Teacher Briefcase");
        primaryStage.setScene(cena);
        FrontController frontController = new FrontController();
        frontController.carregarBarraPrincipal();
    }
    
}

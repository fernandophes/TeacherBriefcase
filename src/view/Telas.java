package src.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.controller.DisciplinaDetalhesController;
import src.controller.ProvaController;
import src.controller.QuestaoComAlternativasController;
import src.controller.QuestaoSubjetivaController;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoComAlternativasVO;
import src.model.VO.QuestaoSubjetivaVO;

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
        telaDisciplinas();
    }

    public static void barraPrincipal() {
        
    }

    public static void telaProva(ProvaVO prova) throws Exception {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("telaProva.fxml"));
        loader.setController(new ProvaController(prova));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        primaryStage.setTitle(prova.getTitulo() + " | Teacher Briefcase");
        primaryStage.setScene(cena);
    }

    public static void telaDisciplinas() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("telaDisciplinas.fxml"));
        Scene cena = new Scene(root);
        primaryStage.setTitle("Disciplinas | Teacher Briefcase");
        primaryStage.setScene(cena);
    }

    public static void telaDisciplinaDetalhes(DisciplinaVO disciplina) throws Exception {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("telaDisciplina.fxml"));
        loader.setController(new DisciplinaDetalhesController(disciplina));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        primaryStage.setTitle(disciplina.getNome() + " | Teacher Briefcase");
        primaryStage.setScene(cena);
    }

    public static void telaQuestaoSubjetiva(QuestaoSubjetivaVO questao) throws Exception {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("telaQuestaoSubjetiva.fxml"));
        loader.setController(new QuestaoSubjetivaController(questao));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        primaryStage.setTitle("Questao de " + questao.getDisciplina().getNome() + " | Teacher Briefcase");
        primaryStage.setScene(cena);
    }

    public static void telaQuestaoComAlternativas(QuestaoComAlternativasVO questao) throws Exception {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("telaQuestaoComAlternativas.fxml"));
        loader.setController(new QuestaoComAlternativasController(questao));
        Parent root = loader.load();
        Scene cena = new Scene(root);
        primaryStage.setTitle("Questao de " + questao.getDisciplina().getNome() + " | Teacher Briefcase");
        primaryStage.setScene(cena);
    }
    
}

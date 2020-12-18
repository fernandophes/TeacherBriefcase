package src.controller;

import java.util.Iterator;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import src.exception.OperationException;
import src.model.BO.DisciplinaBO;
import src.model.BO.ProfessorBO;
import src.model.BO.ProvaBO;
import src.model.BO.QuestaoBO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;
import src.view.Telas;

public class DisciplinasController extends BarraController {

    private DisciplinaBO disciplinaBO = new DisciplinaBO();

    @FXML
    @Override
    protected void initialize() {
        super.initialize();
        atualizarMinhasDisciplinas();
        atualizarTodasAsDisciplinas();
    }

    @FXML
    private Label labelMinhasDisciplinas;

    @FXML
    private FlowPane flowPaneMinhasDisciplinas;

    public void atualizarMinhasDisciplinas() {

        flowPaneMinhasDisciplinas.getChildren().clear();

        // Título e quantidade
        List<DisciplinaVO> lista = ProfessorVO.logado.getDisciplinas();

        labelMinhasDisciplinas.setText("Minhas Disciplinas (_)".replace("_", String.valueOf(lista.size())));

        // Cards
        Iterator<DisciplinaVO> listaIt = lista.iterator();
        while (listaIt.hasNext())
            flowPaneMinhasDisciplinas.getChildren().add(criarCard(listaIt.next()));
    }

    @FXML
    private Label labelTodasAsDisciplinas;

    @FXML
    private FlowPane flowPaneTodasAsDisciplinas;

    public void atualizarTodasAsDisciplinas() {

        Node criar = flowPaneTodasAsDisciplinas.getChildren().get(0);
        flowPaneTodasAsDisciplinas.getChildren().clear();
        flowPaneTodasAsDisciplinas.getChildren().add(criar);

        try {
            // Título e quantidade
            List<DisciplinaVO> lista = disciplinaBO.listar();
            labelTodasAsDisciplinas.setText("Todas as Disciplinas (_)".replace("_", String.valueOf(lista.size())));

            // Cards
            Iterator<DisciplinaVO> listaIt = lista.iterator();
            while (listaIt.hasNext())
                flowPaneTodasAsDisciplinas.getChildren().add(criarCard(listaIt.next()));

        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public VBox criarCard(DisciplinaVO disciplina) {

        QuestaoBO questaoBO = new QuestaoBO();
        ProvaBO provaBO = new ProvaBO();

        // Corpo do Card
        VBox card = new VBox();
        card.setAlignment(Pos.TOP_CENTER);
        card.setPrefHeight(200.0);
        card.setPrefWidth(200.0);
        card.setPadding(new Insets(8.0));
        FlowPane.setMargin(card, new Insets(4.0));
        card.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #EEEEEE;");

        try {
            // Nome da Disciplina
            Hyperlink nome = new Hyperlink();
            nome.setText(disciplina.getNome());
            nome.setTextFill(Paint.valueOf("#6610f2"));
            nome.setFont(Font.font("System", FontWeight.BOLD, 14.0));
            nome.setWrapText(true);
            nome.setAlignment(Pos.CENTER);
            nome.setTextAlignment(TextAlignment.CENTER);
            nome.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    verDisciplina(event, disciplina);
                }
            });
            card.getChildren().add(nome);

            // Código
            Label codigo = new Label(disciplina.getCodigo());
            card.getChildren().add(codigo);

            // Separador #1
            Separator separador1 = new Separator();
            VBox.setMargin(separador1, new Insets(8.0));
            card.getChildren().addAll(separador1);

            // Dados
            HBox dados = new HBox();
            dados.setPrefHeight(50.0);
            dados.setPrefWidth(200.0);
            dados.setAlignment(Pos.CENTER);
            // dados.setBorder(new Border(new BorderStroke(Paint.valueOf("#000"),
            // BorderStrokeStyle.SOLID, new CornerRadii(0.0), new BorderWidths(3.0))));

            // Dados - Questões
            VBox questoes = new VBox();
            questoes.setPrefHeight(100.0);
            questoes.setPrefWidth(200.0);
            questoes.setAlignment(Pos.CENTER);

            // Dados - Questões - Quantidade
            Label quantQuestoes = new Label();
            quantQuestoes.setText(String.valueOf(questaoBO.buscar(disciplina).size()));
            quantQuestoes.setTextFill(Paint.valueOf("#6610f2"));
            quantQuestoes.setFont(Font.font(16.0));
            questoes.getChildren().add(quantQuestoes);

            // Dados - Questões - Rótulo
            Label rotuloQuestoes = new Label("Questões");
            questoes.getChildren().add(rotuloQuestoes);

            dados.getChildren().add(questoes);

            // Dados - Provas
            VBox provas = new VBox();
            provas.setPrefHeight(100.0);
            provas.setPrefWidth(200.0);
            provas.setAlignment(Pos.CENTER);

            // Dados - Provas - Quantidade
            Label quantProvas = new Label();
            quantProvas.setText(String.valueOf(provaBO.buscar(disciplina).size()));
            quantProvas.setTextFill(Paint.valueOf("#6610f2"));
            quantProvas.setFont(Font.font(16.0));
            provas.getChildren().add(quantProvas);

            // Dados - Questões - Rótulo
            Label rotuloProvas = new Label("Provas");
            provas.getChildren().add(rotuloProvas);

            dados.getChildren().add(provas);

            card.getChildren().add(dados);

            // Separador #2
            Separator separador2 = new Separator();
            VBox.setMargin(separador2, new Insets(8.0));
            card.getChildren().addAll(separador2);

            // Opção
            HBox opcao = new HBox();
            opcao.setPrefHeight(50.0);
            opcao.setPrefWidth(200.0);
            opcao.setAlignment(Pos.CENTER);

            // Opção - Link
            Hyperlink linkOpcao = new Hyperlink();
            boolean leciona = disciplinaBO.contem(ProfessorVO.logado.getDisciplinas(), disciplina);
            linkOpcao.setText((leciona ? "Remover da" : "Adicionar à") + " minha lista");
            linkOpcao.setTextFill(Paint.valueOf(leciona ? "#ff0000" : "#6610f2"));
            linkOpcao.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (leciona)
                        remover(disciplina);
                    else
                        adicionar(disciplina);
                }
            });

            opcao.getChildren().add(linkOpcao);

            card.getChildren().add(opcao);

        } catch (OperationException e) {
            e.printStackTrace();
        }

        return card;
    }

    @FXML
    private TextField novaDisciplinaCodigo;

    @FXML
    private TextField novaDisciplinaNome;

    @FXML
    private Label erroNovaDisciplina;

    public void cadastrar(ActionEvent event) {
        try {
            DisciplinaVO disciplina = new DisciplinaVO();
            disciplina.setCodigo(novaDisciplinaCodigo.getText());
            disciplina.setNome(novaDisciplinaNome.getText());

            disciplinaBO.cadastrar(disciplina);

            atualizarTodasAsDisciplinas();
        } catch (OperationException e) {
            erroNovaDisciplina.setText(e.getMessage());
            erroNovaDisciplina.setVisible(true);
            e.printStackTrace();
        }
    }

    public void verDisciplina(ActionEvent event, DisciplinaVO disciplina) {
        try {
            Telas.telaDisciplinaDetalhes(disciplina);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adicionar(DisciplinaVO disciplina) {
        ProfessorBO professorBO = new ProfessorBO();
        professorBO.adicionar(ProfessorVO.logado, disciplina);
        atualizarMinhasDisciplinas();
        atualizarTodasAsDisciplinas();
    }

    public void remover(DisciplinaVO disciplina) {
        ProfessorBO professorBO = new ProfessorBO();
        professorBO.remover(ProfessorVO.logado, disciplina);
        atualizarMinhasDisciplinas();
        atualizarTodasAsDisciplinas();
    }

}

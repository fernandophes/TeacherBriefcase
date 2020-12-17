package src.controller;

import java.util.Iterator;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import src.exception.OperationException;
import src.model.BO.AssuntoBO;
import src.model.BO.DisciplinaBO;
import src.model.BO.ProvaBO;
import src.model.BO.QuestaoBO;
import src.model.BO.QuestaoComAlternativasBO;
import src.model.BO.QuestaoSubjetivaBO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoComAlternativasVO;
import src.model.VO.QuestaoSubjetivaVO;
import src.model.VO.QuestaoVO;
import src.view.Telas;

public class DisciplinaDetalhesController extends BarraController {

    private static DisciplinaBO disciplinaBO = new DisciplinaBO();
    private static AssuntoBO assuntoBO = new AssuntoBO();
    private static ProvaBO provaBO = new ProvaBO();
    private static QuestaoBO questaoBO = new QuestaoBO();

    private DisciplinaVO disciplina;

    public DisciplinaDetalhesController(DisciplinaVO disciplina) {
        this.disciplina = disciplina;
    }

    @FXML
    @Override
    protected void initialize() {
        super.initialize();
        atualizarDisciplina();
        atualizarAssuntos();
        atualizarProvas();
        atualizarQuestoes();
    }

    @FXML
    TextField campoCodigo;

    @FXML
    TextField campoNome;

    public void atualizarDisciplina() {
        campoCodigo.setText(disciplina.getCodigo());
        campoNome.setText(disciplina.getNome());
    }

    @FXML
    Label labelAssuntos;

    @FXML
    FlowPane flowPaneAssuntos;

    public void atualizarAssuntos() {

        Node criar = flowPaneAssuntos.getChildren().get(0);
        flowPaneAssuntos.getChildren().clear();
        flowPaneAssuntos.getChildren().add(criar);

        // Título e quantidade
        List<String> lista = disciplina.getAssuntos();

        labelAssuntos.setText("Assuntos (_)".replace("_", String.valueOf(lista.size())));

        // Cards
        Iterator<String> listaIt = lista.iterator();
        while (listaIt.hasNext())
            flowPaneAssuntos.getChildren().add(criarCard(listaIt.next()));

    }

    @FXML
    private Label labelProvas;

    @FXML
    private FlowPane flowPaneProvas;

    public void atualizarProvas() {

        Node criar = flowPaneProvas.getChildren().get(0);
        flowPaneProvas.getChildren().clear();
        flowPaneProvas.getChildren().add(criar);

        // Título e quantidade
        List<ProvaVO> lista = provaBO.buscar(disciplina);

        labelProvas.setText("Provas (_)".replace("_", String.valueOf(lista.size())));

        // Cards
        Iterator<ProvaVO> listaIt = lista.iterator();
        while (listaIt.hasNext())
            flowPaneProvas.getChildren().add(criarCard(listaIt.next()));
    }

    @FXML
    private Label labelQuestoes;

    @FXML
    private FlowPane flowPaneQuestoes;

    public void atualizarQuestoes() {

        Node criar = flowPaneQuestoes.getChildren().get(0);
        flowPaneQuestoes.getChildren().clear();
        flowPaneQuestoes.getChildren().add(criar);

        try {
            // Título e quantidade
            List<QuestaoVO> lista = questaoBO.buscar(disciplina);
            labelQuestoes.setText("Questões (_)".replace("_", String.valueOf(lista.size())));

            // Cards
            Iterator<QuestaoVO> listaIt = lista.iterator();
            while (listaIt.hasNext()) {
                QuestaoVO q = listaIt.next();
                flowPaneQuestoes.getChildren().add(criarCard(q));
            }

        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public HBox criarCard(String assunto) {

        // Corpo do Card
        HBox card = new HBox();
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(8.0));
        card.setStyle("-fx-background-color: #F5FF90;");
        FlowPane.setMargin(card, new Insets(4.0));

        // Nome do Assunto
        TextField titulo = new TextField();
        titulo.setText(assunto);
        titulo.setStyle("-fx-background-color: transparent;");
        titulo.setAlignment(Pos.CENTER);
        card.getChildren().add(titulo);

        // Editar
        Button editar = new Button("Editar");
        editar.setTextFill(Paint.valueOf("#ff0000"));
        editar.setMnemonicParsing(false);
        editar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editarAssunto(disciplina, assunto, titulo.getText());
            }
        });

        card.getChildren().add(editar);

        // Excluir
        Button excluir = new Button("X");
        excluir.setTextFill(Paint.valueOf("#ff0000"));
        excluir.setMnemonicParsing(false);
        HBox.setMargin(excluir, new Insets(0.0, 4.0, 0.0, 0.0));
        excluir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                excluirAssunto(disciplina, assunto);
            }
        });

        card.getChildren().add(excluir);

        return card;
    }

    public VBox criarCard(ProvaVO prova) {

        QuestaoBO questaoBO = new QuestaoBO();

        // Corpo do Card
        VBox card = new VBox();
        card.setAlignment(Pos.CENTER);
        card.setPrefHeight(200.0);
        card.setPrefWidth(200.0);
        card.setPadding(new Insets(8.0));
        FlowPane.setMargin(card, new Insets(4.0));
        card.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #EEEEEE;");

        try {
            // Título da Prova
            Hyperlink titulo = new Hyperlink();
            titulo.setText(prova.getTitulo());
            titulo.setTextFill(Paint.valueOf("#F08A4B"));
            titulo.setFont(Font.font("System", FontWeight.BOLD, 14.0));
            titulo.setWrapText(true);
            titulo.setAlignment(Pos.CENTER);
            titulo.setTextAlignment(TextAlignment.CENTER);
            titulo.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    verProva(event, prova);
                }
            });
            card.getChildren().add(titulo);

            // Separador #1
            Separator separador1 = new Separator();
            VBox.setMargin(separador1, new Insets(8.0));
            card.getChildren().addAll(separador1);

            // Dados
            HBox dados = new HBox();
            dados.setPrefHeight(50.0);
            dados.setPrefWidth(200.0);
            dados.setAlignment(Pos.CENTER);

            // Dados - Questões
            VBox questoes = new VBox();
            questoes.setPrefHeight(100.0);
            questoes.setPrefWidth(200.0);
            questoes.setAlignment(Pos.CENTER);

            // Dados - Questões - Quantidade
            Label quantQuestoes = new Label(String.valueOf(questaoBO.buscar(prova).size()));
            quantQuestoes.setTextFill(Paint.valueOf("#F08A4B"));
            quantQuestoes.setFont(Font.font(16.0));
            questoes.getChildren().add(quantQuestoes);

            // Dados - Questões - Rótulo
            Label rotuloQuestoes = new Label("Questões");
            questoes.getChildren().add(rotuloQuestoes);

            dados.getChildren().add(questoes);

            card.getChildren().addAll(dados);

        } catch (OperationException e) {
            e.printStackTrace();
        }

        return card;
    }

    public VBox criarCard(QuestaoVO questao) {

        // Corpo do Card
        VBox card = new VBox();
        card.setAlignment(Pos.CENTER);
        card.setPrefHeight(200.0);
        card.setPrefWidth(200.0);
        card.setPadding(new Insets(8.0));
        FlowPane.setMargin(card, new Insets(4.0));
        card.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #EEEEEE;");

        // Enunciado da Questão
        Hyperlink titulo = new Hyperlink();
        titulo.setText(questao.getEnunciado());
        titulo.setTextFill(Paint.valueOf("#6610f2"));
        titulo.setFont(Font.font("System", FontWeight.BOLD, 14.0));
        titulo.setWrapText(true);
        titulo.setAlignment(Pos.CENTER);
        titulo.setTextAlignment(TextAlignment.CENTER);
        titulo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                verQuestao(event, questao);
            }
        });
        card.getChildren().add(titulo);

        // Separador #1
        Separator separador1 = new Separator();
        VBox.setMargin(separador1, new Insets(8.0));
        card.getChildren().addAll(separador1);

        // Dados - Tipo de Questão
        Label tipo = new Label((questao instanceof QuestaoSubjetivaVO ? "Subjetiva" : "Objetiva") + " - "
                + questao.getDificuldadeRotulo());
        card.getChildren().add(tipo);

        return card;
    }

    public void verQuestao(ActionEvent event, QuestaoVO questao) {

        if (questao instanceof QuestaoSubjetivaVO)
            verQuestaoSubjetiva(event, (QuestaoSubjetivaVO) questao);
        else if (questao instanceof QuestaoComAlternativasVO)
            verQuestaoComAlternativas(event, (QuestaoComAlternativasVO) questao);

    }

    public void verQuestaoSubjetiva(ActionEvent event, QuestaoSubjetivaVO questao) {

        try {
            Telas.telaQuestaoSubjetiva(questao);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void verQuestaoComAlternativas(ActionEvent event, QuestaoComAlternativasVO questao) {

        try {
            Telas.telaQuestaoComAlternativas(questao);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void verProva(ActionEvent event, ProvaVO prova) {
        // TODO
    }

    @FXML
    Label erroNovaProva;

    @FXML
    TextField novaProvaTitulo;

    public void cadastrarProva(ActionEvent event) {
        ProvaVO prova = new ProvaVO();
        prova.setDisciplina(disciplina);
        prova.setTitulo(novaProvaTitulo.getText());

        try {
            provaBO.cadastrar(prova);
        } catch (OperationException e) {
            erroNovaProva.setText(e.getMessage());
            erroNovaProva.setVisible(true);
            e.printStackTrace();
        }
        atualizarProvas();
        atualizarQuestoes();
    }

    @FXML
    TextArea novaQuestaoEnunciado;

    @FXML
    RadioButton nivelFacil;

    @FXML
    RadioButton nivelMedio;

    @FXML
    RadioButton nivelDificil;

    public QuestaoVO cadastrarQuestao(QuestaoVO questao) {
        try {
            questao.setEnunciado(novaQuestaoEnunciado.getText());
            questao.setDisciplina(disciplina);
            if (nivelFacil.isSelected())
                questao.setDificuldade(QuestaoVO.FACIL);
            else if (nivelMedio.isSelected())
                questao.setDificuldade(QuestaoVO.MEDIA);
            else if (nivelDificil.isSelected())
                questao.setDificuldade(QuestaoVO.DIFICIL);
            else
                throw new OperationException("O nível de dificuldade não está correto.");
        } catch (OperationException e) {
            e.printStackTrace();
        }

        return questao;
    }

    public void cadastrarQuestaoSubjetiva(ActionEvent event) {
        try {
            QuestaoSubjetivaVO questao = new QuestaoSubjetivaVO();
            questao = (QuestaoSubjetivaVO) cadastrarQuestao(questao);

            QuestaoSubjetivaBO questaoSubjetivaBO = new QuestaoSubjetivaBO();
            questaoSubjetivaBO.cadastrar(questao);

            verQuestaoSubjetiva(event, questao);
        } catch (OperationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cadastrarQuestaoObjetiva(ActionEvent event) {
        try {
            QuestaoComAlternativasVO questao = new QuestaoComAlternativasVO();
            questao = (QuestaoComAlternativasVO) cadastrarQuestao(questao);

            QuestaoComAlternativasBO questaoComAlternativasBO = new QuestaoComAlternativasBO();
            questaoComAlternativasBO.cadastrar(questao);

            verQuestaoComAlternativas(event, questao);
        } catch (OperationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    TextField novoAssuntoNome;

    public void cadastrarAssunto(ActionEvent event) {
        try {
            disciplinaBO.adicionar(disciplina, novoAssuntoNome.getText());
            atualizarAssuntos();
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void editarAssunto(DisciplinaVO disciplina, String assunto, String novo) {
        try {
            assuntoBO.atualizar(disciplina, assunto, novo);
            atualizarAssuntos();
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void excluirAssunto(DisciplinaVO disciplina, String assunto) {
        try {
            disciplinaBO.remover(disciplina, assunto);
            atualizarAssuntos();
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void editar(ActionEvent event) {
        try {
            disciplina.setCodigo(campoCodigo.getText());
            disciplina.setNome(campoNome.getText());

            disciplinaBO.atualizar(disciplina);
            atualizarDisciplina();
        } catch (OperationException e) {
            e.printStackTrace();
        }

    }

    public void excluir(ActionEvent event) {
        try {
            disciplinaBO.excluir(disciplina);
            voltar(event);
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void voltar(ActionEvent event) {
        try {
            Telas.telaDisciplinas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

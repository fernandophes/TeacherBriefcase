package src.controller;

import java.util.Iterator;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import src.model.BO.ProvaBO;
import src.model.BO.QuestaoBO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoComAlternativasVO;
import src.model.VO.QuestaoSubjetivaVO;
import src.model.VO.QuestaoVO;
import src.view.Telas;

public class ProvaController extends BarraController {

    private static ProvaBO provaBO = new ProvaBO();
    private static QuestaoBO questaoBO = new QuestaoBO();

    private ProvaVO prova;

    @FXML
    private Label labelQuestoesDaProva;

    @FXML
    private FlowPane flowPaneQuestoesDaProva;

    @FXML
    private Label labelQuestoesDaDisciplina;

    @FXML
    private FlowPane flowPaneQuestoesDaDisciplina;

    @FXML
    TextField campoTitulo;

    public ProvaController(ProvaVO prova) {
        this.prova = prova;
    }

    @FXML
    @Override
    protected void initialize() {
        super.initialize();
        atualizarProva();
    }

    public void atualizarProva() {

        campoTitulo.setText(prova.getTitulo());

        atualizarQuestoesDaProva();
        atualizarQuestoesDaDisciplina();
    }

    public void atualizarQuestoesDaProva() {

        flowPaneQuestoesDaProva.getChildren().clear();

        try {
            // Título e quantidade
            List<QuestaoVO> lista = questaoBO.buscar(prova);
            labelQuestoesDaProva.setText("Questões desta prova (" + String.valueOf(lista.size()) + ")");

            // Cards
            Iterator<QuestaoVO> listaIt = lista.iterator();
            while (listaIt.hasNext()) {
                QuestaoVO q = listaIt.next();
                flowPaneQuestoesDaProva.getChildren().add(criarCard(q));
            }

        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void atualizarQuestoesDaDisciplina() {

        flowPaneQuestoesDaDisciplina.getChildren().clear();

        try {
            // Título e quantidade
            List<QuestaoVO> lista = questaoBO.buscar(prova.getDisciplina());
            labelQuestoesDaDisciplina.setText("Todas as questões de " + prova.getDisciplina().getNome() + " ("
                    + String.valueOf(lista.size()) + ")");

            // Cards
            Iterator<QuestaoVO> listaIt = lista.iterator();
            while (listaIt.hasNext()) {
                QuestaoVO q = listaIt.next();
                flowPaneQuestoesDaDisciplina.getChildren().add(criarCard(q));
            }

        } catch (OperationException e) {
            e.printStackTrace();
        }
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

        // Separador #2
        Separator separador2 = new Separator();
        VBox.setMargin(separador2, new Insets(8.0));
        card.getChildren().addAll(separador2);

        // Opção
        HBox opcao = new HBox();
        opcao.setAlignment(Pos.CENTER);

        // Opção - Link
        Hyperlink linkOpcao = new Hyperlink();
        boolean pertence = questaoBO.contem(prova.getQuestoes(), questao);
        linkOpcao.setText((pertence ? "Remover da" : "Adicionar à") + " prova");
        linkOpcao.setTextFill(Paint.valueOf(pertence ? "#ff0000" : "#6610f2"));
        linkOpcao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (pertence)
                    remover(questao);
                else
                    adicionar(questao);
            }
        });

        opcao.getChildren().add(linkOpcao);

        card.getChildren().add(opcao);

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

    public void adicionar(QuestaoVO questao) {
        try {
            provaBO.adicionar(prova, questao);
            atualizarProva();
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void remover(QuestaoVO questao) {
        provaBO.remover(prova, questao);
        atualizarProva();
    }

    public void editar(ActionEvent event) {
        try {
            prova.setTitulo(campoTitulo.getText());

            provaBO.atualizar(prova);
            atualizarProva();
        } catch (OperationException e) {
            e.printStackTrace();
        }

    }

    public void excluir(ActionEvent event) {
        try {
            DisciplinaVO disciplina = prova.getDisciplina();
            provaBO.excluir(prova);
            voltar(disciplina);
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void salvarEVoltar(ActionEvent event) {
        editar(event);
        voltar(prova.getDisciplina());
    }

    public void voltar(DisciplinaVO disciplina) {
        try {
            Telas.telaDisciplinaDetalhes(disciplina);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

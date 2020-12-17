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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import src.exception.OperationException;
import src.model.BO.QuestaoBO;
import src.model.BO.QuestaoComAlternativasBO;
import src.model.VO.QuestaoComAlternativasVO;
import src.model.VO.QuestaoVO;
import src.view.Telas;

public class QuestaoComAlternativasController extends BarraController {

    private static QuestaoBO questaoBO = new QuestaoBO();
    private static QuestaoComAlternativasBO questaoSubjetivaBO = new QuestaoComAlternativasBO();

    private QuestaoComAlternativasVO questao;

    public QuestaoComAlternativasController(QuestaoComAlternativasVO questao) {
        this.questao = questao;
    }

    @FXML
    @Override
    protected void initialize() {
        super.initialize();
        atualizarQuestao();
    }

    @FXML
    TextArea questaoEnunciado;

    @FXML
    TextArea questaoGabarito;

    @FXML
    RadioButton nivelFacil;

    @FXML
    RadioButton nivelMedio;

    @FXML
    RadioButton nivelDificil;

    public void atualizarQuestao() {

        if (questao.getDificuldade() == QuestaoVO.FACIL)
            nivelFacil.setSelected(true);
        else if (questao.getDificuldade() == QuestaoVO.MEDIA)
            nivelMedio.setSelected(true);
        else if (questao.getDificuldade() == QuestaoVO.DIFICIL)
            nivelDificil.setSelected(true);
        else
            nivelFacil.setSelected(true);

        questaoEnunciado.setText(questao.getEnunciado());
        // TODO adicionar alternativas
        atualizarAssuntos();
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
        List<String> lista = questao.getAssuntos();

        labelAssuntos.setText("Assuntos (_)".replace("_", String.valueOf(lista.size())));

        // Cards
        Iterator<String> listaIt = lista.iterator();
        while (listaIt.hasNext())
            flowPaneAssuntos.getChildren().add(criarCard(listaIt.next()));

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
        card.getChildren().add(titulo);

        // Excluir
        Button excluir = new Button("X");
        excluir.setTextFill(Paint.valueOf("#ff0000"));
        excluir.setMnemonicParsing(false);
        HBox.setMargin(excluir, new Insets(0.0, 4.0, 0.0, 0.0));
        excluir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                removerAssunto(assunto);
            }
        });

        card.getChildren().add(excluir);

        return card;
    }

    @FXML
    TextField addAssunto;

    public void adicionarAssunto(ActionEvent event) {
        questaoBO.adicionar(questao, addAssunto.getText());
        atualizarAssuntos();
    }

    public void removerAssunto(String assunto) {
        questaoBO.remover(questao, assunto);
        atualizarAssuntos();
    }

    public void editar(ActionEvent event) {
        try {
            questao.setEnunciado(questaoEnunciado.getText());
            // TODO set alternativas

            if (nivelFacil.isSelected())
                questao.setDificuldade(QuestaoVO.FACIL);
            else if (nivelMedio.isSelected())
                questao.setDificuldade(QuestaoVO.MEDIA);
            else if (nivelDificil.isSelected())
                questao.setDificuldade(QuestaoVO.DIFICIL);
            else
                throw new OperationException("O nível de dificuldade não está correto.");

            questaoSubjetivaBO.atualizar(questao);
            atualizarQuestao();
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }
 
    public void excluir(ActionEvent event) {
        try {
            questaoSubjetivaBO.excluir(questao);
            voltar(event);
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void voltar(ActionEvent event) {
        try {
            Telas.telaDisciplinaDetalhes(questao.getDisciplina());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import src.exception.OperationException;
import src.model.BO.AlternativaBO;
import src.model.BO.QuestaoBO;
import src.model.BO.QuestaoComAlternativasBO;
import src.model.VO.AlternativaVO;
import src.model.VO.QuestaoComAlternativasVO;
import src.model.VO.QuestaoVO;
import src.view.Telas;

public class QuestaoComAlternativasController extends BarraController {

    private static QuestaoBO questaoBO = new QuestaoBO();
    private static QuestaoComAlternativasBO questaoComAlternativasBO = new QuestaoComAlternativasBO();
    private static AlternativaBO alternativaBO = new AlternativaBO();

    private QuestaoComAlternativasVO questao;

    public QuestaoComAlternativasController(QuestaoComAlternativasVO questao) {
        this.questao = questao;
    }

    @FXML
    Hyperlink voltarDisciplina;

    @FXML
    @Override
    protected void initialize() {
        super.initialize();
        atualizarQuestao();

        voltarDisciplina.setText(questao.getDisciplina().getNome());
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
        atualizarAssuntos();
        atualizarAlternativas();
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

    @FXML
    VBox vBoxAlternativas;

    public void atualizarAlternativas() {

        Node criar = vBoxAlternativas.getChildren().get(0);
        vBoxAlternativas.getChildren().clear();
        vBoxAlternativas.getChildren().add(criar);

        // Cards
        List<AlternativaVO> lista = questao.getAlternativas();
        Iterator<AlternativaVO> listaIt = lista.iterator();
        while (listaIt.hasNext())
            vBoxAlternativas.getChildren().add(criarCard(listaIt.next()));

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

    public HBox criarCard(AlternativaVO alternativa) {

        // Corpo do Card
        HBox card = new HBox();
        card.setAlignment(Pos.CENTER_LEFT);
        card.setSpacing(8.0);
        card.setPadding(new Insets(4.0));
        card.setStyle("-fx-background-color: " + (alternativa.isVerdadeira() ? " #00ff00;" : " #ff0000;"));
        VBox.setMargin(card, new Insets(4.0));

        Button veracidade = new Button(alternativa.isVerdadeira() ? "V" : "F");
        veracidade.setMnemonicParsing(false);
        veracidade.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        veracidade.setTextFill(Paint.valueOf("#ffffff"));
        veracidade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                inverterAlternativa(alternativa);
            }
        });
        card.getChildren().add(veracidade);

        // Nome do Assunto
        TextField texto = new TextField();
        texto.setText(alternativa.getTexto());
        texto.setStyle("-fx-background-color: transparent; -fx-text-fill: "
                + (alternativa.isVerdadeira() ? "#000000" : "#ffffff" + ";"));
        texto.setPrefWidth(238.0);
        card.getChildren().add(texto);

        // Botão Editar
        Button editar = new Button("Editar");
        editar.setMnemonicParsing(false);
        editar.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        editar.setTextFill(Paint.valueOf("#ffffff"));
        editar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alternativa.setTexto(texto.getText());
                editarAlternativa(alternativa);
            }
        });
        card.getChildren().add(editar);

        // Excluir
        Button excluir = new Button("X");
        excluir.setMnemonicParsing(false);
        excluir.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        excluir.setTextFill(Paint.valueOf("#ffffff"));
        excluir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                excluirAlternativa(alternativa);
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

    @FXML
    TextField novaAlternativaTexto;

    public AlternativaVO criarAlternativa() {

        AlternativaVO alternativa = new AlternativaVO();
        alternativa.setTexto(novaAlternativaTexto.getText());

        return alternativa;

    }

    public void criarAlternativaVerdadeira(ActionEvent event) {
        try {
            AlternativaVO alternativa = criarAlternativa();
            alternativa.setVerdadeira(true);
            questaoComAlternativasBO.adicionar(questao, alternativa);
            atualizarAlternativas();
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void criarAlternativaFalsa(ActionEvent event) {
        try {
            AlternativaVO alternativa = criarAlternativa();
            alternativa.setVerdadeira(false);
            questaoComAlternativasBO.adicionar(questao, alternativa);
            atualizarAlternativas();
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void removerAssunto(String assunto) {
        questaoBO.remover(questao, assunto);
        atualizarAssuntos();
    }

    public void editar(ActionEvent event) {
        try {
            questao.setEnunciado(questaoEnunciado.getText());

            if (nivelFacil.isSelected())
                questao.setDificuldade(QuestaoVO.FACIL);
            else if (nivelMedio.isSelected())
                questao.setDificuldade(QuestaoVO.MEDIA);
            else if (nivelDificil.isSelected())
                questao.setDificuldade(QuestaoVO.DIFICIL);
            else
                throw new OperationException("O nível de dificuldade não está correto.");

            questaoComAlternativasBO.atualizar(questao);
            atualizarQuestao();
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void inverterAlternativa(AlternativaVO alternativa) {
        alternativa.setVerdadeira(!alternativa.isVerdadeira());
        editarAlternativa(alternativa);
    }

    public void editarAlternativa(AlternativaVO alternativa) {
        try {
            alternativaBO.atualizar(alternativa);
            atualizarAlternativas();
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void excluirAlternativa(AlternativaVO alternativa) {
        try {
            questaoComAlternativasBO.remover(questao, alternativa);
            atualizarAlternativas();
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void excluir(ActionEvent event) {
        try {
            questaoComAlternativasBO.excluir(questao);
            voltar();
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void salvarEVoltar(ActionEvent event) {
        editar(event);
        voltar();
    }

    public void voltar() {
        try {
            Telas.telaDisciplinaDetalhes(questao.getDisciplina());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

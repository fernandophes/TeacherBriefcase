package src.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import src.exception.OperationException;
import src.model.BO.DisciplinaBO;
import src.model.BO.ProfessorBO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;
import src.view.Telas;

public class DisciplinasTabController extends BarraController {

    private static DisciplinaBO disciplinaBO = new DisciplinaBO();

    @FXML
    private TableView<DisciplinaVO> tabela;

    @FXML
    @Override
    protected void initialize() {
        super.initialize();
        atualizarTabela();

        tabela.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> selecionarLinha(newValue));

    }

    @FXML
    private TableColumn<DisciplinaVO, String> tabelaCodigo;

    @FXML
    private TableColumn<DisciplinaVO, String> tabelaNome;

    private List<DisciplinaVO> disciplinas = new ArrayList<DisciplinaVO>();

    private ObservableList<DisciplinaVO> obsDisciplinas;

    public void atualizarTabela() {

        try {

            tabelaCodigo.setCellValueFactory(new PropertyValueFactory<DisciplinaVO, String>("codigo"));
            tabelaNome.setCellValueFactory(new PropertyValueFactory<DisciplinaVO, String>("nome"));

            disciplinas.clear();
            disciplinas.addAll(disciplinaBO.listar());
            obsDisciplinas = FXCollections.observableArrayList(disciplinas);

            tabela.setItems(obsDisciplinas);

        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    @FXML
    Button botaoAbrir;

    @FXML
    Button botaoExcluir;

    @FXML
    Button botaoAddRmv;

    public void selecionarLinha(DisciplinaVO disciplina) {

        botaoAbrir.setDisable(false);
        botaoExcluir.setDisable(false);

        if (disciplinaBO.contem(ProfessorVO.logado.getDisciplinas(), disciplina)) {
            botaoAddRmv.setText("Remover");
        } else {
            botaoAddRmv.setText("Adicionar");
        }

        botaoAddRmv.setDisable(false);

    }

    public void abrirLinha(ActionEvent event) {

        DisciplinaVO disciplina = tabela.getSelectionModel().getSelectedItem();
        try {
            Telas.telaDisciplinaDetalhes(disciplina);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void excluirLinha(ActionEvent event) {

        DisciplinaVO disciplina = tabela.getSelectionModel().getSelectedItem();
        tabela.getItems().remove(disciplina);
        try {
            disciplinaBO.excluir(disciplina);
        } catch (OperationException e) {
            e.printStackTrace();
        }

    }

    public void adicionarOuRemover(ActionEvent event) {
        
        DisciplinaVO disciplina = tabela.getSelectionModel().getSelectedItem();
        ProfessorBO professorBO = new ProfessorBO();
        
        if (disciplinaBO.contem(ProfessorVO.logado.getDisciplinas(), disciplina)) {
            professorBO.remover(ProfessorVO.logado, disciplina);
        } else {
            professorBO.adicionar(ProfessorVO.logado, disciplina);
        }

        selecionarLinha(disciplina);
        
    }

    @FXML
    private TextField novaDisciplinaCodigo;

    @FXML
    private TextField novaDisciplinaNome;

    public void cadastrar(ActionEvent event) {
        try {
            DisciplinaVO disciplina = new DisciplinaVO();
            disciplina.setCodigo(novaDisciplinaCodigo.getText());
            disciplina.setNome(novaDisciplinaNome.getText());

            disciplinaBO.cadastrar(disciplina);

            atualizarTabela();

            novaDisciplinaCodigo.setText(null);
            novaDisciplinaNome.setText(null);
        } catch (OperationException e) {
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

    public void voltar(ActionEvent event) {
        try {
            Telas.telaDisciplinas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

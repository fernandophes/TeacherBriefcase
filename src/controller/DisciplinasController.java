package src.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import src.exception.OperationException;
import src.model.BO.DisciplinaBO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;

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

    public void atualizarMinhasDisciplinas() {
        int quantidade = ProfessorVO.logado.getDisciplinas().size();
        labelMinhasDisciplinas.setText(labelMinhasDisciplinas.getText().replace("_", String.valueOf(quantidade)));
    }

    @FXML
    private Label labelTodasAsDisciplinas;

    public void atualizarTodasAsDisciplinas() {
        int quantidade;
        try {
            quantidade = disciplinaBO.listar().size();
            labelTodasAsDisciplinas.setText(labelTodasAsDisciplinas.getText().replace("_", String.valueOf(quantidade)));
        } catch (OperationException e) {
            e.printStackTrace();
        }
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

}

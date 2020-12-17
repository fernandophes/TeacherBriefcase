package src.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import src.model.VO.ProfessorVO;
import src.view.Telas;

public class BarraController {

    @FXML
    protected void initialize() {
        atualizarBarra();
    }

    @FXML
    private Button buttonProfessor;

    public void atualizarBarra() {
        buttonProfessor.setText(ProfessorVO.logado.toString());
    }

    public void sair(ActionEvent event) {
        try {
            ProfessorVO.logado = null;
            Telas.telaLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

package src.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import src.exception.AuthenticationException;
import src.model.BO.ProfessorBO;
import src.model.VO.ProfessorVO;
import src.view.Telas;

public class FrontController {

    ProfessorBO professorBO = new ProfessorBO();

    @FXML
    private Label erroAut;
    @FXML
    private TextField loginEmail;
    @FXML
    private TextField loginSenha;

    public void autenticar(ActionEvent event) {
        try {
            ProfessorVO professor = new ProfessorVO();
            professor.setEmail(loginEmail.getText());
            professor.setSenha(loginSenha.getText());
            professorBO.autenticar(professor);
            ProfessorVO.logado = professor;
            Telas.telaInicial();
        } catch (AuthenticationException e) {
            erroAut.setText(e.getMessage());
            erroAut.setVisible(true);
            e.printStackTrace();
        } catch (Exception e) {
            erroAut.setText(e.getMessage());
            erroAut.setVisible(true);
            e.printStackTrace();
        }
    }

    @FXML
    private TextField cadastroNome;
    @FXML
    private TextField cadastroEmail;
    @FXML
    private TextField cadastroSenha;
    @FXML
    private TextField cadastroSenhaConfirm;

    public void cadastrar(ActionEvent event) {
        try {
            if (cadastroSenha.getText().equals(cadastroSenhaConfirm.getText())) {
                ProfessorVO professor = new ProfessorVO();
                professor.setNome(cadastroNome.getText());
                professor.setEmail(cadastroEmail.getText());
                professor.setSenha(cadastroSenha.getText());
                professorBO.cadastrar(professor);
                ProfessorVO.logado = professor;
                Telas.telaInicial();
            } else
                throw new AuthenticationException("As senhas não conferem");
        } catch (AuthenticationException e) {
            erroAut.setText(e.getMessage());
            erroAut.setVisible(true);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sair(ActionEvent event) {
        try {
            ProfessorVO.logado = null;
            Telas.telaLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private Button buttonProfessor;

    public void carregarBarraPrincipal() {
        buttonProfessor.setText(ProfessorVO.logado.getNome());
    }
}

package src.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import src.exception.AuthenticationException;
import src.model.BO.ProfessorBO;
import src.model.VO.ProfessorVO;

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
        } catch (AuthenticationException e) {
            erroAut.setText(e.getMessage());
            erroAut.setVisible(true);
            e.printStackTrace();
        }
    }

    @FXML
    private Label erroCad;
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
            } else
                throw new AuthenticationException("As senhas não conferem");
        } catch (AuthenticationException e) {
            erroCad.setText(e.getMessage());
            erroCad.setVisible(true);
            e.printStackTrace();
        }
    }
}

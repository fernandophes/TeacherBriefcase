package model.BO;

import model.VO.DisciplinaVO;
import model.VO.ProfessorVO;

public class ProfessorBO {
    void cadastrar(ProfessorVO professor) {
        // cadastra um novo Professor

        // analisa
        // DAO
    }

    ProfessorVO[] listar() {
        // lista todos os professores


        ProfessorVO[] lista = {};
        // analisa
        // DAO
        return lista;
    }

    ProfessorVO buscar(ProfessorVO professor) {
        // busca um professor

        ProfessorVO resultado = new ProfessorVO();
        // DAO
        // ajusta
        return resultado;
    }

    void editar(ProfessorVO professor) {
        // edita os dados de um professor

        // analisa
        // DAO
    }

    void excluir(ProfessorVO professor) {
        // exclui um professor

        // analisa
        // DAO
    }

    boolean autenticar(ProfessorVO professor) {
        // verifica se o e-mail e a senha do Professor correspondem ao BD
        
        // analisa
        // DAO
        // ajusta
        return true;
    }

    void adicionar(ProfessorVO professor, DisciplinaVO disciplina) {
        // adiciona a disciplina à lista, caso ainda não exista

        // analisa
        // DAO
    }

    void remover(ProfessorVO professor, DisciplinaVO disciplina) {
        // remove a disciplina deste professor

        // analisa
        // DAO
    }
}
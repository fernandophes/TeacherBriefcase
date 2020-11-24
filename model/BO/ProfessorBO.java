package model.BO;

import java.util.List;

import model.VO.DisciplinaVO;
import model.VO.ProfessorVO;

public class ProfessorBO {
    public void cadastrar(ProfessorVO professor) {
        // cadastra um novo Professor

        // analisa
        // DAO
    }

    public ProfessorVO[] listar() {
        // lista todos os professores

        ProfessorVO[] lista = {};
        // analisa
        // DAO
        return lista;
    }

    public ProfessorVO buscar(ProfessorVO professor) {
        // busca um professor

        ProfessorVO resultado = new ProfessorVO();
        // DAO
        // ajusta
        return resultado;
    }

    public void editar(ProfessorVO professor) {
        // edita os dados de um professor

        // analisa
        // DAO
    }

    public void excluir(ProfessorVO professor) {
        // exclui um professor

        // analisa
        // DAO
    }

    public boolean autenticar(ProfessorVO professor) {
        // verifica se o e-mail e a senha do Professor correspondem ao BD

        // analisa
        // DAO
        // ajusta
        return true;
    }

    public void adicionar(ProfessorVO professor, DisciplinaVO disciplina) {
        // adiciona a disciplina à lista, caso ainda não exista

        List<DisciplinaVO> lista = professor.getDisciplinas();

        // Se esta disciplina não estiver na lista deste professor, será adicionada
        if (!lista.contains(disciplina)) {
            lista.add(disciplina);
            professor.setDisciplinas(lista);

            // atualiza a disciplina
            DisciplinaBO disciplinaBO = new DisciplinaBO();
            disciplinaBO.adicionar(disciplina, professor);

            // DAO
        }
    }

    public void remover(ProfessorVO professor, DisciplinaVO disciplina) {
        // remove a disciplina deste professor

        List<DisciplinaVO> lista = professor.getDisciplinas();

        // Se esta disciplina estiver na lista deste professor, será removida
        if (lista.remove(disciplina)) {
            professor.setDisciplinas(lista);

            // atualiza a disciplina
            DisciplinaBO disciplinaBO = new DisciplinaBO();
            disciplinaBO.remover(disciplina, professor);

            // DAO
        }
    }
}

package model.BO;

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

        DisciplinaVO[] lista = professor.getDisciplinas();

        // verifica se o professor ja possui uma disciplina de mesmo nome cadastrada
        for (int i = 0; i < lista.length; i++) {
            if (lista[i].getNome().equals(disciplina.getNome())) {
                // ERRO: O professor JA POSSUI uma disciplina com o mesmo nome
                // a execução do método morre
                return;
            }
        }

        // adiciona a disciplina ao final da lista
        lista[lista.length] = disciplina;

        // atualiza o professor
        professor.setDisciplinas(lista);

        // atualiza a disciplina
        DisciplinaBO disciplinaBO = new DisciplinaBO();
        disciplinaBO.adicionar(disciplina, professor);

        // analisa
        // DAO
    }

    public void remover(ProfessorVO professor, DisciplinaVO disciplina) {
        // remove a disciplina deste professor

        DisciplinaVO[] lista = professor.getDisciplinas();

        // verifica se o professor realmente possui uma disciplina de mesmo nome cadastrada
        for (int i = 0; i < lista.length; i++)
            if (lista[i].getNome().equals(disciplina.getNome())) {
                // atualiza o professor
                    // CÓDIGO PARA REMOVER A DISCIPLINA

                // atualiza a disciplina
                DisciplinaBO disciplinaBO = new DisciplinaBO();
                disciplinaBO.remover(disciplina, professor);
                
                return;
            }

        // analisa
        // DAO
    }
}

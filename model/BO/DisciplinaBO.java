package model.BO;

import model.VO.AssuntoVO;
import model.VO.DisciplinaVO;
import model.VO.ProfessorVO;
import model.VO.QuestaoVO;

public class DisciplinaBO {
    public void cadastrar(DisciplinaVO disciplina) {
        // cadastra uma nova disciplina

        // analisa
        // DAO
    }

    public DisciplinaVO[] listar() {
        // lista todas as disciplinas

        DisciplinaVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    public DisciplinaVO buscar(DisciplinaVO disciplina) {
        // busca uma disciplina

        DisciplinaVO resultado = new DisciplinaVO();
        // DAO
        // ajusta
        return resultado;
    }

    public void editar(DisciplinaVO disciplina) {
        // edita os dados de uma disciplina

        // analisa
        // DAO
        // ajusta
    }

    public void excluir(DisciplinaVO disciplina) {
        // exclui uma disciplina

        // analisa
        // DAO
    }

    public void adicionar(DisciplinaVO disciplina, AssuntoVO assunto) {
        // adiciona um assunto à disciplina

        AssuntoVO[] lista = disciplina.getAssuntos();

        // verifica se a disciplina ja possui um assunto de mesmo nome cadastrado
        for (int i = 0; i < lista.length; i++)
            if (lista[i].getNome().equals(assunto.getNome()))
                return;
                // ERRO: A disciplina JA POSSUI um assunto com o mesmo nome
                // a execução do método morre

        // incrementa a lista
        lista[lista.length] = assunto;
        
        // atualiza a disciplina
        disciplina.setAssuntos(lista);

        // atualiza o assunto
        assunto.setDisciplina(disciplina);

        // analisa
        // DAO
        // ajusta
    }

    public void remover(DisciplinaVO disciplina, AssuntoVO assunto) {
        // remove o assunto da disciplina

        AssuntoVO[] lista = disciplina.getAssuntos();

        // verifica se a disciplina realmente possui um assunto de mesmo nome cadastrado
        for (int i = 0; i < lista.length; i++)
            if (lista[i].getNome().equals(assunto.getNome())) {
                // atualiza a disciplina
                    // CÓDIGO PARA REMOVER O ASSUNTO

                // atualiza as questões (remove este assunto da lista de cada uma)
                QuestaoBO questaoBO = new QuestaoBO();
                QuestaoVO[] questoes = assunto.getQuestoes();
                for (int j = 0; j < questoes.length; j++)
                    questaoBO.remover(questoes[j], assunto);

                // atualiza o assunto (exclui, pois o assunto depende da ligação com a disciplina)
                AssuntoBO assuntoBO = new AssuntoBO();
                assuntoBO.excluir(assunto);

                return;
            }

        // analisa
        // DAO
        // ajusta
    }

    public void adicionar(DisciplinaVO disciplina, ProfessorVO professor) {
        // adiciona um professor à disciplina
        
        ProfessorVO[] lista = disciplina.getProfessores();

        // verifica se a disciplina ja possui um professor de mesmo e-mail cadastrado
        for (int i = 0; i < lista.length; i++)
            if (lista[i].getEmail().equals(professor.getEmail()))
                return;
                // ERRO: A disciplina JA POSSUI um professor com o mesmo e-mail
                // a execução do método morre

        // incrementa a lista
        lista[lista.length] = professor;

        // atualiza a disciplina
        disciplina.setProfessores(lista);

        // atualiza o professor
        ProfessorBO professorBO = new ProfessorBO();
        professorBO.adicionar(professor, disciplina);

        // analisa
        // DAO
        // ajusta
    }

    public void remover(DisciplinaVO disciplina, ProfessorVO professor) {
        // remove o professor da disciplina

        ProfessorVO[] lista = disciplina.getProfessores();

        // verifica se a disciplina realmente possui um professor de mesmo e-mail cadastrado
        for (int i = 0; i < lista.length; i++)
            if (lista[i].getEmail().equals(professor.getEmail())) {
                // atualiza a disciplina
                    // CÓDIGO QUE REMOVE O PROFESSOR
                
                // atualiza o professor
                ProfessorBO professorBO = new ProfessorBO();
                professorBO.remover(professor, disciplina);

                return;
            }

        // analisa
        // DAO
        // ajusta
    }
}

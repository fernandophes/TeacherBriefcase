package model.BO;

import java.util.List;

import model.VO.AssuntoVO;
import model.VO.QuestaoVO;

public class AssuntoBO {
    public void cadastrar(AssuntoVO assunto) {
        // cadastra um novo assunto no BD

        // analisa
        // DAO
    }

    public AssuntoVO[] listar() {
        // lista todos os assuntos

        AssuntoVO[] lista = {};
        // DAO
        // ajusta
        return lista;
    }

    public AssuntoVO buscar(AssuntoVO assunto) {
        // busca um assunto

        AssuntoVO resultado = new AssuntoVO();
        // DAO
        // ajusta
        return resultado;
    }

    public void editar(AssuntoVO assunto) {
        // edita os dados de um assunto

        // analisa
        // DAO
        // ajusta
    }

    public void excluir(AssuntoVO assunto) {
        // exclui um assunto

        // analisa
        // DAO
    }

    public void adicionar(AssuntoVO assunto, QuestaoVO questao) {

        List<QuestaoVO> lista = assunto.getQuestoes();

        // Se esta questão não existir na lista deste assunto, poderá ou não ser
        // adicionada
        if (!lista.contains(questao))
            // Esta questão só será adicionada se pertencer à mesma disciplina que este
            // assunto
            if (questao.getDisciplina().equals(assunto.getDisciplina())) {
                lista.add(questao);
                assunto.setQuestoes(lista);

                // Atualiza a questão
                QuestaoBO questaoBO = new QuestaoBO();
                questaoBO.adicionar(questao, assunto);

                // DAO
            }
    }

    public void remover(AssuntoVO assunto, QuestaoVO questao) {

        List<QuestaoVO> lista = assunto.getQuestoes();

        // Se esta questão estiver na lista deste assunto, será removida
        if (lista.remove(questao)) {
            assunto.setQuestoes(lista);

            // Atualiza a questão
            QuestaoBO questaoBO = new QuestaoBO();
            questaoBO.remover(questao, assunto);
        }
    }
}

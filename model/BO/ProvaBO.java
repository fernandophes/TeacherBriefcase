package model.BO;

import java.util.ArrayList;
import java.util.List;

import model.VO.ProvaVO;
import model.VO.QuestaoVO;

public class ProvaBO {
    public void cadastrar(ProvaVO prova) {
        // cadastra uma prova no BD

        // analisa
        // DAO
    }

    public List<ProvaVO> listar() {
        // lista todas as provas

        List<ProvaVO> lista = new ArrayList<ProvaVO>();
        // DAO
        // ajusta
        return lista;
    }

    public ProvaVO buscar(ProvaVO prova) {
        // busca uma prova

        ProvaVO resultado = new ProvaVO();
        // DAO
        // ajusta
        return resultado;
    }

    public void editar(ProvaVO prova) {
        // edita os dados da prova

        // analisa
        // DAO
    }

    public void excluir(ProvaVO prova) {
        // exclui a prova do BD

        // analisa
        // DAO
    }

    public void adicionar(ProvaVO prova, QuestaoVO questao) {

        List<QuestaoVO> lista = prova.getQuestoes();

        // Se esta questão não estiver na lista desta prova, poderá ou não ser
        // adicionada
        if (!lista.contains(questao))
            // Esta questão só será adicionada a esta prova se ambas pertencerem à mesma
            // disciplina
            if (prova.getDisciplina().equals(questao.getDisciplina())) {
                lista.add(questao);
                prova.setQuestoes(lista);

                // Atualizar a questão
                QuestaoBO.adicionar(questao, prova);

                // DAO
            }
    }

    public void remover(ProvaVO prova, QuestaoVO questao) {

        List<QuestaoVO> lista = prova.getQuestoes();

        // Se esta questão estiver na lista desta prova, será removida
        if (lista.remove(questao)) {
            prova.setQuestoes(lista);

            // Atualizar a questão
            QuestaoBO.remover(questao, prova);

            // DAO
        }
    }
}

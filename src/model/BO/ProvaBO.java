package src.model.BO;

import java.util.ArrayList;
import java.util.List;

import src.model.VO.ProvaVO;
import src.model.VO.QuestaoVO;

public class ProvaBO implements ProvaInterBO {
    public void cadastrar(ProvaVO prova) {
        // cadastra uma prova no BD

        // analisa
        // TODO DAO
    }

    public List<ProvaVO> listar() {
        // lista todas as provas

        List<ProvaVO> lista = new ArrayList<ProvaVO>();
        // TODO DAO
        // ajusta
        return lista;
    }

    public ProvaVO buscar(ProvaVO prova) {
        // busca uma prova

        ProvaVO resultado = new ProvaVO();
        // TODO DAO
        // ajusta
        return resultado;
    }

    public void editar(ProvaVO prova) {
        // edita os dados da prova

        // analisa
        // TODO DAO
    }

    public void excluir(ProvaVO prova) {
        // exclui a prova do BD

        // analisa
        // TODO DAO
    }

    public ProvaVO gerar(int quaisquer, int faceis, int medias, int dificeis) {
        // Gera uma prova com questões aleatórias ProvaVO resultado = new ProvaVO();

        // TODO DAO

        return new ProvaVO();
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
                QuestaoBO questaoBO = new QuestaoBO();
                questaoBO.adicionar(questao, prova);

                // TODO DAO
            } else {
                System.out.println("A prova e a questão não pertencem à mesma disciplina");
            }
    }

    public void remover(ProvaVO prova, QuestaoVO questao) {

        List<QuestaoVO> lista = prova.getQuestoes();

        // Se esta questão estiver na lista desta prova, será removida
        if (lista.remove(questao)) {
            prova.setQuestoes(lista);

            // Atualizar a questão
            QuestaoBO questaoBO = new QuestaoBO();
            questaoBO.remover(questao, prova);

            // TODO DAO
        }
    }
}

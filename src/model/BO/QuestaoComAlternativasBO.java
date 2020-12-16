package src.model.BO;

import java.util.List;

import src.model.VO.AlternativaVO;
import src.model.VO.QuestaoComAlternativasVO;
import src.model.VO.QuestaoVO;

public class QuestaoComAlternativasBO extends QuestaoBO {

    public void adicionar(QuestaoComAlternativasVO questao, AlternativaVO alternativa) {
        // adiciona uma alternativa à questão

        List<AlternativaVO> lista = questao.getAlternativas();

        // Se esta alternativa não estiver na lista desta questão, será adicionada
        if (!lista.contains(alternativa)) {
            lista.add(alternativa);
            questao.setAlternativas(lista);

            // atualiza a alternativa
            alternativa.setQuestao(questao);

            // TODO DAO
        }
    }

    public void remover(QuestaoComAlternativasVO questao, AlternativaVO alternativa) {
        // remove a alternativa da questão

        List<AlternativaVO> lista = questao.getAlternativas();

        // Se esta alternativa estiver na lista desta questão, será removida
        if (lista.remove(alternativa)) {
            questao.setAlternativas(lista);

            // atualiza a alternativa (exclui, pois ela depende do vínculo com a questão)
            AlternativaBO alternativaBO = new AlternativaBO();
            alternativaBO.excluir(alternativa);

            // TODO DAO
        }
    }

    @Override
    public QuestaoComAlternativasVO buscar(QuestaoVO questao) {

        QuestaoComAlternativasVO resultado = new QuestaoComAlternativasVO();
        // TODO DAO
        // ajusta

        return resultado;
    }
}

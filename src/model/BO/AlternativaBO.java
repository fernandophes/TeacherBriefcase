package src.model.BO;

import java.util.ArrayList;
import java.util.List;

import src.model.VO.AlternativaVO;
import src.model.VO.QuestaoComAlternativasVO;

public class AlternativaBO implements AlternativaInterBO {
    public void cadastrar(AlternativaVO alternativa) {
        // cadastra uma nova alternativa no BD

        // analisa
        // DAO
    }

    public List<AlternativaVO> listar() {
        // lista todas as alternativas

        List<AlternativaVO> lista = new ArrayList<AlternativaVO>();
        // DAO
        // ajusta
        return lista;
    }

    public AlternativaVO buscar(AlternativaVO alternativa) {
        // busca uma alternativa

        AlternativaVO resultado = new AlternativaVO();
        // DAO
        // ajusta
        return resultado;
    }

    public void editar(AlternativaVO alternativa) {
        // edita os dados de uma alternativa

        // analisa
        // DAO
        // ajusta
    }

    public void excluir(AlternativaVO alternativa) {
        // exclui uma alternativa

        // analisa
        // DAO
    }

    @Override
    public void mudar(AlternativaVO alternativa, QuestaoComAlternativasVO questao) {
        QuestaoComAlternativasBO questaoComAlternativasBO = new QuestaoComAlternativasBO();

        // Atualizando a questão antiga (removendo a alternativa da lista)
        if (alternativa.getQuestao() != null) {
            questaoComAlternativasBO.remover(questao, alternativa);
        }

        // Atualizando a alternativa
        alternativa.setQuestao(questao);

        // Atualizando a nova questão (adicionando a alternativa à lista)
        

    }
}

package src.model.BO;

import src.model.VO.AlternativaVO;
import src.model.VO.QuestaoComAlternativasVO;

public interface AlternativaInterBO extends BaseInterBO<AlternativaVO> {
    
    public void mudar(AlternativaVO alternativa, QuestaoComAlternativasVO questao);

}

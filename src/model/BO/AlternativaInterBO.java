package src.model.BO;

import java.util.List;

import src.model.VO.AlternativaVO;
import src.model.VO.QuestaoComAlternativasVO;

public interface AlternativaInterBO extends BaseInterBO<AlternativaVO> {
    
    public List<AlternativaVO> buscar(QuestaoComAlternativasVO questao);

}

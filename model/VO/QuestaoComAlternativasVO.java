package model.VO;

import java.util.List;

public class QuestaoComAlternativasVO extends QuestaoVO {
    private List<AlternativaVO> alternativas;

    public List<AlternativaVO> getAlternativas() {
        return alternativas;
    }   

    public void setAlternativas(List<AlternativaVO> alternativas) {
        this.alternativas = alternativas;
    }
}

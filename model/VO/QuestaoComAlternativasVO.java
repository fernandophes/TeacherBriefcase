package model.VO;

public class QuestaoComAlternativasVO extends QuestaoVO {
    private AlternativaVO[] alternativas;

    public AlternativaVO[] getAlternativas() {
        return alternativas;
    }   

    public void setAlternativas(AlternativaVO[] alternativas) {
        this.alternativas = alternativas;
    }
}

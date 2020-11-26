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

    @Override
    public String toString() {
        String saida = toString();
        List<AlternativaVO> alternativas = getAlternativas();
        char item = 'a';

        // Listar as alternativas
        while (alternativas.iterator().hasNext()) {
            saida = "\n" + item + ") " + alternativas.iterator().next();
            item++;
        }

        return saida;
    }
    
    public String getQuestaoRespondida() {
        String saida = toString();
        List<AlternativaVO> alternativas = getAlternativas();
        char item = 'a';

        // Listar as alternativas
        while (alternativas.iterator().hasNext()) {
            saida += "\n" + item + ") " + alternativas.iterator().next().getGabarito();
            item++;
        }

        return saida;
    }
    
}

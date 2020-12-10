package src.model.VO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import src.exception.AuthenticationException;

public class QuestaoComAlternativasVO extends QuestaoVO {
    private long id;
    private List<AlternativaVO> alternativas = new ArrayList<AlternativaVO>();

    public QuestaoComAlternativasVO() {

    }

    public QuestaoComAlternativasVO(int dificuldade, String enunciado) throws AuthenticationException {
        super(dificuldade, enunciado);
    }

    public QuestaoComAlternativasVO(int dificuldade, List<String> assuntos, String enunciado)
            throws AuthenticationException {
        super(dificuldade, assuntos, enunciado);
    }

    public long getIdQuestao() {
        return super.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) throws AuthenticationException {
        if (id > 0)
            this.id = id;
        else
            throw new AuthenticationException("O id precisa ser maior que zero");
    }

    public List<AlternativaVO> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<AlternativaVO> alternativas) {
        this.alternativas = alternativas;
    }

    @Override
    public String toString() {
        String saida = super.toString();
        List<AlternativaVO> alternativas = getAlternativas();
        char item = 'a';

        // Listar as alternativas
        Iterator<AlternativaVO> alternativasIt = alternativas.iterator();
        while (alternativasIt.hasNext()) {
            saida += "\n" + item++ + ") " + alternativasIt.next();
        }

        return saida;
    }

    public String getQuestaoRespondida() {
        String saida = super.getQuestaoRespondida();
        List<AlternativaVO> alternativas = getAlternativas();
        char item = 'a';

        // Listar as alternativas
        Iterator<AlternativaVO> alternativasIt = alternativas.iterator();
        while (alternativasIt.hasNext()) {
            saida += "\n" + item++ + ") " + alternativasIt.next().getGabarito();
        }

        return saida;
    }

}

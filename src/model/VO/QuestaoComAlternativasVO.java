package src.model.VO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import src.exception.OperationException;
import src.model.QuestaoDificuldade;

public class QuestaoComAlternativasVO extends QuestaoVO {
    
    private List<AlternativaVO> alternativas = new ArrayList<AlternativaVO>();

    public QuestaoComAlternativasVO() {

    }

    public QuestaoComAlternativasVO(QuestaoVO questao) throws OperationException {
        if (questao != null) {
            setAssuntos(questao.getAssuntos());
            setDataCriacao(questao.getDataCriacao());
            setDificuldade(questao.getDificuldade());
            setDisciplina(questao.getDisciplina());
            setEnunciado(questao.getEnunciado());
            setId(questao.getId());
        } else
            throw new OperationException("A questão fornecida não pode ser nula");
    }

    public QuestaoComAlternativasVO(QuestaoDificuldade dificuldade, String enunciado) throws OperationException {
        super(dificuldade, enunciado);
    }

    public QuestaoComAlternativasVO(QuestaoDificuldade dificuldade, List<String> assuntos, String enunciado)
            throws OperationException {
        super(dificuldade, assuntos, enunciado);
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

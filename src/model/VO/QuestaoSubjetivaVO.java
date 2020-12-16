package src.model.VO;

import java.util.List;

import src.exception.OperationException;

public class QuestaoSubjetivaVO extends QuestaoVO {
    private String gabarito = "";

    public QuestaoSubjetivaVO() {

    }

    public QuestaoSubjetivaVO(int dificuldade, String enunciado) throws OperationException {
        super(dificuldade, enunciado);
    }

    public QuestaoSubjetivaVO(int dificuldade, List<String> assuntos, String enunciado) throws OperationException {
        super(dificuldade, assuntos, enunciado);
    }

    public QuestaoSubjetivaVO(int dificuldade, String enunciado, String gabarito) throws OperationException {
        super(dificuldade, enunciado);
        setGabarito(gabarito);
    }

    public QuestaoSubjetivaVO(int dificuldade, List<String> assuntos, String enunciado, String gabarito)
            throws OperationException {
        super(dificuldade, assuntos, enunciado);
        setGabarito(gabarito);
    }

    public String getGabarito() {
        return gabarito;
    }

    public void setGabarito(String gabarito) throws OperationException {
        if (gabarito != null && !gabarito.isEmpty())
            this.gabarito = gabarito.trim();
        else
            throw new OperationException("O gabarito não pode ficar em branco");
    }

    public String getQuestaoRespondida() {
        String saida = super.getQuestaoRespondida() + "\nResposta: " + getGabarito();
        return saida;
    }

}

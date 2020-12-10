package src.model.VO;

import java.util.List;

import src.exception.AuthenticationException;

public class QuestaoSubjetivaVO extends QuestaoVO {
    private long id;
    private String gabarito = "";

    public QuestaoSubjetivaVO() {

    }

    public QuestaoSubjetivaVO(int dificuldade, String enunciado) throws AuthenticationException {
        super(dificuldade, enunciado);
    }

    public QuestaoSubjetivaVO(int dificuldade, List<String> assuntos, String enunciado) throws AuthenticationException {
        super(dificuldade, assuntos, enunciado);
    }

    public QuestaoSubjetivaVO(int dificuldade, String enunciado, String gabarito) throws AuthenticationException {
        super(dificuldade, enunciado);
        setGabarito(gabarito);
    }

    public QuestaoSubjetivaVO(int dificuldade, List<String> assuntos, String enunciado, String gabarito)
            throws AuthenticationException {
        super(dificuldade, assuntos, enunciado);
        setGabarito(gabarito);
    }

    public long getIdQuestao() {
        return super.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id > 0)
            this.id = id;
    }

    public String getGabarito() {
        return gabarito;
    }

    public void setGabarito(String gabarito) {
        if (gabarito != null && !gabarito.isEmpty())
            this.gabarito = gabarito.trim();
    }

    public String getQuestaoRespondida() {
        String saida = super.getQuestaoRespondida() + "\nResposta: " + getGabarito();
        return saida;
    }

}

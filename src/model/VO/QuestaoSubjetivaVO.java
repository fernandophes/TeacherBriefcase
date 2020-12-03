package src.model.VO;

import java.util.List;

public class QuestaoSubjetivaVO extends QuestaoVO {
    private long id;
    private String gabarito = "";

    public QuestaoSubjetivaVO() {

    }

    public QuestaoSubjetivaVO(int dificuldade, String enunciado) {
        super(dificuldade, enunciado);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id > 0)
            this.id = id;
    }

    public QuestaoSubjetivaVO(int dificuldade, List<String> assuntos, String enunciado) {
        super(dificuldade, assuntos, enunciado);
    }

    public QuestaoSubjetivaVO(int dificuldade, String enunciado, String gabarito) {
        super(dificuldade, enunciado);
        setGabarito(gabarito);
    }

    public QuestaoSubjetivaVO(int dificuldade, List<String> assuntos, String enunciado, String gabarito) {
        super(dificuldade, assuntos, enunciado);
        setGabarito(gabarito);
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

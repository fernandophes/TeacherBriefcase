package model.VO;

public class QuestaoSubjetivaVO extends QuestaoVO {
    private String gabarito;

    public String getGabarito() {
        return gabarito;
    }

    public void setGabarito(String gabarito) {
        this.gabarito = gabarito;
    }

    public String getQuestaoRespondida() {
        String resposta = toString() + "\nResposta: " + getGabarito();
        return resposta;
    }
    
}

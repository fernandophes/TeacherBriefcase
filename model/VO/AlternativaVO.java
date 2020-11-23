package model.VO;

import java.util.Calendar;

public class AlternativaVO {
    private QuestaoComAlternativasVO questao;
    private String texto;
    private boolean verdadeira;
    private Calendar dataCriacao;

    public QuestaoComAlternativasVO getQuestao() {
        return questao;
    }

    public void setQuestao(QuestaoComAlternativasVO questao) {
        this.questao = questao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        if (texto != null && !texto.isEmpty())
            this.texto = texto;
    }

    public boolean isVerdadeira() {
        return verdadeira;
    }

    public void setVerdadeira(boolean verdadeira) {
        this.verdadeira = verdadeira;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        // A data de criação da alternativa pode ser passada ou atual, mas não futura
        if (dataCriacao != null && (dataCriacao.compareTo(Calendar.getInstance()) <= 0))
            this.dataCriacao = dataCriacao;
    }

}

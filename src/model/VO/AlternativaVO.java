package src.model.VO;

import java.util.Calendar;

public class AlternativaVO {
    private long id;
    private String texto = "";
    private boolean verdadeira = false;
    private Calendar dataCriacao = Calendar.getInstance();

    public AlternativaVO() {

    }

    public AlternativaVO(String texto, boolean verdadeira) {
        setTexto(texto);
        setVerdadeira(verdadeira);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id > 0)
            this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        if (texto != null && !texto.isEmpty())
            this.texto = texto.trim();
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

    @Override
    public String toString() {
        return texto;
    }

    public String getGabarito() {
        String resposta = toString() + " (" + (verdadeira ? "V" : "F") + ")";
        return resposta;
    }
    
}

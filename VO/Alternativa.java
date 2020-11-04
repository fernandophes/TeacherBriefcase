package VO;

import java.util.Calendar;

public class Alternativa {
    private int id;
    private int questao;
    // Não recomendado instanciar Questão aqui agora, porque pode causar um ciclo
    // infinito de Alternativa e Questão se instanciando mutuamente
    private String texto;
    private boolean verdadeira;
    private Professor autor;
    private Calendar dataCriacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id >= 0)
            this.id = id;
    }

    public int getQuestao() {
        return questao;
    }

    public void setQuestao(int questao) {
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

    public Professor getAutor() {
        return autor;
    }

    public void setAutor(Professor autor) {
        if (autor != null)
            this.autor = autor;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        if (dataCriacao != null)
            this.dataCriacao = dataCriacao;
    }

}

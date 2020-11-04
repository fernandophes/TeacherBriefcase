package VO;

import java.util.Calendar;

public class Alternativa {
    private int id;
    private int questao;
    // Não recomendado instanciar Questão aqui agora, porque pode causar um ciclo infinito de Alternativa e Questão se instanciando mutuamente
    private String texto;
    private boolean veracidade;
    private Professor autor;
    private Calendar dataCriacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        this.texto = texto;
    }

    public boolean isVeracidade() {
        return veracidade;
    }

    public void setVeracidade(boolean veracidade) {
        this.veracidade = veracidade;
    }

    public Professor getAutor() {
        return autor;
    }

    public void setAutor(Professor autor) {
        this.autor = autor;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}

package VO;

import java.util.Calendar;

public class MultiplaEscolha {
    private int id;
    private String enunciado;
    private Disciplina disciplina;
    private int dificuldade;
    private boolean publica;
    private Professor autor;
    private Calendar dataCriacao;
    private Alternativa[] alternativas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
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

    public Alternativa[] getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(Alternativa[] alternativas) {
        this.alternativas = alternativas;
    }
}

package VO;

import java.util.Calendar;

public class Assunto {
    private int id;
    private String nome;
    private Professor autor;
    private Calendar dataCriacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id >= 0)
            this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty())
            this.nome = nome;
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

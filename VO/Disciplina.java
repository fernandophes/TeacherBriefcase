package VO;

import java.util.Calendar;

public class Disciplina {
    private int id;
    private String nome;
    private int autor;
    // Não dá pra instanciar Professor aqui, por enquanto, pois poderia abrir um
    // ciclo infinito de Disciplina instanciando Professor, e Professor instanciando
    // Disciplina
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

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        if (autor > 0)
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
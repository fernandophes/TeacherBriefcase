package model.VO;

import java.util.Calendar;

public class AssuntoVO {
    private String nome;
    private Calendar dataCriacao;
    private DisciplinaVO disciplina;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty())
            this.nome = nome;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        if (dataCriacao != null)
            this.dataCriacao = dataCriacao;
    }

    public DisciplinaVO getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaVO disciplina) {
        this.disciplina = disciplina;
    }
}

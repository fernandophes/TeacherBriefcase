package model.VO;

import java.util.Calendar;

public class ProvaVO {
    private DisciplinaVO disciplina;
    private String titulo;
    private Calendar dataCriacao;
    private QuestaoVO[] questoes;

    public DisciplinaVO getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaVO disciplina) {
        if (disciplina != null)
            this.disciplina = disciplina;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.isEmpty())
            this.titulo = titulo;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        if (dataCriacao != null)
            this.dataCriacao = dataCriacao;
    }

    public QuestaoVO[] getQuestoes() {
        return questoes;
    }

    public void setQuestoes(QuestaoVO[] questoes) {
        this.questoes = questoes;
    }
}

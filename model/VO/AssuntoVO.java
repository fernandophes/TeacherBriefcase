package model.VO;

import java.util.Calendar;

import model.BO.DisciplinaBO;

public class AssuntoVO {
    private String nome;
    private Calendar dataCriacao;
    private DisciplinaVO disciplina;
    private QuestaoVO[] questoes;

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
        // A data de criação do assunto pode ser passada ou atual, mas não futura
        if (dataCriacao != null && (dataCriacao.compareTo(Calendar.getInstance()) <= 0))
            this.dataCriacao = dataCriacao;
    }

    public DisciplinaVO getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaVO disciplina) {
        this.disciplina = disciplina;

        DisciplinaBO disciplinaBO = new DisciplinaBO();
        disciplinaBO.adicionar(disciplina, this);
    }

    public QuestaoVO[] getQuestoes() {
        return questoes;
    }

    public void setQuestoes(QuestaoVO[] questoes) {
        this.questoes = questoes;
    }
}

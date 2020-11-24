package model.VO;

import java.util.Calendar;
import java.util.List;

import model.BO.DisciplinaBO;

public class AssuntoVO {
    private String nome;
    private Calendar dataCriacao;
    private DisciplinaVO disciplina;
    private List<QuestaoVO> questoes;

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
        // Atualiza a antiga disciplina, se existir uma
        DisciplinaBO antiga = new DisciplinaBO();
        antiga.remover(disciplina, this);

        // Atualiza o assunto
        this.disciplina = disciplina;

        // Atualiza a nova disciplina
        DisciplinaBO nova = new DisciplinaBO();
        nova.adicionar(disciplina, this);
    }

    public List<QuestaoVO> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<QuestaoVO> questoes) {
        this.questoes = questoes;
    }
}

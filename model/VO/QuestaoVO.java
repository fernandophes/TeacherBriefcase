package model.VO;

import java.util.Calendar;

public class QuestaoVO {
    private String enunciado;
    private DisciplinaVO disciplina;
    private int dificuldade;
    private boolean publica;
    private Calendar dataCriacao;
    private AssuntoVO[] assuntos;

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        if (enunciado != null && !enunciado.isEmpty())
            this.enunciado = enunciado;
    }

    public DisciplinaVO getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaVO disciplina) {
        if (disciplina != null)
            this.disciplina = disciplina;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        if (dificuldade >= 0)
            this.dificuldade = dificuldade;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        // A data de criação da questão pode ser passada ou atual, mas não futura
        if (dataCriacao != null && (dataCriacao.compareTo(Calendar.getInstance()) <= 0))
            this.dataCriacao = dataCriacao;
    }

    public AssuntoVO[] getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(AssuntoVO[] assuntos) {
        this.assuntos = assuntos;
    }
}

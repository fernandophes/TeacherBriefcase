package model.VO;

import java.util.Calendar;
import java.util.List;

import model.BO.DisciplinaBO;

public abstract class QuestaoVO {
    private String enunciado;
    private DisciplinaVO disciplina;
    private int dificuldade;
    private Calendar dataCriacao;
    private List<AssuntoVO> assuntos;
    private List<ProvaVO> provas;

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
        if (disciplina != null) {
            // Atualizar a disciplina antiga
            DisciplinaBO antiga = new DisciplinaBO();
            antiga.remover(disciplina, this);
            
            // Atualizar a questão
            this.disciplina = disciplina;

            // Atualizar a nova disciplina
            DisciplinaBO nova = new DisciplinaBO();
            nova.adicionar(disciplina, this);
        }
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        if (dificuldade >= 0)
            this.dificuldade = dificuldade;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        // A data de criação da questão pode ser passada ou atual, mas não futura
        if (dataCriacao != null && (dataCriacao.compareTo(Calendar.getInstance()) <= 0))
            this.dataCriacao = dataCriacao;
    }

    public List<AssuntoVO> getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(List<AssuntoVO> assuntos) {
        this.assuntos = assuntos;
    }

    public List<ProvaVO> getProvas() {
        return provas;
    }

    public void setProvas(List<ProvaVO> provas) {
        this.provas = provas;
    }

    @Override
    public String toString() {
        return "QuestaoVO{" + "enunciado=" + enunciado + ", disciplina=" + disciplina + ", dificuldade=" + dificuldade + ", assuntos=" + assuntos + ", provas=" + provas + '}';
    }
    
}

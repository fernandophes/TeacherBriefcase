package model.VO;

import java.util.Calendar;
import java.util.List;

public class DisciplinaVO {
    private String codigo;
    private String nome;
    private Calendar dataCriacao;
    private List<ProfessorVO> professores;
    private List<AssuntoVO> assuntos;
    private List<QuestaoVO> questoes;
    private List<ProvaVO> provas;

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

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
        // A data de criação da disciplina pode ser passada ou atual, mas não futura
        if (dataCriacao != null && (dataCriacao.compareTo(Calendar.getInstance()) <= 0))
            this.dataCriacao = dataCriacao;
    }

    public List<ProfessorVO> getProfessores() {
        return professores;
    }

    public void setProfessores(List<ProfessorVO> professores) {
        this.professores = professores;
    }

    public List<AssuntoVO> getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(List<AssuntoVO> assuntos) {
        this.assuntos = assuntos;
    }

    public List<QuestaoVO> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<QuestaoVO> questoes) {
        this.questoes = questoes;
    }

    public List<ProvaVO> getProvas() {
        return provas;
    }

    public void setProvas(List<ProvaVO> provas) {
        this.provas = provas;
    }

    @Override
    public String toString() {
        return "DisciplinaVO{" + "codigo=" + codigo + ", nome=" + nome + ", professores=" + professores + ", assuntos=" + assuntos + ", questoes=" + questoes + ", provas=" + provas + '}';
    }
    
}